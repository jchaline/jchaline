package fr.paris.lutece.plugins.scala.dao.artifact

import scala.collection.mutable.MutableList

import org.apache.commons.lang.StringUtils
import org.springframework.stereotype.Repository

import fr.paris.lutece.plugins.genericjpa.bean.AbstractFilter
import fr.paris.lutece.plugins.genericjpa.dao.AbstractDAO
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.bean.artifact.ArtifactFilter
import fr.paris.lutece.plugins.scala.service.ScalaPlugin
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository("scala.artifactdao")
class ArtifactDAO extends AbstractDAO[Int, Artifact] {

  protected override def buildCriteriaQuery(abstractFilter: AbstractFilter[Int], root: Root[Artifact],
    cq: CriteriaQuery[Artifact], cb: CriteriaBuilder) =
    {
    try{
      
      if (abstractFilter.isInstanceOf[ArtifactFilter]) {
        val filter = abstractFilter.asInstanceOf[ArtifactFilter]
        var listPredicates = new MutableList[Predicate]

        if (StringUtils.isNotBlank(filter.getArtifactId)) {
//          listPredicates += cb.like(root.get(null), filter.getArtifactId)
        }
        if (StringUtils.isNotBlank(filter.getGroupId)) {
//          listPredicates += cb.like(root.get(null), filter.getGroupId)
        }

        if (!listPredicates.isEmpty) {
          //cq.where(listPredicates(0), listPredicates(1));
        }
      }
    }
    catch{
      case e:Exception => println(e)
    }
    }

  def getBeanClass(): Class[Artifact] = {
    classOf[Artifact]
  }

  def getPluginName(): String = {
    ScalaPlugin.PLUGIN_NAME
  }

  def findByStrPrimaryKey(key: String): Artifact = {
    null
  }
}