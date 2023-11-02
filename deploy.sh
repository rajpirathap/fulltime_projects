#!/bin/bash


cd ${CODEBUILD_SRC_DIR}


#
# Utility function to retrieve data from SSM Parameter:
# PARAMS: getSsmParameterValue <1: parameter key> <2:is encrypted? true | false>
# example: getSsmParameterValue /${PROJECT_NAME}/${ENV_NAME}/api-gateway/domain/domain_name true
#
getSsmParameterValue () {
	local PARAMETER_KEY=$1
	local IS_ENCRYPTED=$2
	
	if [ "x${IS_ENCRYPTED}" = "xtrue" ]; then
		aws ssm get-parameter --with-decryption --name "${PARAMETER_KEY}" | grep "Value" | sed -E 's/(.*"Value": "(.*)"[^"]*)/\2/g';
	else
		aws ssm get-parameter --name "${PARAMETER_KEY}" | grep "Value" | sed -E 's/(.*"Value": "(.*)"[^"]*)/\2/g'
	fi
}


# WORKAROUND, as build without install will throw exception.
PROJ_DIRECTORY=admin
echo "===== INSTALL Dependencies - START ===== ${PROJ_DIRECTORY} ====="
cd ${CODEBUILD_SRC_DIR}/${PROJ_DIRECTORY}
rm -rf node_modules
#npm install
yarn install
echo "===== INSTALL Dependencies -  END  ===== ${PROJ_DIRECTORY} ====="


echo "===== UPDATE CONFIG - START ===== ${PROJ_DIRECTORY} ====="
cd $CODEBUILD_SRC_DIR/$PROJ_DIRECTORY
# Delete development config file
rm .env

# Download env config file from Configuration Bucket
aws s3 cp s3://${CONFIGURATION_S3_BUCKET_NAME}/${CONFIGURATION_OBJECT_PREFIX}/ ${CODEBUILD_SRC_DIR}/${PROJ_DIRECTORY} --recursive --quiet --sse aws:kms --only-show-errors

ls -la ${CODEBUILD_SRC_DIR}/${PROJ_DIRECTORY}

# POPULATE PARAMETER FROM SSM Parameter

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/api-gateway/domain/domain_name
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#TF_VAR_domain_name=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/s3/website/admin_bucket_id
echo " Get ssm parameter: ${SSM_PARAM_KEY}"
S3_BUCKET=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/s3/website/admin_website_context
echo " Get ssm parameter: ${SSM_PARAM_KEY}"
S3_PREFIX=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/s3/website/static_resource_bucket_id
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#STATIC_RESOURCE_S3_BUCKET=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/s3/website/static_resource_context
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#STATIC_RESOURCE_S3_PREFIX=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/cognito/user_pool/identity_pool_id
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#IDENTITY_POOL_ID=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/cognito/user_pool/user_pool_id
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#USER_POOL_ID=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/cognito/user_pool/app_client_id
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#USER_POOL_WEB_CLIENT_ID=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/api-gateway/domain/domain_name
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#API_GATEWAY_DOMAIN=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/application/config/api_gateway_context
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#API_GATEWAY_CONTEXT=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

#SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/cloudfront/website/domain_name
#echo " Get ssm parameter: ${SSM_PARAM_KEY}"
#DOMAIN_NAME=$(getSsmParameterValue ${SSM_PARAM_KEY} true);

SSM_PARAM_KEY=/${PROJECT_NAME}/${ENV_NAME}/cloudfront/website/distribution_id
echo " Get ssm parameter: ${SSM_PARAM_KEY}"
CDN_DISTRIBUTION_ID=$(getSsmParameterValue ${SSM_PARAM_KEY} true);


# Set Parameter via ENV VARS
#TF_VAR_aws_region=${AWS_DEFAULT_REGION}
#VUE_APP_IDENTITY_POOL_ID=${IDENTITY_POOL_ID}
#VUE_APP_USER_POOL_WED_CLIENT_ID=${USER_POOL_WEB_CLIENT_ID}
#VUE_APP_USER_POOL_ID=${USER_POOL_ID}
#VUE_APP_IDENTITY_POOL_ID_ADMIN=${ADMIN_IDENTITY_POOL_ID}
#VUE_APP_USER_POOL_WED_CLIENT_ID_ADMIN=${ADMIN_USER_POOL_ID}
#VUE_APP_USER_POOL_ID_ADMIN=${ADMIN_USER_POOL_WEB_CLIENT_ID}
#VUE_APP_S3_BUCKET=${STATIC_RESOURCE_S3_BUCKET}
#VUE_APP_S3_LEVEL=${STATIC_RESOURCE_S3_PREFIX}
#VUE_APP_REGION=${AWS_DEFAULT_REGION}
#VUE_APP_SES_GATEWAY=https://${API_GATEWAY_DOMAIN}
#VUE_APP_RDS_GATEWAY=https://${API_GATEWAY_DOMAIN}
#VUE_APP_API_ENV=/${API_GATEWAY_CONTEXT}
#VUE_APP_CORS_HEADERS=Access-Control-Allow-Methods,Origin,X-Requested-With,Content-Type,Accept,Authorization
#VUE_APP_CORS_ORIGIN=*
#VUE_APP_CORS_CREDENTIALS=true
#VUE_APP_CORS_METHOD=GET,HEAD,OPTIONS,POST,PUT,DELETE
#VUE_APP_BASE_URL=https://${DOMAIN_NAME}/${S3_PREFIX}

echo "===== UPDATE CONFIG - END ===== ${PROJ_DIRECTORY} ====="


# BUILD the Static Website (Vue SSG)
echo "===== Build Package - START ===== ${PROJ_DIRECTORY} ====="
cd $CODEBUILD_SRC_DIR/$PROJ_DIRECTORY
npm run build
echo "===== Build Package -  END  ===== ${PROJ_DIRECTORY} ====="


# DEPLOY
echo "===== Deploy - START ===== ${PROJ_DIRECTORY} ====="
# Upload build files to S3 Bucket
cd dist
aws s3 sync . s3://${S3_BUCKET}/${S3_PREFIX} --delete

# Invalidate CloudFront Cache
aws cloudfront create-invalidation --distribution-id ${CDN_DISTRIBUTION_ID} --paths "/${S3_PREFIX}/*"
echo "===== Deploy -  END  ===== ${PROJ_DIRECTORY} ====="


echo "
===== Deployment Complete."

