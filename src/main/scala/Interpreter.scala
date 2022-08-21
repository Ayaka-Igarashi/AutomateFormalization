import Terms._

object Interpreter {
  type Value = String
  def test_interpret_term() = {
    var env = initEnv("abc")
    env = env.updated("current_input_character", "u")
    val terms = List(
      Function("consume",List(Function("next_input_character", Nil))),
      Function("set_to",List(Function("return_state", Nil),Function("character_reference_state", Nil))),
      Function("switch_to",List(Function("return_state", Nil))),
      Function("emit",List(Function("end-of-file_token", Nil))),
      Function("append_to",List(Function("current_input_character",Nil), Function("temporary_buffer",Nil))),
      Function("append_to",List(Function("<>",Nil), Function("temporary_buffer",Nil))),
    )
    env = terms.foldLeft(env) {(e, term) => {
      val newenv = interpretTerm(term, e)
      println(newenv)
      newenv
    }}
  }

  def test_zikkou() = {
    var env = initEnv("&aa")
    env = zikkou(env)
    println(env)
  }

  def zikkou(env0: Map[String,Value]): Map[String,Value] = {
    var env = env0
    // stateに対応するものを取り出す
    val preterms = List(Function("consume",List(Function("next_input_character", Nil))))
    env = preterms.foldLeft(env) {(e, term) => {
      val newenv = interpretTerm(term, e)
      println(newenv)
      newenv
    }}
    // character matchingする
    val terms = List(
      Function("set_to",List(Function("return_state", Nil),Function("data_state", Nil))),
      Function("switch_to",List(Function("character_reference_state", Nil))),
    )
    env = terms.foldLeft(env) {(e, term) => {
      val newenv = interpretTerm(term, e)
      println(newenv)
      newenv
    }}
    env
  }

  def initEnv(input: String): Map[String,Value] = {
    return Map(
      "input" -> input,
      "state" -> "data_state",
      "outputTokens" -> ""
    )
  }

  def updateEnv(env: Map[String,Value]): Map[String,Value] = {
    val next_input_character = env("input").headOption match {
      case Some(c) => c.toString
      case None => "<EOF>"
    }
    env.updated("next_input_character", next_input_character)
  }

  // (term, env) -> new env
  def interpretTerm(term: Term, env0: Map[String,Value]): Map[String,Value] = {
    var env = env0
    env = updateEnv(env)

    term match {
      case Function("switch_to",List(Function(z0, Nil))) => {
        env.updated("state", env.getOrElse(z0, z0))
      }
      case Function("set_to",List(Function(z0, Nil),Function(z1, Nil))) => {
        env.updated(z0, env.getOrElse(z1, z1))
      }
      case Function("emit",List(Function(z0, Nil))) => {
        env.updated("outputTokens", env("outputTokens") + (interpretToken(z0)) + " ")
      }
      case Function("consume",List(Function(z0, Nil))) => {
        if (env("input").startsWith(env.getOrElse(z0, z0))) {
          env = env.updated("input", env("input").slice(env.getOrElse(z0, z0).size, env("input").size))
          env = env.updated("current_input_character", env.getOrElse(z0, z0))
          env
        } else {
          env
        }
      }
      case Function("ignore_the",List()) => return env
      case Function("append_to",List(Function(z0, Nil),Function(z1, Nil))) => {
        return env.updated(z1, env.getOrElse(z1, "") + env.getOrElse(z0, z0))
      }
      case Function("treat_it_as_per_the_\"_else_\"_below",List()) => return env
      case _ => return env
    }
  }

  def interpretToken(str: String): String = {
    str
  }
}
