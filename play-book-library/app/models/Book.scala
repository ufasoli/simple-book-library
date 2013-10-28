package models

import play.api.libs.json.Json
import com.novus.salat.global._

import play.api.Play.current
import java.util.Date
import com.novus.salat.annotations._
import com.novus.salat.dao._
import se.radley.plugin.salat._
import mongoContext._
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 28.10.13
 * Time: 08:26
 *
 */
case class Book(@Key("_id") id: ObjectId = new ObjectId, bookId: String = "", bookTitle: String, bookCategory: String, userBorrowed: Option[String], borrowedOn: Option[Date]) {
  // implicit val bookFormat = Json.format[Book]
}


object Book extends ModelCompanion[Book, ObjectId] {
  def collection = mongoCollection("book")
  val dao = new SalatDAO[Book, ObjectId](collection =collection) {}


  def findAllFilterTitle(filter: String) = {
    println(MongoDBObject("bookTitle" ->
      MongoDBObject("$regex" -> s".*$filter.*")))

    dao.find(
      MongoDBObject("bookTitle" ->
        MongoDBObject("$regex" -> s".*$filter.*")
      ))
  }

  def findUserBorrowed(username:String) = dao.find(MongoDBObject("userBorrowed" -> username))


}


