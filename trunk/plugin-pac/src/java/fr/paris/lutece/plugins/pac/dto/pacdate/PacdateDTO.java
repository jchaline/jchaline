package fr.paris.lutece.plugins.pac.dto.pacdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.dto.GenericJPADTO;
import fr.paris.lutece.plugins.pac.dto.pacuser.PacuserDTO;


public class PacdateDTO extends GenericJPADTO<Integer, Pacdate>
{
    private Integer _id;
    private String _date;
    private String _type;
    private PacuserDTO _pacuser;
    
    /**
     * @return the date
     */
    public String getDate( )
    {
        return _date;
    }

    /**
     * @param dateConge the date to set
     */
    public void setDate( String date )
    {
        this._date = date;
    }

    /**
     * @return the id
     */
    public Integer getId( )
    {
        return _id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id )
    {
        this._id = id;
    }

    @Override
    public Pacdate convert( )
    {
        Pacdate target = _dozerMapper.map( this, Pacdate.class );
        return target;
    }

    public static PacdateDTO convert( Pacdate bean )
    {
        return _dozerMapper.map( bean, PacdateDTO.class );
    }

    public static List<PacdateDTO> convert( Set<Pacdate> setBean )
    {
        List<Pacdate> list = new ArrayList<Pacdate>( );
        list.addAll( setBean );
        return convert( list );
    }

    public static List<PacdateDTO> convert( List<Pacdate> listBean )
    {
        List<PacdateDTO> list = new ArrayList<PacdateDTO>( );
        for ( Pacdate pacuser : listBean )
        {
            list.add( _dozerMapper.map( pacuser, PacdateDTO.class ) );
        }
        return list;
    }

    /**
     * @return the type
     */
    public String getType( )
    {
        return _type;
    }

    /**
     * @param type the type to set
     */
    public void setType( String type )
    {
        this._type = type;
    }

    /**
     * @return the pacuser
     */
    public PacuserDTO getPacuser( )
    {
        return _pacuser;
    }

    /**
     * @param pacuser the pacuser to set
     */
    public void setPacuser( PacuserDTO pacuser )
    {
        this._pacuser = pacuser;
    }
}