import Commands._

object ParsingLang {
  private def test() = {
    val com = CommandVp("create", List(Noun("end_tag_token", None,Some(0))))
    val pl = command2ParseLang(com)
    println(pl)
  }
  //LAtt(LVar("current_token"), Att1(A1Var("attributes"), Att1(LastElem, Att1(A1Var("name"), ANone))))

  trait Cexp
  case class Assign(lvar: Lexp, e: Eexp) extends Cexp
  case class AssignIdx(lvar: Lexp, e: Eexp) extends Cexp
  case object Skip extends Cexp
  case class If(bexp: Bexp, c1: Cexp, c2: Cexp) extends Cexp
  case class Cons(c1: Cexp, c2: Cexp) extends Cexp

  trait Bexp
  case class Equal(e1: Eexp, e2: Eexp) extends Bexp
  case class Or(b1: Bexp, b2: Bexp) extends Bexp

  trait Lexp
  // case class LLexp(x: String, att: Option[Attexp1])
  case class LVar(x: String) extends Lexp
  case class LAtt(l: LVar, a: Att1) extends Lexp

  trait Eexp
  case class EVal(x: String) extends Eexp
  case class EAtt1(e: Eexp, att: Att1) extends Eexp
  case class EAtt2(e: Eexp, att: A2Var) extends Eexp
  case class EPlus(e1: Eexp, e2: Eexp) extends Eexp
  case class EMul(e1: Eexp, e2: Eexp) extends Eexp
  case class ECons(e1: Eexp, e2: Eexp) extends Eexp
  case class ELength(e: Eexp) extends Eexp
  case class EIdxof(list: Eexp, idx: Eexp) extends Eexp

  trait Attexp
  trait Attexp1 extends Attexp
  case class Att1(att: A1, rst: Attexp1) extends Attexp1
  case object ANone extends Attexp1
  trait A1
  case class A1Var(x: String) extends A1
  case object LastElem extends A1
  // case class A1Att(x: String, att: Attexp1) extends Attexp1
  
  trait Attexp2 extends Attexp
  case class A2Var(x: String) extends Attexp2

  private def error(error: String) = Base.customError(error)
  def command2ParseLang(command: Command): Cexp = {
    // print("\u001b[2K\u001b[G");print(command)
    command match {
      case CommandIfOtherwise(com,b,args1,args2) => {
        com match {
          case "if" => {
            val be = command2Bexp(b)
            val celist1 = args1.map(command2ParseLang(_))
            val celist2 = args2.map(command2ParseLang(_))
            If(be, cexplist2cexp(celist1), cexplist2cexp(celist2))
          }
          case "if_then" => {
            val be = command2Bexp(b)
            val celist1 = args1.map(command2ParseLang(_))
            If(be, cexplist2cexp(celist1), Skip)
          }
          case "otherwise" => Skip
        }
      }
      case CommandVp(com,args) => {
        com match {
          case "switch_to" => {
            args match {
              case Noun(state, _, _) :: Nil => Assign(LVar("state"), EVal(state))
              case _ => error("command2ParseLang : switch command invalid\n>%s".format(args))
            }
          }
          case "reconsume_in" => {
            args match {
              case Noun(state, _, _) :: Nil => Cons(Assign(LVar("state"), EVal(state)), Assign(LVar("reconsume_flag"), EVal("True")))
              case _ => error("command2ParseLang : reconsume command invalid\n>%s".format(args))
            }
          }
          case "set_to" => {
            args match {
              case lvar :: evar :: Nil => {
                val l = lvar match {
                  case Noun(lv, None, Some(id)) => LVar("x"+id)
                  case Noun(lv, None, None) => LVar(lv)
                  case Noun(lv, Some(att), Some(id)) => LAtt(LVar("x"+id),Att1(A1Var(att),ANone))
                  case Noun(lv, Some(att), None) => LAtt(LVar(lv),Att1(A1Var(att),ANone))
                }
                val e = evar match {
                  case Noun(ev, None, Some(id)) => EVal("x"+id)
                  case Noun(ev, None, None) => EVal(ev)
                  case Noun(ev, Some(att), Some(id)) => EAtt1(EVal("x"+id),Att1(A1Var(att),ANone))
                  case Noun(ev, Some(att), None) => EAtt1(EVal(ev),Att1(A1Var(att),ANone))
                }
                Assign(l, e)
              }
              case _ => error("command2ParseLang : set command invalid\n>%s".format(args))
            }
          }
          case "append_to" => {
            args match {
              case evar :: lvar :: Nil => {
                val (l,e1) = lvar match {
                  case Noun(lv, None, Some(id)) => (LVar("x"+id),EVal("x"+id))
                  case Noun(lv, None, None) => (LVar(lv),EVal(lv))
                  case Noun(lv, Some(att), Some(id)) => (LAtt(LVar("x"+id),Att1(A1Var(att),ANone)),EAtt1(EVal("x"+id), Att1(A1Var(att),ANone)))
                  case Noun(lv, Some(att), None) => (LAtt(LVar(lv),Att1(A1Var(att),ANone)),EAtt1(EVal(lv), Att1(A1Var(att),ANone)))
                }
                val e2 = evar match {
                  case Noun(ev, None, Some(id)) => EVal("x"+id)
                  case Noun(ev, None, None) => EVal(ev)
                  case Noun(ev, Some(att), Some(id)) => EAtt1(EVal("x"+id), Att1(A1Var(att),ANone))
                  case Noun(ev, Some(att), None) => EAtt1(EVal(ev), Att1(A1Var(att),ANone))
                }
                Assign(l, ECons(e1,e2))
              }
              case _ => error("command2ParseLang : append command invalid\n>%s".format(args))
            }
          }
          case "consume" => {
            args match {
              case Noun(c, _, _) :: Nil => {
                If(Equal(EVal("reconsume_flag"), EVal("False")),
                  If(Equal(EVal("input_index"), ELength(EVal("input"))),
                    Assign(LVar("current_input_character"), EVal("eof")),
                    Cons(Assign(LVar("current_input_character"), EIdxof(EVal("input"), EVal("input_index"))), 
                      Assign(LVar("input_index"), EPlus(EVal("input_index"), ELength(EVal(c)))))), 
                  Assign(LVar("reconsume_flag"), EVal("False")))
              }
              case _ => error("command2ParseLang : consume command invalid\n>%s".format(args))
            }
          }
          case "create" => {
            args match {
              case Noun(tok, None, Some(id)) :: Nil => {
                if (tok == "start_tag_token") Cons(Assign(LVar("x"+id), EVal(tok)), Cons(AssignIdx(LVar("current_token"), EVal("x"+id)), AssignIdx(LVar("last_start_tag_token"), EVal("x"+id))))
                else Cons(Assign(LVar("x"+id), EVal(tok)), AssignIdx(LVar("current_token"), EVal("x"+id)))
              }
              case Noun(tok, _, Some(id)) :: Nil => {
                println("WARNING: command2ParseLang : create command invalid\n>%s".format(args))
                if (tok == "start_tag_token") Cons(Assign(LVar("x"+id), EVal(tok)), Cons(AssignIdx(LVar("current_token"), EVal("x"+id)), AssignIdx(LVar("last_start_tag_token"), EVal("x"+id))))
                else Cons(Assign(LVar("x"+id), EVal(tok)), AssignIdx(LVar("current_token"), EVal("x"+id)))
              }
              case Noun(tok, _, _) :: Nil => {
                println("WARNING: command2ParseLang : create command invalid\n>%s".format(args))
                if (tok == "start_tag_token") Cons(Assign(LVar("x0"), EVal(tok)), Cons(AssignIdx(LVar("current_token"), EVal("x0")), AssignIdx(LVar("last_start_tag_token"), EVal("x0"))))
                else Cons(Assign(LVar("x0"), EVal(tok)), AssignIdx(LVar("current_token"), EVal("x0")))
              }
              case _ => error("command2ParseLang : create command invalid\n>%s".format(args))
            }
          }
          case "ignore_the_character" => Skip
          case "treat_it_as_per_the_\"_anything_else_\"_entry_below" => Assign(LVar("treat_flag"), EVal("True"))
          case "emit" => {
            args match {
              case Noun(tok, None, None) :: Nil => Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal(tok)))
              case Noun(tok, None, Some(id)) :: Nil => Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal("x"+id)))
              case Noun(tok, _, Some(id)) :: Nil => {
                println("WARNING: command2ParseLang : emit command invalid\n>%s".format(args))
                Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal("x"+id)))
              }
              case Noun(tok, _, _) :: Nil => {
                println("WARNING: command2ParseLang : emit command invalid\n>%s".format(args))
                Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal(tok)))
              }
              case _ => error("command2ParseLang : emit command invalid\n>%s".format(args))
            }
          }
          case "this_is_parse_error" => {
            args match {
              case Noun(e, None, _) :: Nil => Assign(LVar("error_list"), ECons(EVal("error_list"), EVal(e)))
              case _ => error("command2ParseLang : error command invalid\n>%s".format(args))
            }
          }
          case "add_to_the_character_reference_code" => {
            args match {
              case evar :: Nil => {
                val e = evar match {
                  case Noun(ev, None, Some(id)) => EVal("x"+id)
                  case Noun(ev, None, None) => EVal(ev)
                  case Noun(ev, Some(att), Some(id)) => EAtt1(EVal("x"+id), Att1(A1Var(att),ANone))
                  case Noun(ev, Some(att), None) => EAtt1(EVal(ev), Att1(A1Var(att),ANone))
                }
                Assign(LVar("character_reference_code"), EPlus(EVal("character_reference_code"), e))
              }
              case _ => error("command2ParseLang : add command invalid\n>%s".format(args))
            }
          }
          case "multiply_the_character_reference_code_by" => {
            args match {
              case evar :: Nil => {
                val e = evar match {
                  case Noun(ev, None, Some(id)) => EVal("x"+id)
                  case Noun(ev, None, None) => EVal(ev)
                  case Noun(ev, Some(att), Some(id)) => EAtt1(EVal("x"+id), Att1(A1Var(att),ANone))
                  case Noun(ev, Some(att), None) => EAtt1(EVal(ev), Att1(A1Var(att),ANone))
                }
                Assign(LVar("character_reference_code"), EMul(EVal("character_reference_code"), e))
              }
              case _ => error("command2ParseLang : multiply command invalid\n>%s".format(args))
            }
          }
          case "start_a_new_attribute_in_the_current_tag_token" => {
            Assign(LAtt(LVar("current_token"), Att1(A1Var("attributes"), ANone)), ECons(EAtt1(EVal("current_token"), Att1(A1Var("attributes"), ANone)), EVal("attribute")))
          }
          case "flush_code_points_consumed_as_a_character_reference" => {
            If(Equal(EVal(""), EVal("")),
                  Assign(LAtt(LVar("current_token"), Att1(A1Var("attributes"), Att1(LastElem, Att1(A1Var("value"), ANone)))), ECons(EAtt1(EVal("current_token"), Att1(A1Var("attributes"), Att1(LastElem, Att1(A1Var("value"), ANone)))),EVal("temporary_buffer"))), 
                  Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal("temporary_buffer"))))
          }
          case _ => Skip
        }
      }
    }
  }

  def command2Bexp(command: Command): Bexp = {
    command match {
      case CommandVp("is", args) => {
        args match {
          case Noun(ev1,_,_) :: Noun(ev2,_,_) :: Nil => Equal(EVal(ev1), EVal(ev2))
          case _ => error("bexp: argsnum")
        }
      }
      case CommandVp("the_character_reference_was_consumed_as_part_of_an_attribute", args) => {
        args match {
          case Nil => Or(Equal(EVal("return_state"), EVal("Attribute_value_double_quoted_state")), Or(Equal(EVal("return_state"), EVal("Attribute_value_single_quoted_state")),Equal(EVal("return_state"), EVal("Attribute_value_unquoted_state"))))
          case _ => error("bexp: argsnum")
        }
      }
      case CommandVp("the_current_end_tag_token_is_an_appropriate_end_tag_token", args) => {
        args match {
          case Nil => Equal(EAtt1(EVal("current_tag_token"), Att1(A1Var("name"), ANone)), EAtt1(EVal("last_start_tag_token"), Att1(A1Var("name"), ANone)))
          case _ => error("bexp: argsnum")
        }
      }
      case _ => error("bexp")
    }
  }

  def cexplist2cexp(clist: List[Cexp]): Cexp = {
    clist match {
      case Nil => Skip
      case c :: Nil => c
      case c :: rst => Cons(c, cexplist2cexp(rst))
    }
  }
  def ccons2cexplist(cexp: Cexp): List[Cexp] = {
    cexp match {
      case Cons(c1,c2) => ccons2cexplist(c1) ++ ccons2cexplist(c2)
      case _ => List(cexp)
    }
  }

  def displayCexp(cexp: Cexp, indent: Int = 0): String = {
    val indents = " "*indent
    val str = cexp match {
      case Assign(l,e) => { "%s = %s".format(l,e) }
      case AssignIdx(l,e) => "%s <- %s".format(l,e)
      case Skip => "Skip"
      case Cons(c1,c2) => "%s\n%s%s".format(displayCexp(c1),indents, displayCexp(c2))
      case If(b,c1,c2) => {
        "If %s :\n %sthen %s\n %selse %s".format(b, indents,displayCexp(c1,indent+1), indents,displayCexp(c2,indent+1))
      }
    }
    indents + str
  }

  def combineIf(commands: List[Command]): List[Command] = {
    commands match {
      case Nil => Nil
      case CommandIf("if_then", args1) :: CommandIf("otherwise", args2) :: rst => {
        CommandIfOtherwise("if",args1.head, args1.tail, args2) :: combineIf(rst)
      }
      case CommandIf("if_then", args1) :: rst => {
        CommandIfOtherwise("if_then",args1.head, args1.tail, Nil) :: combineIf(rst)
      }
      case CommandIf("otherwise", args1) :: rst => {
        CommandIfOtherwise("otherwise",null, Nil, args1) :: combineIf(rst)
      }
      case com :: rst => com :: combineIf(rst)
    }
  }
}
