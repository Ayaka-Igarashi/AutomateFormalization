import Main._
import Terms._
import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import scala.util.Random
import Convert._

object GenerateTrainingData {
  def generateDataTemplate() = {
    // NLP結果読み込み
    val states = load_nlp_file("src/input/nlpOut_depnp_a.txt")
    // ontologyつくる
    Ontology.makeOntology()
    // println(Ontology.ontology)
    // 文章全部取り出してAntiunificationで規則生成
    val rules = rule_generating(states, "src/rules_depnp.txt")
    // rules.foreach(r => println(generateCode(r)))

    val codefile = new PrintWriter("src/main/scala/DataTemplate_.scala")
    codefile.println("import Terms._\nobject DataTemplate {\nval trainingDatas = List(")
    rules.zipWithIndex.foreach{ case(r,i) => {
      codefile.println("// %s".format(displayTerm(toCommand(r))))
      val znum = toCommand(r) match {
        case Function(s, l) => {
          l match {
            case l: List[Term] => l.size
            case _ => 0
          }
        }
        case _ => 0
      }
      codefile.println("( %s ,\n \"\", %d),".format(generateCode(toCommand(r)), znum))
      codefile.println()
    }}
    codefile.println(")\n}")
    codefile.close()
  }

  def generateData() = {
    val dataOut = new PrintWriter("src/trainingData.txt")
    // NLP結果読み込み
    val states = load_nlp_file("src/input/nlpOut_depnp_a.txt")
    // ontologyつくる
    Ontology.makeOntology()

    // generateWordSet_ori()
    import DataTemplate._
    val random = new Random
    // 引数の個数に関して確率の重みをつける(引数が多いほど出やすくなる)
    val weights = ruleWeights(templates.map(t => t._3))
    val randomMax = weights.last
    for (i <- 1 to 25000) {
      val randomValue = random.nextInt(randomMax)
      val ruleidx = searchRuleIdx(weights, randomValue)
      // if (ruleidx == 10) println(randomValue)
      val rule = templates(ruleidx)
      val subCount = rule._3
      val subs = getOntologyObject(subCount)
      val natStr = replaceSubs(replaceCc(replaceDet(rule._2)), subs)
      val termStr = rule._1 match {
        case term: Term => processTerm(term, subs)
        case list: List[Term] => {
          (list.foldLeft(""){(str, term) => str + "| " + processTerm(term, subs)}).tail
        }
      }
      dataOut.println("%s\t%s".format(natStr, termStr))
    }
    generateData_if(dataOut)
    dataOut.close()
  }

  def generateData_if(dataOut: PrintWriter) = {
    import DataTemplate._
    val random = new Random
    // 引数の個数に関して確率の重みをつける(引数が多いほど出やすくなる)
    val weights = ruleWeights(templates_if.map(t => t._3))
    val randomMax = weights.last
    for (i <- 1 to 15000) {
      val randomValue = random.nextInt(randomMax)
      val ruleidx = searchRuleIdx(weights, randomValue)
      // if (ruleidx == 10) println(randomValue)
      val rule = templates_if(ruleidx)
      val termCount = rule._3
      val bCount = rule._4
      val subs = generateVpTerm(termCount) ++ generateZyouken(bCount)
      val natStr = replaceSubs(rule._2, subs.map(m => (m._1, m._2._1)).toMap)
      val termStr =  normalize(processTerm(rule._1, subs.map(m => (m._1, m._2._2)).toMap))
      dataOut.println("%s\t%s".format(natStr, termStr))
    }
  }

  def generateWordSet_ori() = {
    val set = Main.allSentences.flatMap(sent => {
      sent.split(' ')
    }).toSet
    val dataOut = new PrintWriter("src/wordset_ori.txt")
    set.foreach(w => {
      if (w.startsWith("$")) dataOut.println(w.slice(1, w.size))
      else if (w.contains(".")) dataOut.println(w.replace(".", ""))
      else if (w.contains(",")) dataOut.println(w.replace(",", ""))
      else if (w=="") {}
      else dataOut.println(w)
    })
    dataOut.close()
  }

  def generateWordSet() = {
    val dataOut = new PrintWriter("src/wordset.txt")
    // NLP結果読み込み
    val states = load_nlp_file("src/input/nlpOut_depnp_a.txt")
    // ontologyつくる
    Ontology.makeOntology()
    val size = Ontology.ontology.i - 1
    for (i <- 0 to size) {
      dataOut.println(OntologyMatch.searchValue(i))
    }
    dataOut.close()
  }

  def generateWordSet_nat() = {
    val dataOut = new PrintWriter("src/wordset_input.txt")
    // NLP結果読み込み
    val states = load_nlp_file("src/input/nlpOut_depnp_a.txt")
    // ontologyつくる
    Ontology.makeOntology()
    val size = Ontology.ontology.i - 1
    for (i <- 0 to size) {
      val w = OntologyMatch.searchValue(i)
      w.split('_').foreach(w1 => dataOut.println(w1))
      dataOut.println(w)
    }
    dataOut.close()
  }

  def processTerm(term: Term, subs: Map[String, String]): String = {
    var s = displayTerm(term)
    subs.foreach{case(k,v) => {
      s = s.replace(k, v)
    }}
    s.replace("(", " ( ").replace(")", " ) ").replace(",", " , ")
  }

  // <det> => a or an or the
  def replaceDet(str: String): String = {
    val dets = Map(0->"a", 1->"an", 2->"the")
    val random = new Random
    var s = str
    while (s.contains("<det>")) {
      val i = random.nextInt(3)
      s = s.replaceFirst("<det>", dets(i))
    }
    s
  }

  def replaceCc(str: String): String = {
    val ccs = Map(0 -> ",", 1 -> "and", 2 -> "and")
    val random = new Random
    var s = str
    while (s.contains("<cc>")) {
      val i = random.nextInt(2)
      s = s.replaceFirst("<cc>", ccs(i))
    }
    s
  }

  // <z0> => ontology's object string
  def replaceSubs(str: String, subs: Map[String, String]): String = {
    var s = str
    subs.foreach{case(k,v) => {
      var v1 = v
      if (!(v1.endsWith("state") && !v1.contains("return_state"))) v1 = v1.replace("_", " ")
      // corefの処理
      v1 = v1.replaceAll(" /[0-9]+", "")
      // pos関係の処理 " . " => "'s " or " of "
      if (v1.contains(" . ")) {
        val random = new Random
        val r = random.nextInt(11)
        if (r <= 7) v1 = v1.replace(" . ", " 's ")
        else if (r == 8) v1 = v1.replace(" . ", "s ")
        else if (r == 9) v1 = v1.replace(" . ", " ")
        else {
          val list = v1.split(" . ")
          v1 = list(1) + " of " + list(0)
        }
      }
      s = s.replace("<%s>".format(k), v1)
    }}
    s
  }

  def getOntologyObject(n: Int): Map[String, String] = {
    val random = new Random
    List.range(0,n).map(i => {
      val max = Ontology.ontology.i - 1
      val obji = random.nextInt(max)
      var obj = OntologyMatch.searchValue(obji) + " /0"

      // if (obj == "_"){println(obji)
      // println(obj)}

      OntologyMatch.getRandomAttribute(obji) match {
        case None => 
        case Some(atti) => {
          obj = obj + " . " + OntologyMatch.searchValue(atti) + " /0"
        }
      }
      ("z%d".format(i),obj)
    }).toMap
  }

  def generateVpTerm(n: Int): Map[String, (String,String)] = {
    import DataTemplate._
    val random = new Random
    // 引数の個数に関して確率の重みをつける(引数が多いほど出やすくなる)
    val weights = ruleWeights(templates.map(t => t._3))
    val randomMax = weights.last
    List.range(0,n).map(i => {
      val randomValue = random.nextInt(randomMax)
      val ruleidx = searchRuleIdx(weights, randomValue)
      // if (ruleidx == 10) println(randomValue)
      val rule = templates(ruleidx)
      val subCount = rule._3
      val subs = getOntologyObject(subCount)
      val natStr = replaceSubs(replaceCc(replaceDet(rule._2)), subs)
      val termStr = rule._1 match {
        case term: Term => processTerm(term, subs)
        case list: List[Term] => {
          (list.foldLeft(""){(str, term) => str + "| " + processTerm(term, subs)}).tail
        }
      }
      ("t%d".format(i),(natStr, termStr))
    }).toMap
  }

  def generateZyouken(n: Int): Map[String, (String,String)] = {
    import DataTemplate._
    List.range(0,n).map(i => {
      val rule = templates_b(0)
      val subCount = rule._3
      val subs = getOntologyObject(subCount)
      val natStr = replaceSubs(replaceCc(replaceDet(rule._2)), subs)
      val termStr = rule._1 match {
        case term: Term => processTerm(term, subs)
        case list: List[Term] => {
          (list.foldLeft(""){(str, term) => str + "| " + processTerm(term, subs)}).tail
        }
      }
      ("b%d".format(i),(natStr, termStr))
    }).toMap
  }

  def ruleWeights(list: List[Int]): List[Int] = {
    val w0 = 5
    val w1 = 100
    val w2 = 1000
    var acc = 0
    list.map(n => {
      if (n == 0) {acc += w0; acc}
      else if (n == 1) {acc += w1; acc}
      else {acc += w2; acc}
    })
  }

  def searchRuleIdx(weights: List[Int], i: Int): Int = {
    weights.zipWithIndex.foreach((w,idx) => {
      if (i < w) return idx
    })
    return weights.size-1
  }

  def normalize(str: String): String = {
    str.replace("  ", " ")
  }
}
