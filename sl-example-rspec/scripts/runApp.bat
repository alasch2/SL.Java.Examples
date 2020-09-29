SETLOCAL

:: SET agentpath="C:\Users\Bartosz Kaczkowski\IdeaProjects\SL.OnPremise.Agents.Java\java-agent-bootstrapper\target\java-agent-bootstrapper-3.0.0-SNAPSHOT.jar"
SET agentpath=path/to/test-listener-jar

:: SET token="C:\dev\SeaLights\sl-agents-token.txt"
SET token=path/to/token_file

SET targetJar=target/sl-example-rspec-1.0.0-SNAPSHOT.jar

SET ssnidfile=buildSession4Tests.txt

SET logoptions=-Dsl.log.toConsole=true
SET includes=-Dsl.includes=io.sl.ex.*
SET tia_opts=-Dsl.tia.disabled=false

SET JAVA_OPTS= %logoptions% -Dsl.buildSessionIdFile=%ssnidfile% -Dsl.tokenFile=%token% %includes% %tia_opts% -Dsl.enableUpgrade=false

java %JAVA_OPTS% -javaagent:%agentpath% -jar %targetJar%
