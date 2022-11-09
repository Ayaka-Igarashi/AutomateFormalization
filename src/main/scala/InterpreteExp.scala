import ParsingLang._
import Commands._
import Environment._

object InterpreteExp {
  def test() = {
    val com = CommandVp("create", List(Noun("end_tag_token", None,Some(0))))
    val com1 = CommandVp("set_to", List(Noun("return_state", None,None),Noun("Character_state", None,None)))
    val com2 = CommandVp("switch_to", List(Noun("return_state", None,None)))
    val pl = Cons(command2ParseLang(com1),command2ParseLang(com2))
    val test_env = initialEnv
    val test_state = initialState
    println("E = %s".format(test_env))
    println("S = %s".format(test_state))
    // val test_c = Cons(Skip,Assign(LVar("return_state"), EVal("Data_state")))
    val (e,s) = interpretCexp(pl,test_env,test_state)
    println("E = %s".format(e))
    println("S = %s".format(s))
  }

  private def error(error: String) = Base.customError(error)

  def interpretCexp(cexp: Cexp, env: Env, state: State): (Env, State) = {
    cexp match {
      case Assign(lvar, e) => {
        val (loc,state1) = interpretEexp(e,env,state)
        lvar match {
          case LVar(x) => {
            if (env.get(x) == None) {
              ((env + (x->loc)), state1)
            }
            else {
              val locx = env(x)
              val v = state1(loc)
              (env, state1 + (locx->v))
            }
          }
          case LAtt(LVar(x), att) => {
            val locx = env(x)
            val obj = state(locx)
            val obje = state1(loc)
            val newobj = substituteInAttribute(att, obje, obj)
            (env, state1 + (locx->newobj))
          }
        }
      }
      case AssignIdx(lvar, e) => {
        val (loc,state1) = interpretEexp(e,env,state)
        lvar match {
          case LVar(x) => ((env + (x->loc)), state1)
          case _ => error("assign idx to attribute, error")
        }
      }
      case Skip => (env,state)
      case If(bexp, c1, c2) => {
        val b = interpretBexp(bexp)
        if (b) {
          interpretCexp(c1,env,state)
        } else {
          interpretCexp(c2,env,state)
        }
      }
      case Cons(c1, c2) => {
        val (env1,state1) = interpretCexp(c1,env,state)
        interpretCexp(c2,env1,state1)
      }
    }
  }

  def interpretBexp(bexp: Bexp): Boolean = {
    true
  }

  def interpretEexp(eexp: Eexp, env: Env, state: State): (Loc, State) = {
    eexp match {
      case EVal(x) => {
        if (env.get(x) == None) {
          val newl = getNewLoc()
          (newl, state+(newl->str2obj(x)))
        } else (env(x),state)
      }
      case EAtt1(e,att) =>(0,state)
      case EAtt2(e,att) =>(0,state)
      case EPlus(e1,e2) => {
        val (l1, state1) = interpretEexp(e1,env,state)
        val (l2, state2) = interpretEexp(e2,env,state1)
        val v = (state2(l1), state2(l2)) match {
          case (IInt(i1), IInt(i2)) => IInt(i1+i2)
          case _ => error("interpretEexp: EPlus: not int")
        }
        val newl = getNewLoc()
        (newl, state+(newl->v))
      }
      case EMul(e1,e2) => {
        val (l1, state1) = interpretEexp(e1,env,state)
        val (l2, state2) = interpretEexp(e2,env,state1)
        val v = (state2(l1), state2(l2)) match {
          case (IInt(i1), IInt(i2)) => IInt(i1*i2)
          case _ => error("interpretEexp: EMul: not int")
        }
        val newl = getNewLoc()
        (newl, state+(newl->v))
      }
      case ECons(e1,e2) => {
        val (l1, state1) = interpretEexp(e1,env,state)
        val (l2, state2) = interpretEexp(e2,env,state1)
        val v = (state2(l1), state2(l2)) match {
          case (IList(list), obj) => IList(list :+ obj)
          case _ => error("interpretEexp: ECons: not list")
        }
        val newl = getNewLoc()
        (newl, state+(newl->v))
      }
      case ELength(e) => {
        val (l, state1) = interpretEexp(e,env,state)
        val v = state1(l) match {
          case IList(list) => IInt(list.size)
          case _ => error("interpretEexp: ELength: not list")
        }
        val newl = getNewLoc()
        (newl, state+(newl->v))
      }
      case EIdx(e1,e2) => {
        val (l1, state1) = interpretEexp(e1,env,state)
        val (l2, state2) = interpretEexp(e2,env,state1)
        val v = (state2(l1), state2(l2)) match {
          case (IList(list), IInt(i)) => list(i)
          case _ => error("interpretEexp: EIdx: not (list,int)")
        }
        val newl = getNewLoc()
        (newl, state+(newl->v))
      }
    }
  }

  def substituteInAttribute(lvar: Attexp1, obj1: ParsingObject, obj2: ParsingObject): ParsingObject = {
    obj2
  }

  def str2obj(str: String): ParsingObject = {
    if (str.endsWith("_state")) IState(str)
    else if (str.endsWith("_token")) IToken(str,Map())
    else if (str.endsWith("_parse_error")) IError(str)
    else if (str.contains("attribute")) ITokenAttribute(Map())
    else if (str == "True") IBool(true)
    else if (str == "False") IBool(false)
    else if (str.toIntOption != None) IInt(str.toInt)
    else if (varlist.contains(str)) IVar(str)
    else if (str.size == 1) IChar(str(0))
    else string2charlist(str)
  }
}