object Base {
  class MyError(s: String) extends Throwable
  def error() = throw(new MyError("error"))
  def customError(s: String) = {
    println("ERROR: "+s)
    throw(new MyError(s))
  }

  class ErrorLog() {
    var log = ""
    def add(s: String) = log += s
    def addln(s: String = "") = log += s+"\n"
  }
}