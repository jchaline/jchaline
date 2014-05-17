package battle.csn.lucette2.game.structure

import org.apache.commons.lang3.ArrayUtils

class  MultiTab[T] extends ICases[T] {
  var deep: Int = _
  var value: Option[T] = _
  var subTab= List[MultiTab[T]]()
  var dimensions: Seq[Int] = _

  /**
   * Get the sub tab
   * @param index the index of the sub tab
   * @return the tab
   */
  private def getSubTab(index: Int): MultiTab[T] = subTab(index)

  def MultiTab() = this.deep = 1

  def getSizes() = dimensions

  def get(dimensions: Seq[Int]) = {
    if (dimensions.length == 0) {
      value 
    } else {
      getSubTab(dimensions(0)).get(dimensions.drop(0))
    }
  }
  
  def set(value: T, dimensions: Seq[Int]) = if (dimensions.length == 0) this.value = Some(value) else getSubTab(dimensions(0)).set(value, dimensions.drop(0));

  @Override
  def updateSizes(dimensions: Seq[Int]) =
    {
      this.dimensions = dimensions;
      this.deep = dimensions.length;
      if (dimensions.length > 0) {
        var dimensionsUpdate = dimensions.drop(1);
        this.subTab = List[MultiTab[T]]()
        var i = 0
        for (i <- 0 to dimensions(0)-1) {
          var t = new MultiTab[T]()
          t.updateSizes(dimensionsUpdate);
          this.subTab :+ t
        }
      } else {
        this.value = None
      }
    }

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
          result += this.getSubTab(i).get(Seq());
        }
      }
      result;
    }
}