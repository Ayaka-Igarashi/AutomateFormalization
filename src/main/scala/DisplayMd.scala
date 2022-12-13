object DisplayMd {
  def displayInMd(str: String): String = {
    str.replace("\n","\n\n")
  }
  def inRed(str: String): String = {
    str.split("\n").map(s => "<span style=\"color: red; \">%s</span>".format(s)).mkString("\n")
  }
  def inBlue(str: String): String = {
    str.split("\n").map(s => "<span style=\"color: blue; \">%s</span>".format(s)).mkString("\n")
  }
}
