import play.api.libs.json._

import Environment._
import Interpreter._
import TestUtils._

object TestInterpreter {
  def error(str: String) = Base.customError(str)

  def init() = {
    loadDef()
  }

  def doTest(json: JsValue): Boolean = {
    // if ((test\"description").get.toString == "CDATA in HTML content") return false

    val test = formatTest(json)

    val initialStates = test.initialStates
    val doubleEscaped = test.doubleEscaped
    val input = test.input
    val lastStartTagName = test.lastStartTagName
    val correctOutputs = test.outputs

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

}