package fr.paris.lutece.plugins.scala.service.artifact

import org.scalatest.junit.JUnitRunner
import org.mockito.runners.MockitoJUnitRunner
import org.scalatest.junit.AssertionsForJUnit
import org.scalatest.mock.MockitoSugar
import org.junit.runner.RunWith
import fr.paris.lutece.plugins.scala.dao.artifact.ArtifactDAO
import org.mockito.Mock
import org.mockito.InjectMocks
import org.junit.Test
import org.mockito.Mockito._
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import net.sf.json.JSONSerializer

@RunWith(classOf[MockitoJUnitRunner])
class ArtifactServiceTest extends AssertionsForJUnit with MockitoSugar {

  @Mock
  var artifactDAO: ArtifactDAO = null

  @InjectMocks
  var artifactService: ArtifactService = null
  
  @Test
  def toJsonTest(){
    var a1 = new Artifact()
    a1._artifactId="plugin-form"
    a1._version="3.0.0"
    a1._groupId="fr.paris.lutece.plugins"
    var a2 = new Artifact()
    a2._artifactId="plugin-form"
    a2._version="4.0.0"
    a2._groupId="fr.paris.lutece.plugins"
    
    var json = JSONSerializer.toJSON(a1)
    println(json)
    var list = List(a1,a2)
    var jsonList = JSONSerializer.toJSON(list)
    println(jsonList)
  }

  @Test
  def findByStrKeyTest() {
    var idBean = "1"
      
    when(artifactDAO.findByStrPrimaryKey(idBean)).thenReturn(new Artifact())
    var bean = artifactService.findByStrPrimaryKey(idBean)
    assert(bean != null)
  }
}