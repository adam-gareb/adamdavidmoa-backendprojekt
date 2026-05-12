#!/bin/bash

mvn clean compile

mvn exec:java "-Dexec.mainClass=se.yrgo.spring.client.Client"