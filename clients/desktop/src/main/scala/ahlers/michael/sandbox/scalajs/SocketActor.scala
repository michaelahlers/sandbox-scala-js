package ahlers.michael.sandbox.scalajs

import java.net.URI

import ahlers.michael.sandbox.scalajs.SocketActor._
import akka.actor.{ ActorLogging, FSM, Props, UnboundedStash }
import org.scalajs.dom.WebSocket

import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * @author <a href="michael@ahlers.consulting">Michael Ahlers</a>
 */
object SocketActor {

  sealed trait State
  case object Idle extends State
  case object Open extends State
  case object Done extends State

  sealed trait Event
  case object Opened extends Event
  case object Closed extends Event
  case object Failed extends Event
  case class Received(message: String) extends Event

  sealed trait Command
  case class Send(message: String) extends Command
  case object Close extends Command

  case object ConnectionFailed extends RuntimeException

  def props(location: URI): Props =
    Props(new SocketActor(location))
      .withMailbox("akka.actor.mailbox.unbounded-deque-based")

  def props(location: String): Props = props(URI.create(location))

}

class SocketActor(location: URI) extends FSM[State, WebSocket] with UnboundedStash with ActorLogging {

  startWith(Idle, new WebSocket(location.toString) {
    onopen = _ => self ! Opened
    onmessage = event => self ! Received(event.data.toString)
    onclose = _ => self ! Closed
    onerror = _ => self ! Failed
  })

  when(Idle, stateTimeout = 1 minute) {

    case Event(Opened, _) =>
      goto(Open)

    case Event(Closed | Failed | StateTimeout, _) =>
      throw ConnectionFailed

    case Event(Send(data), _) =>
      log.debug("""Attempt to send data "{}" before connected to "{}".""", data, location)
      stash()
      stay()

    case Event(Close, _) =>
      stay()

  }

  when(Open) {

    case Event(Closed | Failed, _) =>
      throw ConnectionFailed

    case Event(Received(message), _) =>
      log.debug("""Socket received message "{}".""", message)
      stay()

    case Event(Send(data), socket) =>
      socket.send(data)
      stay()

    case Event(Close, _) =>
      goto(Done)

  }

  when(Done) {

    case Event(Send(message), _) =>
      log.warning("""Attempted to send message "{}" after disconnected from "{}".""", message, location)
      stay()

    case Event(Close, _) =>
      stay()

  }

  onTermination {
    case StopEvent(_, _, socket) =>
      socket.close()
  }

  onTransition {
    case _ -> Open =>
      log.info("""Connected socket to "{}".""", location)
      unstashAll()
    case _ -> Done =>
      log.info("""Disconnecting socket from "{}".""", location)
      context.stop(self)
  }

  initialize()

}
