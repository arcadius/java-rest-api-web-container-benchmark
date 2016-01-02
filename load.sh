#!/usr/bin/env bash

# warm up
curl http://localhost:8080/api

#The benchmark
ab -k -n 10000000 -c 10 http://localhost:8080/api >> result.log 2>&1
