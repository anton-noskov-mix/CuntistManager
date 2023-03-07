package controllers

import model.InitPkg
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.Inject

class ManagerController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def init = Action {
    val names = InitPkg().init()
    Ok(views.html.manager(names))
  }

}
