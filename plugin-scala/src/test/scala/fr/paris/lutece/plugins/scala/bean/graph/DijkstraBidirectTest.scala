package fr.paris.lutece.plugins.scala.bean.graph

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._

class DijkstraBidirectTest extends AssertionsForJUnit with MockitoSugar {

  var graph: Graph = null

  @Before
  def init() {
    graph = new Graph()
  }
  
  def initNodes(graph:Graph){
    graph.addNode("A")
    graph.addNode("B")
    graph.addNode("C")
    graph.addNode("D")
    graph.addNode("E")
    graph.addNode("F")
    graph.addNode("G")
    graph.addNode("H")
    graph.addNode("I")
    graph.addNode("J")
  }
  
  def initLinks(graph:Graph){
    graph.addLink("A", "B", 85)
    graph.addLink("A", "C", 217)
    graph.addLink("A", "E", 173)
    graph.addLink("B", "F", 80)
    graph.addLink("C", "G", 186)
    graph.addLink("C", "H", 103)
    graph.addLink("H", "D", 183)
    graph.addLink("H", "J", 167)
    graph.addLink("F", "I", 250)
    graph.addLink("I", "J", 84)
    graph.addLink("E", "J", 502)
  }

  @After
  def clean() {
    graph = null
  }

  @Test
  def mockTest() {
    val mockGraph = mock[Graph]
    when(mockGraph.shortestWay("A", "B")).thenReturn(List("A", "B"))
    assert(List("A", "B") === mockGraph.shortestWay("A", "B"))
    verify(mockGraph).shortestWay("A", "B")
  }

  @Test
  def graphShortestWayTest() {
    fail("not yet implemented")
  }

  @Test
  def seeNeighborsTest() {
    initNodes(graph)
    var addNodeToA = graph.addLink("A", _:String, _:Int)
    
    var neighbors = graph.seeNeighbors("A")
    var nbNeighbors  = neighbors.size
    assertTrue(neighbors.size==0)
    addNodeToA("B",24)
    addNodeToA("C",17)
    
    assertTrue(neighbors.size==2)
  }
  
  @Test
  def staticMethodsTest(){
      fail("not yet implemented")
  }

}