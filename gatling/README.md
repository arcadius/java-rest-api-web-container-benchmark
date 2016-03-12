
`./gradlew clean runGatlingTest -Dtest.host=192.168.1.3:8080 -Dtest.pause=1 -Dtest.testDuration=15 -Dtest.testUsers=128 -Dtest.rampUpDuration=3 -Dtest.warmUpUsers=10 -Dtest.warmUpDuration=10 -Dtest.container=Grizzly`

|Test parameter|Meaning|Default value|
|---|---|---|
|`test.host`  |host:port  |127.0.0.1:8080|
|`test.pause`|How long in minutes the real simulation should wait for in order to let all warmup request finish running. This for the simulation only. The warmup does not pauses|2 minutes|
|`test.warmUpDuration`|The duration in minutes of the warm up simulation. Total test time will be `test.warmUpDuration + test.testDuration` |15 minutes|
|`test.testDuration`|The duration in minutes of the real simulation. Total test time will be `test.warmUpDuration + test.testDuration` |15 minutes|
|`test.testUsers`|Total number of concurrent users for the test|1|
|`test.warmUpUsers`|Total number of concurrent users for the warm-up period|1|
|`test.rampUpDuration`|Ramp up time in minutes to reach `test.users` |1 minute|
|`test.container`|one of `tomcat`, `undertow`, `jetty` or `grizzly`. This is just for display in the Gatling report|Generic Container|




--------------

Too many things have changed since round 2

- I noticed that Undertow was having hard time with HTTP 1.0 as apache AB only does HTTP 1.0
This is a problem as most client will be doing HTTP 1.1

So, I implemented the tests in Gatling

- Headers: One of the issues pointed out by Stuart is that different containers are sending different headers, 
meaning that the overall data transmitted over the network by each of the tested containers is never the same.
 To address this, changes have been done so that every single container are returning the very same header.
The header looks like this:

```
HTTP/1.1 200 OK
Server: TestServer
Content-Length: 27
Content-Type: application/json;charset=UTF-8
Date: Wed, 09 Mar 2016 18:57:19 GMT
```

- Another issue mentioned is that ideally we want the HTTP server being tested and the load generator to be on sepaate servers. 
This has been addressed too.
This leads us to 2 machines.
- HTTP servers running on 


```
arcad@arcad-idea:~/devenv/project/menelic/java-rest-api-web-container-benchmark$ ./sysinfo.sh 
===CPU:
processor	: 0
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
cpu cores	: 2
processor	: 1
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
cpu cores	: 2
processor	: 2
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
cpu cores	: 2
processor	: 3
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
cpu cores	: 2
 
===RAM: 
             total       used       free     shared    buffers     cached
Mem:          7.7G       3.0G       4.6G       323M       123M       1.4G
-/+ buffers/cache:       1.5G       6.2G
Swap:         7.9G         0B       7.9G
===Java version: 
java version "1.8.0_72"
Java(TM) SE Runtime Environment (build 1.8.0_72-b15)
Java HotSpot(TM) 64-Bit Server VM (build 25.72-b15, mixed mode)
 
===OS: 
Linux arcad-idea 3.16.0-60-generic #80~14.04.1-Ubuntu SMP Wed Jan 20 13:37:48 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux

```


Note that that CPU is a 2 core evn those it is showing as 
and the load generator machine:

```
TODO
```


- warm-up: Now, we are running the warm-up for 10minutes before the real test starts for 15 minutes.

