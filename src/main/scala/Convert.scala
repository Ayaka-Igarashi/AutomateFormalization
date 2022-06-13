import Terms._

object Convert {
    var count = 0
    val stermRules: List[Term] = List(
        Function("S", List(Function("NP", HedgeVariable("x1")), 
                            Function("VP", List(Function("VBZ", List(Function("is", Nil))),Function("NP", HedgeVariable("x2"))))))  
    )
    val ifTermRules: List[Term] = List(
        Function("S",Function("SBAR", List(Function("IN", List(Function("if", Nil))), TermVariable("x1")))
                         ::Function(",", List(Function(",", Nil)))
                         ::Function("ADVP", List(Function("RB", List(Function("then", Nil)))))
                         ::TermVariable("x2")
                         ::Nil),
        Function("S", Function("ADVP", List(Function("RB", List(Function("otherwise", Nil)))))
                         ::Function(",", List(Function(",", Nil)))
                         ::TermVariable("x1")
                         ::Nil)
    )

    def convertTerms(terms: List[(Term, String)], rules: List[Term]): List[(List[Term], String)] = {
        terms.map(t => {
            //if (t != convertTerm(t, rules)) println(displayTerm(convertTerm(t, rules)))
            val ruleSymbols = ExtractRule.rulesSymbols(rules++stermRules++ifTermRules)
            val converted = convertTerm(t._1, rules)
            val onlyConverted = extractOnlyConverted(converted, ruleSymbols)
            // List(converted)
            if (onlyConverted.isEmpty) {
                val fail: Term = Function("FAIL", Nil)
                (List(fail), t._2)
            } else {
                (onlyConverted, t._2)
            }
        })
    }
    // 返り値List[Term]の方がいい？
    def convertTerm(term: Term, rules: List[Term]): Term = {
        term match {
            case TermVariable(x) => term
            case Function("S", child) => {
                // println(toCommandIf(term))
                ifTermRules.foreach(r => {
                    convertNp_insub(conv(r, term),r) match {
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
                stermRules.foreach(r => {
                    convertNp_insub(conv(r, term),r) match {
                        case None => 
                        case Some(sub) => {
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
                        Function("S", conved)
                    }
                }
            }
            case Function("VP", child) => {
                rules.foreach(r => {
                    convertNp_insub(conv(r, term), r) match {
                        case None => 
                        case Some(sub) => {

                            return Terms.substitution(toCommand(r), sub)
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
    
    // 未使用
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
                            case Function("DT", List(Function("this", Nil))) => List(t)
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
                case s: String => if (s != ",") sym += "_"+s
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

    def extractOnlyConverted(term: Term, ruleSymbols: List[String]): List[Term] = {
        term match {
            case Function(s, child) => {
                if (ruleSymbols.contains(s)) List(term)
                else {
                    child match {
                        case HedgeVariable(_) => Nil
                        case list: List[Term] => list.flatMap(c => extractOnlyConverted(c, ruleSymbols))
                    }
                }
            }
            case TermVariable(x) => Nil
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

    def convertNp_insub(sub: Option[Map[String, Term|List[Term]]], ori: Term): Option[Map[String, Term|List[Term]]] = {
        // return sub
        sub match {
            case None => None
            case Some(m) => {
                Some(m.map((s,t) => {
                    if (getParentNode(s, ori) == "NP") {
                        t match {
                            case term: Term => s -> convertNP2(term)
                            case list: List[Term] => s -> list.map(convertNP2(_))
                        }
                    } else {
                        t match {
                            case Function(sym, List(np)) if sym == "NP" => {//s -> convertNP2(Function(sym, list))
                                np match {
                                    case npt: Term => {s -> convertNP2(npt)}
                                    case _ => s -> t
                                }
                            }
                            case _ => s -> t
                        }
                        // t match {
                        //     case term: Term => s -> convertNP(term)
                        //     case list: List[Term] => s -> list.map(convertNP(_))
                        // }
                        // s -> t
                    }                    
                }))
            }
        }
    }

    def convertNP2(term: Term): Term = {
        val slist = ConvertNp.convert(term)
        var s = ""
        slist.foreach(w => s += w + " & ")
        s = s.slice(0,s.length-3)
        // println(s)
        Function(s, Nil)
    }

    def convertNP(term: Term): Term = {
        term match {
            case Function("NP", List(np)) => {
                np match {
                    case np_term: Term => {
                        val slist = ConvertNp.convert(np_term)
                        var s = ""
                        slist.foreach(w => s += w + " & ")
                        s = s.slice(0,s.length-3)
                        // println(s)
                        Function(s, Nil)
                    }
                    case _ => term
                }
                
            }
            case Function(s, child) => {
                child match {
                    case HedgeVariable(_) => term
                    case list: List[Term] => Function(s, list.map(convertNP(_)))
                }
            }
            case TermVariable(x) => term
        }
    }
}