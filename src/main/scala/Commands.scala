object Commands {
  trait Command
  case class CommandIfOtherwise(com: String, b: CommandVp ,t: List[CommandVp], f: List[CommandVp]) extends Command
  case class CommandIf(com: String, args: List[CommandVp]) extends Command
  case class CommandVp(com: String, args: List[Noun]) extends Command
  case class Noun(meisi: String, att: Option[String], coref: Option[Int])
}
