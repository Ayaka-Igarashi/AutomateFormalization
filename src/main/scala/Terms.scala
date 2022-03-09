import Base._

object Terms {
  type Symbol = String
  case class HedgeVariable(x: String)
  trait Term
  case class TermVariable(x: String) extends Term
  case class Function(f: Symbol, args: Hedge) extends Term
  type Hedge = List[Term]|HedgeVariable
  
  def substitution(term: Term, sub: Map[String, Term|List[Term]]): Term = {
    term match {
      case TermVariable(x) => {
        sub(x) match {
          case t: Term => t
          case h: List[Term] => {
            Function("", h)
            // println("error1: " + x + " " + sub(x))
            // error()
          }
        }
      }
      case Function(s, child) => {
        child match {
          case HedgeVariable(x) => {
            sub(x) match {
              case h: List[Term] => Function(s, h)
              case _ => {
                println("error2: " + x + " " + sub(x))
                error()
              }
            }
          }
          case Nil => term
          case lst: List[Term] => {
            val subd = lst.map(t => {
              substitution(t, sub)
            })
            Function(s, subd)
          }
        }
      }
    }
  }
  
  // return bool
  // term2がterm1にマッチするか
  def isMatch(term1: Term, term2: Term): Boolean = {
      (term1, term2) match {
          case (Function(s1, c1), Function(s2, c2)) => {
            if (s1 != s2) false
            else {
              c1 match {
                case HedgeVariable(x) => true
                case l1: List[Term] => {
                  c2 match {
                    case l2: List[Term] => {
                      if (l1.length != l2.length) false
                      else {
                          var b = true
                          (l1 zip l2).foreach(a => {
                              if (!isMatch(a._1,a._2)) b = false
                          })
                          b
                      }
                    }
                    case _ => false
                  }
                }
              }
              // (c1, c2) match {
              //   // case (HedgeVariable(x), l2) => {
              //   //     true
              //   // }
              //   case (l1, l2): (List[Term], List[Term]) => {
                    
              //   }
              //   case _ => false
              // }
            }
          }
          case (TermVariable(x), Function(s2, lst2)) => true
          case (TermVariable(x), TermVariable(y)) => true
          case _ => false
      }
  }

  def displayTerm(term: Term): String = {
    term match {
      case TermVariable(x) => x
      case Function(f, args) => {
        var string = displaySymbol(f)
        string += displayHedge(args)
        string
      }
    }
  }
  
  def displayHedge(hedge: Hedge): String = {
    var string = ""
    hedge match {
      case HedgeVariable(hv) => {
        string += "(" + hv + ")"
      }
      case terms: List[Term] => {
        if (terms.length != 0) {
            string += "("
            for (t <- terms) {
                string += displayTerm(t)
                string += ","
            }
            if (string.endsWith(",")) string = string.substring(0, string.length - 1)
            string += ")"
        }
      }
    }
    string
  }

  def displaySymbol(symbol: Symbol): String = {
    symbol
  }

  def getFirstLeaf(term: Term): String = {
    term match{
      case Function(s, Nil) => s
      case Function(s, child) => {
        child match {
          case list: List[Term] => getFirstLeaf(list.head)
          case _ => ""
        }
      }
      case _ => ""
    }
  }
}