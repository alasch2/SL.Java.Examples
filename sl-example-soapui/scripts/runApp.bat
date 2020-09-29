echo off
SETLOCAL

rem SET agentpath="C:/Users/ala schneider/Dev/SL.OnPremise.Agents.Java/java-agent-bootstrapper/target/java-agent-bootstrapper-3.0.0-SNAPSHOT.jar"
SET agentpath=path/to/test-listener

SET targetJar=target/sl-example-soapui-1.0.0-SNAPSHOT.jar

rem SET token="C:\SL\ENV\tokens\sl-agents-token.txt"
SET token=path/to/token file

rem SET ssnidfile=buildSession4Tests.txt
SET ssnidfile=path/to/buildsessionid file

SET logoptions=-Dsl.log.toConsole=true
SET includes=-Dsl.includes=io.sl.ex.*
SET tia_opts=-Dsl.tia.disabled=true

SET JAVA_OPTS= %logoptions% -Dsl.buildSessionIdFile=%ssnidfile% -Dsl.tokenFile=%token% %includes% %tia_opts% -Dsl.enableUpgrade=false

java %JAVA_OPTS% -javaagent:%agentpath% -jar %targetJar%
