import org.junit.Test
import org.junit.Assert.*

import TestInterpreter._
import TestUtils._

import Environment._

class MyTest:
  @Test 
  def testExample(): Unit = {
    // correct_value , value
    val msg = "I was compiled by Scala 3. :)"
    assertEquals("I was compiled by Scala 3. :)", msg)
    assertEquals(3+4, 7)
  }
  @Test
  def testExample2(): Unit = {
    assertEquals(string2charlist("pu"),IList(List(IChar('p'), IChar('u'))))
  }

  @Test
  def testContentModelFlags(): Unit = {
    init()
    val json = parseJson("src/html_test_files/pendingSpecChanges.test")
    // val json = parseJson("src/html_test_files/_a.test")
    val bool = doTest((json\"tests")(0))
  }