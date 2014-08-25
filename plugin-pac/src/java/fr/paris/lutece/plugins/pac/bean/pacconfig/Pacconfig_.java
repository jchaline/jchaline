package fr.paris.lutece.plugins.pac.bean.pacconfig;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Pacconfig.class )
public class Pacconfig_
{
    public static volatile SingularAttribute<Pacconfig, Integer> _id;
    public static volatile SingularAttribute<Pacconfig, String> _team;
    public static volatile SingularAttribute<Pacconfig, Integer> _dayFrequency;
    public static volatile SingularAttribute<Pacconfig, Integer> _monthFrequency;
    public static volatile SingularAttribute<Pacconfig, Integer> _dayWait;
    public static volatile SingularAttribute<Pacconfig, Integer> _monthWait;
    public static volatile SingularAttribute<Pacconfig, Date> _firstDate;
}
