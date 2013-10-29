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


      val username:String = request.body.asJson.get.\("username").as[String]

      println(s"Changing username to [$username]")
      /*val username =

        if (request.body.asFormUrlEncoded.get("username") != null && !request.body.asFormUrlEncoded.get("username").isEmpty) {
           request.body.asFormUrlEncoded.get("username")(0)
        }
        else {
          "unknown"
        }*/

      request.session - "username"
      Ok(Json.toJson(Map("msg" -> "success"))).withSession(session - "username" + ("username" -> username))
  }

  def currentUser() = Action {
    implicit request =>

      val username =  request.session.get("username").getOrElse("unknown")

      Ok(Json.toJson(Map("username" -> username)))
  }

}
