
# Running the Gatling load test

To run the load test, please run the following command

`./gradlew clean runGatlingTest -Dtest.host=192.168.1.3:8080 -Dtest.pause=1 -Dtest.testDuration=15 -Dtest.testUsers=128 -Dtest.rampUpDuration=3 -Dtest.warmUpUsers=10 -Dtest.warmUpDuration=10 -Dtest.container=Grizzly`


Please look at the table below for more detail about the parameters passed to the test

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



