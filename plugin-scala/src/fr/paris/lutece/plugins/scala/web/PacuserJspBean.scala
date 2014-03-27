package fr.paris.lutece.plugins.scala.web

class PacuserJspBean {

  def test(): Unit = {
  }

  var x: Int = 0
  var y: Int = 0
  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
  }
  override def toString(): String = "(" + x + ", " + y + ")";
}