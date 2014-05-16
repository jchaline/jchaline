package battle.csn.lucette.game.board;

import java.util.ArrayList;
import java.util.List;

import battle.csn.lucette.game.structure.Move;
import battle.csn.lucette.game.structure.Serie;


/**
 * Plateau du Tic Tac Toe, also called morpion !
 */
public class TictactoeBoard extends AbstractBoard<Integer>
{

    public static final int POS_X = 0;
    public static final int POS_Y = 1;
    public static final int INDEX_EQUIPE_BLANC = 0;
    public static final int INDEX_EQUIPE_NOIR = 1;

    @Override
    public List<Move> getMoveAvailables( )
    {
        List<Move> list = new ArrayList<Move>( );
        for ( int i = 0; i < _cases.getSizes( )[0]; i++ )
        {
            for ( int j = 0; j < _cases.getSizes( )[1]; j++ )
            {
                if ( _cases.get( i, j ) == Etat.EMPTY )
                {
                    list.add( new Move( i, j ) );
                }
            }
        }

        return list;
    }

    public TictactoeBoard( )
    {
        super( 3, 3 );
    }

    public TictactoeBoard( int x, int y )
    {
        super( 3, 3 );
    }

    @Override
    public void play( Move move )
    {
        if ( validateMove( move ) )
        {
            _cases.set( getIdEquipes( ).get( INDEX_EQUIPE_BLANC ).equals( move.getIdEquipe( ) ) ? Etat.WHITE
                    : Etat.BLACK, move.getPositions( ).get( POS_X ), move.getPositions( ).get( POS_Y ) );
        }
    }

    @Override
    protected boolean validateMove( Move move )
    {
        return true;
    }

    @Override
    public String getGameStatus( String idEquipe )
    {
        int typeEquipe = idEquipe.equals( getIdEquipes( ).get( 0 ) ) ? Etat.WHITE : Etat.BLACK;
        String status = AbstractBoard.ANNULE;
        if ( getMoveAvailables( ).size( ) == 0 )
        {
            if ( findSeries( 3, typeEquipe, true, true, true ).size( ) > 0 )
            {
                status = AbstractBoard.GAGNE;
            }
            else if ( findSeries( 3, typeEquipe * -1, true, true, true ).size( ) > 0 )
            {
                status = AbstractBoard.PERDU;
            }
        }
        return status;
    }

    /**
     * Obtention de toutes les series de coordonnées d'une taille donnée.
     * @param size nombre de coordonnées nécessaires pour accepter une series
     * @param typeCase
     * @param horiz true pour une recherche horizontal, false sinon
     * @param vert true pour une recherche vertical, false sinon
     * @param diag true pour une recherche diagonal, false sinon
     * @return les series
     */
    private List<Serie> findSeries( int size, int typeCase, boolean horiz, boolean vert, boolean diag )
    {
        List<Serie> series = new ArrayList<Serie>( );
        //les recherches sont effectués vers la droite & vers le bas
        for ( int i = 0; i < _cases.getSizes( )[0]; i++ )
        {
            for ( int j = 0; j < _cases.getSizes( )[1]; j++ )
            {
                if ( readCase( i, j ) == typeCase )
                {
                    //recherche de serie
                    if ( horiz )
                    {

                    }
                    if ( vert )
                    {

                    }
                    if ( diag )
                    {

                    }
                }
            }
        }
        return series;
    }

    private boolean isValide( int... coords )
    {
        boolean valide = false;
        valide = coords[0] >= 0 && coords[1] < _cases.getSizes( )[0];
        valide &= coords[1] >= 0 && coords[1] < _cases.getSizes( )[1];
        return valide;
    }

    @Override
    public void updateBoard( String strBoard )
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IBoard<Integer> deepCopy( )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBoard( )
    {
        // TODO Auto-generated method stub
        return null;
    }

}
