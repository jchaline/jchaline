package fr.paris.lutece.plugins.scala.bean.graph

class Graph(name:String="Graph", bidirectional:Boolean=true) {
  var nodes: Map[String,Node] = Map()

  def shortestWay(node1: String, node2: String): List[String] = {
    null
  }
  
  def addNode(nameNode:String){
    nodes += nameNode -> new Node(nameNode)
  }
  
  def addLink(name1:String,name2:String,distance:Int){
    nodes(name1).addNeighbor(name2, distance)
    if(bidirectional){
      nodes(name2).addNeighbor(name1, distance)
    }
  }
  
  def removeLink(name1:String, name2:String){
    nodes(name1).delNeighbor(name2)
    if(bidirectional){
      nodes(name2).delNeighbor(name1)
    }
  }
  
  def distance(node1:String, node2:String):Int={
    -1
  }
  
  def seeNeighbors(name:String):Map[String,Int]={
    null
  }
}

object Graph{
  def size(graph:Graph){
    println("test")
  }
}