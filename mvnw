#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup shell script with auto-download of wrapper jar
# ----------------------------------------------------------------------------

set -euf

BASE_DIR=$(cd "$(dirname "$0")"; pwd -P)
WRAPPER_JAR="$BASE_DIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_PROPERTIES="$BASE_DIR/.mvn/wrapper/maven-wrapper.properties"

WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"
if [ -f "$WRAPPER_PROPERTIES" ]; then
  WRAPPER_URL=$(grep -E '^wrapperUrl=' "$WRAPPER_PROPERTIES" | sed -e 's/^wrapperUrl=//')
fi

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Maven Wrapper jar from $WRAPPER_URL"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
  else
    echo "Error: Please install curl or wget to download the Maven Wrapper jar" >&2
    exit 1
  fi
fi

JAVA_EXE="java"
if [ -n "${JAVA_HOME:-}" ]; then
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

if ! command -v "$JAVA_EXE" >/dev/null 2>&1; then
  echo "Error: JAVA_HOME is not set and no 'java' command could be found in your PATH." >&2
  exit 1
fi

WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain
exec "$JAVA_EXE" ${MAVEN_OPTS:-} -classpath "$WRAPPER_JAR" "-Dmaven.multiModuleProjectDirectory=$BASE_DIR" $WRAPPER_LAUNCHER "$@"
