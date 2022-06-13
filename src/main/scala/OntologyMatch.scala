import Ontology._

object OntologyMatch {
  val ontology = Ontology.ontology
  def matching(str: String): Int = {
    matching_sub(str, ontology) match {
      case None => ontology.i
      case Some(i,f) => i
    }
  }

  def findAttribute(i: Int, str: String): Int = {
    val node = searchNode(i)
    // println(node.value)
    var max: Float = 0
    var nodei = 0
    node.attributes.foreach(a => {
      val v = ismatch(str, a.value)
      // println(a.value + " : " + str)
      // println("v= " + v)
      if (v >= max) {max = v; nodei = a.i}
    })
    nodei
  }

  def matching_sub(str: String, o: ONode): Option[(Int, Float)] = {
    val matchDegree = ismatch(str, o.value)
    if (matchDegree < 0.2) {
      None
    } else {
      var list: List[(Float, Int)] = Nil
      o.children.foreach(child => {
        matching_sub(str, child) match {
          case None =>
          case Some(i,f) => list :+= (f,i)
        }
      })
      if (list.size == 0) Some(o.i, matchDegree)
      else {
        val sorted = list.sortWith({(x,y) => x._1>y._1})
        // println(o.value + ": ")
        // println(sorted)
        Some(sorted.head._2, sorted.head._1)
      }
    }
  }
  // token_current_DOCTYPE , v: DOCTYPE_token -> 0.66
  def ismatch(str: String, v: String): Float = {
    val words = str.split('_').map(w => w.toLowerCase).toSet
    val words_v = v.split('_').map(w => w.toLowerCase).toSet
    if (words_v.size == 0) return 0.2
    var c = 0
    words.foreach(w => {
      if (words_v.contains(w)) c += 1
    })
    var c2 = 0
    words_v.foreach(w => {
      if (words.contains(w)) c2 += 1
    })
    if (c2.toFloat/words_v.size >= 0.5) math.max((c.toFloat/words.size), 0.2).toFloat
    else (c.toFloat/words.size)
  }

  def searchValue(i: Int): String = {
    searchValue_sub(i, ontology)
  }

  def searchValue_sub(i: Int, n: ONode): String = {
    if (i == n.i) {
      n.value
    } else {
      n.attributes.foreach(a => {
        if (i == a.i) return a.value
      })
      n.children.foreach(child => {
        if (i == child.i) return child.value
        if (i < child.i) return searchValue_sub(i, child)
      })
      return "_"
    }
  }

  def searchNode(i: Int): ONode = {
    searchNode_sub(i, ontology)
  }

  def searchNode_sub(i: Int, n: ONode): ONode = {
    if (i == n.i) {
      n
    } else {
      n.attributes.foreach(a => {
        if (i == a.i) return a
      })
      n.children.foreach(child => {
        if (i == child.i) return child
        if (i < child.i) return searchNode_sub(i, child)
      })
      return ontology
    }
  }
}
