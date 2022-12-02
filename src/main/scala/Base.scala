object Base {
  class MyError(s: String) extends Throwable {
    var message = ""
  }
  def error() = throw(new MyError("error"))
  def customError(s: String) = {
    customErrorLogs.addln("ERROR: "+s)
    val e = new MyError(s)
    e.message = s
    throw(e)
  }

  val customErrorLogs = new ErrorLog()

  class ErrorLog() {
    var log = ""
    def add(s: String) = log += s
    def addln(s: String = "") = log += s+"\n"
  }
}