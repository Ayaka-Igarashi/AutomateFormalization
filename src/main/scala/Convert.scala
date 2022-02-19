import Terms._

object Convert {
    def convertTerms(terms: List[Term], rules: List[Term]): List[Term] = {
        terms.map(t => {
            //if (t != convertTerm(t, rules)) println(displayTerm(convertTerm(t, rules)))
            convertTerm(t, rules)
        })
    }
    // 返り値List[Term]の方がいい？
    def convertTerm(term: Term, rules: List[Term]): Term = {
        term match {
            case TermVariable(x) => term
            case Function("S", child) => {
                // println(toCommandIf(term))
                stermRules.foreach(r => {
                    conv(r, term) match {
                        case None => 
                        case Some(sub) => {
                            val conv_sub = sub.map(m => {
                                m._2 match {
                                    case t: Term => (m._1,convertTerm(t, rules))
                                    case _: List[Term] => m
                                }
                            })
                            return Terms.substitution(toCommand(r), conv_sub)//list
                        }
                    }
                })
                child match {
                    case HedgeVariable(x) => term//nil
                    case Nil => term//Nil
                    case lst: List[Term] => {
                        val conved = lst.map(t => {
                            convertTerm(t, rules)
                        })
                        Function("S", conved)
                    }
                }
            }
            case Function("VP", child) => {
                rules.foreach(r => {
                    conv(r, term) match {
                        case None => 
                        case Some(sub) => {
                            // println(displayTerm(Terms.substitution(toCommand(r), sub)))
                            return Terms.substitution(toCommand(r), sub)//list
                        }
                    }
                })
                child match {
                    case HedgeVariable(x) => term//nil
                    case Nil => term//Nil
                    case lst: List[Term] => {
                        val conved = lst.map(t => {
                            convertTerm(t, rules)
                        })
                        Function("VP", conved)
                    }
                }
            }
            case Function(s, child) => {
                child match {
                    case HedgeVariable(x) => term
                    case Nil => term
                    case lst: List[Term] => {
                        val conved = lst.map(t => {
                            convertTerm(t, rules)
                        })
                        Function(s, conved)
                    }
                }
            }
        }
    }
    
    def convertNorm(term: Term): Term = {
        // return removeDT(term)
        term match {
            case Function("NP", _) => {
                // println(displayTerm(term))
                val removed = removeDT(term)
                // println(" " +displayTerm(removed))
                kaisouka(removed)
            }
            case Function(s, child) => {
                child match {
                    case lst: List[Term] => {
                        val conved = lst.map(t => {
                            convertNorm(t)
                        })
                        Function(s, conved)
                    }
                    case _ => term
                }
            }
            case _ => term
        }
        
    }
    
    def removeDT(term: Term): Term = {
        // return term
        term match {
            case Function(l, child) => {
                child match {
                    case list: List[Term] => {
                        Function(l, list.flatMap(t => {
                        t match {
                            case Function("DT", _) => List()
                            case Function(cl, clist) => List(removeDT(t))
                            case _ => List(t)
                        }
                        }))
                    }
                    case _ => term
                }
            }
            case _ => term
        }
    }
    
    // VP(VB(Switch),PP(IN(to),NP(DT(the),NN(z0)))) => SwitchToThe(z0)
    def toCommand(term: Term): Term = {
        // var child: List[Term] = Nil
        // var word = ""
        // term match {
        //     case Function(s, child) => {
        //         child match {
        //             case HedgeVariable(h) => child :+= term
        //             case Nil => word += "_" + s
        //             case list: List[Term] => list
        //         }
        //     }
        //     case TermVariable(x) => child :+= term
        // }
        val leaves = getTermLeaves(term)
        var sym = ""
        var child: List[Term] = List()
        leaves.foreach(t => {
            t match {
                case s: String => sym += "_"+s
                case Function(s, HedgeVariable(h)) => child :+= Function(s, HedgeVariable(h))
                case TermVariable(x) => child :+= TermVariable(x)
            }
        })
        if (sym != "") sym = sym.tail
        return Function(sym, child)
    }
    
    def kaisouka(term: Term): Term = {
        term match {
            case Function(l, child) => {
                child match {
                    case list: List[Term] => {
                        var sym = ""
                        var cld: List[Term] = Nil
                        list.foreach(t => {
                            t match {
                                case Function(_, List(Function(w, Nil))) => sym += w + "_"
                                case _ => cld :+= kaisouka(t)
                            }
                        })
                        Function(sym.slice(0,sym.length-1), cld)
                    }
                    case _ => term
                }
            }
            case _ => term
        }
    }
    
    def getTermLeaves(term: Term): List[String|Term] = {
        var list: List[String|Term] = List()
        term match {
            case Function(s, List()) => list :+= s
            case Function(s, child) => {
                child match {
                    case HedgeVariable(h) => list :+= Function(s, HedgeVariable(h))
                    case lst: List[Term] => lst.foreach(t => list ++= getTermLeaves(t))
                }
            }
            case TermVariable(x) => list +:= TermVariable(x)
        }
        list
    }
    
    val stermRules: List[Term] = List(
        Function("S",Function("SBAR", List(Function("IN", List(Function("if", Nil))), TermVariable("x1")))
                         ::Function(",", List(Function(",", Nil)))
                         ::Function("ADVP", List(Function("RB", List(Function("then", Nil)))))
                         ::TermVariable("x2")
                         ::Nil),
        Function("S", Function("ADVP", List(Function("RB", List(Function("otherwise", Nil)))))
                         ::Function(",", List(Function(",", Nil)))
                         ::TermVariable("x1")
                         ::Nil),
        Function("S", List(Function("NP", HedgeVariable("x1")), 
                            Function("VP", List(Function("VBZ", List(Function("is", Nil))),Function("NP", HedgeVariable("x2"))))))
        
    )
    
    def toCommandIf(term: Term): Term = {
        term match {
            //(S (SBAR (IN If) (S (NP (DT the) (JJ current) (NML (NN end) (NN tag)) (NN token)) (VP (VBZ is) (NP (DT an) (JJ appropriate) (NML (NN end) (NN tag)) (NN token))))) (, ,) (ADVP (RB then)) (VP (VB switch) (PP (IN to) (NP (DT the) (NN Before_attribute_name_state)))) (. .))
            case Function("S", s) => {
                s match {
                    case Function("SBAR", List(Function("IN", List(Function("if", Nil))), Function("S", cons)))
                         ::Function(",", _)
                         ::Function("ADVP", List(Function("RB", List(Function("then", Nil)))))
                         ::Function("VP", state)
                        //  ::Function(".", _)
                         ::Nil => {
                        Function("if_then", List(Function("S", cons), Function("VP", state)))
                    }
                    case Function("ADVP", List(Function("RB", List(Function("otherwise", Nil)))))
                         ::Function(",", _)
                         ::state
                        //  ::Function(".", _)
                         ::Nil => {
                        Function("otherwise", List(state))
                    }
                    case _ => Function("_", List())
                }
            }
            case _ => Function("_", List())
        }
    }
    
    // return Option[Map[TermVariable, Term]]
    // term2がterm1にマッチしたら代入を返す
    def conv(term1: Term, term2: Term): Option[Map[String, Term|List[Term]]] = {
        (term1, term2) match {
            case (Function(s1, c1), Function(s2, c2)) => {
                if (s1 != s2) None
                else {
                    (c1, c2) match {
                        case (HedgeVariable(x), l2): (_, List[Term]) => {
                            Some(Map((x, l2)))
                        }
                        case (l1, l2): (List[Term], List[Term]) => {
                            if (l1.length != l2.length) None
                            else {
                                var list: Map[String, Term|List[Term]] = Map()
                                (l1 zip l2).foreach(a => {
                                    conv(a._1,a._2) match {
                                        case None => return None
                                        case Some(l) => list ++= l
                                    } 
                                })
                                Some(list)
                            }
                        }
                        case _ => None
                    }
                }
                
            }
            case (TermVariable(x), Function(s2, lst2)) => Some(Map((x, Function(s2, lst2))))
            case _ => None
        }
    }
    
    // def elimDT(term: Term): Term = {
    //     term match {
    //         case Function("DT", _) => 
    //         case Function(s, l) => {
    //             l.flatMap(t => {t match {
    //                 case Function("DT", _) => List()
    //             }})
    //         }
    //     }
    // }
}