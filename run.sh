#!/bin/bash

mvn clean package

cd book-management-service || exit
docker build --platform linux/amd64 -t book-management-service .

cd ../book-rental-information-service || exit
docker build --platform linux/amd64 -t book-rental-information-service .

cd ..
docker compose up