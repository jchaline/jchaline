package fr.paris.lutece.plugins.scala.bean.graph

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._

class DijkstraTest extends AssertionsForJUnit with MockitoSugar {
  
  val graph :Graph = new Graph()
  
  
  @Test
  def mockTest() {
    val mockGraph = mock[Graph]
    when(mockGraph.shortestWay("A", "B")).thenReturn(List("A", "B"))
    assert(List("A", "B") === mockGraph.shortestWay("A", "B"))
    verify(mockGraph).shortestWay("A", "B")
  }
  
  
  
  
}