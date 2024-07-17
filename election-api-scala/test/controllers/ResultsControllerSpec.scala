package controllers

import model.Scoreboard
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.{Json, OFormat}
import service.MapResultService

class ResultsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  //TODO: You will need the json format for any classes used within the Scoreboard here
  implicit val scoreboardJson: OFormat[Scoreboard] = Json.format[Scoreboard]


  "ResultsController" should {
    val service = new MapResultService
    val controller = new ResultsController(Helpers.stubControllerComponents(), service)

    "Test first 5 results" in {
      val scoreboard = runXResults(5)
      scoreboard must not be empty
      // LAB = 4
      // LD = 1
      // winner = noone
    }

    "First 100 results" in {
      val scoreboard = runXResults(100)
      scoreboard must not be empty
      // LD == 12
      // LAB == 56
      // CON == 31
      // winner = noone
    }

    "First 554 results" in {
      val scoreboard = runXResults(554)
      scoreboard must not be empty
      // LD == 52
      // LAB = 325
      // CON = 167
      // winner = LAB
    }

    "All results" in {
      val scoreboard = runXResults(650)
      scoreboard must not be empty
      // LD == 62
      // LAB == 349
      // CON == 210
      // winner = LAB
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
