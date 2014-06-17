package battle.csn.lucette.controller;

import java.util.HashMap;
import java.util.Map;

import battle.csn.lucette.checker.Board;
import battle.csn.lucette.checker.Move;
import battle.csn.lucette.checker.StupidIA;


/**
 * Sinleton en charge de la maintenance en mémoire des parties
 * @author michael
 * 
 */
public class Controller
{

    public static int numPartie = 0;

    public static Map<Integer, Board> parties = new HashMap<Integer, Board>( );
    public static Map<Integer, String> equipes = new HashMap<Integer, String>( );
    public static Map<Integer, Integer> levels = new HashMap<Integer, Integer>( );
    public static Map<Integer, Move> lastMoves = new HashMap<Integer, Move>( );

    public static int newPartie( String idEquipe, String level )
    {
        numPartie++;
        Board board = new Board( );
        board.reset( );
        parties.put( numPartie, board );
        equipes.put( numPartie, idEquipe );
        levels.put( numPartie, Integer.parseInt( level ) );
        return numPartie;
    }

    /**
     * Retourne la derniere partie créé par cette équipe.
     * @param idEquipe
     * @return
     */
    public static String next( String idEquipe )
    {
        Integer max = Integer.MIN_VALUE;
        for ( Integer idPartie : equipes.keySet( ) )
        {
            if ( equipes.get( idPartie ).equals( idEquipe ) )
            {
                if ( idPartie > max )
                {
                    max = idPartie;
                }
            }
        }
        if ( max > Integer.MIN_VALUE )
            return max.toString( );
        return "NA";
    }

    public static String status( String idPartie, String idEquipe )
    {
        int partie = Integer.parseInt( idPartie );
        for ( Integer currentPartie : equipes.keySet( ) )
        {
            //inutile ici car il n'y a qu'un joueur 
            if ( currentPartie.equals( partie ) && equipes.get( partie ).equals( idEquipe ) )
            {
                Board b = parties.get( partie );
                if ( b.getPossibleMoves( ).size( ) == 0 )
                {
                    //game is over 
                    if ( b.evaluateBoard( ) >= 0 )
                    {
                        return "GAGNE";
                    }
                    else
                    {
                        return "PERDU";
                    }
                }
                else if ( b.getTurn() == Board.WHITE )
                {
                    return "OUI";
                }
                else
                {
                    return "NON";
                }
            }
        }
        throw new RuntimeException( "Impossible de trouver la partie " + idPartie + "/" + idEquipe );
    }

    public static String board( String idPartie )
    {
        int partie = Integer.parseInt( idPartie );
        Board b = parties.get( partie );
        if ( b != null )
        {
            return b.toString( );
        }
        else
        {
            throw new RuntimeException( "Partie " + partie + " introuvable" );
        }
    }

    public static String lastMove( String idPartie )
    {
        int partie = Integer.parseInt( idPartie );
        if ( parties.get( partie ) == null )
        {
            throw new RuntimeException( "Partie " + partie + " introuvable" );
        }
        Move move = lastMoves.get( partie );
        if ( move == null )
        {
            return "NA";
        }
        else
        {
            return move.toString( );
        }
    }

    public static String move( final String idPartie, String idEquipe, String x1, String y1, String x2, String y2 )
    {
        final int partie = Integer.parseInt( idPartie );
        //idEquipe ici ne sert a rien car on sait toujours qui joue
        int _x1 = Integer.parseInt( x1 );
        int _y1 = Integer.parseInt( y1 );
        int _x2 = Integer.parseInt( x2 );
        int _y2 = Integer.parseInt( y2 );
        final Board b = parties.get( partie );
        if ( b != null )
        {
            if ( b.getTurn() == Board.BLACK )
            {
                return "PTT";
            }
            else
            {
                try
                {
                    b.move( _x1, _y1, _x2, _y2 );
                    lastMoves.put( partie, new Move( _x1, _y1, _x2, _y2 ) );
                    //on appelle l'IA maintenant dans un nouveau Thread
                    new Thread( new Runnable( )
                    {
                        public void run( )
                        {
                            Move lastMove = StupidIA.findBestMove( Board.BLACK, b, levels.get( partie ) );
                            b.move( lastMove );
                            lastMoves.put( partie, lastMove );
                        }
                    } ).start( );
                    return "OK";
                }
                catch ( Exception e )
                {
                    e.printStackTrace( );
                    return "KO";
                }
            }
        }
        else
        {
            throw new RuntimeException( "Partie " + partie + " introuvable" );
        }
    }

}
