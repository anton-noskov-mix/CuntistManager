package controllers

import model.Task

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
      Redirect(routes.HomeController.tasks)
  }

  def tasks() = Action {
    implicit request =>
      Ok(views.html.index(Task.tasks))
  }

  def newTask() = Action(parse.formUrlEncoded) {
    implicit request =>
      Task.addTask(request.body("taskName").head)
      Redirect(routes.HomeController.index)
  }

  def deleteTask(id: Int) = Action {
    Task.deleteTask(id)
    Ok
  }


}
