#!/usr/bin/env bash
set -e
git checkout $CF_BRANCH

# This script will do a release of the artifact according to http://maven.apache.org/maven-release/maven-release-plugin/
git config --global user.email "openbanking@forgerock.com";
git config --global user.name "GitBot";

MAVEN_REPO_LOCAL="";

if [ ! -z "$1" ]
then
     MAVEN_REPO_LOCAL="-Dmaven.repo.local=$1"
fi
mvn -s settings.xml $MAVEN_REPO_LOCAL -Dusername=$GITHUB_ACCESS_TOKEN release:prepare -B
mvn -s settings.xml $MAVEN_REPO_LOCAL release:perform -B