@echo off
call mvn -q clean compile

call rm -rf database.*

call mvn -q exec:java "-Dexec.mainClass=se.yrgo.spring.client.Client"
