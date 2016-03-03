#!/usr/bin/env bash
#
set -e
#
URL=http://localhost:8080/api
#
echo " " >> result.log 2>&1
echo "========================================================== " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================WARM-UP======================== " >> result.log 2>&1
echo "============`date` Warming up the API with 100 million requests" >> result.log 2>&1

echo " " >> result.log 2>&1
ab -k -n 100000000 -c 1 -d -q $URL >> result.log 2>&1
echo " " >> result.log 2>&1


echo "============`date` API warmed up. " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END WARM-UP======================== " >> result.log 2>&1
echo "=== Sleeping for 2s" >> result.log 2>&1
sleep 2
echo "====================BENCHMARK======================== " >> result.log 2>&1

echo "============`date` Running load test ..." >> result.log 2>&1
#The benchmark
ab -k -n 100000000 -c 128 $URL >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` Done ..." >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END BENCHMARK======================== " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
