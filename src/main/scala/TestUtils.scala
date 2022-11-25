import OutputParse._
import play.api.libs.json._

object TestUtils {
  class AAA() {
    //
  }
  def parseJson(filename: String): JsValue = {
    val s = load_file(filename)
    val json = Json.parse(s.mkString("\n"))
    json
  }
}