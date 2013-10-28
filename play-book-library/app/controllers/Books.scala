package controllers

import play.api.mvc.{Action, Controller}
import models.Book
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.bson.types.ObjectId
import java.util.Date
import play.api.data.Form


/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 28.10.13
 * Time: 08:32
 *
 */
object Books extends Controller {

  def list(filter:String) = Action {

    val books =
    if(filter != null && !filter.isEmpty) {

      Book.findAllFilterTitle(filter).toList
    }
    else{

      Book.findAll().toList
    }

    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)

    Ok(mapper.writeValueAsString(books)).as(JSON)
  }

  def listUser() = Action{  implicit  request =>

  val books = Book.findUserBorrowed(request.session.get("username").get)

    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)

    Ok(mapper.writeValueAsString(books)).as(JSON)
  }


  def borrowBook(bookId: ObjectId) = Action {  implicit request =>

      val userBorrowed = request.session.get("username").get
      val bookOption = Book.findOneById(bookId)
      val book = bookOption.get
      val updatedBook = book.copy(userBorrowed = Some(userBorrowed), borrowedOn = Some(new Date()))
      Book.save(updatedBook)
      Ok("")
  }

  def returnBook(bookId: ObjectId) = Action { implicit request =>

    val bookOption = Book.findOneById(bookId)
    val book = bookOption.get
    val updatedBook = book.copy(userBorrowed = null, borrowedOn = null)
    Book.save(updatedBook)
    Ok("")
  }


}
