object Commands {
  // trait Command
  // case class SCommand(com: String, args: List[VPCommand]) extends Command
  // case class VPCommand(com: String, args: List[Hikisuu]) extends Command
  case class Command(com: String, args: List[Command]|List[Hikisuu])
  case class Hikisuu(meisi: String, att: Option[String], coref: Int)
}
