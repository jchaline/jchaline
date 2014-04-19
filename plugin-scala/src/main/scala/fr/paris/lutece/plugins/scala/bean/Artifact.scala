package fr.paris.lutece.plugins.scala.bean

import org.hibernate.validator.constraints.NotEmpty

class Artifact {
  
  @NotEmpty
  var version="";
  var group="";
  var id="";
  
  

}