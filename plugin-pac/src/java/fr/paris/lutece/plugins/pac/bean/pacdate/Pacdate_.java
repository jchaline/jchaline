package fr.paris.lutece.plugins.pac.bean.pacdate;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Pacdate.class )
public class Pacdate_
{
    public static volatile SingularAttribute<Pacdate, Integer> _id;
    public static volatile SingularAttribute<Pacdate, Integer> _idOwner;
    public static volatile SingularAttribute<Pacdate, String> _type;
    public static volatile SingularAttribute<Pacdate, Date> _dateConge;
}
