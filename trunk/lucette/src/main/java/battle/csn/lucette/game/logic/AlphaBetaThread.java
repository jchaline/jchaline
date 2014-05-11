package battle.csn.lucette.game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.Logger;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.structure.Move;

import com.google.common.base.Function;


public class AlphaBetaThread extends RecursiveTask<Integer> implements ILogic
{
    private static final Logger logger = Logger.getLogger( AlphaBetaThread.class );
    private static final long serialVersionUID = -5197231610232749425L;

    private IBoard<Integer> _plateau;
    private int _alpha, _beta;
    private Function<IBoard<Integer>, Integer> _heuristique;
    private boolean _findMax;
    private int _deep;
    private Move _move;

    public AlphaBetaThread( )
    {
    }

    /**
     * @param plateau le plateau à évaluer
     * @param alpha valeur minimum
     * @param beta valeur maximum
     * @param heuristique methode permettant d'évaluer un plateau
     * @param findMax boolean indiquant s'il faut chercher le meilleur ou le
     *            pire des plateau (pour l'un ou d'autre des joueurs)
     * @param deep profondeur maximum pour rechercher une valeur
     */
    public AlphaBetaThread( IBoard<Integer> plateau, int alpha, int beta,
            Function<IBoard<Integer>, Integer> heuristique, boolean findMax, int deep )
    {
        _plateau = plateau;
        _alpha = alpha;
        _beta = beta;
        _heuristique = heuristique;
        _findMax = findMax;
        _deep = deep;
    }

    /**
     * Méthode que nous allons utiliser pour les traitements
     * en mode parallèle.
     * @throws ScanException
     */
    public int parallelScan( )
    {
        int result = _findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        //List d'objet qui contiendra les sous-tâches créées et lancées
        List<AlphaBetaThread> list = new ArrayList<AlphaBetaThread>( );

        //ici il faut être capable de detecter si un plateau est gagnant ou perdant pour éviter de descendre trop profondement
        // si le plateau est gagnant/perdant, on retourne la valeur correspondante. Sinon, analyse des fils

        //Analyse des fils : pour chaque coup disponible, un nouveau thread est créé 

        List<Move> moveAvailables = _plateau.getMoveAvailables( );
        if ( moveAvailables.size( ) == 0 || _deep == 0 )
        {
            result = _heuristique.apply( _plateau );
        }
        else
        {
            for ( Move move : moveAvailables )
            {
                IBoard<Integer> deepCopy = _plateau.deepCopy( );
                deepCopy.play( move );

                AlphaBetaThread childThread = new AlphaBetaThread( deepCopy, _alpha * -1, _beta * -1, _heuristique,
                        !_findMax, _deep - 1 );

                //Give the first move to all childs
                if ( _move == null )
                {
                    childThread.setMove( move );
                }
                //reuse the first move 
                else
                {
                    childThread.setMove( _move );
                }
                //Nous l'ajoutons à la liste des tâches en cours pour récupérer le résultat plus tard
                list.add( childThread );

                //lance l'action en tâche de fond
                childThread.fork( );
            }
            //comparaison de tous les résultats
            for ( AlphaBetaThread t : list )
            {
                int childThreadResult = t.join( );
                if ( _findMax )
                {
                    if ( childThreadResult > result )
                    {
                        result = childThreadResult;
                        _move = t.getMove( );
                    }
                }
                else
                {
                    if ( childThreadResult < result )
                    {
                        result = childThreadResult;
                        _move = t.getMove( );
                    }
                }
            }
        }

        return result;
    }

    @Override
    protected Integer compute( )
    {
        int result = 0;
        result = parallelScan( );
        return result;
    }

    /**
     * @return the _plateau
     */
    public IBoard<Integer> getPlateau( )
    {
        return _plateau;
    }

    /**
     * @param _plateau the _plateau to set
     */
    public void setPlateau( IBoard<Integer> _plateau )
    {
        this._plateau = _plateau;
    }

    /**
     * @return the _alpha
     */
    public int getAlpha( )
    {
        return _alpha;
    }

    /**
     * @param _alpha the _alpha to set
     */
    public void setAlpha( int _alpha )
    {
        this._alpha = _alpha;
    }

    /**
     * @return the _beta
     */
    public int getBeta( )
    {
        return _beta;
    }

    /**
     * @param _beta the _beta to set
     */
    public void setBeta( int _beta )
    {
        this._beta = _beta;
    }

    /**
     * @return the _heuristique
     */
    public Function<IBoard<Integer>, Integer> getHeuristique( )
    {
        return _heuristique;
    }

    /**
     * @param _heuristique the _heuristique to set
     */
    public void setHeuristique( Function<IBoard<Integer>, Integer> _heuristique )
    {
        this._heuristique = _heuristique;
    }

    /**
     * @return the _findMax
     */
    public boolean isFindMax( )
    {
        return _findMax;
    }

    /**
     * @param _findMax the _findMax to set
     */
    public void setFindMax( boolean _findMax )
    {
        this._findMax = _findMax;
    }

    /**
     * @return the _deep
     */
    public int getDeep( )
    {
        return _deep;
    }

    /**
     * @param _deep the _deep to set
     */
    public void setDeep( int _deep )
    {
        this._deep = _deep;
    }

    /**
     * @return the move
     */
    public Move getMove( )
    {
        return _move;
    }

    /**
     * @param move the move to set
     */
    public void setMove( Move move )
    {
        this._move = move;
    }
}
