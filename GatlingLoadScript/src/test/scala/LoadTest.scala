import Requests._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps


class LoadTest extends Simulation{
  val httpConf = http
      .baseURL("https://www.ulmart.ru/")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
      .acceptEncodingHeader("gzip, deflate, br")
      .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
      .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
      .check(status is 200)

  val scn = scenario("BaseLoad") randomSwitch(
    (25, search),
    (25, clothes),
    (25, mobile),
    (25, office)
  )

  setUp(scn.inject(constantUsersPerSec(100) during(10 seconds)).protocols(httpConf)).assertions(Seq(
    global.responseTime.percentile3.between(5,10)
  ))
}
