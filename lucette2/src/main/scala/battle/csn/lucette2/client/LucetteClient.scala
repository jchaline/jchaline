package battle.csn.lucette2.client

import battle.csn.lucette2.network.RestClient
import org.apache.commons.cli.Options
import org.apache.commons.cli.{Option => OptionApache }
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.OptionGroup
import org.apache.commons.cli.DefaultParser
import battle.csn.lucette2.service.InitGameHelper
import battle.csn.lucette2.engine.FightStateMachine
import org.apache.commons.cli.ParseException

class LucetteClient {
  val TEAM_PASSWORD = "sopra123"
  val TEAM_NAME = "lucette"
  val APP_NAME = "RobotKiller"

  def main(args: Array[String]) {
    run(args)
  }

  val restClient = new RestClient()

  def run(args: Array[String]) {
    val options = getOptions()

    try {
      var cmd = new DefaultParser().parse(options, args)
      var teamId = getTeamName(cmd)
      
      // TODO : récupérer l'identidiant d'équipe
      if (cmd.hasOption("h")) {
        help(options)
      } else if (cmd.hasOption("p")) {
        pings()
      } else if (cmd.hasOption("m")) {
        var game = InitGameHelper.getGameId(teamId)
        new FightStateMachine(teamId, game).start()
      } else if (cmd.hasOption("e")) {
        var level = Integer.parseInt(cmd.getOptionValue("e"))
        if (level < 0 || level > 5) {
          System.out.println("Level must be between 0 and 5.")
          help(options)
        } else {
          var game = InitGameHelper.getTrainingGameId(teamId, level)
          new FightStateMachine(teamId, game).start()
        }
      }
    } catch {
      case e: ParseException => {

        System.out.println(e.getMessage())
        help(options)
      }
    }

  }

  def getTeamName(cmd: CommandLine) =
    {
      var team = ""
      if (cmd.hasOption("t")) {
        team = cmd.getOptionValue("t")
      } else {
        team = TEAM_NAME;
      }

      var mdp = ""
      if (cmd.hasOption("pwd")) {
        mdp = cmd.getOptionValue("pwd")
      } else {
        mdp = TEAM_PASSWORD;
      }

      new RestClient().getIdEquipe(team, mdp)
    }

  def help(options: Options) {
    new HelpFormatter().printHelp(APP_NAME, options)
  }

  def pings() {
    println("ping : " + restClient.ping())
  }

  def getOptions() =
    {
      var options = new Options()
      var optionE = OptionApache.builder("e").desc("Entrainement").longOpt("entrainement").numberOfArgs(1)
        .`type`(classOf[Int]).build()

      var optionM = OptionApache.builder("m").desc("Match").longOpt("match").build()
      var optionP = OptionApache.builder("p").desc("Ping - network test").longOpt("ping").build()
      var optionH = OptionApache.builder("h").desc("Help").longOpt("help").build();
      var optionT = OptionApache.builder("t").desc("team name (default value \"" + TEAM_NAME + "\")")
        .longOpt("team").numberOfArgs(1).`type`(classOf[String]).build()
      var optionPwd = OptionApache.builder("pwd").desc("team password (default value \"" + TEAM_PASSWORD + "\")")
        .longOpt("password").numberOfArgs(1).`type`(classOf[String]).build()
      var og = new OptionGroup()
      og.addOption(optionM)
      og.addOption(optionE)
      og.addOption(optionP)
      og.addOption(optionH)
      og.setRequired(true)
      options.addOptionGroup(og)
      options.addOption(optionT)
      options.addOption(optionPwd)
      options
    }
}