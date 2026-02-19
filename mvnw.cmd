@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, supports auto-downloading
@REM maven-wrapper.jar when not present.
@REM ----------------------------------------------------------------------------

@ECHO OFF
setlocal

set MAVEN_WRAPPER_PROJECTBASEDIR=%~dp0
if "%MAVEN_WRAPPER_PROJECTBASEDIR:~-1%"=="\" set MAVEN_WRAPPER_PROJECTBASEDIR=%MAVEN_WRAPPER_PROJECTBASEDIR:~0,-1%

set WRAPPER_JAR=%MAVEN_WRAPPER_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
set WRAPPER_PROPERTIES=%MAVEN_WRAPPER_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties

if exist "%WRAPPER_PROPERTIES%" (
  for /F "usebackq tokens=1,2 delims==" %%A in ("%WRAPPER_PROPERTIES%") do (
    if "%%A"=="wrapperUrl" set WRAPPER_URL=%%B
  )
)

if not defined WRAPPER_URL (
  set WRAPPER_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar
)

if not exist "%WRAPPER_JAR%" goto downloadWrapper
goto afterDownload

:downloadWrapper
echo Downloading Maven Wrapper jar from %WRAPPER_URL%
powershell -Command "$ProgressPreference='SilentlyContinue'; [Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -UseBasicParsing -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'"
if errorlevel 1 (
  echo Failed to download %WRAPPER_URL%
  exit /B 1
)
:afterDownload

set MAVEN_HOME=
set JAVA_EXE=java
if not "%JAVA_HOME%"=="" (
  set JAVA_EXE=%JAVA_HOME%\bin\java.exe
  if not exist "%JAVA_EXE%" (
    echo Error: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
    exit /B 1
  )
) else (
  where java >nul 2>nul || (
    echo.
    echo Error: JAVA_HOME is not set and no 'java' command could be found in your PATH.
    echo Please set the JAVA_HOME variable in your environment to match the
    echo location of your Java installation.
    exit /B 1
  )
)

:run
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

set WRAPPER_CLASSPATH="%WRAPPER_JAR%"
for %%i in ("%MAVEN_WRAPPER_PROJECTBASEDIR%\.mvn\jvm.config") do (
  if exist "%%~fi" (
    set JVM_CONFIG=%%~fi
  )
)

set MAVEN_OPTS=%MAVEN_OPTS% %MAVEN_DEBUG_OPTS%

if not "%MAVEN_SKIP_RC%"=="" goto endrc
  if exist "%HOME%\mavenrc_post.bat" call "%HOME%\mavenrc_post.bat"
  if exist "%HOME%\mavenrc_pre.bat" call "%HOME%\mavenrc_pre.bat"
  if exist "%MAVEN_WRAPPER_PROJECTBASEDIR%\mavenrc_post.bat" call "%MAVEN_WRAPPER_PROJECTBASEDIR%\mavenrc_post.bat"
  if exist "%MAVEN_WRAPPER_PROJECTBASEDIR%\mavenrc_pre.bat" call "%MAVEN_WRAPPER_PROJECTBASEDIR%\mavenrc_pre.bat"
:endrc

set MAVEN_CMD_LINE_ARGS=%*

"%JAVA_EXE%" %MAVEN_OPTS% -classpath %WRAPPER_CLASSPATH% "-Dmaven.multiModuleProjectDirectory=%MAVEN_WRAPPER_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %MAVEN_CMD_LINE_ARGS%
set EXIT_CODE=%ERRORLEVEL%
exit /B %EXIT_CODE%
