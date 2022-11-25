import play.api.libs.json._

import Environment._
import Interpreter._

object TestInterpreter {
  def error(str: String) = Base.customError(str)

  def init() = {
    loadDef()
  }

  def doTest(test: JsValue): Boolean = {
    // if ((test\"description").get.toString == "CDATA in HTML content") return false

    val initialStates = getInitialStates(test\"initialStates")
    val doubleEscaped = (test\"doubleEscaped") match {
      case JsUndefined() => false
      case JsDefined(JsBoolean(v)) => v
      case _ => error("doubleEscaped type error: %s".format(test\"doubleEscaped"))
    }
    var input_ = (test\"input").get.toString
    val input = if (doubleEscaped == true) {
      val reg = "\\\\u[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]".r
      reg.replaceAllIn(input_, m => m.toString.substring(1,6))
    } else input_

    val lastStartTagName = test\"lastStartTag" match {
      case JsUndefined() => null
      case JsDefined(JsString(s)) => s
      case _ => error("lastStartTagName type error: %s".format(test\"lastStartTag"))
    }

    val correctOutputs_ = test\"output" match {
      case JsDefined(JsArray(a)) => a.toList
      case _ => error("output is not array: %s".format(test\"output"))
    }
    val correctOutputs = normalizeOutput(correctOutputs_, doubleEscaped)

    initialStates.foldLeft(true)((bool, initState) => {
      val env = initialEnv
      var state = initialState + (env("state")->IState(initState))
      if (lastStartTagName != null) state += (env("last_start_tag_token")->IToken("start_tag_token", initialTokenAttributes + ("name"->string2charlist(lastStartTagName))))
      val (e_,s_) = Interpreter.interpreter(input, env, state)
      // println(displayES(e_,s_))

      val outputList = s_(e_("output_tokens")) match {
        case IList(list) => list
        case _ => error("invalid output_tokens")
      }
      var outputTokens = normalizeTokens(outputList)
      println()
      println(outputTokens)
      println()
      println(correctOutputs)
      val isCorrect = outputTokens == correctOutputs
      println(isCorrect)
      bool && isCorrect
    })
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
                    case JsString(n) => tokenAttributes += ("name"->string2charlist(n))
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(2) match {
                    case JsString(pubid) => tokenAttributes += ("public_identifier"->string2charlist(pubid))
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(3) match {
                    case JsString(sysid) => tokenAttributes += ("system_identifier"->string2charlist(sysid))
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(4) match {
                    case JsBoolean(flag) => tokenAttributes += ("force-quirks_flag"->IBool(flag))
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case "StartTag" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => tokenAttributes += ("name"->string2charlist(n))
                    case JsNull => 
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  list(2) match {
                    case JsObject(attributeMap) => {
                      val attributes = attributeMap.map(att => {
                        ITokenAttribute(Map("name"->string2charlist(att._1), "value"->string2charlist(att._2.toString)))                        
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
                    case JsString(n) => tokenAttributes += ("name"->string2charlist(n))
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case "Comment" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => tokenAttributes += ("data"->string2charlist(n))
                    case _ => error("invalid test file: normalizeOutput")
                  }
                  IToken(normalizedName, initialTokenAttributes ++ tokenAttributes)
                }
                case "Character" => {
                  var tokenAttributes: Map[String,ParsingObject] = Map()
                  list(1) match {
                    case JsString(n) => tokenAttributes += ("data"->string2charlist(n))
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
      case IChar(c) :: rst => {
        combineIChar(IToken("character_token", initialTokenAttributes + ("data"->IList(List(IChar(c))))) :: rst)
      }
      case IToken(tok, att) :: IChar(c2) :: rst if tok == "character_token" => {
        val data = att("data") match {
          case IList(list) => IList(list :+ IChar(c2))
          case _ => null
        }
        combineIChar(IToken("character_token", att + ("data"->data)) :: rst)
      }
      case obj :: rst => obj :: combineIChar(rst)
    }
  }
}