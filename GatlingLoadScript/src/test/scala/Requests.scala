import io.gatling.http.Predef._
import io.gatling.core.Predef._


object Requests {
  val search = exec(http("Search")
      .get("search?string=core+i7&rootCategory=&sort=6")
      .formParam("text", "ралли дакар")
      .check(status is 200))


  val clothes = exec(http("Clothes")
      .get("catalog/verhnaa_odezda_7")
      .check(status is 200))

  val mobile = exec(http("Mobile")
      .get("catalog/mobiles_gps_mp3")
      .check(status is 200))

  val office = exec(http("Office")
      .get("catalog/office_networks")
      .check(status is 200))

}
