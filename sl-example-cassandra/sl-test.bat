:: example:
:: pl.bat target\gs-spring-boot-0.1.0.jar 
::
echo off
SETLOCAL
::SET agentpath="C:\Users\ala schneider\Dev\SL.OnPremise.Agents.Java\java-api-analytics-tester\target\java-api-analytics-tester-1.0.0-SNAPSHOT.jar"
SET agentpath="C:/Users/ala schneider/Dev/SL.OnPremise.Agents.Java/java-agent-bootstrapper/target/java-agent-bootstrapper-1.0.0-SNAPSHOT.jar"
::SET plpath=java-pl-agent-bootstrapper-1.0.0-SNAPSHOT.jar

SET targetJar=target\ex-cassandra-0.0.1-SNAPSHOT.jar
::SET includes=%2
SET server=http://dev-api-gw.dev.sealights.co/api
SET logoptions=-Dsl.log.enabled=true -Dsl.log.level=info -Dsl.log.toConsole=true -Dsl.log.toFile=true
::SET token="eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL0RFVi1hcGkuYXV0aC5zZWFsaWdodHMuaW8vIiwiand0aWQiOiJERVYtYXBpLGktMGM4ODA2MGVlZDU1YTg2YTMsQVBJR1ctZDFmZTg1ODAtZTc3Ni00MWIxLWFiNzgtOTQwMTg0N2Q5YjZkLDE1MjUwOTIyNTMzODUiLCJzdWJqZWN0IjoiU2VhTGlnaHRzQGFnZW50IiwiYXVkaWVuY2UiOlsiYWdlbnRzIl0sIngtc2wtcm9sZSI6ImFnZW50IiwieC1zbC1zZXJ2ZXIiOiJodHRwczovL2Rldi1hcGktZ3cuZGV2LnNlYWxpZ2h0cy5jby9hcGkiLCJzbF9pbXBlcl9zdWJqZWN0IjoiIiwiaWF0IjoxNTI1MDkyMjUzfQ.F4KNg9iUAq3N-XstjcTxIoSnMveTC-mJR0SkBNiko1KSdS2ATUcHtpUOUeXq3iwI-MkDLVj8wdjbYpK53oXaraAZTYFOAOoQunAJogzgsD8f8qfiJkFKbY5jGwRJgLCkf8w5IuclxLTWZeqPKXblFdwVLB_O9V_vRoLfXS2AHI3WPxnO6Cdt25fFHYdqH1sgcSqkawBAva2WKzk5Wk-XnexgzGGP51mb_lFLZ-hovJPsyOXqfrAUt0ZcnnEWXfXBtByFV_W4KUhhWmFO7KEDncccRF7nD7BYJpOhMTP8lMsGhIqQyG2WdwTy6YG_UJlItCaliY-AHmhxwCwvAFC_Ca3w2nXMpSBCcjsMZqNuLfVlnvCuNrEdpgUsyie7Gxo_iVRVsNUm_f0medWihYibC87v31VJMtWLXnc26wopumItoJp184nD5NWdfE_gkXCI56FyDVw8-pNhuTHFYIlfzd6IIn5W9IJgWZcTs_oYBE-qjRhQXCIIpnLy_Kp6pwAe3PXo4rLZ6OU9njqfNZ_Bk4y-PUArsPfZXkrjm7panIL2en4EKInSunh0g36zQ-q66nPDf8z51lv2tnjHdgEF2XUPpQ-GIIW4-cpvfgYd_zI1ruhwQtKT7RWD9eDgJACp_MPoHluSz6ZqeDmTqkYxRIcq0p52C_gqPSLru29H-wY"
SET token=sltoken.txt
SET coloring=-Dsl.featuresData.enableHttpColoring=false
SET ssnidfile=buildSessionId.txt
SET includes=io.sl.ex.*
SET debugcfg="-agentlib:jdwp=transport=dt_socket,server=n,address=DESKTOP-FC1M245:5005,suspend=y"

echo ...
::echo Running java %logoptions% -Dsl.buildSessionId=%ssnid% -Dsl.tokenFile=%token% %coloring% -Dsl.includes=%includes% -javaagent:%listenerpath% -jar %targetJar%
::echo Running java -Dsl.includes=%includes% -javaagent:%listenerpath% -jar %targetJar%
echo Running %RUN_NO_DEBUG%
echo ...

::java %logoptions% -Dsl.includes=hello.* -Dsl.buildSessionId=111 -javaagent:%plpath% -jar %targetJar%
:: API analytics active
::java %logoptions%  -Dsl.buildSessionIdFile=%ssnidfile% -Dsl.tokenFile=%token% %coloring% -Dsl.includes=%includes% -javaagent:%agentpath% -jar %targetJar%

SET RUN_NO_DEBUG=java %logoptions%  -Dsl.buildSessionIdFile=%ssnidfile% -Dsl.tokenFile=%token% %coloring% -Dsl.includes=%includes% -javaagent:%agentpath% -jar %targetJar%
SET RUN_DEBUG=java %debugcfg% -javaagent:%agentpath% -jar %targetJar%
if "%2"=="" (
	%RUN_NO_DEBUG%
	) else (
	%RUN_DEBUG%
	)
::%RUN_NO_DEBUG%

