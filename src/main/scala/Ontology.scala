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
      ONode("state", Nil, List(
        ONode("return_state", Nil, Nil)
      )++stateTree()),
      ONode("token", Nil, List(
        ONode("DOCTYPE_token", List(
          ONode("name"),
          ONode("public_identifier"),
          ONode("system_identifier"),
          ONode("force-quirks_flag")
        ), Nil),
        ONode("tag_token", Nil, List(
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
          ), Nil)
        )),
        ONode("comment_token", List(
          ONode("data")
        )),
        ONode("character_token", List(
          ONode("data")
        ), characterTree()),
        ONode("end-of-file_token", Nil, Nil)
      )),
      ONode("input_character", Nil, List(
        ONode("current_input_character"),
        ONode("next_input_character")
      )),
      ONode("parse_error", Nil, List(
        ONode("unexpected-null-character_parse_error")
      )++errorTree()),
      ONode("string", Nil, List(
        ONode("empty_string")
      )),
    ))
  }

  def characterTree = () => {
    Main.unicodeList.map(u => ONode(u+"_character_token")).toList
  }
  def stateTree = () => {
    Main.stateList.map(s => ONode(s))
  }
  def errorTree = () => {
    Main.errorList.map(s => ONode(s))
  }

}
