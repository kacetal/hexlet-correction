.DEFAULT_GOAL := package-run

package-run: package run

run: docker-db
	java --enable-preview -jar ./target/hexlet-correction*.jar

package:
	./mvnw clean package -T 1C

unit-tests:
	./mvnw clean test

docker-db:
	docker-compose -f ./src/main/docker/postgresql.yml up -d
