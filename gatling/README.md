
gradle clean build runTest -Dtest.host=127.0.0.1:8080 -Dtest.pause=1 -Dtest.duration=3 -Dtest.users=2 -Dtest.ramp=1 -Dtest.warmUpDuration=0 -Dtest.container=Tomcat
