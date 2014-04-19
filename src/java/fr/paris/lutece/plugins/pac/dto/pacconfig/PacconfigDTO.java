package fr.paris.lutece.plugins.pac.dto.pacconfig;

import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.dto.GenericJPADTO;


public class PacconfigDTO extends GenericJPADTO<Integer, Pacconfig>
{
    private Integer _id;
    private Integer _dayFrequency;
    private Integer _monthFrequency;
    private String _team;
    private String _firtDate;

    public PacconfigDTO( Integer dayFrequency, Integer monthFrequency, String team )
    {
        this._dayFrequency = dayFrequency;
        this._monthFrequency = monthFrequency;
        this._team = team;
    }

    @Override
    public Integer getId( )
    {
        return _id;
    }

    @Override
    public void setId( Integer key )
    {
        _id = key;
    }

    @Override
    public Pacconfig convert( )
    {
        Pacconfig target = _dozerMapper.map( this, Pacconfig.class );
        return target;
    }

    public static PacconfigDTO convert( Pacconfig bean )
    {
        return _dozerMapper.map( bean, PacconfigDTO.class );
    }

    public static List<PacconfigDTO> convert( List<Pacconfig> listBean )
    {
        List<PacconfigDTO> list = new ArrayList<PacconfigDTO>( );
        for ( Pacconfig bean : listBean )
        {
            list.add( _dozerMapper.map( bean, PacconfigDTO.class ) );
        }
        return list;
    }

    /**
     * @return the team
     */
    public String getTeam( )
    {
        return _team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam( String team )
    {
        this._team = team;
    }

    /**
     * @return the firtDate
     */
    public String getFirtDate( )
    {
        return _firtDate;
    }

    /**
     * @param firtDate the firtDate to set
     */
    public void setFirtDate( String firtDate )
    {
        this._firtDate = firtDate;
    }

    /**
     * @return the dayFrequency
     */
    public Integer getDayFrequency( )
    {
        return _dayFrequency;
    }

    /**
     * @param dayFrequency the dayFrequency to set
     */
    public void setDayFrequency( Integer dayFrequency )
    {
        this._dayFrequency = dayFrequency;
    }

    /**
     * @return the monthFrequency
     */
    public Integer getMonthFrequency( )
    {
        return _monthFrequency;
    }

    /**
     * @param monthFrequency the monthFrequency to set
     */
    public void setMonthFrequency( Integer monthFrequency )
    {
        this._monthFrequency = monthFrequency;
    }

}
