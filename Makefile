JAR=target/tidb-1.0.0-jar-with-dependencies.jar

build:
	mvn clean compile assembly:single

run-server-simple:
	java -cp $(JAR) server.simple.Main

run-server-advanced:
	java -cp $(JAR) server.advanced.Main

run-client:
	java -cp $(JAR) client.Main
