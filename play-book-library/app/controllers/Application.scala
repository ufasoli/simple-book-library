package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 25.10.13
 * Time: 16:15
 *
 */
object Application extends Controller {

  def index() = Action {
              Redirect(routes.Products.list())
  }

}
