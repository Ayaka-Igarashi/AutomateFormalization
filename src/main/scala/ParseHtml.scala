import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter, PrintWriter}
import scala.collection.JavaConverters._
import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Node}

object StateStructure {
  case class State(var name: String, var prev: String, var trans: List[Trans])
  case class Trans(character: String, process: String)
}

// HTML文章の解析、構造化する
object ParseHtml {
  import StateStructure._
  var replaceStates: String = "[mM]arkup declaration open state|[nN]amed character reference state|[nN]umeric character reference end state"
  def getReplaceStates(): String = replaceStates
  var stateList: List[State] = List()
  var state: State = State(null, "", List())
  var charas: List[String] = List()

  var haveReadTrans: Boolean = false

  var htmlOut: PrintWriter = null

  var parseHtmlString: String = ""
  var parseTestOut: PrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File("src/parseTest.txt"))))

  def parseHtml(): List[State] = {
    System.out.println("> html_parse_start")
    htmlOut = new PrintWriter(new BufferedWriter(new FileWriter(new File("src/outputHTML.txt"))))

    // htmlのparse
    val doc: Document = Jsoup.parse(new File("src/input/inputHTML.txt"),null)

    // 必要なものを取り出す
    val rootNode: Node = doc.body()
    readHtml(rootNode)
    htmlOut.close()

    System.out.println("> html_parse_fin")
    stateList
  }

  def readHtml(node: Node): Unit = {
    node.nodeName() match {
      case "h5" => {haveReadTrans = false; stateName(node)}
      case "p" => {
        if (!haveReadTrans) {
          val leave = getLeave(node)
          state.prev += leave
          parseHtmlString += leave + "\n"
        }
      }
      case "dl" => {
        haveReadTrans = true
        trans(node)
        stateList :+= state
        htmlOut.println(state)
      }
      case _ => {
        val children: List[Node] = node.childNodes().asScala.toList

        for (child <- children) {
          readHtml(child)
        }
      }
    }
  }

  def stateName(node: Node) = {
    state = State(null, "", List())
    val children: List[Node] = node.childNodes().asScala.toList
    for (child <- children) {
      if (child.nodeName() == "dfn") {
        val stateName = getLeave(child)
        state.name = stateName.replace(" ", "_").replace("-","_")
          .replace("(", "").replace(")", "")
//        state.name = stateName.replace("-","_")
        val komoji: String = stateName(0).toLower + stateName.tail
        replaceStates += ("|" + stateName.replace("(", "\\(").replace(")", "\\)") + "|" + komoji.replace("(", "\\(").replace(")", "\\)"))
      }
    }
  }

  def trans(node: Node):Unit = {
    node.nodeName() match {
      case "dt" => {
        var leave = getLeave(node)
        leave = leave.replace("\n", "")
        //chara = leave
        charas :+= leave
      }
      case "dd" => {
        var leave = getLeave(node)
        leave = leave.replace("\n", "")
        //leave = Replacement.replace(leave)
        for (c <- charas) {
          state.trans :+= Trans(c, leave)
//          parseTestOut.println(leave)
          parseHtmlString += leave + "\n"
        }
        //state.trance :+= Trance(chara, leave)
        charas = List()
      }
      case _ => {
        val children: List[Node] = node.childNodes().asScala.toList
        for (child <- children) {
          trans(child)
        }
      }
    }
  }

  // nodeの葉の要素を文字列にして取得する
  def getLeave(node: Node):String = {
    var str: String = ""
    if (node.childNodeSize() == 0) {
      node.toString()
    } else {
      val children: List[Node] = node.childNodes().asScala.toList
      for (child <- children) {
        str += getLeave(child)
      }
      str
    }
  }
}
