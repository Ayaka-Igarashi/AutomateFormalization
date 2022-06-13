object ExtractRule{
    import Terms._
    import java.io.{PrintWriter}
    
    val log1: PrintWriter = new PrintWriter("src/extractRule_LOG.txt")
    
    def extractRules(candidates: List[(Term, (Int,Int))]): Set[Term] = {
        log1.println("===========start============")
        var rules: Set[(Term, Int)] = Set()
        for (c <- candidates) {
            val term = c._1
            val matchCount = c._2._1
            
            log1.println("\n------------------")
            log1.println("rule: ")
            rules.foreach(r => log1.println(displayTerm(r._1)))
            log1.println("term: ")
            log1.println(displayTerm(term))
            log1.println("------------------")
            
            if (leafCount(term) < 1) {log1.println("leafCount == 0")}
            else {
                val children = extractChildren(term, rules)
                if (children != Set()) {
                    if (children.foldLeft(true)((x,y) => (y._2 < matchCount) & x)) {
                        rules --= children
                        rules += (term, matchCount)
                        log1.println("children: ")
                        children.foreach(r => log1.println(displayTerm(r._1)))
                    }
                    else log1.println("no change1")
                } else {
                    findParent(term, rules) match {
                        case Some(parent) => {
                            if (parent._2 <= matchCount) {
                                rules -= parent
                                rules += (term, matchCount)
                                log1.println("parent:")
                                log1.println(displayTerm(parent._1))
                            }
                            else log1.println("no change2")
                        }
                        case None => rules += (term, matchCount);log1.println("add")
                    }
                }
            }
        }
        log1.println("fin\n")
        rules.map(_._1)
    }

    def rulesSymbols(rules: List[Term]): List[String] = {
        rules.map(rule => {
            Convert.toCommand(rule) match {
                case Function(s, _) => s
                case _ => ""
            }
        })
    }
    
    def leafCount(term: Term): Int = {
        term match {
            case Function(word, child) => {
                child match {
                    case Nil => {
                        if (word == "to") 0 // 要修正
                        else 1
                    }
                    case t :: rst => (t +: rst).foldLeft(0)((x,y) => x+leafCount(y))
                    case HedgeVariable(_) => 0
                }
            }
            case TermVariable(_) => 0
        }
    }
    def extractChildren(parent: Term, terms: Set[(Term, Int)]):Set[(Term, Int)] = {
        terms.flatMap(t => {
            if (isAChildOfB(t._1, parent)) Set(t)
            else {val nil: Set[(Term, Int)] = Set();nil}
        })
    }
    def isAChildOfB(a: Term, b: Term): Boolean = {
        isMatch(b,a)
    }
    def findParent(child: Term, terms: Set[(Term, Int)]): Option[(Term, Int)] = {
        terms.foreach(t => {
            if (isAChildOfB(child, t._1)) return Some(t)
        })
        None
    }
    
    def extractRule_old(candidates: List[(Term, (Int,Int))]): List[Term] = {
        var max = 0
        var bestTerm: Term = null
        candidates.foreach {case (term, (matchnum, all)) => {
            val score = abstractionDegree(term)*(matchnum*100/all)/10
            // println(abstractionDegree(term))
            if (max < score) {
                max = score
                bestTerm = term
            }// else if (bestTerm == null) bestTerm = term
        }}
        List(bestTerm)
    }
    
    // low: high abstraction, high: concrete
    def abstractionDegree(term: Term): Int = {
        term match {
            case Function(_, child) => {
                child match {
                    case Nil => 1
                    case t :: rst => (t +: rst).foldLeft(1)((x,y) => x+abstractionDegree(y))
                    case HedgeVariable(_) => 0
                }
            }
            case TermVariable(_) => 0
        }
    }
}