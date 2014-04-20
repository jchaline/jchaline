package fr.paris.lutece.plugins.scala.bean.graph

class Node(name: String) {

  private var neighbors: Map[String, Int] = Map()

  /**
   * Add neighbor to the current node
   * @param nodeName the name of the node neighbor
   * @param distance the distance between the node and his neighbor
   */
  def addNeighbor(nodeName: String, distance: Int): Unit = {
  }

  /**
   * Add neighbor to the current node
   * @param node the node neighbor
   * @param distance the distance between the node and his neighbor
   */
  def addNeighbor(node: Node, distance: Int): Unit = {
  }

  /**
   * Remove neighbor from the current node
   * @param nodeName the name of the node neighbor
   * @return the distance or -1 if neighbor doesn't exist
   */
  def delNeighbor(nodeName: String): Int = {
    -1
  }

  /**
   * Remove neighbor from the current node
   * @param node the node neighbor
   * @return the distance or -1 if neighbor doesn't exist
   */
  def delNeighbor(nodeName: Node): Int = {
    -1
  }

}