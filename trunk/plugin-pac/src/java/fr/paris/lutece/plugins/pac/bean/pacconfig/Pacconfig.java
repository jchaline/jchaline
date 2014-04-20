package fr.paris.lutece.plugins.pac.bean.pacconfig;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean;


/**
 * the Pacdate bean
 * @author jchaline
 */
@Entity
@Table( name = "pac_config" )
public class Pacconfig extends AbstractBean<Integer>
{
    private static final long serialVersionUID = -6154572655913748217L;
    public static final String TABLE = "pac_config";

    @Id
    @TableGenerator( table = "pac_sequences", name = "pac_config_sequence", pkColumnValue = "pac_config_id", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pac_config_sequence" )
    @Column( name = "id" )
    private Integer _id;

    @Column( name = "dayFrequency" )
    private Integer _dayFrequency;
    
    @Column( name = "monthFrequency" )
    private Integer _monthFrequency;

    @Column( name = "dayWait" )
    private Integer _dayWait;
    
    @Column( name = "monthWait" )
    private Integer _monthWait;

    @Column( name = "team" )
    private String _team;

    @Column( name = "firstDate" )
    private Date _firstDate;
    
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
     * @return the firstDate
     */
    public Date getFirstDate( )
    {
        return _firstDate;
    }

    /**
     * @param firstDate the firstDate to set
     */
    public void setFirstDate( Date firstDate )
    {
        this._firstDate = firstDate;
    }

    /**
     * @return the _monthFrequency
     */
    public Integer getMonthFrequency( )
    {
        return _monthFrequency;
    }

    /**
     * @param monthFrequency the _monthFrequency to set
     */
    public void setMonthFrequency( Integer monthFrequency )
    {
        this._monthFrequency = monthFrequency;
    }

    /**
     * @return the _dayFrequency
     */
    public Integer getDayFrequency( )
    {
        return _dayFrequency;
    }

    /**
     * @param dayFrequency the _dayFrequency to set
     */
    public void setDayFrequency( Integer dayFrequency )
    {
        this._dayFrequency = dayFrequency;
    }

    /**
     * @return the dayWait
     */
    public Integer getDayWait( )
    {
        return _dayWait;
    }

    /**
     * @param dayWait the dayWait to set
     */
    public void setDayWait( Integer dayWait )
    {
        this._dayWait = dayWait;
    }

    /**
     * @return the monthWait
     */
    public Integer getMonthWait( )
    {
        return _monthWait;
    }

    /**
     * @param monthWait the monthWait to set
     */
    public void setMonthWait( Integer monthWait )
    {
        this._monthWait = monthWait;
    }

}
