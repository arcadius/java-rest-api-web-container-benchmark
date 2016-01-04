#!/usr/bin/env bash

echo "Warming up API"
curl http://localhost:8080/api
echo " "
echo "API warmed..."

echo "Running load test ..."
#The benchmark
ab -k -n 10000000 -c 128 http://localhost:8080/api >> result.log 2>&1
echo " "
echo "Done ..."