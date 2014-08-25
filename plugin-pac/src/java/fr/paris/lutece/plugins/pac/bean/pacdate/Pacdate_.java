package fr.paris.lutece.plugins.pac.bean.pacdate;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;

@StaticMetamodel( Pacdate.class )
public class Pacdate_
{
    public static volatile SingularAttribute<Pacdate, Integer> _id;
    public static volatile SingularAttribute<Pacdate, Pacuser> _pacuser;
    public static volatile SingularAttribute<Pacdate, String> _type;
    public static volatile SingularAttribute<Pacdate, Date> _date;
}
