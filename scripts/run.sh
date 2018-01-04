#!/bin/bash

./mvnw clean package
java -jar ./target/dataminingTask-1.0-SNAPSHOT-jar-with-dependencies.jar $@
