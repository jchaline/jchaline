package fr.paris.lutece.plugins.scala.bean.graph

class Graph(name:String="Graph", bidirectional:Boolean=true) {
  var nodes: Map[String,Node] = Map()

  def shortestWay(node1: String, node2: String): List[String] = {
    null
  }
  
  def addNode(nameNode:String){
    
  }
  
  def addLink(name1:String,name2:String,distance:Int){
    
  }
  
  def removeLink(name1:String, name2:String){
    
  }
  
  def distance(node1:String, node2:String):Int={
    -1
  }
  
  def seeNeighbors(name:String):Map[String,Int]={
    null
  }
}