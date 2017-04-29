package ahlers.michael

import javax.inject.Inject

import controllers.AssetsFinder
import play.api.mvc.{ BaseController, ControllerComponents }

class Application @Inject() (val controllerComponents: ControllerComponents, assetsFinder: AssetsFinder) extends BaseController {

  def index = Action {
    Ok(ahlers.michael.html.index(assetsFinder))
  }

}
