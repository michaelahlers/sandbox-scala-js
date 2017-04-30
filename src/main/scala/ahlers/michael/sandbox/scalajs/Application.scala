package ahlers.michael.sandbox.scalajs

import javax.inject.Inject

import controllers.AssetsFinder
import play.api.mvc.{ BaseController, ControllerComponents }

class Application @Inject() (val controllerComponents: ControllerComponents, assetsFinder: AssetsFinder) extends BaseController {

  def desktop = Action {
    Ok(ahlers.michael.sandbox.scalajs.html.desktop(assetsFinder))
  }

}
