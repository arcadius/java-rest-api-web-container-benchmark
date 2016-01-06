# java-rest-api-web-container-benchmark
Trying to find out which web container to use for your nest Java REST API?

This project compare 
tomcat, Jetty, Grizzly and Undertow in term of serving JAX-RS response

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

There is a blog post about this at 
http://menelic.com/2016/01/06/java-rest-api-benchmark-tomcat-vs-jetty-vs-grizzly-vs-undertow/