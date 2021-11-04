package controllers

import akka.stream.Materializer
import model.Scoreboard
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import play.api.libs.json.{Json, OFormat}
import play.api.mvc._
import service.MapResultService

class ResultsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  implicit lazy val materializer: Materializer = app.materializer
  implicit val scoreboardJson: OFormat[Scoreboard] = Json.format[Scoreboard]


  "ResultsController" should {
    val controller             = new ResultsController(Helpers.stubControllerComponents(), new MapResultService())

    "Test first 5 results" in {
     val  scoreboard = runXResults(5)
      scoreboard match {
        case Some(sc) => matchScoreboard(scoreboard = sc, LabSeats = 4, LDSeats = 1)
        case None => fail("Didn't return a scoreboard")
      }
    }

    "First 100 results" in {
    }

    "First 554 results" in {
    }

    "All results" in {
    }

    def matchScoreboard(scoreboard:Scoreboard, LabSeats:Int = 0,
                        LDSeats:Int = 0, ConSeats:Int = 0, winner: Option[String] = None) {

      fail("Implement me")
    }

    def runXResults(number: Int): Option[Scoreboard] = {
      for (i <- 1 to number) {
        val filename = "test/results/result%03d.json".format(i)
        val json = scala.io.Source.fromFile(filename).mkString
        controller.postResult().apply(FakeRequest(POST, "/result").withJsonBody(Json.parse(json)))
      }
      val result = controller.getScoreboard().apply(FakeRequest(GET,"/scoreboard"))
      val jsStr = contentAsJson(result)
      jsStr.asOpt[Scoreboard]
    }
  }
}
