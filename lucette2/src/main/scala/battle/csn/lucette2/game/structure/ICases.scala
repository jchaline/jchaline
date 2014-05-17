package battle.csn.lucette2.game.structure

trait ICases[T] {
/**
     * Demande la valeur d'une case
     * @param dimensions coordonnées de la valeur
     * @return valeur
     */
    def get(dimensions:Seq[Int]):Option[T]
    
    /**
     * Set la valeur d'une case
     * @param value valeur à set
     * @param dimensions coordonnées de la valeur
     */
    def set(value:T, dimensions:Seq[Int]):Unit
    
    /**
     * Retourne les tailles de chaque dimensions
     * @return un tableau d'entier
     */
    def getSizes():Seq[Int]
    
    /**
     * Redimensionne le plateau
     * @param dimensions nouvelles tailles
     */
    def updateSizes(dimensions:Seq[Int]):Unit
}