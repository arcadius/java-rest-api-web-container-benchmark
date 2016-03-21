# java-rest-api-web-container-benchmark
Are you wondering which web container to use for your nest Java REST API?

If your answer is yes, then you are not alone.

This project compares: 
**Tomcat**, **Jetty**, **Grizzly** and **Undertow** in term of serving **JAX-RS** responses

TO avoid any latency, the REST API does not try to access any backend service.

There are 3 blog entries supporting this benchmark:


- http://menelic.com/2016/01/06/java-rest-api-benchmark-tomcat-vs-jetty-vs-grizzly-vs-undertow/

- and http://menelic.com/2016/01/25/java-rest-api-benchmark-tomcat-vs-jetty-vs-grizzly-vs-undertow-round-2/

- and http://menelic.com/2016/03/12/java-rest-api-benchmark-tomcat-vs-jetty-vs-grizzly-vs-undertow-round-3/


##Setup
To run this benchmark, you will need:

- The latest Oracle JDK 1.8 or later ( http://www.oracle.com/technetwork/java/javase/downloads ). 
- install apache bench (https://httpd.apache.org/docs/2.2/programs/ab.html ).
 On debian/ubuntu: 
`sudo apt-get install apache2-utils` 

##Running load tests with default server configuration
to run the test, 

- first start the API server by doing:
`./gradlew -p <SERVER NAME> bootRun`
where `<SERVER NAME>` is one of `grizzly`, `jetty`, `tomcat` or `undertow`

- then in a new console, do
`./run-load-test.sh`
feel free to edit the script for for different load profile 

##Changing server thread pool size and header
One can easily change server thread pool size on the command line: 

Note that it has been advised (Undertow folk) that we take as worker-thread 16*numberOfCores

In my case, we have 2 cores => workers=32

####Grizzly 

`./gradlew bootRun -p grizzly -Dserver.grizzly.worker-threads=32 -Dserver.server-header=TestServer`



####Jetty

`./gradlew bootRun -p jetty -Dserver.jetty.min-threads=32 -Dserver.jetty.max-threads=32 -Dserver.server-header=TestServer` 


####Tomcat

`./gradlew bootRun -p tomcat -Dserver.tomcat.max-threads=32 -Dserver.server-header=TestServer`


####Undertow

`./gradlew bootRun -p undertow -Dserver.undertow.worker-threads=32 -Dserver.server-header=TestServer`


#Actuator
To enable spring boot actuator,please look in `build.gradle` for the comments
`/**To enable actuator...`
