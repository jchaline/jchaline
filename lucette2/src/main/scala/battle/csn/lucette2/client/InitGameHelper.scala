package battle.csn.lucette2.service

import _root_.battle.csn.lucette2.network.RestClient

object InitGameHelper {
  def getGameId(team:String)= {
    new RestClient().getGameId(team)
  }

  def getTrainingGameId(team:String , level:Integer )={
    new RestClient().getNewPracticeGame( team, level)
  }
}