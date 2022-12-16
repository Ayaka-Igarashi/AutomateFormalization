object Environment {
  type Loc = Int
  type Env = Map[String, Loc]
  type State = Map[Loc, ParsingObject]

  val varlist = List(
    "return_state","temporary_buffer","character_reference_code",
    "state",
    "current_input_character","current_token","last_start_tag_token",
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
    initialEnv("input")->string2charlist(""),
    initialEnv("input_index")->IInt(0),
    initialEnv("reconsume_flag")->IBool(false),
    initialEnv("treat_flag")->IBool(false),
    initialEnv("output_tokens")->IList(List[IToken]()),
    initialEnv("error_list")->IList(List[IError]()),
  )

  val initialTokenAttributes: Map[String, ParsingObject] = Map(
    "name"->null,
    "value"->null,
    "attributes"->IList(Nil: List[ITokenAttribute]),
    "data"->null,//IList(Nil: List[IChar]),
    "public_identifier"->null,
    "system_identifier"->null,
    "force-quirks_flag"->IBool(false),
    "self-closing_flag"->IBool(false)
  )

  trait ParsingObject
  case class IVar(v: String) extends ParsingObject

  case class IInt(i: Long) extends ParsingObject
  case class IBool(b: Boolean) extends ParsingObject
  case class IChar(c: Char) extends ParsingObject
  case object IEOF extends ParsingObject
  case class IList(list: List[ParsingObject]) extends ParsingObject

  case class IState(state: String) extends ParsingObject
  case class IToken(token_name: String, attribute: Map[String, ParsingObject]) extends ParsingObject
  case class ITokenAttribute(attribute: Map[String, ParsingObject]) extends ParsingObject
  case class IError(error: String) extends ParsingObject

  // trait ParsingObjectWithHole
  // case object Hole
  case object Hole extends ParsingObject

  // case class IObj[A](obj: A) extends ParsingObject
  def string2charlist(str: String): ParsingObject = {
    // surrogate
    var str_nosurrogate = ""
    var previousIsSurrogate = false
    str.zipWithIndex.foreach((c,i) => {
      if (!previousIsSurrogate) {
        if (str.codePointAt(i) != c.toInt) {
          str_nosurrogate += str.codePointAt(i).toChar
          previousIsSurrogate = true
        } else str_nosurrogate += c
      } else previousIsSurrogate = false
    })
    IList(str_nosurrogate.map(c => IChar(c)).toList)
  }

  def charlist2string(ilist: ParsingObject): String = {
    ilist match {
      case IList(list) => {
        list.map(o => {
          o match {
            case IChar(c) => c.toString()
            case _ => ""
          }
        }).mkString("")
      }
      case _ => ""
    }
  }

  ///////////////////// display function ///////////////////////////
  def displayES(env: Env, state: State): String = {
    val o = Out()
    def getVal(loc: Loc): String = {
      state.get(loc) match {
        case None => "--"
        case Some(po) => "%s".format(po)
      }
    }
    varlist.foreach(v => {
      o.outln("| %1$-20s -> %2$-2s -> %3$s".format(v, env(v), getVal(env(v))))
    })
    val other = env.filterNot(sl => varlist.contains(sl._1))
    other.foreach(v => {
      o.outln("| %1$-20s -> %2$-2s -> %3$s".format(v._1,v._2, getVal(v._2)))
    })
    o.outln()
    o.outline
  }
  private class Out() {
    var outline = ""
    def out(s: String) = outline += s
    def outln(s: String = "") = outline += s + "\n"
  }
}
