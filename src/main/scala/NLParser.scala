object NLParser {
    import scala.collection.mutable._
    import scala.sys.process._

    def parse() = {
        val out = ArrayBuffer[String]()
        val err = ArrayBuffer[String]()
        val logger = ProcessLogger(
            (o: String) => out += o,
            (e: String) => err += e)

        val process = Process("sh parse.sh") !! logger
        
        println("--out--")
        println(out)
        println("--err--")
        println(err)
        println("--finished nlparsing--")
    }
}