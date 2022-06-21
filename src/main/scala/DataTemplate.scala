import Terms._
object DataTemplate {
val templates: List[(Term|List[Term], String, Int)] = List(
// switch_to(z0)
( Function("switch_to",List(TermVariable("z0"))) ,
 "switch to <det> <z0>", 1),

// set_to(z0,z1)
( Function("set_to",List(TermVariable("z0"),TermVariable("z1"))) ,
 "set <det> <z0> to <det> <z1>", 2),
 // set_to(z0,z1) | set_to(z2,z3)
( List(Function("set_to",List(TermVariable("z0"),TermVariable("z1"))), Function("set_to",List(TermVariable("z2"),TermVariable("z3")))) ,
 "set <det> <z0> to <det> <z1> <cc> <det> <z2> to <det> <z3>",4),
 // set_to(z0,z2) | set_to(z1,z2)
( List(Function("set_to",List(TermVariable("z0"),TermVariable("z2"))), Function("set_to",List(TermVariable("z1"),TermVariable("z2")))) ,
 "set <det> <z0> <cc> <det> <z1> to <det> <z2>",3),

// create(z0)
( Function("create",List(TermVariable("z0"))) ,
 "create <det> <z0>", 1),

// reconsume_in(z0)
( Function("reconsume_in",List(TermVariable("z0"))) ,
 "reconsume in <det> <z0>", 1),

// multiply_the_character_by_16
( Function("multiply_the_character_by",List(TermVariable("z0"))) ,
 "multiply the character reference code by <z0>", 1),

// consume(z0)
( Function("consume",List(TermVariable("z0"))) ,
 "consume <det> <z0>", 1),

// start_a_new_in_the_current_tag
( Function("start_a_new_in_the_current_tag",List()) ,
 "start a new attribute in the current tag token", 0),

// add_to_the_character(z0)
( Function("add_to_the_character",List(TermVariable("z0"))) ,
 "add <det> <z0> to the character reference code", 1),

// ignore_the
( Function("ignore_the",List()) ,
 "ignore the character", 0),

// append_to(z0,z1)
( Function("append_to",List(TermVariable("z0"),TermVariable("z1"))) ,
 "append <det> <z0> to <det> <z1>", 2),

// // append_the_lowercase_of_the_current_input_-LRB-_add_0x0020_to_the_'s_code_-RRB-_to(z0)
// ( Function("append_the_lowercase_of_the_current_input_-LRB-_add_0x0020_to_the_'s_code_-RRB-_to",List(TermVariable("z0"))) ,
//  "", 1),

// // to_the_current_tag_'s_tag(z0,z1)
// ( Function("to_the_current_tag_'s_tag",List(TermVariable("z0"),TermVariable("z1"))) ,
//  "", 2)

// is(z0)
( Function("is",List(TermVariable("z0"))) ,
 "This is <det> <z0>", 1),

// emit(z0)
( Function("emit",List(TermVariable("z0"))) ,
 "emit <det> <z0>", 1),
 // emit(z0) | emit(z1)
( List(Function("emit",List(TermVariable("z0"))), Function("emit",List(TermVariable("z1")))) ,
 "emit <det> <z0> <cc> <det> <z1>",2), 
 // emit(z0) | emit(z1) | emit(z2)
( List(Function("emit",List(TermVariable("z0"))), Function("emit",List(TermVariable("z1"))), Function("emit",List(TermVariable("z2")))) ,
 "emit <det> <z0> <cc> <det> <z1> <cc> <det> <z2>",3),

// // emit_the_current_input_as_a_character
// ( Function("emit_the_current_input_as_a_character",List()) ,
//  "", 0)

// treat_it_as_per_the_"_else_"_below
( Function("treat_it_as_per_the_\"_else_\"_below",List()) ,
 "treat it as per the \"anything else\" entry below", 0)

)
}
