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
    val convertOut = new PrintWriter("src/out/convertout_translate_transformer.txt")
    val list = OutputParse.load_file("src/input/translateout_transformer.txt")
    var id = 0
    for (l <- list) {
      id += 1
      // if (l.startsWith("#")) {
      //   convertOut.println(l)
      // } else 
      if (l.startsWith(" => ")) {
        convertOut.println(l)
        convertOut.print(" => ")
        val terms = parseStr(l.slice(4, l.length), 0)
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

  def parseStr(s: String, id: Int): List[Command] = {
    val charStream = new ANTLRInputStream(s)
    val lexer = new CommandStringLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new CommandStringParser(tokens)
    parser.addErrorListener(new MyErrorListener())
    var terms: List[Term] = Nil
    try {
      // val test = parser.start()
      // println(test.toStringTree(parser))
      val tree = parser.start()
      terms = CommandStringTranslater.transStart(tree)
    } catch {
      case e: RuntimeException => {
        println("error at %d : %s".format(id,e))
        terms = List(Function("PARSE_ERROR", Nil))
      }
    }
    terms.flatMap(term => {
      term2command(term) match {
        case c: Command => List(c)
        case _ => Nil
      }
    })
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
    ctx match {
      case ctx: With_argsContext => {
        val sym = transSymbol(ctx.symbol)
        val args = transArgs(ctx.args)
        Function(sym, args)
      }
      case ctx: No_argsContext => {
        val sym = transSymbol(ctx.symbol)
        Function(sym, Nil)
      }
    }
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
        "# " + transSymbol(ctx.symbol)+ "_" + ctx.IDX.getText().slice(1,2)
      }
      case ctx: Noun_dotContext => {
        "#d " + transSymbol(ctx.symbol(0)) + "_" + ctx.IDX.getText().slice(1,2) + " . " + transSymbol(ctx.symbol(1))
      }
    }
  }

  def term2command(term: Term): Command|Noun = {
    term match {
      case Function(f, args) => {
        args match {
          case argslist: List[Function] => {
            if (f.startsWith("#")) {
              if (f.startsWith("#d")) {
                val spl = f.split(" . ")
                val coref = spl(0).last match {
                  case '_' => None
                  case i => Some(i.toString.toInt)
                }
                Noun(spl(0).slice(3, spl(0).length-2), Some(spl(1)), coref)
              } else {
                val coref = f.last match {
                  case '_' => None
                  case i => Some(i.toString.toInt)
                }
                Noun(f.slice(2, f.length-2), None, coref)
              }
            } else {
              val children = argslist.map(t => {
                term2command(t)
              })
              val p = children.partition(child => {
                child match {
                  case c: CommandVp => true
                  case n: Noun => false
                  case _ => true
                }
              })
              if (p(0) == Nil) CommandVp(f, p(1).map(i => i match {case c: Noun => c}))
              else CommandIf(f, p(0).map(i => i match {case c: CommandVp => c}))
            }
          }
          case _ => null
        }
      }
      case _ => null
    }
  }
  
}
