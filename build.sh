#!/usr/bin/env bash

if [[ ! -d "dist" ]]; then
 mvn clean install
fi

cd dist
java -jar dataset_generator.jar -config config.yml
