import Terms._
object DataTemplate {
val templates: List[(Term|List[Term], String, Int)] = List(
// // subtract_from_the_character_'s_code_point(z0)
// ( Function("subtract_from_the_character_'s_code_point",List(TermVariable("z0"))) ,
//  "subtract <det> <z0> from the character 's code point"),

// // if_then(z0,z1,z2,z3)
// ( Function("if_then",List(TermVariable("z0"),TermVariable("z1"),TermVariable("z2"),TermVariable("z3"))) ,
//  "If <det> <z0> <det> <z1> <det> <z2> , then <det> <z3>"),

// // if_then_switch_to_the(z0,z1)
// ( Function("if_then_switch_to_the",List(TermVariable("z0"),TermVariable("z1"))) ,
//  "If <det> <z0> , then switch to the <det> <z1>"),

// set_to(z0,z1)
( Function("set_to",List(TermVariable("z0"),TermVariable("z1"))) ,
 "set <det> <z0> to <det> <z1>",2),

// create(z0)
( Function("create",List(TermVariable("z0"))) ,
 "create <det> <z0>",1),

// reconsume_in(z0)
( Function("reconsume_in",List(TermVariable("z0"))) ,
 "reconsume in <det> <z0>",1),

// emit(z0)
( Function("emit",List(TermVariable("z0"))) ,
 "emit <det> <z0>",1),

// // emit_the_current_input_character_as_a_character_token
// ( Function("emit_the_current_input_character_as_a_character_token",List()) ,
//  "emit the current input character as a character token"),

// multiply_the_character_reference_code_by(z0)
( Function("multiply_the_character_reference_code_by",List(TermVariable("z0"))) ,
 "multiply the character reference code by <z0>",1),

// consume(z0)
( Function("consume",List(TermVariable("z0"))) ,
 "consume <det> <z0>",1),

// start_a_new_attribute_in_the_current_tag_token
( Function("start_a_new_attribute_in_the_current_tag_token",List()) ,
 "start a new attribute in the current tag token",0),

// // add_0x0020_to_the_character_'s_code_point
// ( Function("add_0x0020_to_the_character_'s_code_point",List()) ,
//  "add 0x0020 to the character's code point"),

// ignore_the_character
( Function("ignore_the_character",List()) ,
 "ignore the character",0),

// this_is_parse_error(z0,z1)
( Function("this_is_parse_error",List(TermVariable("z0"))) ,
 "This is <det> <z0>",1),

// switch_to(z0)
( Function("switch_to",List(TermVariable("z0"))) ,
 "switch to <det> <z0>",1),

// flush_code_points_consumed_as_a_character_reference
( Function("flush_code_points_consumed_as_a_character_reference",List()) ,
 "Flush code points consumed as a character reference",0),

// treat_it_as_per_the_"_anything_else_"_entry_below
( Function("treat_it_as_per_the_\"_anything_else_\"_entry_below",List()) ,
 "treat it as per the \" anything else \" entry below",0),

// append_to(z0,z1)
( Function("append_to",List(TermVariable("z0"),TermVariable("z1"))) ,
 "append <det> <z0> to <det> <z1>",2),

// set_to(z0,z1) | set_to(z2,z3)
( List(Function("set_to",List(TermVariable("z0"),TermVariable("z1"))), Function("set_to",List(TermVariable("z2"),TermVariable("z3")))) ,
 "set <det> <z0> to <det> <z1> <cc> <det> <z2> to <det> <z3>",4),
// set_to(z0,z2) | set_to(z1,z2)
( List(Function("set_to",List(TermVariable("z0"),TermVariable("z2"))), Function("set_to",List(TermVariable("z1"),TermVariable("z2")))) ,
 "set <det> <z0> <cc> <det> <z1> to <det> <z2>",3),
// add_to_the_character(z0)
( Function("add_to_the_character",List(TermVariable("z0"))) ,
 "add <det> <z0> to the character reference code",1),
// emit(z0) | emit(z1)
( List(Function("emit",List(TermVariable("z0"))), Function("emit",List(TermVariable("z1")))) ,
 "emit <det> <z0> <cc> <det> <z1>",2), 
// emit(z0) | emit(z1) | emit(z2)
( List(Function("emit",List(TermVariable("z0"))), Function("emit",List(TermVariable("z1"))), Function("emit",List(TermVariable("z2")))) ,
 "emit <det> <z0> <cc> <det> <z1> <cc> <det> <z2>",3),

)

val templates_if: List[(Term, String, Int, Int)] = List(
// if
( Function("if_then",List(TermVariable("b0"), TermVariable("t0"))) ,
 "if <b0> , then <t0>", 1,1),
// otherwise
( Function("otherwise",List(TermVariable("t0"))) ,
 "otherwise , <t0>", 1,0)
)

val templates_multi: List[(List[Term], String, Int)] = List(
// 2
( List(TermVariable("t0"), TermVariable("t1")) ,
 "<t0> <conj> <t1>",2),
// // 3
// ( List(TermVariable("t0"), TermVariable("t1"), TermVariable("t2")) ,
//  "<t0> <conj> <t1> <conj> <t2>", 3),
// // 4
// ( List(TermVariable("t0"), TermVariable("t1"), TermVariable("t2"), TermVariable("t3")) ,
//  "<t0> <conj> <t1> <conj> <t2> <conj> <t3>", 4)
)

val templates_b: List[(Term, String, Int)] = List(
( Function("is",List(TermVariable("z0"), TermVariable("z1"))) ,
 "<det> <z0> is <det> <z1>", 2)
)
}
