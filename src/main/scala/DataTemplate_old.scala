import Terms._
object DataTemplate_old {

val templates: List[(Term|List[Term], String, Int)] = List(
// VP(VB(switch),PP(IN(to),NP(z0)))
( Function("VP",List(Function("VB",List(Function("switch",List()))),Function("PP",List(Function("IN",List(Function("to",List()))),Function("NP",List(TermVariable("z0"))))))) ,
 "switch to <det> <z0>",1),

// VP(VB(set),NP(z0),PP(IN(to),z1)) => set_to (z0,z1)
( Function("VP",List(Function("VB",List(Function("set",List()))),Function("NP",List(TermVariable("z0"))),Function("PP",List(Function("IN",List(Function("to",List()))),TermVariable("z1"))))) ,
 "set <det> <z0> to <det> <z1>",2),

// VP(VB(set),NP(z0),PP(IN(to),z1))
( List(Function("VP",List(Function("VB",List(Function("set",List()))),Function("NP",List(TermVariable("z0"))),Function("PP",List(Function("IN",List(Function("to",List()))),TermVariable("z1"))))),
        Function("VP",List(Function("VB",List(Function("set",List()))),Function("NP",List(TermVariable("z2"))),Function("PP",List(Function("IN",List(Function("to",List()))),TermVariable("z3")))))) ,
 "set <det> <z0> to <det> <z1> <cc> <det> <z2> to <det> <z3>",4),

// VP(VB(set),NP(z0),PP(IN(to),z1))
( List(Function("VP",List(Function("VB",List(Function("set",List()))),Function("NP",List(TermVariable("z0"))),Function("PP",List(Function("IN",List(Function("to",List()))),TermVariable("z2"))))),
        Function("VP",List(Function("VB",List(Function("set",List()))),Function("NP",List(TermVariable("z1"))),Function("PP",List(Function("IN",List(Function("to",List()))),TermVariable("z2")))))) ,
 "set <det> <z0> <cc> <det> <z1> to <det> <z2>",3),

// VP(VB(create),NP(z0))
( Function("VP",List(Function("VB",List(Function("create",List()))),Function("NP",List(TermVariable("z0"))))) ,
 "create <det> <z0>",1),

// VP(VB(reconsume),PP(IN(in),NP(z0)))
( Function("VP",List(Function("VB",List(Function("reconsume",List()))),Function("PP",List(Function("IN",List(Function("in",List()))),Function("NP",List(TermVariable("z0"))))))) ,
 "reconsume in <det> <z0>",1),

// VP(VB(multiply),NP(code(det(the),compound(reference(compound(character))))),PP(IN(by),NP(z0)))
( Function("VP",List(Function("VB",List(Function("multiply",List()))),Function("NP",List(Function("code",List(Function("det",List(Function("the",List()))),Function("compound",List(Function("reference",List(Function("compound",List(Function("character",List()))))))))))),Function("PP",List(Function("IN",List(Function("by",List()))),Function("NP",List(TermVariable("z0"))))))) ,
 "multiply the character reference code by <z0>",1),

// VP(VB(consume),NP(z0))
( Function("VP",List(Function("VB",List(Function("consume",List()))),Function("NP",List(TermVariable("z0"))))) ,
 "consume <det> <z0>",1),

// VP(VB(start),NP(attribute(det(a),amod(new))),PP(IN(in),NP(token(det(the),amod(current),compound(tag)))))
( Function("VP",List(Function("VB",List(Function("start",List()))),Function("NP",List(Function("attribute",List(Function("det",List(Function("a",List()))),Function("amod",List(Function("new",List()))))))),Function("PP",List(Function("IN",List(Function("in",List()))),Function("NP",List(Function("token",List(Function("det",List(Function("the",List()))),Function("amod",List(Function("current",List()))),Function("compound",List(Function("tag",List()))))))))))) ,
 "start a new attribute in the current tag token", 0),

// VP(VB(add),NP(z0),PP(IN(to),NP(code(det(the),compound(reference(compound(character)))))))
( Function("VP",List(Function("VB",List(Function("add",List()))),Function("NP",List(TermVariable("z0"))),Function("PP",List(Function("IN",List(Function("to",List()))),Function("NP",List(Function("code",List(Function("det",List(Function("the",List()))),Function("compound",List(Function("reference",List(Function("compound",List(Function("character",List()))))))))))))))) ,
 "add <det> <z0> to the character reference code",1),

// VP(VB(ignore),NP(character(det(the))))
( Function("VP",List(Function("VB",List(Function("ignore",List()))),Function("NP",List(Function("character",List(Function("det",List(Function("the",List()))))))))) ,
 "ignore the character",0),

// VP(VB(append),NP(z0),PP(IN(to),NP(z1)))
( Function("VP",List(Function("VB",List(Function("append",List()))),Function("NP",List(TermVariable("z0"))),Function("PP",List(Function("IN",List(Function("to",List()))),Function("NP",List(TermVariable("z1"))))))) ,
 "append <det> <z0> to <det> <z1>",2),

// // VP(z0,z1,PP(IN(to),NP(name(nmod:poss(token(det(the),amod(current),compound(tag),case('s))),compound(tag)))))
// val r10 = ( Function("VP",List(TermVariable("z0"),TermVariable("z1"),Function("PP",List(Function("IN",List(Function("to",List()))),Function("NP",List(Function("name",List(Function("nmod:poss",List(Function("token",List(Function("det",List(Function("the",List()))),Function("amod",List(Function("current",List()))),Function("compound",List(Function("tag",List()))),Function("case",List(Function("'s",List()))))))),Function("compound",List(Function("tag",List()))))))))))) ,
//  "")

// VP(VBZ(is),NP(z0))
( Function("VP",List(Function("VBZ",List(Function("is",List()))),Function("NP",List(TermVariable("z0"))))) ,
 "This is <det> <z0>",1),

// // VP(VBN(consumed),PP(IN(as),NP(reference(det(a),compound(character)))))
// val r12 = ( Function("VP",List(Function("VBN",List(Function("consumed",List()))),Function("PP",List(Function("IN",List(Function("as",List()))),Function("NP",List(Function("reference",List(Function("det",List(Function("a",List()))),Function("compound",List(Function("character",List()))))))))))) ,
//  "")

// VP(VB(emit),NP(z0))
( Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(TermVariable("z0"))))) ,
 "emit <det> <z0>",1),

// VP(VB(emit),NP(z0)) | VP(VB(emit),NP(z1))
( List(Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(TermVariable("z0"))))), Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(TermVariable("z1")))))) ,
 "emit <det> <z0> <cc> <det> <z1>",2), 

// VP(VB(emit),NP(z0)) | VP(VB(emit),NP(z1))
( List(Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(TermVariable("z0"))))), 
    Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(TermVariable("z1"))))),
    Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(TermVariable("z2")))))) ,
 "emit <det> <z0> <cc> <det> <z1> <cc> <det> <z2>",3),

// // VP(VB(emit),NP(character(det(the),amod(current),compound(input))),PP(IN(as),NP(token(det(a),compound(character)))))
// val r14 = ( Function("VP",List(Function("VB",List(Function("emit",List()))),Function("NP",List(Function("character",List(Function("det",List(Function("the",List()))),Function("amod",List(Function("current",List()))),Function("compound",List(Function("input",List()))))))),Function("PP",List(Function("IN",List(Function("as",List()))),Function("NP",List(Function("token",List(Function("det",List(Function("a",List()))),Function("compound",List(Function("character",List()))))))))))) ,
//  "")

// VP(VB(treat),NP(it),PP(IN(as),PP(IN(per),NP(entry(det(the),compound(anything(punct("),amod(else),punct("))),advmod(below))))))
( Function("VP",List(Function("VB",List(Function("treat",List()))),Function("NP",List(Function("it",List()))),Function("PP",List(Function("IN",List(Function("as",List()))),Function("PP",List(Function("IN",List(Function("per",List()))),Function("NP",List(Function("entry",List(Function("det",List(Function("the",List()))),Function("compound",List(Function("anything",List(Function("punct",List(Function("\"",List()))),Function("amod",List(Function("else",List()))),Function("punct",List(Function("\"",List()))))))),Function("advmod",List(Function("below",List()))))))))))))) ,
 "treat it as per the \"anything else\" entry below", 0)
)
}