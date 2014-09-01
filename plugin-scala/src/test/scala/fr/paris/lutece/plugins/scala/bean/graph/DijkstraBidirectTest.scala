package fr.paris.lutece.plugins.scala.bean.graph

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._

class DijkstraBidirectTest extends AssertionsForJUnit with MockitoSugar {

  var graph: Graph = _

  @Before
  def init() {
    graph = new Graph()
  }

  def initNodes(graph: Graph) {
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

  def initLinks(graph: Graph) {
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
    when(mockGraph.findShortestWay("A", "B")).thenReturn(List("A", "B"))
    assert(List("A", "B") === mockGraph.findShortestWay("A", "B"))
    verify(mockGraph).findShortestWay("A", "B")
  }

  @Test
  @Ignore
  def graphShortestWayTest() {
    initNodes(graph)
    initLinks(graph)

    var way = graph.findShortestWay("A", "J")

    var expected = List("A", "C", "H", "J")
    assert(way.equals(expected))
    
    way = graph.findShortestWay("A", "J")
    assert(way.equals(expected.reverse))
  }

  @Test
  def addNeighborsTest() {
    initNodes(graph)
    var addNeighbor = graph.addLink(_: String, _: String, 10)

    var neighbors = graph.getNeighbors("A")
    assertTrue(neighbors.size == 0)
    addNeighbor("A", "B")
    addNeighbor("A", "C")

    neighbors = graph.getNeighbors("A")
    assertTrue(neighbors.size == 2)

    addNeighbor("A", "B")
    neighbors = graph.getNeighbors("A")
    assertTrue(neighbors.size == 2)

    addNeighbor("D", "A")
    neighbors = graph.getNeighbors("A")
    assertTrue(neighbors.size == 3)
  }

}