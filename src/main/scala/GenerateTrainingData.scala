import Main._
import Terms._
import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import scala.util.Random
import scala.util.matching.Regex
import Convert._
import scala.compiletime.ops.string

object GenerateTrainingData {
  val normalDataNum_ev = 1000
  val ifDataNum_ev = 1000
  val multiDataNum_ev = 1000

  val normalDataNum = normalDataNum_ev//25000 //25000 simple:40000 if:30000
  val ifDataNum = ifDataNum_ev//15000 //15000
  val multiDataNum = multiDataNum_ev//10000

  val traingDataFile = "src/trainingData.txt"

  def getOntologyObject_(xlist: List[String]): Map[String, List[String]] = {getOntologyObjectWithIdx(xlist)}

  // データを生成する
  def generateData() = {
    val dataOut = new PrintWriter(traingDataFile)
    // NLP結果読み込み
    val states = load_nlp_file("src/input/nlpOut_depnp_a.txt")
    // ontologyつくる
    Ontology.makeOntology()

    // val pairs = generateVpTerm(normalDataNum)
    // pairs.foreach(m => {
    //   val natStr = m._2._1
    //   val termStr = m._2._2
    //   dataOut.println("%s\t%s".format(natStr, termStr))
    // })
    // generateData_if(dataOut)
    generateData_multi(dataOut)
    dataOut.close()
  }

  // json形式での出力
  var jsonid = 1
  def generateData_json() = {
    val dataOut = new PrintWriter("src/trainingData.json")
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
    for (i <- 1 to normalDataNum) {
      val randomValue = random.nextInt(randomMax)
      val ruleidx = searchRuleIdx(weights, randomValue)
      // if (ruleidx == 10) println(randomValue)
      val rule = templates(ruleidx)
      // val subCount = rule._3
      val subs = rule._1 match {
        case term: Term => getOntologyObject(getAllTermVariables(term))
        case list: List[Term] => getOntologyObject(list.flatMap(getAllTermVariables(_)))
      }
      val natStr = replaceSubs(replaceCc(replaceDet(rule._2)), subs).replace("\"","\\\"")
      val termStr = rule._1 match {
        case term: Term => processTerm_prefix(term, subs).replace("\"","\\\"").replace("("," ").replace(")"," ").replace(","," ").replace("  "," ").replace("  "," ")
        case list: List[Term] => {
          var str = processTerm_prefix(list.head, subs).replace("\"","\\\"").replace("("," ").replace(")"," ").replace(","," ").replace("  "," ").replace("  "," ")
          list.tail.foreach(t => {
            str = "+ " + str
            str = str +" "+ processTerm_prefix(t, subs).replace("\"","\\\"").replace("("," ").replace(")"," ").replace(","," ").replace("  "," ").replace("  "," ")
          })
          str
        }
      }
      // println(termStr)
      dataOut.println("{\n\t\"id\":\"%d\",\n\t\"input\":\"%s\",\n\t\"output\":\"%s\"\n}".format(jsonid,natStr.replace("  "," ").trim(), termStr.replace("  "," ").trim()))
      jsonid += 1
    }
    // generateData_if(dataOut)
    // generateData_multi(dataOut)
    dataOut.close()
  }

  // 複数文のデータセットの生成
  def generateData_multi(dataOut: PrintWriter) = {
    import DataTemplate._
    val random = new Random
    // 引数の個数に関して確率の重みをつける(引数が多いほど出やすくなる)
    val weights = ruleWeights(templates_multi.map(t => t._3))
    val randomMax = weights.last
    for (i <- 1 to multiDataNum) {
      val randomValue = random.nextInt(randomMax)
      val ruleidx = searchRuleIdx(weights, randomValue)
      // if (ruleidx == 10) println(randomValue)
      val rule = templates_multi(ruleidx)
      val termCount = rule._3
      val subs = generateVpTerm(termCount)
      val natStr = replaceConj(replaceSubs(rule._2, subs.map(m => (m._1, List(m._2._1))).toMap))
      val termStr =  normalize((rule._1.foldLeft(""){(str, term) => str + "| " + processTerm(term, subs.map(m => (m._1, List(m._2._2))).toMap)}).tail)
      dataOut.println("%s\t%s".format(natStr, termStr))
    }
  }

  // if文のデータセットの生成
  def generateData_if(dataOut: PrintWriter) = {
    import DataTemplate._
    val random = new Random
    // 引数の個数に関して確率の重みをつける(引数が多いほど出やすくなる)
    val weights = ruleWeights(templates_if.map(t => t._3))
    val randomMax = weights.last
    for (i <- 1 to ifDataNum) {
      val randomValue = random.nextInt(randomMax)
      val ruleidx = searchRuleIdx(weights, randomValue)
      // if (ruleidx == 10) println(randomValue)
      val rule = templates_if(ruleidx)
      val termCount = rule._3
      val bCount = rule._4
      val subs = generateVpTerm(termCount) ++ generateZyouken(bCount)
      val natStr = replaceSubs(rule._2, subs.map(m => (m._1, List(m._2._1))).toMap)
      val termStr =  normalize(processTerm(rule._1, subs.map(m => (m._1, List(m._2._2))).toMap))
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

  def processTerm(term: Term, subs: Map[String, List[String]]): String = {
    var s = displayTerm(term)
    subs.foreach{case(k,v) => {
      s = s.replace(k, v.mkString(" . "))
    }}
    s.replace("(", " ( ").replace(")", " ) ").replace(",", " , ")
  }

  def processTerm_prefix(term: Term, subs: Map[String, List[String]]): String = {
    var s = displayTerm(term)
    subs.foreach{case(k,v) => {
      var str = v.head
      v.tail.foreach(str2 => {
        str = ". " + str
        str = str + " "+ str2
      })
      s = s.replace(k, str)
    }}
    
    val vnum = getAllTermVariables(term).size
    if (vnum < 2) {
      val n = 2 - vnum
      s += " _0_"*n
    }
    // s = s.replace("(", " ( ").replace(")", " ) ").replace(",", " , ")
    s.trim()
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
      val i = random.nextInt(3)
      s = s.replaceFirst("<cc>", ccs(i))
    }
    s
  }

  def replaceConj(str: String): String = {
    val ccs = Map(0 -> ",", 1 -> ",", 2 -> "and")
    val random = new Random
    var s = str
    while (s.contains("<conj>")) {
      val i = random.nextInt(3)
      s = s.replaceFirst("<conj>", ccs(i))
    }
    s
  }

  // String内の<z0> を ontology's object string　に置き換える
  def replaceSubs(str: String, subs: Map[String, List[String]]): String = {
    var s = str
    subs.foreach{case(k,v) => {
      var v1 = v.mkString(" . ")
      // 参照関係の処理([_]を消す)
      v1 = v1.replace(" [_]", "")
      if (!(v1.endsWith("state") && !v1.contains("return_state"))) v1 = v1.replace("_", " ")
      v1 = v1.replace("_state", " state")
      // 所有関係ある場合の処理 " . " をランダムに "'s " or " of " or " "　に置き換え
      if (v1.contains(" . ")) {
        val random = new Random
        val r = random.nextInt(11)
        if (r <= 1) v1 = v1.replace(" . ", " 's ")
        // else if (r <= 2) v1 = v1.replace(" . ", "s ")
        else if (r <= 2) {
          val list = v1.split(" . ")
          v1 = list(1) + " of " + list(0)
        }
        else v1 = v1.replace(" . ", " ")
      }
      // <z0> を v1に置き換え
      val k2 = k match {
        case s"${z}:${s}" => z
        case _ => k
      }
      s = s.replace("<%s>".format(k2), v1)
    }}
    s
  }

  def getOntologyObject(xlist: List[String]): Map[String, List[String]] = {
    val random = new Random
    xlist.map(x => {
      var i = 0
      var min: Int = 0
      var max: Int = Ontology.ontology.i - 1
      x match {
        case s"z${num}:${str}" => {
          i = num.toInt
          val stri = OntologyMatch.searchIndex(str)
          val (min2, max2) = Ontology.getSubtreeIndexRange(stri)
          min = min2
          max = max2
        }
        case s"z${num}" => {
          i = num.toInt
        }
        case _ =>
      }

      val obji = min + random.nextInt(max-min)
      var objs = List(OntologyMatch.searchValue(obji)) 

      OntologyMatch.getRandomAttribute(obji) match {
        case None => 
        case Some(atti) => {
          objs :+= OntologyMatch.searchValue(atti) 
        }
      }
      (x.format(i),objs)
    }).toMap
  }

  def getOntologyObjectWithIdx(xlist: List[String]): Map[String, List[String]] = {
    val random = new Random
    xlist.map(x => {
      var i = 0
      var min: Int = 0
      var max: Int = Ontology.ontology.i - 1
      x match {
        case s"z${num}:${str}" => {
          i = num.toInt
          val stri = OntologyMatch.searchIndex(str)
          val (min2, max2) = Ontology.getSubtreeIndexRange(stri)
          min = min2
          max = max2
        }
        case s"z${num}" => {
          i = num.toInt
        }
        case _ =>
      }
      val obji = min + random.nextInt(max-min)
      var objs: List[String] = List() 

      OntologyMatch.getRandomAttribute(obji) match {
        case None => objs :+= (OntologyMatch.searchValue(obji) + getIndex(2))
        case Some(atti) => {
          objs :+= (OntologyMatch.searchValue(obji) + getIndex(5))
          objs :+= (OntologyMatch.searchValue(atti))
        }
      }
      (x.format(i), objs)
    }).toMap
  }

  def getIndex(n: Int): String = {
    if (n==0) return " [_]"
    val random = new Random
    val r = random.nextInt(50)
    if (r <= 0) " [3]"
    else if (r <= 1) " [2]"
    else if (r <= 3) " [1]"
    else if (r <= 4*n) " [0]"
    else " [_]"
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
      val rule = templates(ruleidx)
      // 引数の個数文オントロジーの単語を採ってくる
      // return Map[String, List[String]]  e.g. Map("z0" -> List("character_token", "data"))
      val subs = rule._1 match {
        case term: Term => getOntologyObject_(getAllTermVariables(term))
        case list: List[Term] => getOntologyObject_(list.flatMap(getAllTermVariables(_)))
      }
      // 自然言語の文にオントロジーの単語を代入する
      val natStr = replaceSubs(replaceCc(replaceDet(rule._2)), subs)
      // 機械語の文にオントロジーの単語を代入する
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
      // val subCount = rule._3
      val subs = getOntologyObject_(getAllTermVariables(rule._1))
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

  // テンプレートを自動で作る
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
}
