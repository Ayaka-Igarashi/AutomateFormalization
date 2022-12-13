import Environment._
import ParsingLang._
import InterpreteExp._
import Commands._
object OtherStateDef {
  val otherStates = List("Markup_declaration_open_state","After_DOCTYPE_name_state","Named_character_reference_state","Numeric_character_reference_end_state")
  def interpretOtherState(current_state: String, env: Env, state: State): (Env, State) = {
    current_state match {
      case "Markup_declaration_open_state" => {
        val input = charlist2string(state(env("input")))
        val inputIdx = if (state(env("reconsume_flag")) == IBool(true)) {
          state(env("input_index")) match {
            case IInt(i) => i-1
            case _ => -1
          }
        } else {
          state(env("input_index")) match {
            case IInt(i) => i
            case _ => -1
          }
        }
        val command_flag = if (state(env("reconsume_flag")) == IBool(true)) List(CommandVp("set_to", List(Noun("reconsume_flag",None,None), Noun("False", None,None))))
        else Nil
        val commands = 
        if (input.size >= inputIdx + 2 && input.substring(inputIdx, inputIdx+2) == "--") {
          List(
            CommandVp("set_to", List(Noun("input_index", None,None), Noun((inputIdx+2).toString, None,None))),
            CommandVp("create", List(Noun("comment_token",None,Some(0)))),
            CommandVp("set_to", List(Noun("comment_token", Some("data"), Some(0)), Noun("empty_string",None,None))),
            CommandVp("switch_to", List(Noun("Comment_start_state",None,None)))
          )
        } else if (input.size >= inputIdx + 7 && input.substring(inputIdx, inputIdx+7).toLowerCase() == "doctype") {
          List(
            CommandVp("set_to", List(Noun("input_index", None,None), Noun((inputIdx+7).toString, None,None))),
            CommandVp("switch_to", List(Noun("DOCTYPE_state",None,None)))
          )
        } else if (input.size >= inputIdx + 7 && input.substring(inputIdx, inputIdx+7) == "[CDATA[") {
          val c1 = CommandVp("set_to", List(Noun("input_index", None,None), Noun((inputIdx+7).toString, None,None)))
          val c2 =
          if (true) {
            List(
              CommandVp("switch_to", List(Noun("CDATA_section_state",None,None)))
            )
          } else {
            List(
              CommandVp("this_is_parse_error", List(Noun("cdata-in-html-content_parse_error",None,None))),
              CommandVp("create", List(Noun("comment_token",None,Some(0)))),
              CommandVp("set_to", List(Noun("comment_token", Some("data"), Some(0)), Noun("string_\"[CDATA[\"",None,None))),
              CommandVp("switch_to", List(Noun("Bogus_comment_state",None,None)))
            )
          }
          c1 :: c2
        } else {
          List(
            CommandVp("this_is_parse_error", List(Noun("incorrectly-opened-comment_parse_error",None,None))),
            CommandVp("create", List(Noun("comment_token",None,Some(0)))),
            CommandVp("set_to", List(Noun("comment_token", Some("data"), Some(0)), Noun("empty_string",None,None))),
            CommandVp("switch_to", List(Noun("Bogus_comment_state",None,None)))
          )
        }
        val cexplist = (command_flag ++ commands).map(command2ParseLang(_))
        cexplist.foldLeft((env,state))((envstate, c) => {
          val e = envstate._1
          val s = envstate._2
          interpretCexp(c, e, s)
        })
      }
      case "After_DOCTYPE_name_state" => {
        val input = charlist2string(state(env("input")))
        val inputIdx = if (state(env("reconsume_flag")) == IBool(true)) {
          state(env("input_index")) match {
            case IInt(i) => i-1
            case _ => -1
          }
        } else {
          state(env("input_index")) match {
            case IInt(i) => i
            case _ => -1
          }
        }
        val command_flag = if (state(env("reconsume_flag")) == IBool(true)) List(CommandVp("set_to", List(Noun("reconsume_flag",None,None), Noun("False", None,None))))
        else Nil
        val current_input_character: Char = if (input.size >= inputIdx + 1) input(inputIdx) else '0'
        // val current_input_character = input(inputIdx)
        val commands = 
        current_input_character.toInt match {
          case i if (i==0x9||i==0xa||i==0xc||i==0x20) => {
            List(
              CommandVp("ignore_the_character", List())
            )
          }
          case 0x3e => {
            List(
              CommandVp("switch_to", List(Noun("Data_state",None,None))),
              CommandVp("emit", List(Noun("current_token",None,None)))
            )
          }
          case 0 => {
            List(
              CommandVp("this_is_parse_error", List(Noun("eof-in-doctype_parse_error",None,None))),
              CommandVp("set_to", List(Noun("current_token",Some("force-quirks_flag"),None), Noun("on",None,None))),
              CommandVp("emit", List(Noun("current_token",None,None))),
              CommandVp("emit", List(Noun("end-of-file_token",None,None)))
            )
          }
          case _ => {
            if (input.size >= inputIdx + 6 && input.substring(inputIdx, inputIdx+6).toLowerCase() == "public") {
              List(
                CommandVp("set_to", List(Noun("input_index", None,None), Noun((inputIdx+6).toString, None,None))),
                CommandVp("switch_to", List(Noun("After_DOCTYPE_public_keyword_state",None,None)))
              )
            } else if (input.size >= inputIdx + 6 && input.substring(inputIdx, inputIdx+6).toLowerCase() == "system") {
              List(
                CommandVp("set_to", List(Noun("input_index", None,None), Noun((inputIdx+6).toString, None,None))),
                CommandVp("switch_to", List(Noun("After_DOCTYPE_system_keyword_state",None,None)))
              )
            } else {
              List(
                CommandVp("this_is_parse_error", List(Noun("invalid-character-sequence-after-doctype-name_parse_error",None,None))),
                CommandVp("set_to", List(Noun("current_token",Some("force-quirks_flag"),None), Noun("on",None,None))),
                CommandVp("reconsume_in", List(Noun("Bogus_DOCTYPE_state",None,None)))
              )
            }
          }
        }
        val cexplist = (command_flag ++ commands).map(command2ParseLang(_))
        cexplist.foldLeft((env,state))((envstate, c) => {
          val e = envstate._1
          val s = envstate._2
          interpretCexp(c, e, s)
        })
      }
      case "Named_character_reference_state" => {
        val inputIdx = if (state(env("reconsume_flag")) == IBool(true)) {
          state(env("input_index")) match {
            case IInt(i) => i-1
            case _ => -1
          }
        } else {
          state(env("input_index")) match {
            case IInt(i) => i
            case _ => -1
          }
        }
        val input = charlist2string(state(env("input")))
        
        val returnState = state(env("return_state")) match {
          case IState(state) => state
          case _ => null
        }

        val matchedop = namedCharacterReferenceMap.foldLeft(None: Option[(String,String)])((matched,map) => {
          if (input.size >= inputIdx+map._1.size && input.substring(inputIdx, inputIdx+map._1.size)==map._1) {
            matched match {
              case None => Some(map._1,map._2)
              case Some((s1,s2)) => {
                if (map._1.size > s1.size) Some(map._1,map._2)
                else matched
              }
            }
          } else matched
        })
        val command_flag = if (state(env("reconsume_flag")) == IBool(true)) List(CommandVp("set_to", List(Noun("reconsume_flag",None,None), Noun("False", None,None))))
        else Nil
        val commands = matchedop match {
          case Some((characters_name, characters)) => {
            val com1 = List(
              CommandVp("set_to", List(Noun("input_index", None,None), Noun((inputIdx+characters_name.size).toString, None,None))),
              CommandVp("append_to", List(Noun(characters_name,None,None), Noun("temporary_buffer", None,None)))
            )
            val b1 = interpretBexp(command2Bexp(CommandVp("the_character_reference_was_consumed_as_part_of_an_attribute", Nil)), env, state)
            val b2 = characters_name.last != 0x003b
            val b3 = if (input.size > inputIdx+characters_name.size) {
              val next_input_c = input(inputIdx+characters_name.size)
              next_input_c == 0x003d || (0x0030 <= next_input_c && next_input_c <= 0x0039 ) || (0x0041 <= next_input_c && next_input_c <= 0x005a) || (0x0061 <= next_input_c && next_input_c <= 0x007a)
            } else false
            val com2 = if (b1 && b2 && b3) {  
              List(
                CommandVp("flush_code_points_consumed_as_a_character_reference",Nil),
                CommandVp("switch_to", List(Noun("return_state",None,None)))
              )
            } else {  
              val com = if (b2) {
                List(
                  CommandVp("this_is_parse_error", List(Noun("missing-semicolon-after-character-reference_parse_error",None,None)))
                )
              } else Nil
              com ++ List(
                CommandVp("set_to", List(Noun("temporary_buffer",None,None), Noun("empty_string", None,None))),
                CommandVp("append_to", List(Noun(characters,None,None), Noun("temporary_buffer", None,None))),
                CommandVp("flush_code_points_consumed_as_a_character_reference",Nil),
                CommandVp("switch_to", List(Noun("return_state",None,None)))
              )
            }
            com1 ++ com2
          }
          case None => {
            List(
              CommandVp("flush_code_points_consumed_as_a_character_reference",Nil),
              CommandVp("switch_to", List(Noun("Ambiguous_ampersand_state",None,None))),
              CommandVp("set_to", List(Noun("reconsume_flag",None,None), Noun("True", None,None)))
            )
          }
        }
        val cexplist = (command_flag ++ commands).map(command2ParseLang(_))
        cexplist.foldLeft((env,state))((envstate, c) => {
          val e = envstate._1
          val s = envstate._2
          interpretCexp(c, e, s)
        })
      }
      case "Numeric_character_reference_end_state" => {
        val character_reference_code = state(env("character_reference_code")) match {
          case IInt(i) => i
          case _ => -1
        }
        val commands1 = 
        if (character_reference_code == 0) {
          List(
            CommandVp("this_is_parse_error", List(Noun("null-character-reference_parse_error",None,None))),
            CommandVp("set_to", List(Noun("character_reference_code",None,None), Noun(0xfffd.toString, None,None)))
          )
        } else if (character_reference_code > 0x10ffff) {
          List(
            CommandVp("this_is_parse_error", List(Noun("character-reference-outside-unicode-range_parse_error",None,None))),
            CommandVp("set_to", List(Noun("character_reference_code",None,None), Noun(0xfffd.toString, None,None)))
          )
        } else if (0xd800<=character_reference_code && character_reference_code<=0xdfff) {
          List(
            CommandVp("this_is_parse_error", List(Noun("surrogate-character-reference_parse_error",None,None))),
            CommandVp("set_to", List(Noun("character_reference_code",None,None), Noun(0xfffd.toString, None,None)))
          )
        } else if ((0xfdd0<=character_reference_code && character_reference_code<=0xfdef)
                  || List(0xFFFE, 0xFFFF, 0x1FFFE, 0x1FFFF, 0x2FFFE, 0x2FFFF, 0x3FFFE, 0x3FFFF, 0x4FFFE, 0x4FFFF, 0x5FFFE, 0x5FFFF, 0x6FFFE, 0x6FFFF, 0x7FFFE, 0x7FFFF, 0x8FFFE, 0x8FFFF, 0x9FFFE, 0x9FFFF, 0xAFFFE, 0xAFFFF, 0xBFFFE, 0xBFFFF, 0xCFFFE, 0xCFFFF, 0xDFFFE, 0xDFFFF, 0xEFFFE, 0xEFFFF, 0xFFFFE, 0xFFFFF, 0x10FFFE, 0x10FFFF).contains(character_reference_code)) {
          List(
            CommandVp("this_is_parse_error", List(Noun("noncharacter-character-reference_parse_error",None,None)))
          )
        } else if (character_reference_code == 0x0d 
                  || (((0 <= character_reference_code && character_reference_code <=0x1f) || (0x7f<=character_reference_code && character_reference_code<=0x9f))
                      && !(List(0x9,0xa,0xc,0xd,0x20).contains(character_reference_code)))) {
          List(
            CommandVp("this_is_parse_error", List(Noun("control-character-reference_parse_error",None,None)))
          )
        } else {
          // 表を照らし合わせ
          state80table.get(character_reference_code) match {
            case Some(code) => {
              List(CommandVp("set_to", List(Noun("character_reference_code",None,None), Noun(code.toString, None,None))))
            }
            case None => Nil
          }
        }
        val commands2 = List(
          CommandVp("set_to", List(Noun("temporary_buffer",None,None), Noun("empty_string", None,None))),
          CommandVp("append_to", List(Noun("character_reference_code",None,None), Noun("temporary_buffer", None,None))),
          CommandVp("flush_code_points_consumed_as_a_character_reference",Nil),
          CommandVp("switch_to", List(Noun("return_state",None,None))),
        )
        val cexplist = (commands1++commands2).map(command2ParseLang(_))
        cexplist.foldLeft((env,state))((envstate, c) => {
          val e = envstate._1
          val s = envstate._2
          interpretCexp(c, e, s)
        })
      }
    }
  }

  ///////////////////// get table contents functions ////////////////////////
  import java.io.File
  import org.jsoup.Jsoup
  import org.jsoup.nodes.{Document, Node}
  import scala.collection.immutable.ListMap

  def init() = {
    loadNamedCharacterReferenceCodeTable()
    loadCodePointTable()
  }
  var namedCharacterReferenceMap: ListMap[String, String] = ListMap()
  var state80table: Map[Long, Int] = Map()
  // Numeric_character_reference_end_stateの表を取り出す
  def loadCodePointTable() = {
    // htmlのparse
    val doc: Document = Jsoup.parse(new File("src/tables/state80table.txt"),null)

    val rootNode: Node = doc.body()
    var node = rootNode.childNode(0)

    var i = 0
    var m: Long = -1
    while (node != null) {
      i match {
        case i2 if i2 % 3 == 0 => {
          val str = node.toString.replace(" ", "").replace("\n", "")
          m = Integer.parseInt(str.slice(2, str.length), 16)
        }
        case i2 if i2 % 3 == 1 => {
          val str = node.toString.replace(" ", "").replace("\n", "")
          state80table += (m -> Integer.parseInt(str.slice(2, str.length), 16))
        }
        case _ =>
      }
      node = node.nextSibling()
      i += 1
    }
  }
  // NamedCharacterReferenceCodeの表を取り出す
  def loadNamedCharacterReferenceCodeTable() = {
    // htmlのparse
    val doc: Document = Jsoup.parse(new File("src/tables/namedCharacterReferencesCode.txt"),null)

    val rootNode: Node = doc.body()
    var node = rootNode.childNode(0).childNode(0).childNode(0)

    while (node != null) {
      val str = node.childNode(0).childNode(1).childNode(0).toString.replace(" ", "")
      val unicode_str = node.childNode(0).nextSibling().childNode(0).toString.replace(" ", "")
      val re = "U\\+[0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F]?".r
      val it = re.findAllMatchIn(unicode_str)
      var characters: String = ""
      while (it.hasNext) {
        val us = it.next()
        val s = us.toString().slice(2, us.toString().length)
        val hex = Integer.parseInt(s, 16)
        if (s.startsWith("0")) {
          characters += hex.toChar.toString
        }
        else {
          characters += (((hex - 0x10000) / 0x400 + 0xD800).toChar.toString + ((hex - 0x10000) % 0x400 + 0xDC00).toChar.toString)
        }
      }
      namedCharacterReferenceMap += (str -> characters)
      node = node.nextSibling()
    }
  }
}
