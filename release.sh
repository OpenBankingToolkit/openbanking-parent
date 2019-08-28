#!/usr/bin/env bash

# This script will do a release of the artifact according to http://maven.apache.org/maven-release/maven-release-plugin/
#git config --global user.email "codefresh@forgerock.com";
#git config --global user.name "CodeFresh";
mvn -s settings.xml -Dusername=$GITHUB_ACCESS_TOKEN release:prepare
mvn -s settings.xml release:perform