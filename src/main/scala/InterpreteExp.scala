import ParsingLang._
import Commands._
import Environment._

object InterpreteExp {
  def test() = {
    import CommandStringTranslater._
    val c0 = "consume ( next_input_character [_] )"
    // val c1 = "create ( start_tag_token [0] ) | set_to ( start_tag_token [0] . name , empty_string [_] )"
    // val c1 = "this_is_parse_error ( eof-before-tag-name_parse_error [_] )"
    // val c1 = "emit ( U+003C_LESS-THAN_SIGN_character_token [_] ) | emit ( end-of-file_token [_] )"
    val c1 = "reconsume_in ( Bogus_comment_state [_] )"
    // val c2 = "append_to ( current_input_character [_] , current_token [_] . name )"
    // val c1 = "set_to ( temporary_buffer [_] , empty_string [_] )"
    val c2 = "consume ( next_input_character [_] )"
    val com0 = parseStr(c0,0)
    val com1 = parseStr(c1,0)
    val com2 = parseStr(c2,0)
    val comlist = List(com0,com1,com2).flatMap(c => c)
    // val com = CommandVp("create", List(Noun("end_tag_token", None,Some(0))))
    // val com1 = CommandVp("set_to", List(Noun("return_state", None,None),Noun("Character_state", None,None)))
    // val com2 = CommandVp("switch_to", List(Noun("return_state", None,None)))
    // val com1 = CommandVp("set_to", List(Noun("current_token", Some("name"),None),Noun("abc", None,None)))
    // val com2 = CommandVp("switch_to", List(Noun("return_state", None,None)))
    // val pl = Cons(command2ParseLang(com0),command2ParseLang(com1))
    val test_env = initialEnv
    val test_state = initialState
    println("E = %s".format(test_env))
    println("S = %s".format(test_state))
    println()
    var e_ = test_env
    var s_ = test_state
    // val test_c = Cons(Skip,Assign(LVar("return_state"), EVal("Data_state")))
    for (com <- comlist) {
      val (e,s) = interpretCexp(command2ParseLang(com),e_,s_)
      e_ = e
      s_ = s
      println("E = %s".format(e))
      println("S = %s".format(s))
      println()
    }
    
  }

  private def error(error: String) = Base.customError(error)

  def interpretCexp(cexp: Cexp, env: Env, state: State): (Env, State) = {
    cexp match {
      case Create(lvar, e) => {
        lvar match {
          case LVar(x) => {
            val (v,state1) = interpretEexp(e,env,state)
            val newLoc = getNewLoc()
            ((env + (x->newLoc)), state1+ (newLoc->v))
          }
          case _ => error("cant create att")
        }
      }
      case Assign(lvar, e) => {
        val (v,state1) = interpretEexp(e,env,state)
        lvar match {
          case LVar(x) => {
            env.get(x) match {
              case Some(locx) => (env, state1 + (locx->v))
              case None => error("cant find %s in env (%s) at %s %s".format(x, Assign(lvar, e),SearchWrong.currentPos,SearchWrong.currentPosIdx))
            }
          }
          case LAtt(LVar(x), att) => {
            var locx: Loc = -1
            try {
              locx = env(x)
            } catch {
              case e => {
                error("not found %s in env".format(x))
                // locx = env("current_token")
              }
            }
            // val locx = env(x)
            val obj = state(locx)
            val newobj = substituteInAttribute(att, v, obj)
            (env, state1 + (locx->newobj))
          }
        }
      }
      case AssignLoc(lvar, e) => {
        lvar match {
          case LVar(x) => {
            e match {
              case EVal(variable) => {
                env.get(variable) match {
                  case Some(loc) => ((env + (x->loc)), state)
                  case None => error("cant find %s in env (%s)".format(variable, AssignLoc(lvar, e)))
                }
              }
              case _ => error("ParsingLang convertion invalid")
            }
          }
          case _ => error("assign idx to attribute, error")
        }
      }
      case Skip => (env,state)
      case If(bexp, c1, c2) => {
        val b = interpretBexp(bexp,env,state)
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

  def interpretBexp(bexp: Bexp, env: Env, state: State): Boolean = {
    bexp match {
      case Equal(e1,e2) => {
        val (v1, state1) = interpretEexp(e1,env,state)
        val (v2, state2) = interpretEexp(e2,env,state1)
        if (v1 == v2) true
        else false
      }
      case Or(b1,b2) => {
        val bool1 = interpretBexp(b1, env, state)
        val bool2 = interpretBexp(b2, env, state)
        bool1 || bool2
      }
      case And(b1,b2) => {
        val bool1 = interpretBexp(b1, env, state)
        if (!bool1) return false
        val bool2 = interpretBexp(b2, env, state)
        bool1 || bool2
      }
      case Exist(EVal(v)) => {
        state.get(env(v)) match {
          case None => false
          case Some(_) => true
        }
      }
    }
  }

  def interpretEexp(eexp: Eexp, env: Env, state: State): (ParsingObject, State) = {
    eexp match {
      case EVal(x) => {
        if (env.get(x) == None) {
          (str2obj(x, env, state), state)
        } else {
          try {
            (state(env(x)),state)
          } catch {
            case e => {
              // println(x)
              // println(displayES(env,state))
              error("cant find %s".format(x))
            }
          }
        }
      }
      case EAtt1(e,att) => {
        val (v, state1) = interpretEexp(e,env,state)
        val v2 = getAttributeVal(v, att)
        (v2, state1)
      }
      case EAtt2(e,A2Var(att)) => {
        val (v, state1) = interpretEexp(e,env,state)
        val v2 = interpretA2(v, att)
        (v2, state)
      }
      case EPlus(e1,e2) => {
        val (v1, state1) = interpretEexp(e1,env,state)
        val (v2, state2) = interpretEexp(e2,env,state1)
        val v = (v1, v2) match {
          case (IInt(i1), IInt(i2)) => IInt(i1+i2)
          case _ => error("interpretEexp: EPlus: not int")
        }
        (v, state)
      }
      case EMul(e1,e2) => {
        val (v1, state1) = interpretEexp(e1,env,state)
        val (v2, state2) = interpretEexp(e2,env,state1)
        val v = (v1, v2) match {
          case (IInt(i1), IInt(i2)) => IInt(i1*i2)
          case _ => error("interpretEexp: EMul: not int")
        }
        (v, state)
      }
      case ECons(e1,e2) => {
        val (v1, state1) = interpretEexp(e1,env,state)
        val (v2, state2) = interpretEexp(e2,env,state1)
        val v = (v1, v2) match {
          case (IList(list), obj) => IList(list :+ obj)
          case (IChar(c), obj) => IList(List(IChar(c)) :+ obj)
          case _ => error("interpretEexp: ECons: not list: %1$s %2$s %3$s".format(e1,v1,displayES(env,state2)))
        }
        (v, state)
      }
      case ELength(e) => {
        val (v1, state1) = interpretEexp(e,env,state)
        val v = v1 match {
          case IList(list) => IInt(list.size)
          case IChar(_) => IInt(1)
          case _ => error("interpretEexp: ELength: not list")
        }
        (v, state)
      }
      case EIdxof(e1,e2) => {
        val (v1, state1) = interpretEexp(e1,env,state)
        val (v2, state2) = interpretEexp(e2,env,state1)
        val v = (v1, v2) match {
          case (IList(list), IInt(i)) => list(i)
          case _ => error("interpretEexp: EIdx: not (list,int)")
        }
        (v, state)
      }
    }
  }

  def interpretA2(obj: ParsingObject, att: String): ParsingObject = {
    att match {
      case "lowercase_version" => {
        obj match {
          case IChar(c) => IChar(c.toLower)
          case IList(List(IChar(c))) => IList(List(IChar(c.toLower)))
          case _ => error("cant lower case: %s".format(obj))
        }
      }
      case "numeric_version" => { // あってるかわからない
        obj match {
          case IChar(c) => IInt(c.toInt)
          case IList(List(IChar(c))) => IList(List(IInt(c.toInt)))
          case _ => error("cant numeric case: %s".format(obj))
        }
      }
    }
  }

  def getAttributeVal(obj: ParsingObject, att: Att1): ParsingObject = {
    // println("%s , %s".format(obj,att))
    getAttributeVal_sub(obj, att)
  }
  def getAttributeVal_sub(obj: ParsingObject, att: Attexp1): ParsingObject = {
    att match {
      case Att1(a, rst) => {
        val v = (obj, a) match {
          case (IToken(_, m), A1Var(avar)) => {
            m.get(avar) match {
              case None => error("getAttributeVal_sub1 : no such attribute")
              case Some(o) => o
            }
          }
          case (ITokenAttribute(m), A1Var(avar)) => {
            m.get(avar) match {
              case None => error("getAttributeVal_sub2 : no such attribute")
              case Some(o) => o
            }
          }
          case (IList(list), LastElem) => list.last
          case _ => error("getAttributeVal_sub3 : error obj: %s , att: %s".format(obj,att))
        }
        getAttributeVal_sub(v, rst)
      }
      case ANone => obj
    }
  }

  def substituteInAttribute(att: Att1, obj1: ParsingObject, obj2: ParsingObject): ParsingObject = {
    substituteInAttribute_sub(att, obj1, obj2, Hole)
  }

  def substituteInAttribute_sub(att: Attexp1, obj1: ParsingObject, obj2: ParsingObject, obj_s: ParsingObject): ParsingObject = {
    att match {
      case Att1(a, rst) => {
        val (obj_s2, v) = (obj2, a) match {
          case (IToken(t, m), A1Var(avar)) => {
            m.get(avar) match {
              case None => error("substituteInAttribute_sub : no such attribute")
              case Some(o) => {
                val newm = m.map((k,v) => {
                  if (k == avar) (k,Hole)
                  else (k,v)
                })
                (IToken(t, newm), o)
              }
            }
          }
          case (ITokenAttribute(m), A1Var(avar)) => {
            m.get(avar) match {
              case None => error("substituteInAttribute_sub : no such attribute")
              case Some(o) => {
                val newm = m.map((k,v) => {
                  if (k == avar) (k,Hole)
                  else (k,v)
                })
                (ITokenAttribute(newm), o)
              }
            }
          }
          case (IList(list), LastElem) => {
            try {
              (IList(list.slice(0, list.size-1) :+ Hole), list.last)
            } catch {
              case e => {
                println("dont exist last elem: att: %s, obj1: %s, obj2: %s, obj_s: %s".format(att, obj1,obj2,obj_s))
                (IList(list.slice(0, list.size-1) :+ Hole), list.last)
              }
            }
          }
          case _ => error("substituteInAttribute_sub : error")
        }
        val obj3 = substituteInAttribute_sub(rst, obj1, v, obj_s2)
        substituteInHole(obj_s2, obj3)
      }
      case ANone => obj1
    }
  }

  def substituteInHole(objs: ParsingObject, value: ParsingObject): ParsingObject = {
    if (objs == Hole) value
    else {
      objs match {
        case IList(list) => {
          if (list.last == Hole) IList(list.slice(0, list.size-1) :+ value)
          else error("substituteInNull: list")
        }
        case IToken(t, m) => {
          val newm = m.map((k,v)=>{
            if (v == Hole) (k,value)
            else (k,v)
          })
          IToken(t, newm)
        }
        case ITokenAttribute(m) => {
          val newm = m.map((k,v)=>{
            if (v == Hole) (k,value)
            else (k,v)
          })
          ITokenAttribute(newm)
        }
        case _ => error("substituteInNull")
      }
    }
  }

  def str2obj(str: String, env: Env, state: State): ParsingObject = {
    if (str.endsWith("_state")) IState(str)
    else if (str == "current_tag_token") state(env("current_token"))
    else if ("U\\+[0-9A-F]{4}.*_character_token".r.matches(str)) IChar(Integer.parseInt(str.substring(2,6), 16).toChar)
    else if (str.endsWith("_token")) IToken(str,initialTokenAttributes)
    else if (str.endsWith("_parse_error")) IError(str)
    else if (str.contains("attribute")) ITokenAttribute(Map("name"->null, "value"->null))
    else if (str == "next_input_character") {
      state(env("input")) match {
        case IList(Nil) => IEOF
        case IList(list) => list.head
        case _ => error("str2obj: next_input_character")
      }
    }
    else if (str == "eof") IEOF
    else if (str == "empty_string") IList(Nil)
    else if (str.startsWith("string_")) string2charlist(str.substring(8,str.size-1))
    else if (str == "True") IBool(true)
    else if (str == "False") IBool(false)
    else if (str.toIntOption != None) IInt(str.toInt)
    else if (varlist.contains(str)) IVar(str) // これいる？変数の値を取ってきた方がいい？
    else if (str.size == 1) IChar(str(0))
    else string2charlist(str)
  }
}