package fr.paris.lutece.plugins.pac.bean.pacuser;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;

/**
 * The Pacuser class for JPA and DAO
 * @author jchaline
 */
@StaticMetamodel( Pacuser.class )
public class Pacuser_ {
	public static volatile SingularAttribute<Pacuser, Integer> _id;
	public static volatile SingularAttribute<Pacuser, String> _guid;
	public static volatile SingularAttribute<Pacuser, String> _strNom;
	public static volatile SingularAttribute<Pacuser, String> _strPrenom;
	public static volatile SingularAttribute<Pacuser, Date> _dernierPac;
	public static volatile SingularAttribute<Pacuser, Date> _dateSortie;
	public static volatile SingularAttribute<Pacuser, Date> _dateEntree;
	public static volatile SetAttribute<Pacuser, Pacdate> _joursConges;
}