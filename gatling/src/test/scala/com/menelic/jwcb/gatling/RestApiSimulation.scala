package com.menelic.jwcb.gatling


import com.menelic.jwcb.gatling.Common._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class RestApiSimulation extends Simulation {
  val name = getClass.getSimpleName

  val scn = scenario(name).pause(testPause minutes).during(testDuration minutes) {
    exec(http(containerName)
      .get(apiPath))
  }

  setUp(scn.inject(rampUsers(testUsers).over(rampUpDuration minutes)).protocols(httpProtocol))
}