#!/usr/bin/env bash
set -e

# This script will do a release of the artifact according to http://maven.apache.org/maven-release/maven-release-plugin/
git config --global user.email "openbankbot@forgerock.com";
git config --global user.name "openbankbot";

git config --global commit.gpgsign true
git config --global user.signingkey $GITHUB_GPG_KEY_ID
echo "GITHUB_GPG_KEY_ID = $GITHUB_GPG_KEY_ID"

echo  "$GITHUB_GPG_KEY" > private.key
gpg --import ./private.key


MAVEN_REPO_LOCAL="";

if [ -n "$1" ]
then
     MAVEN_REPO_LOCAL="-Dmaven.repo.local=$1"
fi
mvn -s settings.xml $MAVEN_REPO_LOCAL -Dusername=$GITHUB_ACCESS_TOKEN release:prepare -B
mvn -s settings.xml $MAVEN_REPO_LOCAL release:perform -B -Darguments="-Dmaven.javadoc.skip=true"