@echo off
call mvn -q clean compile

del /f /q database.*

call mvn -q exec:java "-Dexec.mainClass=se.yrgo.spring.client.Client"
