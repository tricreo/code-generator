@echo off
java -Dlogback.configurationFile=./logback.xml -classpath .;.\lib\* jp.tricreo.codegenerator.Application %*



