import InterpreteExp._
import Environment._
import ParsingLang._

object Interpreter {
  type UnicodeRange = List[(Int,Int)]
  case class StateDef(prev: List[Cexp], trans: List[(UnicodeRange, List[Cexp])])

  var statenameList: List[String] = Nil
  var stateCommandList: Map[String, (List[(String,String)],List[(String, List[(String,String)])])] = null
  var definition: Map[String, StateDef] = null

  def loadDef(): Any = {
    if (definition != null) return
    definition = makeStateDefinition("src/out/fixed.txt")
    println("\u001b[37m\u001b[46mINFO: \u001b[0m definition was set")
    OtherStateDef.init()
  }

  def interpreter_default(str: String) = {
    var env = initialEnv
    var state = initialState.updated(env("input"), string2charlist(str))
    println(displayES(env,state))
    // println("E = %s".format(env))
    // println("S = %s".format(state))
    var eofFlag = false
    var loopNum = 0
    while (!eofFlag && loopNum < 10) {
      // eofFlag = true // ここ消す
      val current_state = state(env("state")).asInstanceOf[IState].state
      val (e,s) = interpretState(current_state, env, state)
      s(e("output_tokens")).asInstanceOf[IList].list.lastOption match {
        case Some(IToken("end-of-file_token",_)) => eofFlag = true
        case _ =>
      }
      env = e
      state = s
      println(displayES(e,s))
      loopNum += 1
      // println("S = %s\n".format(s))
    }
  }

  def interpreter(str: String, initenv: Env, initstate: State): (Env,State) = {
    var env = initenv
    var state = initstate.updated(env("input"), string2charlist(str))
    // println(displayES(env,state))
    // println("E = %s".format(env))
    // println("S = %s".format(state))
    var eofFlag = false
    while (!eofFlag) {
      // eofFlag = true // ここ消す
      val current_state = state(env("state")).asInstanceOf[IState].state
      val (e,s) = interpretState(current_state, env, state)
      s(e("output_tokens")).asInstanceOf[IList].list.lastOption match {
        case Some(IToken("end-of-file_token",_)) => eofFlag = true
        case _ =>
      }
      env = e
      state = s
      // println(displayES(e,s))
      // println("S = %s\n".format(s))
    }
    (env,state)
  }

  def interpretState(current_state: String, env: Env, state: State): (Env, State) = {
    if (OtherStateDef.otherStates.contains(current_state)) return OtherStateDef.interpretOtherState(current_state,env,state)
    
    val stateDef = definition(current_state)
    val prev = stateDef.prev
    val trans = stateDef.trans
    if(state(env("treat_flag")) == IBool(true)) {
      val state1 = state.updated(env("treat_flag"), IBool(false))
      val anythingelse = trans.last._2
      val (env_t, state_t) = anythingelse.foldLeft((env,state1))((envstate, c) => {
        val e = envstate._1
        val s = envstate._2
        interpretCexp(c, e, s)
      })
      return (env_t,state_t)
    }
    val (env_p, state_p) = prev.foldLeft((env,state))((envstate, c) => {
      val e = envstate._1
      val s = envstate._2
      interpretCexp(c, e, s)
    })
    val unicode: Int = state_p(env_p("current_input_character")) match {
      case IChar(c) => c
      case IEOF => -1
    }
    var alreadyMatched = false
    var matchedCexp: List[Cexp] = Nil
    for (t <- trans) {
      if (characterIsMatch(unicode, t._1) && !alreadyMatched) {
        matchedCexp = t._2
        alreadyMatched = true
      }
    }
    val (env_t, state_t) = matchedCexp.foldLeft((env_p,state_p))((envstate, c) => {
      val e = envstate._1
      val s = envstate._2
      interpretCexp(c, e, s)
    })
    (env_t,state_t)
  }

  def characterIsMatch(c: Int, range: UnicodeRange): Boolean = {
    for (r<-range) {
      if (r._1 <= c && c <= r._2) return true
    }
    false
  }

  def toUnicodeRange(s: String): UnicodeRange = {
    s match {
      case "ASCII upper alpha" => List((0x0041, 0x005A))
      case "ASCII lower alpha" => List((0x0061, 0x007A))
      case "ASCII alpha" => List((0x0041, 0x005A), (0x0061, 0x007A))
      case "ASCII alphanumeric" => List((0x0030, 0x0039), (0x0041, 0x005A), (0x0061, 0x007A))
      case "ASCII hex digit" => List((0x0030, 0x0039), (0x0041, 0x0046), ( 0x0061, 0x0066))
      case "ASCII upper hex digit" => List((0x0030, 0x0039), (0x0041, 0x0046))
      case "ASCII lower hex digit" => List((0x0030, 0x0039), (0x0061, 0x0066))
      case "ASCII digit" => List((0x0030, 0x0039))
      case "Anything else" => List((0,0xffff))
      case "EOF" => List((-1,-1))
      case s"U+$x" => {
        val unicode = Integer.parseInt(x.substring(0,4), 16)
        List((unicode,unicode))
      }
      case _ => Nil
    }
  }

  ////////////////////// make definition function /////////////////////////////
  def makeStateDefinition(filename: String): Map[String, StateDef] = {
    val outputline = OutputParse.load_file(filename)
    var statelist: List[(String, List[String], List[String])] = Nil
    var tmp1: String = null
    var tmp2: List[String] = Nil
    var tmp3: List[String] = Nil
    var isPrev = true
    outputline.foreach(o => {
      if (o.startsWith("name:")) {
        if (tmp1 != null) {
          statelist :+= (tmp1,tmp2,tmp3)
          tmp2 = Nil
          tmp3 = Nil
        }
        statenameList :+= o.substring(5,o.size)
        tmp1 = o.substring(5,o.size)
      } else if (o.startsWith("prev:")) isPrev = true
      else if (o.startsWith("trans:")) isPrev = false
      else if (isPrev) tmp2 :+= o
      else tmp3 :+= o
    })
    if (tmp1 != null) statelist :+= (tmp1,tmp2,tmp3)
    stateCommandList = statelist.map(s => (s._1, convertState_noConvert(s._2, s._3))).toMap
    statelist.map(s => (s._1, convertState(s._2, s._3))).toMap
  }

  def convertState(prev: List[String], trans: List[String]): StateDef = {
    val prev1 = convertComlist(prev)
    var translist: List[List[String]] = List()
    var tmp: List[String] = List()
    trans.foreach(t => {
      if (t.startsWith("char:")) {
        if (tmp != Nil){ 
          translist :+= tmp
          tmp = Nil
        }
        tmp :+= t.substring(5,t.size)
      } else {
        tmp :+= t
      }
    })
    if (tmp != Nil) translist :+= tmp
    val trans1 = translist.map(t => convertTrans(t.head, t.tail))
    StateDef(prev1, trans1)
  }

  def convertTrans(char: String, com: List[String]): (UnicodeRange, List[Cexp]) = {
    val unirange = toUnicodeRange(char)
    (unirange, convertComlist(com))
  }
  def convertComlist(comlist: List[String]): List[Cexp] = {
    val commands = comlist.zipWithIndex.filter((_,i) => i%2==1).map(t => {
      val c = t._1.substring(5,t._1.size)
      CommandStringTranslater.parseStr(c,0)
    })
    val combined = combineIf(commands)
    combined.map(coms => cexplist2cexp(coms.map(command2ParseLang(_))))
  }

  def convertState_noConvert(prev: List[String], trans: List[String]): (List[(String,String)],List[(String, List[(String,String)])]) = {
    val partition_prev = prev.zipWithIndex.partition(ni => ni._2 % 2 == 0)
    val prev1 = (partition_prev._1 zip partition_prev._2).map(nini => (nini._1._1, nini._2._1))
    var translist: List[List[String]] = List()
    var tmp: List[String] = List()
    trans.foreach(t => {
      if (t.startsWith("char:")) {
        if (tmp != Nil){ 
          translist :+= tmp
          tmp = Nil
        }
        tmp :+= t.substring(5,t.size)
      } else {
        tmp :+= t
      }
    })
    if (tmp != Nil) translist :+= tmp
    val trans1 = translist.map(t => {
      val partition_t = t.tail.zipWithIndex.partition(ni => ni._2 % 2 == 0)
      val t1 = (partition_t._1 zip partition_t._2).map(nini => (nini._1._1, nini._2._1))
      (t.head, t1)
    })
    (prev1, trans1)
  }

  //////////////////// display functions ///////////////////////////
  def displayDefinition() = {
    var outstring = ""
    def sout(s: String) = outstring += s
    statenameList.foreach(state => {
      val statedef = definition(state)
      sout("name: %s\n".format(state))
      sout("%s\n\n".format(displayStatedef(statedef)))
    })
    val outfile = new java.io.PrintWriter("src/out/definition.txt")
    outfile.println(outstring)
    outfile.close
  }

  def displayStatedef(stateDef: StateDef): String = {
    var outstring = ""
    def sout(s: String) = outstring += s
    stateDef match {
      case StateDef(prev, trans) => {
        sout("prev:\n")
        val clist1 = prev.flatMap(ccons2cexplist(_))
        val cout1 = clist1.map(displayCexp(_)).mkString("\n")
        sout(cout1)
        sout("\ntrans:\n")
        trans.foreach(t => {
          sout("char: %s\n".format(displayUnicodeRange(t._1)))
          val clist2 = t._2.flatMap(ccons2cexplist(_))
          val cout2 = clist2.map(displayCexp(_)).mkString("\n")
          sout(cout2)
          sout("\n")
        })
      }
    }
    outstring
  }
  def displayUnicodeRange(unirange: UnicodeRange): String = {
    unirange.map(r => "[%s, %s]".format(r._1,r._2)).mkString(", ")
  }

  def displayDefinitionWith() = {
    var outstring = ""
    def sout(s: String) = outstring += s
    statenameList.foreach(state => {
      val statedef = definition(state)
      sout("name: %s\n".format(state))
      sout("%s\n\n".format(displayStatedefWith(state, statedef)))
    })
    val outfile = new java.io.PrintWriter("src/out/definitionWith.txt")
    outfile.println(outstring)
    outfile.close
  }

  def displayStatedefWith(stateName: String, stateDef: StateDef): String = {
    var outstring = ""
    def sout(s: String) = outstring += s

    val stateComs: (List[(String,String)],List[(String, List[(String,String)])]) = stateCommandList(stateName)
    val prevComs: List[String] = stateComs._1.map(ss => "%s\n%s".format(ss._1,ss._2))
    val transComs: List[(String, List[String])] = stateComs._2.map(sl => (sl._1, sl._2.map(ss => "%s\n%s".format(ss._1,ss._2))))
    stateDef match {
      case StateDef(prev, trans) => {
        sout("prev:\n")
        val clist1 = prev.map(ccons2cexplist(_))
        val clist1_2 = clist1.map(cl => cl.map(displayCexp(_)).mkString("\n"))
        val cout1 = (prevComs zip clist1_2).map(cc => "%s\n==>\n%s".format(cc._1,cc._2)).mkString("\n")
        sout(cout1)
        sout("\ntrans:\n")
        (transComs zip trans).foreach(tt => {
          val t = tt._2
          sout("char: %s (%s)\n".format(displayUnicodeRange(t._1), tt._1._1))
          val clist2 = t._2.map(ccons2cexplist(_))
          val clist2_2 = clist2.map(cl => cl.map(displayCexp(_)).mkString("\n"))
          val cout2 = (tt._1._2 zip clist2_2).map(cc => "%s\n==>\n%s".format(cc._1,cc._2)).mkString("\n")
          sout(cout2)
          sout("\n")
        })
      }
    }
    outstring
  }

}
