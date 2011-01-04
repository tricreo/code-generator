#!/bin/sh

mvn clean package
mvn dependency:copy-dependencies -DoutputDirectory=bin/lib
cp target/codegen-0.0.1.jar bin/lib

