package ahlers.michael

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom

import scala.scalajs.js._

object Application extends JSApp {

  val HelloWorld =
    ScalaComponent.builder[String]("HelloWorld")
      .render_P({ name =>
        div(
          img(className := "ahlers-consulting-logo", width := "25%"),
          p("Hello, ", name, "!")
        )
      })
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
