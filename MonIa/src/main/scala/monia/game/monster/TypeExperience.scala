package monia.game.monster

object TypeExperience extends Enumeration{
    type TypeExperience = Value
    val Starter, Fast, Normal, Slow = Value
    
    def get(name:String)={
      name.toLowerCase() match{
        case "starter" => Starter
        case "fast" => Fast
        case "slow" => Slow
        case _ => Normal
      }
    }
}