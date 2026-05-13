#!/bin/bash

mvn -q clean compile

rm -rf database.*

mvn -q exec:java "-Dexec.mainClass=se.yrgo.spring.client.Client"