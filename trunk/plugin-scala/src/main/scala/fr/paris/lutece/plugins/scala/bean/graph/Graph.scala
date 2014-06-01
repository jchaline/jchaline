package fr.paris.lutece.plugins.scala.bean.graph

class Graph(name: String = "Graph", bidirectional: Boolean = true) {
  /**
   * All nodes members of the graph
   */
  var nodes: Map[String, Node] = Map()

  /**
   * The actual position
   */
  var position: String = null

  def findShortestWay(node1: String, node2: String): List[String] = {
    null
  }

  def addNode(nameNode: String) {
    nodes += nameNode -> new Node(nameNode)
  }

  def addLink(name1: String, name2: String, distance: Int) {
    nodes(name1).addNeighbor(name2, distance)
    if (bidirectional) {
      nodes(name2).addNeighbor(name1, distance)
    }
  }

  def removeLink(name1: String, name2: String) {
    nodes(name1).delNeighbor(name2)
    if (bidirectional) {
      nodes(name2).delNeighbor(name1)
    }
  }

  def distance(node1: String, node2: String): Int = {
    -1
  }

  def getNeighbors(name: String): Map[String, Int] = {
    if (nodes.contains(name)) {
      nodes(name).getNeighbors()
    } else {
      Map()
    }
  }

  /**
   * Get the actuals neighbors
   */
  def getNeighbors(): Map[String, Int] = {
    getNeighbors(position)
  }

  /**
   * Move to neighbors node and return the distance between the nodes
   */
  def move(name: String): Int = {
    var distance = -1
    if (getNeighbors().contains(name)) {
      position = name
      distance = getNeighbors()(name)
    }
    distance
  }
}

object Graph {
  def size(graph: Graph) {
    println("test")
  }
}