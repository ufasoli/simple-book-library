package controllers

import play.api.mvc.{Flash, Action, Controller}
import models.Product
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.i18n.Messages

object Products extends Controller {
  private val productForm: Form[Product] = Form(
  mapping(
      // form fields and validation
      "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findByEan(_).isEmpty),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )
    // functions to map between form and model
    (Product.apply)(Product.unapply)
  )


  def save = Action { implicit request =>

    // validation occurs during the binding phase
    val newProductForm = productForm.bindFromRequest()

  // fold is used to "fold multiple return values into one"
  // in our case either a form with errors or a form without
  // fold takes 2 arguments which are scala functions and
  // are called accordlingly there were validation errors or not
    newProductForm.fold(

      hasErrors = { form =>
        Redirect(
            routes.Products.newProduct())
            .flashing(Flash(form.data) +              //add form data to the flash scope
          ("error" -> Messages("validation.errors")))
            },
      success = { newProduct =>

        Product.add(newProduct)
        val message = Messages("products.new.success", newProduct.name)
        Redirect(routes.Products.show(newProduct.ean)).flashing("success" -> message)

      }
    )

  }


  def newProduct = Action{ implicit request =>
      val form = if (flash.get("error").isDefined)
        productForm.bindFromRequest()
      else
        productForm

    Ok(views.html.products.editProduct(form))
  }


  def list = Action {
    implicit request =>
      val products = Product.findAll
      Ok(views.html.products.list(products))
  }


  def show(ean: Long) = Action {
    implicit request =>

      Product.findByEan(ean).map {
        product =>
          Ok(views.html.products.details(product))

      }.getOrElse(NotFound)

  }
}