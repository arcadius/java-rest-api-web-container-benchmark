#!/usr/bin/env bash

#
URL=http://localhost:8080/api
#
echo " " >> result.log 2>&1
echo "============================================================================================================ " >> result.log 2>&1

echo "============`date` Warming up the API for max 720 secs or for 300 million requests, whichever comes first" >> result.log 2>&1
echo " " >> result.log 2>&1
ab -t 720 -n 300000000 -c 1 -d -q $URL >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` API warmed up. " >> result.log 2>&1

echo "============`date` Running load test ..." >> result.log 2>&1
#The benchmark
ab -k -n 10000000 -c 128 $URL >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` Done ..." >> result.log 2>&1
echo " " >> result.log 2>&1