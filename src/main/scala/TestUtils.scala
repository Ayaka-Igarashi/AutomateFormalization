import OutputParse._
import Environment._

import play.api.libs.json._

object TestUtils {
  case class TestJson(
    description: String,
    initialStates: List[String],
    doubleEscaped: Boolean,
    input: String,
    lastStartTagName: String,
    outputs: List[IToken],
    errors: List[IError]
  )

  private def error(str: String) = Base.customError(str)

  def parseJson(filename: String): JsValue = {
    val s = load_file(filename)
    val json = Json.parse(s.mkString("\n"))
    json
  }

  def getTests(json: JsValue): List[JsValue] = {
    (json\"tests") match {
      case JsDefined(JsArray(array)) => array.toList
      case _ => Base.customError("json invalid")
    }
  }

  def formatTest(json: JsValue): TestJson = {
    var description = (json\"description") match {
      case JsDefined(JsString(s)) => s
      case _ => error("no input error: %s".format(json\"description"))
    }
    val initialStates = getInitialStates(json\"initialStates")
    val doubleEscaped = (json\"doubleEscaped") match {
      case JsUndefined() => false
      case JsDefined(JsBoolean(v)) => v
      case _ => error("doubleEscaped type error: %s".format(json\"doubleEscaped"))
    }
    var input_ = (json\"input") match {
      case JsDefined(JsString(s)) => s
      case _ => error("no input error: %s".format(json\"input"))
    }
    val input = if (doubleEscaped == true) {
      val reg = "\\\\u[0-9a-fA-F]{4}".r
      reg.replaceAllIn(input_, m => Integer.parseInt(m.toString.substring(2,6), 16).toChar.toString)
    } else input_

    val lastStartTagName = json\"lastStartTag" match {
      case JsUndefined() => null
      case JsDefined(JsString(s)) => s
      case _ => error("lastStartTagName type error: %s".format(json\"lastStartTag"))
    }

    val correctOutputs_ = json\"output" match {
      case JsDefined(JsArray(a)) => a.toList
      case _ => error("output is not array: %s".format(json\"output"))
    }
    val correctOutputs = normalizeOutput(correctOutputs_, doubleEscaped)

    val errors = json\"errors" match {
      case JsUndefined() => Nil
      case JsDefined(JsArray(a)) => {
        a.toList.map(err => {
          err\"code" match {
            case JsDefined(JsString(s)) => IError(s+"_parse_error")
            case _ => error("test file is invalid. error doesnt have code value")
          }
        })
      }
      case _ => error("test file is invalid. error is no array")
    }
    
    TestJson(
      description,
      initialStates,
      doubleEscaped,
      input,
      lastStartTagName,
      correctOutputs,
      errors
    )
  }

  def getInitialStates(initialStates: JsLookupResult): List[String] = {
    initialStates match {
      case JsDefined(JsArray(arr)) => arr.toList.map(s => {
        s match {
          case JsString(s) => s.replace(" ","_")
          case _ => error("invalid test file: %s".format(s))
        }
      })
      case _ => List("Data_state")
    }
  }
  def normalizeOutput(outputs: List[JsValue], doubleEscaped: Boolean): List[IToken] = {
    outputs.map(out => {
      out match {
        case JsArray(array) => {
          val list = array.toList
          list.head match {
            case JsString(tokenName) => {
              val normalizedName = normalizeTokenName(tokenName)
              tokenName match {
                case "DOCTYPE" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => {
                      val n1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(n, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else n
                      tokenAttributes += ("name"->string2charlist(n1))}
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(2) match {
                    case JsString(pubid) => {
                      val pubid1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(pubid, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else pubid
                      tokenAttributes += ("public_identifier"->string2charlist(pubid1))}
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(3) match {
                    case JsString(sysid) => {
                      val sysid1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(sysid, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else sysid
                      tokenAttributes += ("system_identifier"->string2charlist(sysid1))}
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(4) match {
                    case JsBoolean(flag) => tokenAttributes += ("force-quirks_flag"->IBool(!flag))
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken("DOCTYPE_token", initialTokenAttributes ++ tokenAttributes)
                }
                case "StartTag" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => {
                      val n1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(n, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else n
                      tokenAttributes += ("name"->string2charlist(n1))}
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(2) match {
                    case JsObject(attributeMap) => {
                      val attributes = attributeMap.map(att => {
                        att._2 match {
                          case JsString(attval) => ITokenAttribute(Map("name"->string2charlist(att._1), "value"->string2charlist(attval)))
                          case _ => error("invalid test file: normalizeOutput")
                        }               
                      }).toList
                      tokenAttributes += ("attributes"->IList(attributes))
                    }
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  if (list.size == 4) {
                    list(3) match {
                      case JsBoolean(flag) => tokenAttributes += ("self-closing_flag"->IBool(flag))
                      case _ => error("invalid test file: normalizeOutput")
                    }
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case "EndTag" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => {
                      val n1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(n, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else n
                      tokenAttributes += ("name"->string2charlist(n1))}
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case "Comment" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => {
                      val n1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(n, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else n
                      tokenAttributes += ("data"->string2charlist(n1))}
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case "Character" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => {
                      val n1 = if (doubleEscaped) "\\\\u[0-9a-fA-F]{4}".r.replaceAllIn(n, m => Integer.parseInt(m.toString().tail.tail, 16).toChar.toString)
                      else n
                      tokenAttributes += ("data"->string2charlist(n1))}
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case _ => error("invalid test file: normalizeOutput")
              }
            }
            case _ => error("invalid test file: normalizeOutput")
          }
        }
        case _ => error("invalid test file: normalizeOutput")
      }
    }) :+ IToken("end-of-file_token", initialTokenAttributes)
  }

  def normalizeTokenName(str: String): String = {
    val str1 = str.head.toLower + str.tail
    "[A-Z]".r.replaceAllIn(str1, m=>"_"+m.toString.toLowerCase)+"_token"
  }

  def decomposeCharacterToken(tokens: List[IToken]): List[ParsingObject] = {
    tokens.flatMap(token => {
      token match {
        case IToken("character_token", att) => {
          att("data") match {
            case IList(list) => list
            case _ => error("failed to decompose character token: %s".format(token))
          }
        }
        case _ => List(token)
      }
    })
  }

  def normalizeTokens(outputs: List[ParsingObject]): List[IToken] = {
    combineIChar(outputs).map(obj => {
      obj match {
        case IToken(tok, att) => IToken(tok,att)
        case _ => error("not itoken")
      }
    })
  }

  def combineIChar(outputs: List[ParsingObject]): List[ParsingObject] = {
    outputs match {
      case Nil => Nil 
      case obj :: Nil => obj :: Nil
      case Environment.IChar(c) :: rst => {
        combineIChar(Environment.IToken("character_token", initialTokenAttributes + ("data"->IList(List(IChar(c))))) :: rst)
      }
      case Environment.IToken(tok, att) :: Environment.IChar(c2) :: rst if tok == "character_token" => {
        val data = att("data") match {
          case Environment.IList(list) => Environment.IList(list :+ IChar(c2))
          case _ => null
        }
        combineIChar(Environment.IToken("character_token", att + ("data"->data)) :: rst)
      }
      case obj :: rst => obj :: combineIChar(rst)
    }
  }
}