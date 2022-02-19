object Base {
  class MyError(s: String) extends Throwable
  def error() = throw(new MyError("error"))
  def customError(s: String) = throw(new MyError(s))
}