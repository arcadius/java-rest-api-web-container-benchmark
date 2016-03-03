#!/usr/bin/env bash
#
set -e
#
URL=http://localhost:8080/api
#timeout in sec
REQ_TIMEOUT=600
NUM_REQUEST=100000000
CONCURRENCY=128
#
echo " " >> result.log 2>&1
echo "========================================================== " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================WARM-UP======================== " >> result.log 2>&1
echo "============`date` Warming up the API with ${NUM_REQUEST} requests" >> result.log 2>&1

echo " " >> result.log 2>&1
ab -k -n $NUM_REQUEST -c 1 -d -q -s $REQ_TIMEOUT $URL >> result.log 2>&1
echo " " >> result.log 2>&1


echo "============`date` API warmed up. " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END WARM-UP======================== " >> result.log 2>&1
echo "=== Sleeping for 2s" >> result.log 2>&1
sleep 2
echo "====================BENCHMARK======================== " >> result.log 2>&1

echo "============`date` Running load test ..." >> result.log 2>&1
#The benchmark
ab -k -n $NUM_REQUEST -c $CONCURRENCY -s $REQ_TIMEOUT $URL >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` Done ..." >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END BENCHMARK======================== " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
