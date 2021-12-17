package controllers

import akka.stream.Materializer
import model.Scoreboard
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.{Json, OFormat}
import service.MapResultService

class ResultsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  implicit lazy val materializer: Materializer = app.materializer
  //TODO: You will need the json format for any classes used within the Scoreboard here
  implicit val scoreboardJson: OFormat[Scoreboard] = Json.format[Scoreboard]


  "ResultsController" should {
    val service = new MapResultService
    val controller = new ResultsController(Helpers.stubControllerComponents(), service)

    "Test first 5 results" in {
      val scoreboard = runXResults(5)
      scoreboard match {
        case Some(sc) => matchScoreboard(scoreboard = sc, LabSeats = 4, LDSeats = 1)
        case None => fail("Didn't return a scoreboard")
      }
    }

    "First 100 results" in {
      val scoreboard = runXResults(100)
      scoreboard match {
        case Some(sc) => matchScoreboard(scoreboard = sc, LabSeats = 56, LDSeats = 12, ConSeats = 31)
        case None => fail("Didn't return a scoreboard")
      }
    }

    "First 554 results" in {
      val scoreboard = runXResults(554)
      scoreboard match {
        case Some(sc) => matchScoreboard(scoreboard = sc, LabSeats = 325, LDSeats = 52, ConSeats = 167, winner = Some("LAB"))
        case None => fail("Didn't return a scoreboard")
      }
    }

    "All results" in {
      val scoreboard = runXResults(650)
      scoreboard match {
        case Some(sc) => matchScoreboard(scoreboard = sc, LabSeats = 349, LDSeats = 62, ConSeats = 210, winner = Some("LAB"))
        case None => fail("Didn't return a scoreboard")
      }
    }

    def matchScoreboard(scoreboard: Scoreboard, LabSeats: Int = 0,
                        LDSeats: Int = 0, ConSeats: Int = 0, winner: Option[String] = None) {
      var ld, lab, con = 0

      //TODO: set the seats by party

      ld mustEqual LDSeats
      lab mustEqual LabSeats
      con mustEqual ConSeats
//      something mustEqual winner
      fail("Implement me")
    }

    def runXResults(number: Int): Option[Scoreboard] = {
      service.reset()
      for (i <- 1 to number) {
        val filename = "test/results/result%03d.json".format(i)
        val json = scala.io.Source.fromFile(filename).mkString
        controller.postResult().apply(FakeRequest(POST, "/result").withJsonBody(Json.parse(json)))
      }
      val result = controller.getScoreboard().apply(FakeRequest(GET, "/scoreboard"))
      val jsStr = contentAsJson(result)
      jsStr.asOpt[Scoreboard]
    }
  }
}
