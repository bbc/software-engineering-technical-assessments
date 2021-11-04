package controllers

import model.{ApiResponse, ConstituencyResult, PartyResult, Scoreboard}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json._
import service.ResultService

import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success}

@Singleton
class ResultsController @Inject()(val controllerComponents: ControllerComponents,
                                  val resultService: ResultService)
  extends BaseController {
  implicit val partyResultJson: OFormat[PartyResult] = Json.format[PartyResult]
  implicit val constituencyResultJson: OFormat[ConstituencyResult] = Json.format[ConstituencyResult]
  implicit val scoreboardJson: OFormat[Scoreboard] = Json.format[Scoreboard]
  implicit val apiResponseJson : OFormat[ApiResponse] = Json.format[ApiResponse]



  def getResult(id: Int): Action[AnyContent] = Action {
    val foundItem = resultService.GetResult(id)
    foundItem match {
      case Some(item) => Ok(Json.toJson(item))
      case None => NotFound
    }
  }

  def postResult(): Action[AnyContent] = Action {
    implicit request =>
      val content = request.body
      val jsonObject = content.asJson
      val constituencyResult: Option[ConstituencyResult] = jsonObject.flatMap( js =>
          Json.fromJson[ConstituencyResult](js).asOpt
        )
      constituencyResult match {
        case Some(cs) => resultService.NewResult(cs) match {
          case Success(_) => Created(Json.toJson(ApiResponse(message = "Created", error = "")))
          case Failure(exception) => InternalServerError(Json.toJson(ApiResponse(message = "Internal Server Error", error = exception.getMessage)))
        }
        case None => BadRequest(Json.toJson(ApiResponse(message = "BadRequest", error = "Could not format body as json")))
      }
  }

  def getScoreboard: Action[AnyContent] =  Action {
    InternalServerError(Json.toJson(ApiResponse(message = "Implement me", error = "")))
  }

}
