package com.menelic.jwcb.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._


object Common {
  val apiPath = "/api"

  val hostPort = System.getProperty("test.host", "127.0.0.1:8080")
  val containerName = System.getProperty("test.container", "Generic")

  val users = Integer.getInteger("test.users", 128)
  val ramp = Integer.getInteger("test.ramp", 5)
  val pause = Integer.getInteger("test.pause", 2)
  val testDuration = Integer.getInteger("test.duration", 15)
  val warmUpDuration = Integer.getInteger("test.warmUpDuration", 15)


  val httpProtocol = http
    .baseURL("http://" + hostPort)
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .connection("keep-alive")
    .disableCaching
    .check(status.is(200))
    .check(bodyString.is("{\"greeting\":\"Hello World!\"}"))
}

