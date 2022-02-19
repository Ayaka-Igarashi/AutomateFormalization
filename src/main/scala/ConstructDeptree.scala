import OutputParse._
import Contree._
import Deptree._

object ConstructDeptree {

    def constructDeptreeList(): List[DNode] = {
        val parsed = OutputParse.parse("src/output_synconst_0.txt", "src/output_syndephead_0.txt", "src/output_syndeplabel_0.txt")
        // val a = parsed.head
        parsed.map(p => {
            val f = format(p)
            constructDeptree(f)
        })
    }

    def constructDeptree(format: List[(Int, String, Int, String)]): DNode = {
        val (found, rst) = findhead(0, format)
        found match {
            case List((i, w, 0, "root")) => {
                constructDeptree_sub(i, w, rst)._1
            }
            case _ => println("wrong");DNode("", List())
        }
    }

    // return (deptree, rst)
    def constructDeptree_sub(rootidx: Int, rootlabel: String,format: List[(Int, String, Int, String)]): (DNode,List[(Int, String, Int, String)]) = {
        var (found, rst) = findhead(rootidx, format)
        val children = found.map(f => {
            val i = f._1;val w = f._2;val l = f._4
            val (dt, r) = constructDeptree_sub(i, w, rst)
            rst = r
            (dt, l)
        })
        (DNode(rootlabel ,children), rst)
    }

    def findhead(n: Int, format: List[(Int, String, Int, String)]): (List[(Int, String, Int, String)], List[(Int, String, Int, String)]) = {
        format.partition(t => t._3 == n)
    }

    def format(parsed: (Contree, List[(Int, String)])): List[(Int, String, Int, String)] = {
        val exc = parsed._1
        val exd = parsed._2
        val (dephead, deplabel) = exd.unzip
        val leaves = getLeaves(exc)
        for (List(i, j, k, l) <- List(List.range(1, leaves.length+1), leaves, dephead, deplabel).transpose) yield (i.toString.toInt, j.toString, k.toString.toInt, l.toString)
    }

    
}
