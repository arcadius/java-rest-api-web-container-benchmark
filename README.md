# java-rest-api-web-container-benchmark
Trying to find out which web container to use for your nest Java REST API?

This project compares: 
tomcat, Jetty, Grizzly and Undertow in term of serving JAX-RS responses

Setup:
One needs to install apache bench:

sudo apt-get install apache2-utils

to run the test, first start the API server by doing:

./gradlew -p <SERVER NAME> bootRun

where server name is one of grizzly, jetty, tomcat or grizzly

then in a new console
./run-load-test.sh

There is a blog post about this at 
http://menelic.com/2016/01/06/java-rest-api-benchmark-tomcat-vs-jetty-vs-grizzly-vs-undertow/


actuator: -Dserver.servlet-path=/actuator




Tuning tomcat
gradle bootRun -p tomcat -Dserver.tomcat.max-threads=250


Tuning Undertow

gradle bootRun -p undertow -Dserver.undertow.worker-threads=250


Tuning grizzly

gradle bootRun -p grizzly -Dserver.grizzly.worker-threads=250 



Tuning jetty

gradle bootRun -p jetty -Dserver.jetty.maxThreads=250 -Dserver.jetty.minThreads=250
