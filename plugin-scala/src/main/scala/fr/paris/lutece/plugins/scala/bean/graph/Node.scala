package fr.paris.lutece.plugins.scala.bean.graph

class Node(name: String) {

  private var neighbors: Map[String, Int] = Map()

  def getNeighbors() = neighbors

  /**
   * Add neighbor to the current node
   * @param nodeName the name of the node neighbor
   * @param distance the distance between the node and his neighbor
   */
  def addNeighbor(name: String, distance: Int): Unit = {
    if (!neighbors.contains(name) || neighbors(name) > distance) {
      neighbors += name -> distance
    }
  }

  /**
   * Remove neighbor from the current node
   * @param nodeName the name of the node neighbor
   * @return the distance or -1 if neighbor doesn't exist
   */
  def delNeighbor(name: String): Int = {
    var result = -1
    if (neighbors.contains(name)) {
      result = neighbors(name)
      neighbors -= name
    }
    result
  }
}