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

@RunWith(classOf[MockitoJUnitRunner])
class ArtifactServiceTest extends AssertionsForJUnit with MockitoSugar {

  @Mock
  var artifactDAO: ArtifactDAO = null

  @InjectMocks
  var artifactService: ArtifactService = null

  @Test
  def findByStrKeyTest() {
    var idBean = "1"
      
    when(artifactDAO.findByStrPrimaryKey(idBean)).thenReturn(new Artifact())
    var bean = artifactService.findByStrPrimaryKey(idBean)
    assert(bean != null)
  }
}