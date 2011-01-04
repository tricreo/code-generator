@echo off
java -Dlogback.configurationFile=./logback.xml -classpath .;.\lib\* jp.co.nikkeibp.software.codegen.Application %*



