import Commands._

object ParsingLang {
  private def test() = {
    val com = CommandVp("create", List(Noun("end_tag_token", None,Some(0))))
    val pl = command2ParseLang(com)
    println(pl)
  }

  trait Cexp
  case class Assign(lvar: Lexp, e: Eexp) extends Cexp
  case class AssignIdx(lvar: Lexp, e: Eexp) extends Cexp
  case object Skip extends Cexp
  case class If(bexp: Bexp, c1: Cexp, c2: Cexp) extends Cexp
  case class Cons(c1: Cexp, c2: Cexp) extends Cexp

  trait Bexp
  case class Equal(e1: Eexp, e2: Eexp) extends Bexp

  trait Lexp
  case class LVar(x: String) extends Lexp
  case class LAtt(l: Lexp, a: Attexp1) extends Lexp

  trait Eexp
  case class EVal(x: String) extends Eexp
  case class EAtt1(e: Eexp, att: Attexp1) extends Eexp
  case class EAtt2(e: Eexp, att: Attexp2) extends Eexp
  case class EPlus(e1: Eexp, e2: Eexp) extends Eexp
  case class EMul(e1: Eexp, e2: Eexp) extends Eexp
  case class ECons(e1: Eexp, e2: Eexp) extends Eexp
  case class ELength(e: Eexp) extends Eexp
  case class EIdx(list: Eexp, idx: Eexp) extends Eexp

  trait Attexp1
  case class A1Var(x: String) extends Attexp1
  case class A1Att(x: String, att: Attexp1) extends Attexp1

  trait Attexp2
  case class A2Var(x: String) extends Attexp2

  private def error(error: String) = Base.customError(error)
  def command2ParseLang(command: Command): Cexp = {
    command match {
      case CommandIf(com,args) => Skip
      case CommandVp(com,args) => {
        com match {
          case "switch_to" => {
            args match {
              case Noun(state, _, _) :: Nil => Assign(LVar("state"), EVal(state))
              case _ => error("command2ParseLang : switch command invalid")
            }
          }
          case "reconsume_in" => {
            args match {
              case Noun(state, _, _) :: Nil => Cons(Assign(LVar("state"), EVal(state)), Assign(LVar("reconsume_flag"), EVal("True")))
              case _ => error("command2ParseLang : reconsume command invalid")
            }
          }
          case "set_to" => {
            args match {
              case lvar :: evar :: Nil => {
                val l = lvar match {
                  case Noun(lv, None, Some(id)) => LVar("x"+id)
                  case Noun(lv, None, None) => LVar(lv)
                  case Noun(lv, Some(att), Some(id)) => LAtt(LVar("x"+id),A1Var(att))
                  case Noun(lv, Some(att), None) => LAtt(LVar(lv),A1Var(att))
                }
                val e = evar match {
                  case Noun(ev, None, Some(id)) => EVal("x"+id)
                  case Noun(ev, None, None) => EVal(ev)
                  case Noun(ev, Some(att), Some(id)) => EAtt1(EVal("x"+id), A1Var(att))
                  case Noun(ev, Some(att), None) => EAtt1(EVal(ev), A1Var(att))
                }
                Assign(l, e)
              }
              case _ => error("command2ParseLang : set command invalid")
            }
          }
          case "append_to" => {
            args match {
              case evar :: lvar :: Nil => {
                val (l,e1) = lvar match {
                  case Noun(lv, None, Some(id)) => (LVar("x"+id),EVal("x"+id))
                  case Noun(lv, None, None) => (LVar(lv),EVal(lv))
                  case Noun(lv, Some(att), Some(id)) => (LAtt(LVar("x"+id),A1Var(att)),EAtt1(EVal("x"+id), A1Var(att)))
                  case Noun(lv, Some(att), None) => (LAtt(LVar(lv),A1Var(att)),EAtt1(EVal(lv), A1Var(att)))
                }
                val e2 = evar match {
                  case Noun(ev, None, Some(id)) => EVal("x"+id)
                  case Noun(ev, None, None) => EVal(ev)
                  case Noun(ev, Some(att), Some(id)) => EAtt1(EVal("x"+id), A1Var(att))
                  case Noun(ev, Some(att), None) => EAtt1(EVal(ev), A1Var(att))
                }
                Assign(l, ECons(e1,e2))
              }
              case _ => error("command2ParseLang : set command invalid")
            }
          }
          case "consume" => {
            args match {
              case Noun(c, _, _) :: Nil => {
                If(Equal(EVal("reconsume_flag"), EVal("False")),
                  Cons(Assign(LVar("input_index"), EPlus(EVal("input_index"), ELength(EVal(c)))), Assign(LVar("current_input_character"), EIdx(EVal("input"), EVal("input_index")))), 
                  Skip)
              }
              case _ => error("command2ParseLang : reconsume command invalid")
            }
          }
          case "create" => {
            args match {
              case Noun(tok, None, Some(id)) :: Nil => Cons(Assign(LVar("x"+id), EVal(tok)), AssignIdx(LVar("current_token"), EVal("x"+id)))
              case _ => error("command2ParseLang : create command invalid")
            }
          }
          case "ignore_the_character" => Skip
          case "treat_it_as_per_the_\"_anything_else_\"_entry_below" => Assign(LVar("treat_flag"), EVal("True"))
          case "emit" => {
            args match {
              case Noun(tok, None, None) :: Nil => Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal(tok)))
              case Noun(tok, None, Some(id)) :: Nil => Assign(LVar("output_tokens"), ECons(EVal("output_tokens"), EVal("x"+id)))
              case _ => error("command2ParseLang : emit command invalid")
            }
          }
          case "this_is_parse_error" => {
            args match {
              case Noun(e, None, None) :: Nil => Assign(LVar("error_list"), ECons(EVal("error_list"), EVal(e)))
              case _ => error("command2ParseLang : error command invalid")
            }
          }
          case _ => Skip
        }
      }
    }
  }
}
