@echo off
call mvn clean compile

call mvn exec:java "-Dexec.mainClass=se.yrgo.spring.client.Client"
