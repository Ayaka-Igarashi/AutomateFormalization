object Environment {
  type Loc = Int
  type Env = Map[String, Loc]
  type State = Map[Loc, ParsingObject]

  private val varlist = List(
    "return_state","temporary_buffer",
    "state",
    "current_input_character","current_token",
    "input",
    "input_index",
    "reconsume_flag","treat_flag",
    "output_tokens","error_list",
  )

  private var unique_i = varlist.size

  def getNewLoc(): Int = {
    unique_i += 1
    unique_i
  }

  val initialEnv: Env = varlist.zipWithIndex.toMap
  val initialState: State = Map(
    initialEnv("temporary_buffer")->string2charlist(""),
    initialEnv("state")->IState("Data_state"),
    initialEnv("input")->string2charlist("abc"),
    initialEnv("input_index")->IInt(0),
    initialEnv("reconsume_flag")->IBool(false),
    initialEnv("treat_flag")->IBool(false),
    initialEnv("output_tokens")->IList(List[IToken]()),
    initialEnv("error_list")->IList(List[IError]()),
  )

  trait ParsingObject
  case class IVar(v: String) extends ParsingObject

  case class IInt(i: Int) extends ParsingObject
  case class IBool(b: Boolean) extends ParsingObject
  case class IChar(c: Char) extends ParsingObject
  case class IList(list: List[ParsingObject]) extends ParsingObject

  case class IState(state: String) extends ParsingObject
  case class IToken(token_name: String, attribute: Map[String, ParsingObject]) extends ParsingObject
  case class ITokenAttribute(attribute: Map[String, ParsingObject]) extends ParsingObject
  case class IError(error: String) extends ParsingObject

  // case class IObj[A](obj: A) extends ParsingObject
  def string2charlist(str: String): ParsingObject = {
    IList(str.map(c => IChar(c)).toList)
  }
}
