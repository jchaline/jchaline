package fr.paris.lutece.plugins.scala.web

import fr.paris.lutece.portal.web.system.PluginJspBean

class PacuserJspBean extends PluginJspBean {

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