package battle.csn.lucette.game.board;

/**
 * Interface à implementer pour proposer une classe enregistrant les cases d'un plateau de jeu à n dimension
 * @author Jeremy
 *
 * @param <T>
 */
public interface ICases<T>
{
    /**
     * Demande la valeur d'une case
     * @param dimensions coordonnées de la valeur
     * @return valeur
     */
    T get(int...dimensions);
    
    /**
     * Set la valeur d'une case
     * @param value valeur à set
     * @param dimensions coordonnées de la valeur
     */
    void set(T value, int...dimensions);
    
    /**
     * Retourne les tailles de chaque dimensions
     * @return un tableau d'entier
     */
    int[] getSizes();
    
    /**
     * Redimensionne le plateau
     * @param dimensions nouvelles tailles
     */
    void updateSizes(int...dimensions);
    
}
