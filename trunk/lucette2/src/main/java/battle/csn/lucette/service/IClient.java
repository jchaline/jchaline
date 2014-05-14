package battle.csn.lucette.service;

import java.util.List;

/**
 * Interface respectant l'api de la battle
 * @author Jeremy
 *
 */
public interface IClient
{

    /**
     * Indique si c’est au tour du joueur concerné dans la partie concernée
     * Retourne « OUI », « NON », « GAGNE », « PERDU » ou « ANNULE »
     * 
     * @param idEquipe
     * @param idPartie
     * @return
     */
    String getGameStatus( String idPartie, String idEquipe );

    /**
     * Retourne le plateau de jeu de la partie concernée La chaine de caractère
     * retournée sera précisée lors de la battle "NA" si le plateau n'est pas
     * disponible
     * 
     * @param idPartie
     * @return
     */
    String getBoard( String idPartie );

    /**
     * Créer une partie contre l'IA au level specifié
     * 
     * @param idEquipe identifiant de l'équipe
     * @param level niveau de difficulté
     * @return une erreur le cas echéant, null si tout s'est bien passé
     */
    String getNewPracticeGame( String idEquipe, Integer level );

    /**
     * Demande au jeu quel est l'identifiant de la partie a laquelle l'équipe
     * doit participer
     * 
     * @param idEquipe identifiant de l'équipe
     * @param botGame null ou false pour jouer contre des joueurs, vrai pour
     *            jouer
     *            contre les bots
     * @return l'identifiant de la partie
     */
    public String getGameId( String idEquipe );

    /**
     * Methode de test de l'API pour vérifier son bon fonctionnement.
     * 
     * @return devrait retourner pong.
     */
    String ping( );

    /**
     * Cette méthode permet de tester que le client interprete bien le statut
     * d'erreur 403;
     * 
     * @return
     */
    int pingError403( );

    /**
     * Cette méthode permet de tester que le client interprete bien le statut
     * d'erreur 500;
     * 
     * @return
     */
    int pingError500( );
    
    /**
    * Retourne le dernier coup joué sur ce plateau "coordX,coordY" (détail
    * pendant la battle)
    * 
    * @param idPartie
    * @return
    */
    public String getLastMove( String idPartie );
    
    /**
     * Joue un coup dans la partie concernée, pour l’équipe concernée aux
     * coordonnées X et Y données Retourne "OK" si le coup est accepté "KO" si
     * le coup est refusé/interdit "GAGNE" si c'est le dernier coup gagnant
     * "PTT" (Pas Ton Tour) si ce n'est pas au tour du joueur
     * 
     * @param idPartie
     * @param idEquipe
     * @param coordX
     * @param coordY
     * @return
     */
    public String play( String idPartie, String idEquipe, List<Integer> coords );
    
    /**
     * Permet d'obtenir l'id de l'équipe.
     * 
     * @param nomEquipe
     * @param motDePasse
     * @return
     */
    public String getIdEquipe( String nomEquipe, String motDePasse );

}
