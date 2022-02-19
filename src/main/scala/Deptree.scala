object Deptree {
    case class DNode(label: String, children: List[(DNode, String)])
    /*
    case class DNode(label: String, edges: List[Edge])
    case class Edge(label: String, dir: DNode)
    */
}