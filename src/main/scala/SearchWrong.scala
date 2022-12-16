import ParsingLang._
import Interpreter._
import Environment._
import InterpreteExp._

import TestUtils._

object SearchWrong {
  // type CexpNum = (Cexp, Int)
  // case class StateDefNum(prev: List[CexpNum], trans: List[(UnicodeRange, List[CexpNum])])
  
  var wrongPosSet1: Set[(String,Option[UnicodeRange],Int)] = Set()
  var wrongToken: Boolean = false
  var testNum = 0
  var correctNum = 0
  var errorNum = 0
  var stopNum = 0

  var correctPosSet: Set[(String, Option[UnicodeRange])] = Set()
  var wrongPosSet: Set[(String,Option[UnicodeRange])] = Set()

  var correctErrorPosSet: Set[(String, (Option[UnicodeRange], Set[Int]))] = Set()

  var allWrongSentences: Set[String] = Set()
  var allWhiteSentences: Set[String] = Set()

  def searchWrongMain() = {
    testAll_error()
    testAll()
    displayAllInMd()
  }

  def testAll() = {
    val outfile = new java.io.PrintWriter("src/out/wrongs.txt")
    loadDef()
    // testFile("src/html_test_files/contentModelFlags.test")
    // testFile("src/html_test_files/domjs.test")
    // testFile("src/html_test_files/entities.test")
    // testFile("src/html_test_files/escapeFlag.test")
    // testFile("src/html_test_files/namedEntities.test")
    testFile("src/html_test_files/numericEntities.test")
    testFile("src/html_test_files/pendingSpecChanges.test")
    testFile("src/html_test_files/test1.test")
    testFile("src/html_test_files/test2.test")
    testFile("src/html_test_files/test3.test")
    testFile("src/html_test_files/test4.test")
    testFile("src/html_test_files/unicodeChars.test")
    testFile("src/html_test_files/unicodeCharsProblematic.test")
    var wrongcomSet: Set[String] = Set()
    wrongPosSet1.foreach(pos => {
      val coms = pos._2 match {
        case None => getCommandsByIdx(pos._1,true,Nil,pos._3)
        case Some(ur) => getCommandsByIdx(pos._1,false,ur,pos._3)
      }
      wrongcomSet += coms
      outfile.println("[%s / %s / %s]".format(pos._1,pos._2,pos._3))
      outfile.println(coms)
      outfile.println("")
    })
    outfile.println("\n\n-----------------\n")
    wrongcomSet.foreach(c => {
      outfile.println(c)
      outfile.println("")
    })
    outfile.close

    println("correct: %1$s/%2$s\nerror: %3$s/%2$s\nstop: %4$s/%2$s".format(correctNum, testNum, errorNum, stopNum))
    println((wrongPosSet--correctPosSet).size)
  }

  def testFile(file: String) = {
    println("> testing file: %s".format(file))
    val json = parseJson(file)
    val tests = getTests(json)
    var i = 0
    val size = tests.size
    for (test <- tests) { ///.slice(26,27)
      // println(formatTest(test).input)
      println("> %1$4s/%2$-4s".format(i,size))
      i += 1
      val formatted = formatTest(test)
      if (formatted.outputs.size > 1) testOne(formatted)
      print("\u001b[1F\u001b[2K")
    }
    print("\u001b[1F\u001b[2K")
  }
  def testOne(test: TestJson) = {
    val correctout = decomposeCharacterToken(test.outputs)
    test.initialStates.foreach(initS => testOneState(test, initS, correctout))
  }
  def testOneState(test: TestJson, initialS: String, correctout: List[ParsingObject]) = {
    testNum +=1
    // val correctout = decomposeCharacterToken(test.outputs)
    val env = initialEnv
    var state = initialState + (env("state")->IState(initialS))
    if (test.lastStartTagName != null) state += (env("last_start_tag_token")->IToken("start_tag_token", initialTokenAttributes + ("name"->string2charlist(test.lastStartTagName))))
    wrongToken = false
    try {
      val (e,s) = searchWrong(test.input, env, state, correctout)
      
      if (wrongToken == false) {
        // if (wayOfPosSet.contains(("Attribute_name_state", Some(List((-1,65535)))))) {println(test.input);0/0}
        correctPosSet ++= wayOfPosSet
        correctNum += 1}//
      else {
        wrongPosSet ++= wayOfPosSet
        println(correctout)
        println(displayES(e,s))
        0/0
        // println(displayCexp(cexplist2cexp(log)))
      }
    } catch {
      case e: Base.MyError => {
        errorNum += 1
        // println("interpreter実行エラー: %s".format(e.message))
        wrongPosSet ++= wayOfPosSet
        wrongPosSet1 += (currentPos._1, currentPos._2, currentPosIdx)
        // println(displayCexp(cexplist2cexp(log)))
        // if (test.input.size < 10){
        println(test.input)
        Base.error()//}
      }
    }
    0
  }

  var wayOfPosSet: Set[(String, Option[UnicodeRange])] = Set()

  def searchWrong(str1: String, initenv: Env, initstate: State, correctOutputs: List[ParsingObject]): (Env,State) = {
    val str = normalizeNewline(str1)
    var env = initenv
    var state = initstate.updated(env("input"), string2charlist(str))
    var eofFlag = false
    var correctOutIdx = 0
    var lastOutputList: ParsingObject = IList(Nil)
    var switchNum = 0
    wayOfPosSet = Set()
    while (!eofFlag && switchNum < str.size*3+10) {
      val current_state = state(env("state")).asInstanceOf[IState].state
      val (e,s) = interpretState_log(current_state, env, state, logFunction)
      val output_tokens = s(e("output_tokens"))
      output_tokens.asInstanceOf[IList].list.lastOption match {
        case Some(IToken("end-of-file_token",_)) => eofFlag = true
        case _ =>
      }

      // println("%s %s".format(s(e("output_tokens")), correctOutputs(correctOutIdx)))
      val output_tokensSize = output_tokens.asInstanceOf[IList].list.size 
      val lastOutputListSize = lastOutputList.asInstanceOf[IList].list.size
      if (output_tokensSize != lastOutputListSize) {
        // val outputNum = output_tokensSize - lastOutputListSize
        val outToks = output_tokens.asInstanceOf[IList].list.slice(lastOutputListSize, output_tokensSize)
        outToks.foreach(tok => {
          // println("%s".format(tok))
          // println("%s".format(correctOutputs(correctOutIdx)))
          tok match {
            case IList(List(IList(list))) => {
              val toknum = list.size
              (0 to toknum-1).toList.foreach(toki => {
                if (correctOutputs.size <= correctOutIdx+toki||(list(toki) != correctOutputs(correctOutIdx+toki))) {
                  wrongToken = true
                } else if (correctOutputs.size > correctOutIdx+toki && list(toki) == correctOutputs(correctOutIdx+toki)) {
                  if (wrongToken == false){
                    correctPosSet ++= wayOfPosSet
                    wayOfPosSet = Set()}
                }
              })
              correctOutIdx += toknum
            }
            case IList(list) => {
              val toknum = list.size
              (0 to toknum-1).toList.foreach(toki => {
                if (correctOutputs.size <= correctOutIdx+toki||(list(toki) != correctOutputs(correctOutIdx+toki))) {
                  wrongToken = true
                } else if (correctOutputs.size > correctOutIdx+toki && list(toki) == correctOutputs(correctOutIdx+toki)) {
                  if (wrongToken == false){
                    correctPosSet ++= wayOfPosSet
                    wayOfPosSet = Set()}
                }
              })
              correctOutIdx += toknum
            }
            case _ => {
              if (correctOutputs.size <= correctOutIdx||(correctOutputs.size > correctOutIdx && tok != correctOutputs(correctOutIdx))) {
                // println(log)
                wrongToken = true
              } else if (correctOutputs.size > correctOutIdx && tok == correctOutputs(correctOutIdx)) {
                if (wrongToken == false){
                  correctPosSet ++= wayOfPosSet
                  wayOfPosSet = Set()}
              }
              correctOutIdx += 1
            }
          }
        })
        lastOutputList = s(e("output_tokens"))
      }
      env = e
      state = s
      // println(displayES(e,s))
      wayOfPosSet = wayOfPosSet ++ Set(currentPos, (currentPos._1, None))
      switchNum += 1
    }
    if (!eofFlag) {
      stopNum += 1
      wrongToken = true
      // println(str)
      // 0/0
    }
    (env,state)
  }
  //////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////
  def testAll_error() = {
    loadDef()
    testFile_error("src/html_test_files/contentModelFlags.test")
    testFile_error("src/html_test_files/domjs.test")
    testFile_error("src/html_test_files/entities.test")
    testFile_error("src/html_test_files/escapeFlag.test")
    testFile_error("src/html_test_files/namedEntities.test")
    testFile_error("src/html_test_files/numericEntities.test")
    testFile_error("src/html_test_files/pendingSpecChanges.test")
    testFile_error("src/html_test_files/test1.test")
    testFile_error("src/html_test_files/test2.test")
    testFile_error("src/html_test_files/test3.test")
    testFile_error("src/html_test_files/test4.test")
    testFile_error("src/html_test_files/unicodeChars.test")
    testFile_error("src/html_test_files/unicodeCharsProblematic.test")
  }
  def testFile_error(file: String) = {
    println("> testing file: %s".format(file))
    val json = parseJson(file)
    val tests = getTests(json)
    var i = 0
    val size = tests.size
    for (test <- tests) {
      println("> %1$4s/%2$-4s".format(i,size))
      i += 1
      val formated = formatTest(test)
      if (formated.errors != Nil) testOne_error(formated)
      // testOne_error(formated)
      print("\u001b[1F\u001b[2K")
    }
    print("\u001b[1F\u001b[2K")
    // testOne_error(formatTest(tests(7)))
  }

  def testOne_error(test: TestJson) = {
    test.initialStates.foreach(initS => testOneState_error(test, initS, test.errors))
  }
  def testOneState_error(test: TestJson, initialS: String, correctErrors: List[IError]) = {
    testNum +=1
    val env = initialEnv
    var state = initialState + (env("state")->IState(initialS))
    if (test.lastStartTagName != null) state += (env("last_start_tag_token")->IToken("start_tag_token", initialTokenAttributes + ("name"->string2charlist(test.lastStartTagName))))
    wrongToken = false
    try {
      val (e,s) = searchWrong_error(test.input, env, state, correctErrors)
      if (wrongToken == false) {
        correctErrorPosSet ++= wayOfPosSet.map(su => {
          val count = getCommandsCount(su._1,su._2)
          val iset = (0 to count-1).toList.flatMap(i =>{
            val str = getCommands2(su._1,su._2, i)
            val r = "(^Switch|^switch|^Reconsume|^reconsume|^This|^this).*".r
            if (r.matches(str)) Set(i)
            else Set()
          }).toSet
          (su._1, (su._2, iset))
        }).toSet
      } 
    } catch {
      case e: Base.MyError => 
    }
  }

  def searchWrong_error(str1: String, initenv: Env, initstate: State, correctErrors: List[IError]): (Env,State) = {
    val str = normalizeNewline(str1)
    var env = initenv
    var state = initstate.updated(env("input"), string2charlist(str))
    var eofFlag = false
    var correctOutIdx = 0
    var lastError: ParsingObject = null
    var switchNum = 0
    wayOfPosSet = Set()
    while (!eofFlag && switchNum < 1000) {
      val current_state = state(env("state")).asInstanceOf[IState].state
      val (e,s) = interpretState_log(current_state, env, state, logFunction)
      s(e("output_tokens")).asInstanceOf[IList].list.lastOption match {
        case Some(IToken("end-of-file_token",_)) => eofFlag = true
        case _ =>
      }
      s(e("error_list")).asInstanceOf[IList].list.lastOption match {
        case Some(err) if err != lastError => {
          if (correctErrors.size <= correctOutIdx||(correctErrors.size > correctOutIdx && err != correctErrors(correctOutIdx))) {
            // println(log)
            wrongToken = true
            // eofFlag = true
          }
          correctOutIdx += 1
          lastError = err
        }
        case _ =>
      }
      env = e
      state = s
      wayOfPosSet = wayOfPosSet ++ Set(currentPos, (currentPos._1, None))
      switchNum += 1
    }
    (env,state)
  }

  var log = List[Cexp]()
  val logFunction: (List[Cexp],List[Cexp]) => Unit = (l1,l2) => {log = log++l1++l2}

  var currentPos: (String,Option[UnicodeRange]) = ("",None)
  var currentPosIdx = -1
  
  def interpretState_log(current_state: String, env: Env, state: State, logFunc: (List[Cexp],List[Cexp]) => Unit = (l1,l2)=>{}): (Env, State) = {
    if (OtherStateDef.otherStates.contains(current_state)) return OtherStateDef.interpretOtherState(current_state,env,state)
    currentPos = (current_state, None)
    val stateDef = definition(current_state)
    val prev = stateDef.prev
    val trans = stateDef.trans
    if(state(env("treat_flag")) == IBool(true)) {
      val state1 = state.updated(env("treat_flag"), IBool(false))
      val anythingelse = trans.last._2
      currentPosIdx = -1
      currentPos = (current_state, Some(List((-1,0xffff))))
      val (env_t, state_t) = anythingelse.foldLeft((env,state1))((envstate, c) => {
        currentPosIdx += 1
        val e = envstate._1
        val s = envstate._2
        interpretCexp(c, e, s)
      })
      return (env_t,state_t)
    }
    currentPosIdx = -1
    val (env_p, state_p) = prev.foldLeft((env,state))((envstate, c) => {
      currentPosIdx += 1
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
        currentPos = (current_state, Some(t._1))
      }
    }

    currentPosIdx = -1
    val (env_t, state_t) = matchedCexp.foldLeft((env_p,state_p))((envstate, c) => {
      currentPosIdx += 1
      val e = envstate._1
      val s = envstate._2
      interpretCexp(c, e, s)
    })

    logFunc(prev, matchedCexp)
    (env_t,state_t)
  }

  def getCommandsByIdx(stateName: String, isprev: Boolean, chara: UnicodeRange, idx: Int): String = {
    val statedef: (List[(String,String)],List[(String, List[(String,String)])]) = stateCommandList(stateName)
    if (isprev) {
      val coms = statedef._1(idx)
      "%s\n%s".format(coms._1,coms._2)
    } else {
      val trans: List[(String, List[(String,String)])] = statedef._2
      trans.map(sl => (toUnicodeRange(sl._1), sl._2)).toMap.get(chara) match {
        case Some(list) => {
          val coms = list(idx)
          "%s\n%s".format(coms._1,coms._2)
        }
        case None => Base.customError("failed to get commands by idx")
      }
    }
  }

  def getCommands(stateName: String, chara: Option[UnicodeRange]): Set[String] = {
    val statedef: (List[(String,String)],List[(String, List[(String,String)])]) = stateCommandList(stateName)
    chara match {
      case None => {
        statedef._1.map(s=>s._1).toSet
      }
      case Some(ur) => {
        val coms: List[(String,String)] = statedef._2.filter(s=>toUnicodeRange(s._1)==ur).head._2
        coms.map(s=>s._1).toSet
      }
    }
  }
  def getCommands2(stateName: String, chara: Option[UnicodeRange], idx: Int): String = {
    val statedef: (List[(String,String)],List[(String, List[(String,String)])]) = stateCommandList(stateName)
    chara match {
      case None => {
        statedef._1(idx)._1
      }
      case Some(ur) => {
        val coms: List[(String,String)] = statedef._2.filter(s=>toUnicodeRange(s._1)==ur).head._2
        coms(idx)._1
      }
    }
  }

  def getCommandsCount(stateName: String, chara: Option[UnicodeRange]): Int = {
    val statedef: (List[(String,String)],List[(String, List[(String,String)])]) = stateCommandList(stateName)
    chara match {
      case None => statedef._1.size
      case Some(ur) => {
        val coms: List[(String,String)] = statedef._2.filter(s=>toUnicodeRange(s._1)==ur).head._2
        coms.size
      }
    }
  }

  def getAllCorrectSents(): Set[String] = {
    correctErrorPosSet.flatMap(s => {
      s._2._2.map(i => {
        getCommands2(s._1,s._2._1,i)
      }).toSet
    }).toSet ++ 
    correctPosSet.flatMap(s => getCommands(s._1,s._2))
  }

  def getAllWrongSents(): Set[String] = {
    val (cposIdx,wposIdx) = toPosIdx()
    // Map[String, Map[Option[UnicodeRange], Set[Int]]]
    wposIdx.flatMap(sm => {
      sm._2.flatMap(us => {
        us._2.map(s => {
          getCommands2(sm._1,us._1,s)
        })
      })
    }).toSet
  }
  

  //////////////

  def importFixCommands() = {
    val outfile = new java.io.PrintWriter("src/out/fixed.txt")
    val translated = OutputParse.load_file("src/input/translate_125.txt")
    val outputline = OutputParse.load_file("src/out/wrongs126.txt")
    val fixList = outputline.zipWithIndex.groupBy(s => s._2/4).map(s => s._2.map(s2=>s2._1))
    val fixList2 = fixList.map(s => ("%s\n%s".format(s(0),s(1)), "%s\n%s".format(s(0),s(2).replace(" (o) ", " =>  "))))
    var t = translated.mkString("\n")
    fixList2.foreach(s => {
      t = t.replace(s._1,s._2)
    })
    outfile.println(t)
    outfile.close
  }

  /////////////// display function /////////////
  def toPosIdx(): (Map[String, Map[Option[UnicodeRange], Set[Int]]], Map[String, Map[Option[UnicodeRange], Set[Int]]]) = {
    val cposSet: Map[String, Set[Option[UnicodeRange]]] = correctPosSet.groupBy(s => s._1).map(s=>(s._1->s._2.map(u=>u._2)))
    val wposSet = wrongPosSet.groupBy(s => s._1).map(s=>(s._1->s._2.map(u=>u._2)))

    var m: Map[String, Map[Option[UnicodeRange], Map[Int, Int]]] = definition.map(ss => {
      val prevSize = ss._2.prev.size
      var smap: Map[Option[UnicodeRange], Map[Int, Int]] = Map()
      smap += None->((0 to prevSize-1).toList.map(i => i->0).toMap)
      smap ++= ss._2.trans.map(ul => {
        val size = ul._2.size
        Some(ul._1)->((0 to size-1).toList.map(i => i->0).toMap)
      }).toMap
      ss._1->smap
    })
    val cposIdx: Map[String, Map[Option[UnicodeRange], Set[Int]]] = cposSet.map(ss => {
      val state = ss._1
      val uniIdx: Map[Option[UnicodeRange], Set[Int]] = m(state).map(um => um._1->um._2.map(m1=>m1._1).toSet)
      val umap = ss._2.map(u => {
        u->uniIdx(u)
      }).toMap
      state->umap
    }).toMap
    val wposIdx: Map[String, Map[Option[UnicodeRange], Set[Int]]] = wposSet.map(ss => {
      val state = ss._1
      val uniIdx: Map[Option[UnicodeRange], Set[Int]] = m(state).map(um => um._1->um._2.map(m1=>m1._1).toSet)
      val umap = ss._2.map(u => {
        u->uniIdx(u)
      }).toMap
      state->umap
    }).toMap
    (cposIdx,wposIdx)
  }

  def makeWC(cposIdx: Map[String, Map[Option[UnicodeRange], Set[Int]]], wposIdx: Map[String, Map[Option[UnicodeRange], Set[Int]]]): Map[String, Map[Option[UnicodeRange], Map[Int, Int]]] = {
    def doesContains(map: Map[String, Map[Option[UnicodeRange], Set[Int]]],state: String, unicode: Option[UnicodeRange], idx: Int): Boolean = {
      map.get(state) match {
        case Some(map_tmp) => {
          map_tmp.get(unicode) match {
            case Some(set_tmp) => {
              if (set_tmp.contains(idx)) true
              else false
            }
            case None => false
          }
        }
        case None => false
      }
    }
    def doesContains2(set: Set[(String, (Option[UnicodeRange], Set[Int]))],state: String, unicode: Option[UnicodeRange], idx: Int): Boolean = {
      val stateset = set.filter(s => s._1==state).map(s=> s._2)
      val matches = stateset.filter(s => s._1 == unicode).map(s=> s._2)
      matches.headOption match {
        case Some(s) => {
          if (s.contains(idx)) true
          else false
        }
        case None => false
      }
    }
    // 初期化
    var m: Map[String, Map[Option[UnicodeRange], Map[Int, Int]]] = definition.map(ss => {
      val prevSize = ss._2.prev.size
      var smap: Map[Option[UnicodeRange], Map[Int, Int]] = Map()
      smap += None->((0 to prevSize-1).toList.map(i => i->0).toMap)
      smap ++= ss._2.trans.map(ul => {
        val size = ul._2.size
        Some(ul._1)->((0 to size-1).toList.map(i => i->0).toMap)
      }).toMap
      ss._1->smap
    })
    val allCorrectSents = getAllCorrectSents()
    m.map(statem => {
      val state = statem._1
      state->statem._2.map(unim => {
        val unicode = unim._1
        unicode->unim._2.map(ii => {
          val idx = ii._1
          try {
            getCommands2(state,unicode,idx)
          } catch {
            case e => println("%s %s %s".format(state,unicode,idx))
          }
          val sent = getCommands2(state,unicode,idx)
          if (doesContains(wposIdx,state,unicode,idx) && sent.startsWith("If")) {
            allWrongSentences += sent
            idx->(-1)
          }
          else if (doesContains(cposIdx,state,unicode,idx)) {
            idx->1
          } else if (doesContains2(correctErrorPosSet,state,unicode,idx)) {
            idx->1
          } else if (allCorrectSents.contains(getCommands2(state,unicode,idx))) {
            idx->1
          } else if (doesContains(wposIdx,state,unicode,idx)) {
            allWrongSentences += sent
            idx->(-1)
          } else {
            allWhiteSentences += sent
            idx -> 0
          }
        })
      })
    })
  }

  def displayAllInMd() = {
    var outstring = ""
    def sout(s: String) = outstring += s

    val (cposi,wposi) = toPosIdx()
    val wcmap = makeWC(cposi,wposi)
    statenameList.foreach(state => {
      val statedef = definition(state)
      val wcm = wcmap(state)
      sout("name: %s\n".format(state))
      sout("%s\n\n".format(displayAllInMd_sub(state, statedef, wcm)))
    })
    val outfile = new java.io.PrintWriter("src/out/wrong_correct.md")
    outfile.println(outstring.replace("\n","\n\n"))
    outfile.close
    println("correct: %s, wrong: %s, white: %s".format(getAllCorrectSents().size, allWrongSentences.size, allWhiteSentences.size))
  }

  def displayAllInMd_sub(stateName: String, stateDef: StateDef, wcmap: Map[Option[UnicodeRange], Map[Int, Int]]): String = {
    var outstring = ""
    def sout(s: String) = outstring += s

    def color(s: String, i: Int): String = {
      if (i == 1) DisplayMd.inBlue(s)
      else if (i == -1) DisplayMd.inRed(s)
      else s
    }

    val stateComs: (List[(String,String)],List[(String, List[(String,String)])]) = stateCommandList(stateName)
    val prevComs: List[String] = stateComs._1.map(ss => "%s\n%s".format(ss._1,ss._2))
    val transComs: List[(String, List[String])] = stateComs._2.map(sl => (sl._1, sl._2.map(ss => "%s\n%s".format(ss._1,ss._2))))
    stateDef match {
      case StateDef(prev, trans) => {
        sout("prev:\n")
        val prevwc = wcmap(None)
        val clist1 = prev.map(ccons2cexplist(_))
        val clist1_2 = clist1.map(cl => cl.map(displayCexp(_)).mkString("\n"))
        val cout1 = (prevComs zip clist1_2).zipWithIndex.map(cci => {
          val cc = cci._1
          color("%s\n==>\n%s".format(cc._1,cc._2), prevwc(cci._2))
        }).mkString("\n")
        sout(cout1)
        sout("\ntrans:\n")
        (transComs zip trans).foreach(tt => {
          val t = tt._2
          sout("char: %s (%s)\n".format(displayUnicodeRange(t._1), tt._1._1))
          val wc = wcmap(Some(t._1))
          val clist2 = t._2.map(ccons2cexplist(_))
          val clist2_2 = clist2.map(cl => cl.map(displayCexp(_)).mkString("\n"))
          val cout2 = (tt._1._2 zip clist2_2).zipWithIndex.map(cci => {
            val cc = cci._1
            try {
              wc(cci._2)
            } catch {
              case e => println("%s %s %s".format(stateName, wc, t._1))
            }
            color("%s\n==>\n%s".format(cc._1,cc._2), wc(cci._2))
          }).mkString("\n")
          sout(cout2)
          sout("\n")
        })
      }
    }
    outstring
  }

  def displayAllWrong() = {
    def getCom(list: Map[String, (List[(String,String)],List[(String, List[(String,String)])])], sent: String): String = {
      list.foreach(s => {
        s._2._1.foreach(str => {
          if (str._1==sent) return (str._2)
        })
        s._2._2.foreach(sss => sss._2.foreach(ss => {
          if (ss._1==sent) return ss._2
        }))
      })
      ""
    }
    val outfile = new java.io.PrintWriter("src/out/wrong_imi.txt")
    // val sents = getAllWrongSents()
    // println(sents.size)
    allWrongSentences.foreach(sent => {
      val henkan = getCom(stateCommandList, sent)
      outfile.println("%s\n%s\n".format(sent,henkan))
    })
    outfile.close
  }
}
