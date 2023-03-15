import java.io.{PrintWriter}

object Ontology {
  var uniqueId = 0
  case class ONode(value: String, attributes: List[ONode] = Nil, children: List[ONode] = Nil) {
    val i = uniqueId
    uniqueId += 1
  }
  // case class Att(value: String) {
  //   val i = uniqueId
  //   uniqueId += 1
  // }

  var ontology: ONode = null

  def makeOntology = () => {
    ontology = ONode("_", Nil, List(
      ONode("temporary_buffer", Nil, Nil),
      ONode("character_reference_code", Nil, Nil),
      ONode("state", Nil, List(
        ONode("return_state", Nil, Nil),
        ONode("Numeric_character_reference_end_state", Nil, Nil),
        ONode("Named_character_reference_state", Nil, Nil),
      )++stateTree()),
      ONode("token", Nil, List(
        ONode("current_token", List(
          ONode("name"), 
          ONode("data"), 
          ONode("self-closing_flag"),
          ONode("force-quirks_flag"),
          ONode("attribute")
        ),Nil),
        ONode("DOCTYPE_token", List(
          ONode("name"),
          ONode("public_identifier"),
          ONode("system_identifier"),
          ONode("force-quirks_flag")
        ), Nil),
        ONode("tag_token", List(
          ONode("name"), 
          ONode("self-closing_flag"),
          ONode("attribute", List(
            ONode("name"),
            ONode("value")
          ))
        ), List(
          ONode("start_tag_token", List(
            ONode("name"), 
            ONode("self-closing_flag"),
            ONode("attribute", List(
              ONode("name"),
              ONode("value")
            ))
          ), Nil),
          ONode("end_tag_token", List(
            ONode("name"), 
            ONode("self-closing_flag"),
            ONode("attribute", List(
              ONode("name"),
              ONode("value")
            ))
          ), Nil),//List(ONode("appropriate_end_tag_token"))),
          ONode("current_tag_token", List(
            ONode("name"), 
            ONode("self-closing_flag"),
            ONode("attribute", List(
              ONode("name"),
              ONode("value")
            ))
          ), Nil),
        )),
        ONode("comment_token", List(
          ONode("data")
        )),
        ONode("character_token", List(
          ONode("data")
        ), List(
          ONode("U+0026_AMPERSAND_character_token", Nil, Nil),
          ONode("two_U+0026_AMPERSAND_character_token", Nil, Nil),
        )++characterTree()),
        ONode("end-of-file_token", Nil, Nil)
      )),
      ONode("current_attribute", List(
        ONode("name"),
        ONode("value")
      ), Nil),
      ONode("input_character", Nil, List(
        ONode("current_input_character", List(
          ONode("lowercase_version"),
          ONode("uppercase_version"),
          ONode("numeric_version"),
        ), Nil),
        ONode("next_input_character")
      )),
      ONode("parse_error", Nil, List(
      )++errorTree()),
      ONode("string", Nil, List(
        ONode("empty_string"),
        ONode("string_\"script\"")
      )),
      ONode("on"),ONode("off"),
      ONode("number", Nil, List(
        ONode("0"),ONode("10"),ONode("16")
      )),
    ))
  }

  def characterTree = () => {
    // Main.unicodeList.map(u => ONode(u+"_character_token")).toList
    Main.unicodeList.toList.flatMap(u => List(ONode(u.replace(" ", "_")),ONode("two_"+u.replace(" ", "_"))))
  }
  def stateTree = () => {
    Main.stateList.map(s => ONode(s))
  }
  def errorTree = () => {
    Main.errorList.toList.map(s => ONode(s.replace(" ", "_")))
  }

  def getSubtreeIndexRange: Int => (Int,Int) = (head) => {
    var min = head
    val max = head
    val node = OntologyMatch.searchNode(head)
    if (!node.children.isEmpty) {
      val (min2, max2) = getSubtreeIndexRange(node.children.head.i)
      min = min2
    } else if (!node.attributes.isEmpty) {
      val (min2, max2) = getSubtreeIndexRange(node.attributes.head.i)
      min = min2
    }
    (min,max+1)
  }

  def toPython() = {
    Main.load_nlp_file("src/input/nlpOut_depnp_a.txt")
    makeOntology()
    val pyout = new PrintWriter("src/ontology.py")
    var pycode = "class Ontology:\n\tdef __init__(self, value, attributes=[], children=[]):\n\t\tself.value = value\n\t\tself.attributes = attributes\n\t\tself.children = children\n"
    pycode += "ontology = "
    pycode += toPy_sub(ontology)
    pycode += "\n"
    pycode += "ontology_set = "
    pycode += "["+toSet(ontology).mkString(",")+"]"
    pyout.println(pycode)
    pyout.close
  }

  def toPy_sub(ont: ONode): String = {
    val value = ont.value
    val attributes = ont.attributes
    val children = ont.children
    val attributepy_list: List[String] = attributes.map(att => toPy_sub(att))
    val attributespy = attributepy_list.mkString(",")
    val childpy_list: List[String] = children.map(child => toPy_sub(child))
    val childrenpy = childpy_list.mkString(",")
    "Ontology(\"" + value.replace("\"", "\\\"") + "\",["+ attributespy +"],["+ childrenpy +"])"
  }

  def toSet(ont: ONode): Set[String] = {
    val value = ont.value
    val attributes = ont.attributes
    val children = ont.children
    var set: Set[String] = Set()
    set += "\""+value.replace("\"", "\\\"")+"\""
    set ++= attributes.flatMap(att => toSet(att)).toSet
    set ++= children.flatMap(child => toSet(child)).toSet
    set
  }

}
