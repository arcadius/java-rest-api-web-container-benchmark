#!/usr/bin/env bash
echo " " >> result.log 2>&1
echo "============================================================================================================ " >> result.log 2>&1

echo "============`date` Warming up the API for max 720 secs or for 300 million requests, whichever comes first" >> result.log 2>&1
echo " " >> result.log 2>&1
ab -t 720 -n 300000000 -c 1 -d -q http://localhost:8080/api >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` API warmed up. " >> result.log 2>&1

echo "============`date` Running load test ..." >> result.log 2>&1
#The benchmark
ab -k -n 10000000 -c 128 http://localhost:8080/api >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` Done ..." >> result.log 2>&1
echo " " >> result.log 2>&1