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
echo "============`date` Warming up the API for max 600*2 secs or for 100*2 million requests, whichever comes first" >> result.log 2>&1

for i in `seq 1 2`;
        do
            echo "==== in warmin phase $i" >> result.log 2>&1
            echo " " >> result.log 2>&1
            ab -t 600 -n 100000000 -c 1 -d -q $URL >> result.log 2>&1
            echo " " >> result.log 2>&1
        done

echo "============`date` API warmed up. " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END WARM-UP======================== " >> result.log 2>&1
echo " " >> result.log 2>&1
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
