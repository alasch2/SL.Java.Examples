:: example:
:: run-agent 
::
echo off
SETLOCAL
SET JAVA_AGENT_ROOT="C:\Users\AlaSchneider\Dev\SL.OnPremise.Agents.Java"
SET listenerpath="%JAVA_AGENT_ROOT%\java-agent-bootstrapper\target\java-agent-bootstrapper-3.0.0-SNAPSHOT.jar"
rem SET listenerpath="%C:\Users\AlaSchneider\Dev\Playground\sl-java-downloads\sl-test-listener-2.1.1534.jar"

SET targetJar=target\sl-spring-groovy-example-1.0.0-SNAPSHOT.jar
SET logoptions=-Dsl.log.level=info -Dsl.log.toConsole=true -Dsl.log.toFile=true
SET debugOpt="-agentlib:jdwp=transport=dt_socket,server=n,address=localhost:5005,suspend=y"
SET token="C:\SL\ENV\tokens\sl-alasch-token.txt"
rem SET token="C:\SL\ENV\tokens\sl-gal-token.txt"
rem SET token="C:\SL\ENV\tokens\sl-alonw-token.txt"
set FTPS_V6=-Dsl.footprintsEnableV6=true
SET javaOpts=%logoptions% -Dsl.agentEventsMaxAttempts=1 %FTPS_V6% -Dsl.labId=LAB -Dsl.buildSessionIdFile=buildSessionId.txt -Dsl.tokenFile=%token% -javaagent:%listenerpath% -jar %targetJar%
rem SET javaOpts=%logoptions% -Dsl.agentEventsMaxAttempts=1 -Dsl.buildSessionIdFile=buildSessionId.txt -Dsl.tokenFile=%token% -javaagent:%listenerpath% -jar %targetJar%
echo Using javaOpts=[%javaOpts%]

SET RUN_NO_DEBUG=java %javaOpts%
SET RUN_DEBUG=java %debugOpt% %javaOpts%
if "%1"=="" (
	%RUN_NO_DEBUG%
	) else (
	%RUN_DEBUG%
	)
