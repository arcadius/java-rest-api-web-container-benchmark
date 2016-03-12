#!/usr/bin/env bash
echo "===CPU:"
cat /proc/cpuinfo |  egrep "processor|model name|cores"
echo " "
echo "===RAM: "
free -h

echo "===Java version: "
java -version
echo " "
echo "===OS: "
uname -a
