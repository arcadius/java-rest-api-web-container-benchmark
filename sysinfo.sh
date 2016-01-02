#!/usr/bin/env bash
echo "CPU:"
cat /proc/cpuinfo | grep "model name"
echo " "
echo "RAM: "
free -h

echo "Java version: "
java -version