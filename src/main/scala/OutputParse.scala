object OutputParse {
    import java.io._
    import Contree._
    import Terms._

    def parse(constUrl: String, headUrl: String, labelUrl: String): List[(Contree, List[(Int, String)])] = {
        val contreeList = load_file(constUrl)
        val headList = load_file(headUrl)
        val labelList = load_file(labelUrl)

        (contreeList zip (headList zip labelList)).map(l => {
            (parse_synconst(l._1), parse_syndep(l._2._1, l._2._2))
        })
    }

    def parseStringTree(string: String): NoValTerm = {
        var str = string
        val re1 = "\\([^(\\(| |\\))]+ ".r   // matches (####_
        val re2 =    "[^(\\(| |\\))]+\\)".r // matches ###)
        val re3 =                   "\\)".r
        val re4 = " ".r
        val stack = new scala.collection.mutable.Stack[NoValTerm]

        while (str != "") {
            // println(stack)
            re1.findPrefixMatchOf(str) match { case Some(rs) => {
                val s = rs.toString
                val label = s.substring(1, s.length-1)
                stack.push(NoValTerm(label, Nil))
                str = str.substring(s.length, str.length)
            } case None => {
            re2.findPrefixMatchOf(str) match { case Some(rs) => {
                val s = rs.toString
                val label = s.substring(0, s.length-1)
                val leaf = stack.pop
                leaf match {
                    case NoValTerm(l, lst) => stack.push(NoValTerm(l, List(NoValTerm(label,Nil))))
                    case _ =>
                }
                str = str.substring(s.length, str.length)
            } case None => {
            re3.findPrefixMatchOf(str) match { case Some(rs) => {
                if (stack.size > 1) {
                    val leaf = stack.pop
                    val parent = stack.pop
                    parent match {
                        case NoValTerm(l, lst) => {
                            stack.push(NoValTerm(l, lst :+ leaf))
                        }
                        case _ =>
                    }
                }
                str = str.substring(1, str.length)
            } case None => {
            re4.findPrefixMatchOf(str) match { case Some(rs) => {  
                if (stack.size > 1) {
                    val leaf = stack.pop
                    val parent = stack.pop
                    parent match {
                        case NoValTerm(l, lst) => {
                            stack.push(NoValTerm(l, lst :+ leaf))
                        }
                        case _ => 
                    }
                }
                str = str.substring(1, str.length)
            } case None => //
            }}}}}}}            
        }
        stack.pop
    }

    def parseOnlySynconst(constUrl: String): List[Contree] = {
        val contreeList = load_file(constUrl)
        contreeList.map(parse_synconst(_))
    }
    
    def parse_syndephead(str: String): List[Int] = {
        str.substring(1, str.length-1).split(", ").map(s => s.toInt).toList
    }

    def parse_syndeplabel(str: String): List[String] = {
        str.substring(1, str.length-1).split(", ").map(s => s.substring(1, s.length-1)).toList
    }

    def parse_syndep(head: String, label: String): List[(Int, String)] = {
        parse_syndephead(head) zip parse_syndeplabel(label)
    }

    def parse_synconst(string: String): Contree = {
        var str = string
        val re = "\\([A-Z\\.\\,\\:\\-\\`\\'\\$]+ ".r
        val stack = new scala.collection.mutable.Stack[Contree]

        while (str != "") {
            if (str.startsWith(")")) {
                if (stack.size > 1) {
                    val leaf = stack.pop
                    val parent = stack.pop
                    parent match {
                        case Node(l, lst) => stack.push(Node(l, lst :+ leaf))
                        case _ =>
                    }
                }
                str = str.substring(1, str.length)
            } else if (str.startsWith("  )")) {
                val leaf = stack.pop
                leaf match {
                    case Node(l, lst) => //stack.push(Leaf(l, " "))
                    case _ =>
                }
                str = str.substring(3, str.length)
            } else if (str.startsWith(" ")) {
                str = str.substring(1, str.length)
            } else {
                re.findPrefixMatchOf(str) match {
                    case Some(rs) => {
                        val s = rs.toString
                        val label = s.substring(1, s.length-1)
                        stack.push(Node(label, List()))
                        str = str.substring(s.length, str.length)
                    }
                    case None => {
                        val (word, rst) = str.splitAt(str.indexOf(")"))
                        val leaf = stack.pop
                        leaf match {
                            case Node(l, lst) => stack.push(Leaf(l, word))
                            case _ =>
                        }
                        str = rst
                    }
                }
            }
        }
        stack.pop
    }

    def load_file(fileUrl: String): List[String] = {
        val br = new BufferedReader(new InputStreamReader(new FileInputStream(fileUrl), "MS932"))
        val list = try {
            Iterator.continually(br.readLine()).takeWhile(_ != null).toList
        } finally {
            br.close()
        }
        list
    }
}