# java-web-containers-benchmark
Benchmarking servlet containers

sudo apt-get install apache2-utils

./gradlew -p grizzly bootRun
or
./gradlew -p jetty bootRun
or
./gradlew -p tomcat bootRun

or
./gradlew -p undertow bootRun


then in a new console
./run-load-test.sh