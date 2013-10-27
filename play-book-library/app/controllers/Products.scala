package controllers

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}

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
    val newProductForm = productForm.bindFromRequest()

    newProductForm.fold(
      hasErrors = { form =>
        Redirect(routes.Products.newProduct())
      },
      success = { newProduct =>
        Product.add(newProduct)
        Redirect(routes.Products.show(newProduct.ean))
      }
    )

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