package fr.paris.lutece.plugins.scala.dao;

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
import scala.collection.mutable.MutableList

@RunWith(classOf[MockitoJUnitRunner])
class PredicateTest {

  @Test
  def listToSeqTest(){
    var list = MutableList[Int]()
    list+=2
    list+=3
    var test = list.toSeq
    useVarArgs(test:_*)
  }
  
  
  def useVarArgs(args:Int*){
    println(args)
  }
}
