package ahlers.michael

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom

import scala.scalajs.js._

object Application extends JSApp {

  val HelloWorld =
    ScalaComponent.builder[String]("HelloWorld")
      .render_P({ name => p("Hello, ", name, "!") })
      .build

  def main(): Unit = {
    HelloWorld("World") renderIntoDOM {
      val container = dom.document.createElement("div")
      container.setAttribute("id", getClass.getName)
      dom.document.body.appendChild(container)
      container
    }
  }

}
