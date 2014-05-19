package battle.csn.lucette.game.board;

import java.util.List;

import battle.csn.lucette.game.structure.Move;


/**
 * Interface d'un plateau standard utilisé par le jeu local pour simuler et
 * proposer des coups à jouer
 * Un plateau peut avoir besoin de connaitre les joueurs inscrits pour savoir comment joueur les coups,
 * il peut donc acceder à la liste des joueurs.
 * @author Jeremy
 * 
 */
public interface IBoard<T>
{
    /**
     * Joue un coup si possible sur le plateau courant pour l'equipe idEquipe
     * @param move coup joué, pas de position car abstraction du type de coup
     */
    void play(Move move);
    
    /**
     * SIMULATION du comportement du jeu distant
     * Retourne le plateau courant sous forme textuel pour être parsé
     * @return le plateau sous forme texte
     */
    String getBoard();
    
    /**
     * Copie compléte d'un plateau
     * @return plateau copié
     */
    IBoard<T> deepCopy();
    
    /**
     * SIMULATION du comportement du jeu distant
     * Fournit le login utilisé par le plateau pour référencer l'équipe dont le nom est fournit en parametre
     * @param pseudo nom de l'équipe
     * @return l'identifiant de l'équipe
     */
    String getIdEquipe(String pseudo);
    
    /**
     * Permet d'obtenir l'ensemble des coups jouables à un instant t.
     * Il n'est pas nécessaire de fournir un identifiant de joueur,
     * c'est le plateau qui se charge de définir "a qui de jouer"
     * @return la liste des déplacements disponible pour le joueur courant
     */
    List<Move> getMoveAvailables( );

    /**
     * Créer les cases nécessaires pour obtenir un plateau de la taille XxY
     * Attention, cette methode reinitilise complétement le plateau
     * @param sizes tailles 
     */
    void updateSize( int...sizes );

    /**
     * Lire une case du plateau en fonction des coordonnées (x,y)
     * @param x abscisse de la case
     * @param y ordonnée de la case
     * @return la case
     */
    T readCase( int...sizes );
    
    /**
     * @return the _idEquipes
     */
    public List<String> getIdEquipes( );

    /**
     * @param _idEquipes the _idEquipes to set
     */
    public void setIdEquipes( List<String> _idEquipes );

    /**
     * Fournit le statut d'un plateau pour une équipe
     * @param idEquipe
     * @return
     */
    String getGameStatus(String idEquipe);
    
    /**
     * Permet d'affecter une valeur à une case du jeu
     * @param value valeur à affecter
     * @param x abscisse
     * @param y ordonnée
     */
    void writeCase(T value, int...dimensions);
    
    /**
     * Met à jour le plateau via une chaine de caractère
     * @param strBoard le plateau sous forme de string
     */
    void updateBoard(String strBoard);
}
