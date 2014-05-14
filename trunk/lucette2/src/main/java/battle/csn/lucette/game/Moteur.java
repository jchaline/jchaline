package battle.csn.lucette.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import battle.csn.lucette.game.board.AbstractBoard;
import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.bot.IBot;
import battle.csn.lucette.game.logic.ILogic;
import battle.csn.lucette.game.structure.Move;


public class Moteur<T>
{
    private static final String SPRING_BEANS_XML = "SpringBeans.xml";

    private ApplicationContext context;
    protected List<String> _idEquipes = new ArrayList<String>( );
    private IBoard<T> _plateau;
    private String _idPartie;
    private IBot<T> _bot;
    private List<Move> _moveList = new LinkedList<Move>( );
    private String _idGagnant = "";

    public Moteur( String idPartie )
    {
        context = new ClassPathXmlApplicationContext( SPRING_BEANS_XML );
        setIdPartie( idPartie );
        _plateau = context.getBean( IBoard.class );
        _bot = context.getBean( IBot.class );
        _bot.setPlateau( _plateau );
        ILogic bean = (ILogic) context.getBean( "negamax" );
        
        _bot.setLogic( bean);
    }

    /**
     * Determine quel est le coup que jouerait le bot à l'instant présent
     * @param idEquipe l'équipe pour qui le bot jouerait
     * @return le coup joué par le bot
     */
    public Move getBotMove( String idEquipe )
    {
        return _bot.chooseMove( idEquipe );
    }

    /**
     * Joue un coup humain
     * @param idEquipe identifiant de l'équipe jouant le coup
     * @param coords liste des coordonnées à jouer sur le plateau
     */
    public void jouerCoup( String idEquipe, Integer... coords )
    {
        Move move = new Move( coords );
        move.setIdEquipe( idEquipe );
        getPlateau( ).play( move );
        getPlateau( ).getGameStatus( idEquipe );
    }

    /**
     * @return the _idPartie
     */
    public String getIdPartie( )
    {
        return _idPartie;
    }

    /**
     * @param idPartie the _idPartie to set
     */
    public void setIdPartie( String idPartie )
    {
        this._idPartie = idPartie;
    }

    /**
     * @return the _bot
     */
    public IBot<T> getBot( )
    {
        return _bot;
    }

    /**
     * @param bot the _bot to set
     */
    public void setBot( IBot<T> bot )
    {
        this._bot = bot;
    }

    /**
     * @return the _moveList
     */
    public List<Move> getMoveList( )
    {
        return _moveList;
    }

    /**
     * @param _moveList the _moveList to set
     */
    public void setMoveList( List<Move> _moveList )
    {
        this._moveList = _moveList;
    }

    /**
     * Ajout d'équipe au plateau, chaque type de plateau définissant ses propres
     * règles d'ajout d'équipe
     * @param idEquipe identifiant de la nouvelle équipe à ajouter
     */
    public void addEquipe( String idEquipe )
    {
        if ( _idEquipes.size( ) < 2 )
        {
            _idEquipes.add( idEquipe );
            //le moteur met à jour les equipes du plateau
            getPlateau( ).setIdEquipes( _idEquipes );
        }
        else
        {
            throw new UnsupportedOperationException( "Le nombre d'équipe est limité à 2 pour le tic tac toe !" );
        }
    }

    /**
     * SIMULATION du comportement du jeu distant
     * Donne le statut de la partie courante par rapport a l'équipe dont l'id
     * est fournit
     * @param idEquipe equipe qui souhaite obtenir le statut
     * @return retourne « OUI », « NON », « GAGNE », « PERDU » ou « ANNULE »
     */
    public String getGameStatus( String idEquipe )
    {
        String gameStatus = AbstractBoard.ANNULE;

        //premier prerequis, 2 equipes inscrites
        //deuxieme prerequis, faire partie de la liste des equipes inscrites
        if ( _idEquipes.size( ) == 2 && _idEquipes.contains( idEquipe ) )
        {
            //partie gagnée ?
            if ( StringUtils.isNotEmpty( this.getIdGagnant( ) ) )
            {
                if ( idEquipe.equals( this.getIdGagnant( ) ) )
                {
                    gameStatus = AbstractBoard.GAGNE;
                }
                else
                {
                    gameStatus = AbstractBoard.GAGNE;
                }
            }
            //Analyse des coups joué pour savoir a qui de jouer
            else
            {
                gameStatus = getPlateau( ).getGameStatus( idEquipe );
            }
        }
        return gameStatus;
    }

    /**
     * @return the _idGagnant
     */
    public String getIdGagnant( )
    {
        return _idGagnant;
    }

    /**
     * @param _idGagnant the _idGagnant to set
     */
    public void setIdGagnant( String _idGagnant )
    {
        this._idGagnant = _idGagnant;
    }

    /**
     * @return the _plateau
     */
    public IBoard<T> getPlateau( )
    {
        return _plateau;
    }

    /**
     * @param plateau the _plateau to set
     */
    public void setPlateau( IBoard<T> plateau )
    {
        this._plateau = plateau;
    }
}
