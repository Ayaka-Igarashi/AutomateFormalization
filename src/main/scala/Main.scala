object Main {
  import OutputParse._
  import AntiUnification._
  import Terms._
  import Contree._
  import Deptree._
  import ConstructDeptree._
  import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
  import Convert._
  import ExtractRule._
  import StateStructure._

  def main() = {
    // htmlParse()
    
    // println("> parse_start")
    // // 自然言語処理する
    // val states = states_r.map(state => {
    //   val prev = nlp(state._2)
    //   val trans = state._3.map(s => {
    //     (s._1, nlp(s._2))
    //   })
    //   (state._1, prev, trans)
    // })
    
    // NLP結果読み込み
    var outputline = load_file("src/nlpOut.txt")
    var o = outputline.head
    outputline = outputline.tail
    var states: List[(String, List[Contree], List[(String, List[Contree])])] = Nil
    while (o.startsWith("name:")) {
      val statename = o.substring(5, o.length)
      o = outputline.tail.head
      outputline = outputline.tail.tail
      var prev: List[Contree] = List()
      while (o != "trans:") {
        // println(o)
        prev :+= parse_synconst(o)
        o = outputline.head
        outputline = outputline.tail
      }
      var trans: List[(String, List[Contree])] = List()
      while (o != "") {
        o = outputline.head
        outputline = outputline.tail
        val char = o.substring(5, o.length)
        var trees: List[Contree] = List()
        o = outputline.head
        outputline = outputline.tail
        while (!o.startsWith("char:") && o != "") {
          // println(o)
          trees :+= parse_synconst(o)
          o = outputline.head
          outputline = outputline.tail
        }
        trans :+= (char, trees)
        outputline = o::outputline
      }
      states :+= (statename, prev, trans)
      if (outputline.tail != Nil) {
        o = outputline.tail.head
        outputline = outputline.tail.tail
      }
    }

    println("> generating_rules")
    // 文章全部取り出してAntiunificationで規則生成
    val allStatement = states.flatMap(s => {List(s._2)++s._3.map(t => t._2)}).flatten
    // val nprules = npAUnif(allStatement)
    val rules = generateRules(allStatement)
    val rulesOut = new PrintWriter("src/rulesOut.txt")
    rules.foreach(r => rulesOut.println(displayTerm(r)))
    // rulesOut.println("")
    // nprules.foreach(r => rulesOut.println(displayTerm(r)))
    rulesOut.close()
    
    // コマンドに変換
    val state_c = states.map(state => {
      var prev = convertTerms(state._2.map(s =>contreeToTerm(removePeriod(toLowerFirstChar(s)))), rules)
      prev = prev.map(convertNorm(_))
      val trans = state._3.map(s => {
        var conved = convertTerms(s._2.map(s => contreeToTerm(removePeriod(toLowerFirstChar(s)))), rules)
        conved = conved.map(convertNorm(_))
        (s._1, conved)
      })
      (state._1, prev, trans)
    })
    
    // output
    val convertOut = new PrintWriter("src/convertOut.txt")
    state_c.foreach(state => {
      convertOut.println("--" + state._1 + "--")
      convertOut.println("prev: ")
      state._2.foreach(t => convertOut.println(" " + displayTerm(t)))
      convertOut.println("trans: ")
      state._3.foreach(s => {
        convertOut.println(" " + s._1 + " :")
        s._2.foreach(t => convertOut.println("  " + displayTerm(t)))
      })
      convertOut.println("")
    })
    convertOut.close
    
  }
  
  def htmlParse() = {
    // HTMLをパースする
    val stateList = ParseHtml.parseHtml()
    val replaceStates = ParseHtml.getReplaceStates()
    
    // 状態名の置き換え
    val states_r = stateList.map(state => {
      val prev = replaceState(state.prev, replaceStates)
      val trans = state.trans.map(s => {
        (s.character, replaceState(s.process, replaceStates))
      })
      (state.name, prev, trans)
    })
    
    // 出力
    val htmlParseOut = new PrintWriter("src/htmlParseOut.txt")
    states_r.foreach(state => {
      htmlParseOut.println("#name:" + state._1)
      htmlParseOut.println("#prev:")
      htmlParseOut.println(state._2)
      htmlParseOut.println("#trans:")
      state._3.foreach(s => {
        htmlParseOut.println("#char:" + s._1)
        htmlParseOut.println(s._2)
      })
      htmlParseOut.println("#")
    })
    htmlParseOut.close
  }
  
  def replaceState(s: String, replace: String): String = {
    val re = replace.r
    re.replaceAllIn(s, m => {
      val m2 = m.toString().replace(" ", "_").replace("-","_")
        .replace("(", "").replace(")", "")
      m2(0).toUpper + m2.tail
    })
  }
  def nlp(s: String): List[Contree] = {
    List()
  }
  
  def auTest() = {
    val contree = parseOnlySynconst("src/output_const.txt")
    generateRules(contree)
  }
  
  def npAUnif(contree: List[Contree]): List[Term] = {
    val antiUnificationOut: PrintWriter = new PrintWriter("src/NPantiUnificaationOut.txt")
    val out = new PrintWriter("src/NPout.txt")
    
    var nptrees = extractPaticularPhrase(contree,"NP")
    nptrees = nptrees.map(t => (removeDT_(t)))
    // val dividedTrees = divideByVerb(replaced)
    val n = 2
    // var matchlist_note: List[List[String]] = List()
    var rules: List[Term] = Nil
    // antiUnificationの結果リストを計算
    val aulist = antiUnification_shuffle(nptrees.map(contreeToTerm(_)), n, antiUnificationOut)
    val (candidates, mlist) = extractCandidates(nptrees.map(contreeToTerm(_)), aulist, out)
      // matchlist_note :+= mlist
      
      // println("--" + v +"--")
      // extractRules(candidates).foreach(r => println(displayTerm(toCommand(r))))
    rules ++= extractRules(candidates)
    
    
    antiUnificationOut.close
    out.close
    log1.close
    
    rules
  }
  
  // auVTerms
  def generateRules(contree: List[Contree]): List[Term] = {
    val antiUnificationOut: PrintWriter = new PrintWriter("src/antiUnificaationOut.txt")
    val out = new PrintWriter("src/out.txt")
    val npout = new PrintWriter("src/npout.txt")
    
    val vptrees = extractVPTree(contree)
    val replaced = vptrees.map(t => (toLowerFirstChar(t)))
    val dividedTrees = divideByVerb(replaced)
    val n = 3
    var matchlist_note: List[List[String]] = List()
    var rules: List[Term] = Nil
    dividedTrees.foreach { case (v, treelist) => {
      antiUnificationOut.println("\n----- " + v + " ------")
      // antiUnificationの結果リストを計算
      val aulist = antiUnification_shuffle(treelist.map(contreeToTerm(_)), n, antiUnificationOut)
      out.println("\n----- " + v + " ------")
      val (candidates, mlist) = extractCandidates(treelist.map(contreeToTerm(_)), aulist, out)
      
      matchlist_note :+= mlist
      
      val nprules = npAUnif(treelist)
      npout.println("\n----- " + v + " ------")
      nprules.foreach(r => npout.println(displayTerm(r)))
      // println("--" + v +"--")
      // extractRules(candidates).foreach(r => println(displayTerm(toCommand(r))))
      rules ++= extractRules(candidates)
    }}
    
    antiUnificationOut.close
    out.close
    npout.close
    log1.close
    
    rules
  }
  
  // auSterms
  def stermConvertTest() = {
    val sTermOut: PrintWriter = new PrintWriter("src/STermOut.txt")
    val contree = parseOnlySynconst("src/output_const.txt")
    val strees = extractPaticularPhrase(contree,"S")
    val replaced = strees.map(t => removePeriod(toLowerFirstChar(t)))
    val sterms = replaced.flatMap(tree => {
      val c = toCommandIf(contreeToTerm(tree))
      c match {
        case Function("if_then", con::state::Nil) => {
          List(con)
        }
        case Function("otherwise", _) => Nil
        case _ => List(contreeToTerm(tree))
      }
      // sTermOut.println(displayTerm(c))
    })
    // sterms.foreach(s => sTermOut.println(displayTerm(s)))
    val n = 2
    val aulist = antiUnification_shuffle(sterms, n, sTermOut)
    val (candidates, mlist) = extractCandidates(sterms, aulist, sTermOut)
    extractRules(candidates).foreach(r => {
      sTermOut.println(displayTerm(r))
      sTermOut.println("com : "+displayTerm(toCommand(r)))
      })
    sTermOut.close
  }
  
  def auSTerms_old() = { 
    val antiUnificationOut: PrintWriter = new PrintWriter("src/STerm_antiUnificaationOut.txt")
    val contree = parseOnlySynconst("src/output_const.txt")
    val strees = extractPaticularPhrase(contree,"S")
    val replaced = strees.map(toLowerFirstChar(_))
    // val replaced = strees.map(removeVPPhrase(_))
    val dividedTrees = divideByWord(replaced)
    // val replaced = strees.map(toLowerFirstChar(_))
    val n = 2
    dividedTrees.foreach { case (v, treelist) => {
      if (v != "_other"){
      antiUnificationOut.println("\n----- " + v + " ------")
      var auset: Set[Term] = Set()
      var count = 1
      
      for (_ <- 1 to 4) {
        var i = 0
        val trees = scala.util.Random.shuffle(treelist)
        while (i+n < trees.size) {
          val tree = contreeAU(trees.slice(i,i+n))
          auset += tree
          i += n
          count+=1
        }
      }
      var candidates: List[(Term, (Int,Int))] = List()
      val aulist = auset.toList
      var counta = 1
      var mlist: List[String] = List()
      aulist.foreach(au => {
        antiUnificationOut.print(counta + ": ")
        counta += 1
        var matchCount = 0
        val com = toCommand(au)
        antiUnificationOut.println(displayTerm(au))
        antiUnificationOut.println(" ==> " + displayTerm(com))
        
        var outbuffer = ""
        outbuffer += displayTerm(au) + "\n ==> "  + displayTerm(com) + "\n\n"
        treelist.map(contreeToTerm(_)).foreach(tree => {
          outbuffer += displayTerm(tree) + "\n"
          conv(au, tree) match {
            case None => outbuffer += "not match\n"
            case Some(sub) => {
              outbuffer += displayTerm(Terms.substitution(com, sub)) + "\n"
              matchCount += 1
            }
          }
          outbuffer += "\n"
        })
        mlist :+= outbuffer
        antiUnificationOut.println("match : " + matchCount + "/" + treelist.size + "\n")
        candidates :+= (au, (matchCount,treelist.size))
      })
      println("--" + v +"--")
      extractRules(candidates).foreach(r => println(displayTerm(toCommand(r))))
      }
    }}
    
    antiUnificationOut.close()
    log1.close
  }
  
  def antiUnification_shuffle(treelist: List[Term], n: Int, out: PrintWriter): List[Term] = {
    var auset: Set[Term] = Set()
    var count = 1
    for (_ <- 1 to 2) {
      var i = 0
      val trees = scala.util.Random.shuffle(treelist)
      while (i+n < trees.size) {
        val tree = antiUnification(trees.slice(i,i+n))
        auset += tree
        // antiUnificationOut.println("                  :" + trees.slice(i,i+3))
        out.println(count+" : " + displayTerm(tree))
        out.println(" ==> " + displayTerm(toCommand(tree)))
        out.println()
        i += n
        count+=1
      }
    }
    auset.toList
  }
  def extractCandidates(treelist: List[Term], aulist: List[Term], out: PrintWriter): (List[(Term, (Int,Int))], List[String]) = {
    var candidates: List[(Term, (Int,Int))] = List()
    var count = 1
    
    var mlist: List[String] = List()
    aulist.foreach(au => {
      out.print(count + ": ")
      count += 1
      var matchCount = 0
      val com = toCommand(au)
      out.println(displayTerm(au))
      out.println(" ==> " + displayTerm(com))
      
      var outbuffer = ""
      outbuffer += displayTerm(au) + "\n ==> "  + displayTerm(com) + "\n\n"
      treelist.foreach(tree => {
        outbuffer += displayTerm(tree) + "\n"
        conv(au, tree) match {
          case None => outbuffer += "not match\n"
          case Some(sub) => {
            outbuffer += displayTerm(Terms.substitution(com, sub)) + "\n"
            matchCount += 1
          }
        }
        outbuffer += "\n"
      })
      mlist :+= outbuffer
      out.println("match : " + matchCount + "/" + treelist.size + "\n")
      candidates :+= (au, (matchCount,treelist.size))
    })
    (candidates, mlist)
  }
  // 没
  def extractByHand(dividedTrees: Map[String, List[Contree]], matchlist_note: List[List[String]]) = {
    val matchOut = new PrintWriter("src/matchout.txt")
    (dividedTrees zip matchlist_note).foreach { case ((v, _), l) => {
      print(v + ">")
      val input = io.StdIn.readLine()
      println(input)
      input.split(' ').foreach(s => {
        s.toIntOption match {
          case Some(n) => {
            matchOut.println("\n----- " + v + " ------")
            if (n > 0 && n <= l.size) matchOut.println(l(n-1))
            else println("out of range")
          }
          case None => {
            println("not integer")
          }
        }
      })
      // input.toIntOption
    }}
    matchOut.close
  }
  
  def auDeptree() = {
    val antiUnificationOut: PrintWriter = new PrintWriter("src/antiUnificaationOut_dep.txt")
    // val depOut: PrintWriter = new PrintWriter("src/deptree.txt")
    val deptrees = constructDeptreeList()
    // deptrees.foreach(t => depOut.println(displayTerm(deptreeToTerm(t))))
    // depOut.close
    val dividedTrees = divideByVerb_dep(deptrees)
    dividedTrees.foreach { case (v, trees) => {
      antiUnificationOut.println("\n----- " + v + " ------")
      var i = 0
      while (i+3 < trees.size) {
        val tree = deptreeAU(trees.slice(i,i+3))
        antiUnificationOut.println(displayTerm(tree))
        i += 3
      }
      if (i+1 < trees.size) {
        val tree = deptreeAU(trees.slice(i,trees.size))
        antiUnificationOut.println(displayTerm(tree))
      }
    }}
    antiUnificationOut.close
  }
  
  // antiunificationの前処理、文頭を小文字にする
  def toLowerFirstChar(tree: Contree): Contree = {
    tree match {
      case Node(l, list) => {
          if (list == Nil) tree
          else Node(l, toLowerFirstChar(list.head) +: list.tail)
      }
      case Leaf(l, word) => {
        if (word != "") Leaf(l, word.head.toLower + word.tail)
        else tree
      }
    }
  }
  // antiunificationの前処理
  def removePeriod(tree: Contree): Contree = {
    tree match {
      case Node(l, list) => {
          if (list == Nil) tree
          else if (list.last == Leaf(".", ".")) Node(l, list.take(list.size-1))
          else Node(l, list.take(list.size-1) :+ removePeriod(list.last))
      }
      case Leaf(l, word) => {
        tree
      }
    }
  }
  
  def removeDT_(tree: Contree): Contree = {
    tree match {
      case Node(l, list) => {
        Node(l, list.flatMap(t => {
          t match {
            case Node(cl, clist) => List(removeDT_(t))//List(Node(cl, clist.map(removeDT(_))))
            case Leaf("DT", _) => List()
            case Leaf(_, _) => List(t)
          }
        }))
      }
      case Leaf(l, word) => {
        tree
      }
    }
  }
  
  // VP節のみ取り出す
  def extractVPTree(trees: List[Contree]): List[Contree] = {
    var vptrees: List[Contree] = List()
    trees.foreach(t => {
      t match {
            case Node("VP", list) => {
              val smallvp = extractVPTree(list)
              if (smallvp.isEmpty) vptrees :+= t
              else vptrees ++= smallvp
            }
            case Node(_, list) => {
              vptrees ++= extractVPTree(list)
            }
            case Leaf(_, word) =>
      }
    })
    vptrees
  }
  
  // S節のみ取り出す
  def extractPaticularPhrase(trees: List[Contree], sym: String): List[Contree] = {
    var ptrees: List[Contree] = List()
    trees.foreach(t => {
      t match {
            case Node(s, list) if s==sym => {
              // val small = extractPaticularPhrase(list,sym)
              // if (small.isEmpty) ptrees :+= t
              // else ptrees ++= t+:small
              ptrees :+= t
            }
            case Node(_, list) => {
              ptrees ++= extractPaticularPhrase(list,sym)
            }
            case Leaf(_, word) =>
      }
    })
    ptrees
  }
  
  def removeVPPhrase(tree: Contree): Contree = {
    tree match {
      case Node("VP", _) => Node("VP", List())//if (getLeaves(tree))
      case Node(s,list) => Node(s, list.map(removeVPPhrase(_)))
      case Leaf(_,_) => tree
    }
  }
  
  var verbList: Set[String] = Set("set", "reconsume", "emit", "multiply", "consume", "start", "add", "ignore", "switch", "create", "flush", "treat", "append", "_other")

  // 動詞の種類ごとに分ける
  def divideByVerb(trees: List[Contree]): Map[String, List[Contree]] = {
    val emplist: List[List[Contree]] = (1 to verbList.size).toList.map(_ => List())
    var dividedTrees: Map[String, List[Contree]] = (verbList.toList zip emplist).toMap
    trees.foreach(t => {
      val v = getLeaves(t).head.toLowerCase()
      dividedTrees.get(v) match {
        case Some(l) => dividedTrees = dividedTrees.updated(v, l :+ t)
        case None => dividedTrees = dividedTrees.updated("_other", dividedTrees.getOrElse("_other", List()) :+ t)
      }
    })
    dividedTrees
  }
  
  // Stag 種類ごとに分ける
  def divideByWord(trees: List[Contree]): Map[String, List[Contree]] = {
    // println(trees)
    val dividelist: Set[String] = Set("this", "if", "otherwise")
    val emplist: List[List[Contree]] = (1 to dividelist.size).toList.map(_ => List())
    var dividedTrees: Map[String, List[Contree]] = (dividelist.toList zip emplist).toMap
    trees.foreach(t => {
      val v = getLeaves(t).head.toLowerCase()
      dividedTrees.get(v) match {
        case Some(l) => dividedTrees = dividedTrees.updated(v, l :+ t)
        case None => dividedTrees = dividedTrees.updated("_other", dividedTrees.getOrElse("_other", List()) :+ t)
      }
    })
    dividedTrees
  }
  
  def divideByVerb_dep(trees: List[DNode]): Map[String, List[DNode]] = {
    val emplist: List[List[DNode]] = (1 to verbList.size).toList.map(_ => List())
    var dividedTrees: Map[String, List[DNode]] = (verbList.toList zip emplist).toMap
    trees.foreach(t => {
      val v = t match {
        case DNode(l, child) => l.toLowerCase()
      }
      dividedTrees.get(v) match {
        case Some(l) => dividedTrees = dividedTrees.updated(v, l :+ t)
        case None => dividedTrees = dividedTrees.updated("_other", dividedTrees.getOrElse("_other", List()) :+ t)
      }
    })
    dividedTrees
  }
  
  // verblist 作る用
  def searchVerb(trees: List[Contree]): Set[String] = {
    var list: Set[String] = Set()
    trees.foreach(t => {
      val v = getLeaves(t).head.toLowerCase()
      list += v
    })
    list
  }
  
}
