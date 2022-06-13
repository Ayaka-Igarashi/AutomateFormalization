import Terms._

object ConvertNp {
  def convert(term: Term): List[String] = {
    var o = termToNoValTerm(term)
    o = remove_case(o)
    o = remove_det(o)
    var olist = and(o)
    var slist = olist.map(t => nmod(t))
    slist.map(s => {
      var oya = -1
      util(
      s.split('.').toList.map(a => {
        if (oya != -1) {
          val atti = OntologyMatch.findAttribute(oya, coref_kari(a))
          oya = atti
          OntologyMatch.searchValue(atti)
        }
        else{
          val ni = OntologyMatch.matching(coref_kari(a))
          oya = ni
          OntologyMatch.searchValue(ni)
        } 
      }), ".")
      // OntologyMatch.searchValue(OntologyMatch.matching(s))
    })
  }

  def coref_kari(s: String): String = {
    if (s == "its") "current_tag_token"
    else s
  }

  def nmod(term: NoValTerm): String = {
    term match {
      case NoValTerm(sym, list) => {
        var pos: Option[String] = None
        var withoutPos: List[NoValTerm] = Nil
        list.foreach(t => {
          t match {
            case NoValTerm(ts, tlist) => {
              if (ts.contains("nmod")) {
                tlist match {
                    case List(child) => {
                      pos = Some(toFlat(child))
                    }
                    case _ =>
                }
              } else {
                withoutPos :+= t
              }
            }
          }
        })
        pos match {
          case Some(s) => s+"."+toFlat(NoValTerm(sym, withoutPos))
          case None => toFlat(term)
        }
      }
    }
  }

  def and(term: NoValTerm): List[NoValTerm] = {
    term match {
      case NoValTerm(sym, list) => {
        var conj: List[NoValTerm] = Nil
        var withoutConj: List[NoValTerm] = Nil
        list.foreach(t => {
          t match {
            case NoValTerm(ts, tlist) => {
              if (ts.contains("conj")||ts.contains("appos")) {
                tlist match {
                  case List(child) => {
                    conj :+= (child)
                  }
                  case _ =>
                }
              } else {
                withoutConj :+= t
              }
            }
          }
        })
        List(NoValTerm(sym, withoutConj)) ++ conj.map(t => {
          t match {
            case NoValTerm(s, child) => {
              if (sym == s) {
                remove_cc(NoValTerm(s, child))
              } else {
                remove_cc(NoValTerm(s, child)) match {
                  case NoValTerm(s2, child2) => {
                    NoValTerm(s2, child2++withoutConj)
                  }
                }
              }
            }
          }
        })
        // conj match {
        //   case (NoValTerm(s, child)) :: rst => {
            
            
        //   }
        //   case Nil => List(term)
        // }
      }
    }
  }

  def toFlat(term: NoValTerm): String = {
    term match {
      case NoValTerm(f, args) => {
        var str = f
        args.foreach(t => {
          str += "_" + skipLabel(t)
        })
        return str
      }
    }
  }
  def skipLabel(term: NoValTerm): String = {
    term match {
      case NoValTerm(f, List(child)) => {
        toFlat(child)
      }
      case _ => ""
    }
  }

  def remove_cc(term: NoValTerm): NoValTerm = {
    skipNotLabel(term, remove_sub("cc"))
  }
  def remove_case(term: NoValTerm): NoValTerm = {
    skipNotLabel(term, remove_sub("case"))
  }
  def remove_det(term: NoValTerm): NoValTerm = {
    skipNotLabel(term, remove_sub("det"))
  }

  def remove_sub(label: String)(term: NoValTerm): List[NoValTerm] = {
    term match {
      case NoValTerm(l, _) if l == label => Nil
      case NoValTerm(f, List(child)) => {
        List(NoValTerm(f, List(skipNotLabel(child, remove_sub(label)))))
      }
      case _ => Nil
    }
  }
  
  // def remove_case_sub(term: NoValTerm): List[NoValTerm] = {
  //   term match {
  //     case NoValTerm("case", _) => Nil
  //     case NoValTerm(f, List(child)) => {
  //       List(NoValTerm(f, List(skipNotLabel(child, remove_case_sub))))
  //     }
  //     case _ => Nil
  //   }
  // }
  def skipNotLabel(term: NoValTerm, func: NoValTerm=>List[NoValTerm]): NoValTerm = {
    term match {
      case NoValTerm(f, args) => {
        NoValTerm(f, args.flatMap(func))
      }
    }
  }

  def util(list: List[String], moji: String): String = {
    var s = ""
    list.foreach(l => {
      s += l + moji
    })
    s.substring(0, s.size-1)
  }
}