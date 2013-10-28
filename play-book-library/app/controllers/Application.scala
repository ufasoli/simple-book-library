package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 28.10.13
 * Time: 11:00
 *
 */
object Application extends Controller{

  def home= Action{
    println("Home action")
    Ok(views.html.index())
  }

}
