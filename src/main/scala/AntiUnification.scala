import Base._
import Contree._
import Deptree._
import Terms._

object AntiUnification {

  type Substitution = List[(TermVariable, List[Term]|HedgeVariable)]
  type HSubstitution = List[(HedgeVariable, List[Term]|HedgeVariable)]
  
  def contreeAU(treeList: List[Contree]): Term = {
    val functions: List[Term] = treeList.map(tree => contreeToTerm(tree))
    antiUnification(functions)
  }
  def deptreeAU(treeList: List[DNode]): Term = {
    val functions: List[Term] = treeList.map(tree => deptreeToTerm(tree))
    antiUnification(functions)
  }
  
  def antiUnification(terms: List[Term]): Term = {
    uniqueId = 0
    val (term, sub, sub_h) = auTheta(terms, List(), List())
    // txtOut.println(displaySubsutitute(sub))
    // txtOut.println(displaySubsutitute_h(sub_h))
    term
  }

  def contreeToTerm(tree: Contree): Function = {
    tree match {
      case Node(l, children) => {
        Function(l, children.map(l => contreeToTerm(l)) )
      }
      case Leaf(l, word) => {
        Function(l, List(Function(word, List())) )
      }
    }
  }
  def deptreeToTerm(deptree: DNode): Function = {
    deptree match {
      case DNode(l, children) => {
        Function(l, children.map((dt, e) => Function(e, List(deptreeToTerm(dt)) )) )
      }
    }
  }

  var uniqueId: Int = 0

  def auTheta(terms: List[Term], substitution: Substitution, substitution_h: HSubstitution): (Term, Substitution, HSubstitution) = {
    isAllSame(terms) match {
      case Some(term) => (term, substitution, substitution_h) // pattern(7)
      case None => {
        isAllSameFunction(terms) match {
          case Some((symbol, arity, args)) => { // pattern(8)
            if (symbol == "NP") { // NPの特殊処理
              (Function(symbol, List(createFreshVariable())), substitution, substitution_h)
            } else {
              var sub = substitution
              var sub_h = substitution_h
              val auedChildren: List[Term] = args.transpose.map(tlist => {
                val (s, newsub, newsub_h) = auTheta(tlist, sub, sub_h)
                sub = newsub
                sub_h = newsub_h
                s
              })
              (Function(symbol, auedChildren), sub, sub_h)
            }
          }
          case None => {
            isSameFunction(terms) match { // HedgeVariable
              case Some(symbol) => {
                val freshVariable = createFreshHedgeVariable()
                val newSubstitution_h = substitution_h :+ (freshVariable, terms)
                (Function(symbol, freshVariable), substitution, newSubstitution_h)
              }
              case None => {
                isExistBinding(terms, substitution) match {
                  case Some(v) => { // pattern(9)
                    (v, substitution, substitution_h)
                  }
                  case None => { // pattern(10)
                    val frechVariable = createFreshVariable()
                    val newSubstitution: Substitution = substitution :+ (frechVariable, terms)
                    (frechVariable, newSubstitution, substitution_h)
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  /** 補助関数 */
  def isAllSame(terms: List[Term]): Option[Term] = {
    terms match {
      case Nil => customError("no term input")
      case head :: rst => {
        rst.foreach(t => { if(head != t) return None })
        Some(head)
      }
    }
  }

  def isSameFunction(terms: List[Term]): Option[Symbol] = {
    terms match {
      case Nil => customError("no term input")
      case Function(symbol, _) :: rst => {
        rst.foreach(t => {
          t match {
            case Function(s, _) => if (symbol != s) return None
            case _ => customError("term variable was included in input terms")
          }
        })
        Some(symbol)
      }
      case _ => customError("term variable was included in input terms")
    }
  }

  def isAllSameFunction(terms: List[Term]): Option[(Symbol, Int, List[List[Term]])] = {
    terms match {
      case Nil => error()
      case Function(symbol, arg) :: rst => {
        arg match {
          case _: HedgeVariable => customError("hedge variable was included in input terms")
          case arg1: List[Term] => {
            val args: List[List[Term]] = rst.map(t => {
              t match {
                case Function(s, a) => {
                  a match {
                    case _: HedgeVariable => customError("hedge variable was included in input terms")
                    case a1: List[Term] => {
                      if (s != symbol || a1.length != arg1.length) return None
                      else a1
                    }
                  }
                }
                case _ => customError("term variable was included in input terms")
              }
            })
            Some((symbol, arg1.length, arg1 :: args))
          }
        }
        
      }
      case _ => customError("term variable was included in input terms")
    }
  }

  def isExistBinding(terms: List[Term], substitution: Substitution): Option[TermVariable] = {
    substitution.foreach(b => {
      if (b._2 == terms) return Some(b._1)
    })
    return None
  }

  def createFreshVariable(): TermVariable = {
    val freshVariable = "z" + uniqueId
    uniqueId += 1
    TermVariable(freshVariable)
  }

  def createFreshHedgeVariable(): HedgeVariable = {
    val freshVariable = "X" + uniqueId
    uniqueId += 1
    HedgeVariable(freshVariable)
  }

   //
}
