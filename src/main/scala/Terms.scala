import Base._

object Terms {
  type Symbol = String
  case class HedgeVariable(x: String)
  trait Term
  case class TermVariable(x: String) extends Term
  case class Function(f: Symbol, args: Hedge) extends Term
  type Hedge = List[Term]|HedgeVariable

  case class NoValTerm(f: Symbol, args: List[NoValTerm])

  case class TermEx(f: Symbol, args: List[TermEx], ex: Ex)
  type Ex = Int

  def noValTermToTerm(nvTerm: NoValTerm): Term = {
    nvTerm match {
      case NoValTerm(s, list) => {
        return Function(s, list.map(noValTermToTerm(_)))
      }
    }
  }

  def termToNoValTerm(term: Term): NoValTerm = {
    term match {
      case TermVariable(x) => NoValTerm(x, Nil)
      case Function(f, args) => {
        args match {
          case HedgeVariable(h) => NoValTerm(f, List(NoValTerm(h, Nil)))
          case list: List[Term] => NoValTerm(f, list.map(termToNoValTerm(_)))
        }
      }
    }
  }
  
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

  def isMatchSome(term: Term, termlist: List[Term]): Boolean = {
    termlist.foreach(t => {
      if(isMatch(t, term)) return true
    })
    false
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

  def generateCode(term: Term): String = {
    term match {
      case TermVariable(x) => "TermVariable(\"" + x + "\")"
      case Function(f, args) => {
        var string = "Function(\"" + escapeChars(f) + "\","
        string += generateCode_hedge(args) + ")"
        string
      }
    }
  }

  def generateCode_hedge(hedge: Hedge): String = {
    hedge match {
      case HedgeVariable(hv) => "HedgeVariable(\"" + hv + "\")"
      case terms: List[Term] => {
        var string = "List("
        for (t <- terms) {
            string += generateCode(t)
            string += ","
        }
        if (string.endsWith(",")) string = string.substring(0, string.length - 1)
        string += ")"
        string
      }
    }
  }
  
  def escapeChars(str: String): String = {
    str.replace("\\", "\\\\").replace("\"", "\\\"")
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

  def getFirstLeaf_nv(term: NoValTerm): String = {
    term match{
      case NoValTerm(s, Nil) => s
      case NoValTerm(s, list) => getFirstLeaf_nv(list.head)
    }
  }

  def getFirstLeafandTag_nv(term: NoValTerm, parent: String): (String,String) = {
    term match{
      case NoValTerm(s, Nil) => (parent, s)
      case NoValTerm(s, list) => getFirstLeafandTag_nv(list.head, s)
    }
  }

  // "x" and S(NP(x)) => return NP
  def getParentNode(v: String, term: Term): String = {
    getParentNode_sub("", v, term) match {
      case Some(s) => s
      case None => ""
    }
  }
  
  def getParentNode_sub(p: String, v: String, term: Term): Option[String] = {
    term match {
      case TermVariable(x) => {
        if (x == v) return Some(p)
        else None
      }
      case Function(s, child) => {
        child match {
          case HedgeVariable(x) =>{
            if (x == v) return Some(s)
            else None
          }
          case list: List[Term] => {
            list.foreach(t => {
              getParentNode_sub(s, v, t) match {
                case Some(sym) => return Some(sym)
                case None =>
              }
            })
            return None
          }
        }
      } 
    }
  }

  def getAllTermVariables(term: Term): List[String] = {
    term match {
      case Function(_, list) => {
        list match {
          case list: List[Term] => list.flatMap(getAllTermVariables(_))
          case _ => Nil
        }
      }
      case TermVariable(x) => List(x)
    }
  }
}