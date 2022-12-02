import ParsingLang._
import Interpreter._
import Environment._
import InterpreteExp._

import TestUtils._

object SearchWrong {
  // type CexpNum = (Cexp, Int)
  // case class StateDefNum(prev: List[CexpNum], trans: List[(UnicodeRange, List[CexpNum])])
  
  var wrongPosSet: Set[(String,Option[UnicodeRange],Int)] = Set()

  def testAll() = {
    val outfile = new java.io.PrintWriter("src/out/wrongs.txt")
    loadDef()
    // testFile("src/html_test_files/contentModelFlags.test")
    // testFile("src/html_test_files/domjs.test")
    // testFile("src/html_test_files/entities.test")
    // testFile("src/html_test_files/escapeFlag.test")
    // testFile("src/html_test_files/namedEntities.test")
    // testFile("src/html_test_files/numericEntities.test")
    // testFile("src/html_test_files/pendingSpecChanges.test")
    testFile("src/html_test_files/test1.test")
    // testFile("src/html_test_files/test2.test")
    // testFile("src/html_test_files/test3.test")
    // testFile("src/html_test_files/test4.test")
    // testFile("src/html_test_files/unicodeChars.test")
    // testFile("src/html_test_files/unicodeCharsProblematic.test")
    var wrongcomSet: Set[String] = Set()
    wrongPosSet.foreach(pos => {
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
  }
  def testFile(file: String) = {
    val json = parseJson(file)
    val tests = getTests(json)
    // for (test <- tests) {
    //   testOne(formatTest(test))
    // }
    testOne(formatTest(tests(12)))
  }
  def testOne(test: TestJson) = {
    val correctout = decomposeCharacterToken(test.outputs)
    val env = initialEnv
    var state = initialState
    try {
      val (e,s) = searchWrong(test.input, env,state, correctout)
      println(displayES(e,s))
    } catch {
      case e: Base.MyError => {
        // println("interpreter実行エラー: %s".format(e.message))
        wrongPosSet += (currentPos._1, currentPos._2, currentPosIdx)
      }
    }
    0
  }

  def searchWrong(str: String, initenv: Env, initstate: State, correctOutputs: List[ParsingObject]): (Env,State) = {
    var env = initenv
    var state = initstate.updated(env("input"), string2charlist(str))
    var eofFlag = false
    var correctOutIdx = 0
    var lastOutput: ParsingObject = null
    var switchNum = 0
    while (!eofFlag && switchNum < 1000) {
      val current_state = state(env("state")).asInstanceOf[IState].state
      val (e,s) = interpretState_log(current_state, env, state, logFunction)
      s(e("output_tokens")).asInstanceOf[IList].list.lastOption match {
        case Some(IToken("end-of-file_token",_)) => eofFlag = true
        case _ =>
      }
      s(e("output_tokens")).asInstanceOf[IList].list.lastOption match {
        case Some(tok) if tok != lastOutput => {
          if (tok != correctOutputs(correctOutIdx)) {
            // println(log)
            eofFlag = true
          }
          correctOutIdx += 1
          lastOutput = tok
        }
        case _ =>
      }
      env = e
      state = s

      switchNum += 1
    }
    (env,state)
  }

  var log = List[Cexp]()
  val logFunction: (List[Cexp],List[Cexp]) => Unit = (l1,l2) => {log = l1++l2}

  var currentPos: (String,Option[UnicodeRange]) = ("",None)
  var currentPosIdx = -1
  
  def interpretState_log(current_state: String, env: Env, state: State, logFunc: (List[Cexp],List[Cexp]) => Unit = (l1,l2)=>{}): (Env, State) = {
    currentPos = (current_state, None)
    if (OtherStateDef.otherStates.contains(current_state)) return OtherStateDef.interpretOtherState(current_state,env,state)
    
    val stateDef = definition(current_state)
    val prev = stateDef.prev
    val trans = stateDef.trans
    if(state(env("treat_flag")) == IBool(true)) {
      val state1 = state.updated(env("treat_flag"), IBool(false))
      val anythingelse = trans.last._2
      currentPosIdx = -1
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
}
