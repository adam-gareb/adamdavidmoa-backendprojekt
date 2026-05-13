#!/bin/bash

mvn -q clean compile

mvn -q exec:java "-Dexec.mainClass=se.yrgo.spring.client.Client"