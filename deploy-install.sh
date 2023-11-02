#!/bin/bash

echo "
===== deploy-install started."
# Since alias does not work in codebuild, and soft-link does not really work well,
# we decided to use env_vars as workaround to simplify the installation command.

echo "
=== Install yarn"
npm install -g yarn

echo "
===== deploy-install complete.
"
