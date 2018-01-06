#!/bin/bash

./mvnw clean package -DskipTests
java -jar ./target/dataminingTask-1.0-SNAPSHOT-jar-with-dependencies.jar $@
