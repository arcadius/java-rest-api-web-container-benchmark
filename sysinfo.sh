#!/usr/bin/env bash
echo "CPU:"
cat /proc/cpuinfo | grep "model name"
echo " "
echo "RAM: "
free

echo "Java version: "
java -version
echo " "
echo "OS: "
uname -a