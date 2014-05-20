package battle.csn.lucette2.game.structure

import org.apache.commons.lang3.ArrayUtils
import scala.collection.mutable.MutableList

class  MultiTab[T] extends ICases[T] {
  var deep: Int = _
  var value: Option[T] = _
  var subTab= new MutableList[MultiTab[T]]()
  var dimensions: Seq[Int] = _
  var move:Move=_

  def MultiTab() = this.deep = 1

  def getSizes() = dimensions

  def get(dimensions: Seq[Int]) = {
    if (dimensions.length == 0) {
      value 
    } else {
      subTab(dimensions(0)).get(dimensions.drop(1))
    }
  }
  
  def set(value: T, dimensions: Seq[Int]) = {
    if (dimensions.length == 0) {
      this.value = Some(value) 
     }
    else {
      subTab(dimensions(0)).set(value, dimensions.drop(1))
    }
  }

  @Override
  def updateSizes(dimensions: Seq[Int]) =
    {
      this.dimensions = dimensions;
      this.deep = dimensions.length;
      if (dimensions.length > 0) {
        var dimensionsUpdate = dimensions.drop(1);
        var i = 0
        for (i <- 0 to dimensions(0)-1) {
          var t = new MultiTab[T]()
          t.updateSizes(dimensionsUpdate);
          this.subTab += t
        }
      } else {
        this.value = None
      }
    }
  
  def size()=subTab.size

  /**
   * @return the _deep
   */
  def getDeep = deep

  override def toString() =
    {
      var result = "";
      if (deep == 1) {
        var i = 0
        for (i <- 0 to dimensions(0)) {
          result += subTab(i).get(Seq());
        }
      }
      result;
    }
}