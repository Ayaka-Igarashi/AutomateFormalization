import Terms._
object DataTemplateFix {
val templatesfix: List[(Term|List[Term], String, Int)] = List(

// create(z0)
( List(Function("create",List(TermVariable("z0:token#nopos#ref"))), Function("set_to",List(Function("z0:token#nopos#ref . data", Nil),TermVariable("z1")))) ,
 "create <det> <z0> whose data is <det> <z1>",1),

// ( List(Function("append_to",List(TermVariable("z0"),TermVariable("z2"))), Function("append_to",List(TermVariable("z0"),TermVariable("z2"))), Function("append_to",List(TermVariable("z1"),TermVariable("z2")))) ,
//  "append two <det> <z0> and <det> <z1> to <det> <z2>",3),

// ( List(Function("append_to",List(TermVariable("z0"),TermVariable("z1"))), Function("append_to",List(TermVariable("z0"),TermVariable("z1")))) ,
//  "append two <det> <z0> to <det> <z1>",2),

// ( List(Function("emit",List(TermVariable("z0"))), Function("emit",List(TermVariable("z0")))) ,
//  "emit two <det> <z0>",1), 

// emit(z0)
( Function("emit",List(Function("temporary_buffer [_]",Nil))) ,
 "emit a character token for each of the characters in the temporary buffer",0),

( List(Function("emit",List(TermVariable("z0:character_token#nopos"))), Function("emit",List(Function("temporary_buffer [_]",Nil)))) ,
 "emit <det> <z0> and a character token for each of the characters in the temporary buffer",1),

( List(Function("emit",List(TermVariable("z0:character_token#nopos"))), Function("emit",List(TermVariable("z1:character_token#nopos"))), Function("emit",List(Function("temporary_buffer [_]",Nil)))) ,
 "emit <det> <z0> <cc> <det> <z1> and a character token for each of the characters in the temporary buffer",2),

( Function("set_to",List(TermVariable("z0:token"), Function("on [_]",Nil))) ,
 "set <det> <z0>",1),

)


}
