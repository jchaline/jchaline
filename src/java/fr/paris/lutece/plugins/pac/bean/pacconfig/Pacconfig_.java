package fr.paris.lutece.plugins.pac.bean.pacconfig;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Pacconfig.class )
public class Pacconfig_
{
    public static volatile SingularAttribute<Pacconfig, Integer> _id;
    public static volatile SingularAttribute<Pacconfig, String> _team;
    public static volatile SingularAttribute<Pacconfig, String> _frequency;
}
