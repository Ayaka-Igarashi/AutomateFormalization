import DataTemplate._
import Terms._
import Commands._
import Convert._
import CommandStringParser._
import java.io.{PrintWriter}
import org.antlr.v4.runtime._

object CommandStringTranslater {

  class MyErrorListener extends BaseErrorListener {
    override def syntaxError(recognizer: Recognizer[?,?], offendingSymbol: Object, line: Int, charPositionInLine: Int, msg: String, e: RecognitionException) = {
      // println("error: %s".format(msg))
      throw new RuntimeException(msg, e)
    }
    // def reportAmbiguity​(recognizer: Parser,  dfa: org.antlr.v4.runtime.dfa.DFA,  startIndex: Int, stopIndex: Int,  exact: Boolean,  ambigAlts: java.util.BitSet,  configs: org.antlr.v4.runtime.atn.ATNConfigSet) = {
    //   throw new UnsupportedOperationException("Not supported yet.");
    // }
    // def reportAttemptingFullContext(recognizer: Parser,  dfa: org.antlr.v4.runtime.dfa.DFA,  startIndex: Int, stopIndex: Int, ambigAlts: java.util.BitSet,  configs: org.antlr.v4.runtime.atn.ATNConfigSet) = {
    //   throw new UnsupportedOperationException("Not supported yet.");
    // }
    // def reportContextSensitivity(recognizer: Parser,  dfa: org.antlr.v4.runtime.dfa.DFA,  startIndex: Int, stopIndex: Int,  i2: Int, configs: org.antlr.v4.runtime.atn.ATNConfigSet) = {
    //   throw new UnsupportedOperationException("Not supported yet.");
    // }
  }

  def parseAll() = {
    val convertOut = new PrintWriter("src/convertout_translate.txt")
    val list = OutputParse.load_file("src/input/translateout.txt")
    var id = 0
    for (l <- list) {
      id += 1
      // println(id)
      if (l.startsWith("#")) {
        convertOut.println(l)
      } else if (l.startsWith("=> ")) {
        convertOut.println(l)
        convertOut.print("=> ")
        val terms = parseStr(l.slice(3, l.length-5), id)
        for (t <- terms) {
          convertOut.print(t)
          convertOut.print(" | ")
        }
        convertOut.println()
      } else {
        convertOut.println(l)
      }
    }
    convertOut.close()
  }

  def parseAll_beam() = {
    val convertOut = new PrintWriter("src/convertout_translate_beam.txt")
    val list = OutputParse.load_file("src/input/translateout_beam.txt")
    var id = 0
    var skip = 0
    for (l <- list) {
      id += 1
      // println(id)
      if (skip != 0) {
        skip = skip - 1
      } else if (l.startsWith("#")) {
        convertOut.println(l)
      } else if (l.startsWith("=>[")) {
        val terms = parseStr(l.slice(5, l.length-5), id)
        if (terms == List(Function("PARSE_ERROR", Nil))) {
          //
        } else {
          skip = 3 - l(3).toString.toInt
          println(skip)
          convertOut.println(l)
          convertOut.print("=>[" + l(3) + "]")
          for (t <- terms) {
            convertOut.print(t)
            convertOut.print(" | ")
          }
          convertOut.println()
        }
      } else {
        convertOut.println(l)
      }
    }
    convertOut.close()
  }

  def parseStr(s: String, id: Int): List[Term] = {
    val charStream = new ANTLRInputStream(s)
    val lexer = new CommandStringLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new CommandStringParser(tokens)
    parser.addErrorListener(new MyErrorListener())
    var terms: List[Term] = Nil
    try {
      val tree = parser.start()
      terms = CommandStringTranslater.transStart(tree)
    } catch {
      case e: RuntimeException => {
        println("error at %d : %s".format(id,e))
        terms = List(Function("PARSE_ERROR", Nil))
      }
    }
    terms
  }
  
  def transStart(ctx: StartContext): List[Term] = transTermList(ctx.termlist)
  def transTermList(ctx: TermlistContext): List[Term] = {
    ctx match {
      case ctx: Termlist_BarContext => {
        transTerm(ctx.term) :: transTermList(ctx.termlist)
      }
      case ctx: Termlist_termContext => {
        List(transTerm(ctx.term))
      }
    }
  }
  def transTerm(ctx: TermContext): Term = {
    val sym = transSymbol(ctx.symbol)
    val args = transArgs(ctx.args)
    Function(sym, args)
  }
  def transArgs(ctx: ArgsContext): List[Term] = {
    ctx match {
      case ctx: Args_nounContext => transArgsNoun(ctx.argsnoun)
      case ctx: Args_termContext => transArgsTerm(ctx.argsterm)
    }
  }
  def transArgsNoun(ctx: ArgsnounContext): List[Term] = {
    ctx match {
      case ctx: Argsnoun_listContext => {
        Function(transNoun(ctx.noun), Nil) :: transArgsNoun(ctx.argsnoun)
      }
      case ctx: Argsnoun_symbolContext => {
        List(Function(transNoun(ctx.noun), Nil))
      }
      // case ctx: Argsnoun_dotContext => {
      //   transArgsNoun(ctx.argsnoun) match {
      //     case Function(s,_) :: Nil => List(Function(s + transNoun(ctx.noun), Nil))
      //     case _ => List(Function(transNoun(ctx.noun), Nil))
      //   }
      // }
    }
  }
  def transArgsTerm(ctx: ArgstermContext): List[Term] = {
    ctx match {
      case ctx: Argsterm_listContext => {
        transTerm(ctx.term) :: transArgsTerm(ctx.argsterm)
      }
      case ctx: Argsterm_termContext => {
        List(transTerm(ctx.term))
      }
    }
  }
  def transSymbol(ctx: SymbolContext): String = {
    ctx.SYMBOL.getText()
  }
  def transNoun(ctx: NounContext): String = {
    ctx match {
      case ctx: Noun_symbolContext => {
        transSymbol(ctx.symbol)+ " _ " + ctx.IDX.getText()
      }
      case ctx: Noun_dotContext => {
        transSymbol(ctx.symbol) + " . " + transNoun(ctx.noun)
      }
    }
  }

  def term2command(term: Term): Command = {
    term match {
      case Function(f, args) => {
        //
      }
      case _ => //
    }
    null
  }
  
}
