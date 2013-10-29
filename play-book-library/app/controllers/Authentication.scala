package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 28.10.13
 * Time: 15:02
 *
 */
object Authentication extends Controller {

  def changeUser() = Action {
    implicit request =>

      val username =

        if (request.body.asFormUrlEncoded.get("username") != null && !request.body.asFormUrlEncoded.get("username").isEmpty) {
          request.body.asFormUrlEncoded.get("username")(0)
        }
        else {
          "unknown"
        }

         request.session - "username"
      Ok(Json.toJson(Map("msg" -> "success"))).withSession(session - "username"+("username" -> username))
  }

  def currentUser() = Action {
    implicit request =>

      val username =

        if (request.session.get("username").isDefined) {
          request.session.get("username").get
        }
        else {
          "unknown"
        }

      Ok(Json.toJson(Map("username" -> username))).withSession("username" -> username)
  }

}
