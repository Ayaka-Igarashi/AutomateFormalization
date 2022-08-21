object CharacterMatching {
  def characterMatching(character: String, trans: List[(String, List[Term])]): List[Term] = {
    trans match {
      case (character, comList) :: rst => {
        character match {
          case "ASCII upper alpha" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if (c >= 0x0041 && c <= 0x005A) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII lower alpha" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if (c >= 0x0061 && c <= 0x007A) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII alpha" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if ((c >= 0x0041 && c <= 0x005A)||(c >= 0x0061 && c <= 0x007A)) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII alphanumeric" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if ((c >= 0x0030 && c <= 0x0039)||(c >= 0x0041 && c <= 0x005A)||(c >= 0x0061 && c <= 0x007A)) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII hex digit" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if ((c >= 0x0030 && c <= 0x0039)||(c >= 0x0041 && c <= 0x0046)||(c >= 0x0061 && c <= 0x0066)) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII upper hex digit" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if ((c >= 0x0030 && c <= 0x0039)||(c >= 0x0041 && c <= 0x0046)) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII lower hex digit" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if ((c >= 0x0030 && c <= 0x0039)||(c >= 0x0061 && c <= 0x0066)) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "ASCII digit" => {
            character match {
              case s if s.size == 1 => {
                val c = s.head
                if ((c >= 0x0030 && c <= 0x0039)) comList
                else characterMatching(character, rst)
              }
              case _ => characterMatching(character, rst)
            }
          }
          case "Anything else" => comList
          case "EOF" => {
            if (character == "<EOF>") comList
            else characterMatching(character, rst)
          }
          case s => {
            val re =  "(U\\+[0-9A-F][0-9A-F][0-9A-F][0-9A-F])".r
            re.findFirstIn(s) match {
              case Some(unicode) => {
                character match {
                  case s if s.size == 1 => {
                    val c = s.head
                    if (c == Utility.unicodeToInt(unicode)) comList
                    else characterMatching(character, rst)
                  }
                  case _ => characterMatching(character, rst)
                }
              }
              case None =>println("character error : " + s);characterMatching(character, rst)
            }
          }
        }
      }
      case Nil => println("match_error"); List()
    }
  }
}
