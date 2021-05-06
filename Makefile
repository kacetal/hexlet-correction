.DEFAULT_GOAL := package-run

package-run: integration-test run

run: docker-db
	java --enable-preview -jar ./target/hexlet-correction*.jar

package:
	./mvnw clean package -T 1C

unit-tests:
	./mvnw clean test

integration-test:
	./mvnw clean integration-test

docker-db:
	docker-compose -f ./src/main/docker/postgresql.yml up -d
