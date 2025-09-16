#!/bin/bash

cd ~/Desktop/projects/C-Bank/src

javac -cp .:io/github/alirostom1/cbank/drivers/mysql-connector-j-9.4.0.jar -d out io/github/alirostom1/cbank/Main.java

java -cp out:io/github/alirostom1/cbank/drivers/mysql-connector-j-9.4.0.jar io.github.alirostom1.cbank.Main

