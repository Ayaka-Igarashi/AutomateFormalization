object Contree {
    trait Contree
    case class Node(label: String, children: List[Contree]) extends Contree
    case class Leaf(label: String, word: String) extends Contree

    def getLeaves(tree: Contree): List[String] = {
        var leave: List[String] = List()
        tree match {
            case Node(_, list) => {
                for (t <- list) leave ++= getLeaves(t)
            }
            case Leaf(_, word) => leave :+= word 
        }
        leave
    }
}