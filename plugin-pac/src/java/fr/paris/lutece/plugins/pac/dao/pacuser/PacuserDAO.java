package fr.paris.lutece.plugins.pac.dao.pacuser;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate_;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.bean.pacuser.PacuserFilter;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser_;
import fr.paris.lutece.plugins.pac.dao.AbstractPacDAO;
import fr.paris.lutece.plugins.pac.dao.commons.SQLUtils;
import fr.paris.lutece.plugins.pac.utils.commons.PacConstants;


/**
 * The Pacuser DAO class
 * @author jchaline
 */
public class PacuserDAO extends AbstractPacDAO<Integer, Pacuser> implements IPacuserDAO
{
    @Override
    protected void buildCriteriaQuery( GenericJPAFilter<Integer> abstractFilter, Root<Pacuser> root,
            CriteriaQuery<Pacuser> cq, CriteriaBuilder cb )
    {
        if ( abstractFilter instanceof PacuserFilter )
        {
            List<Predicate> listPredicates = new ArrayList<Predicate>( );

            PacuserFilter filter = (PacuserFilter) abstractFilter;

            if ( StringUtils.isNotBlank( filter.getNom( ) ) )
            {
                listPredicates.add( cb.like( root.get( Pacuser_._strNom ),
                        SQLUtils.addPercentEnclosing( filter.getNom( ) ) ) );
            }
            if ( StringUtils.isNotBlank( filter.getPrenom( ) ) )
            {
                listPredicates.add( cb.like( root.get( Pacuser_._strPrenom ),
                        SQLUtils.addPercentEnclosing( filter.getPrenom( ) ) ) );
            }

            if ( filter.getDayPresent( ) != null )
            {
                SetJoin<Pacuser, Pacdate> pacdateJoin = root.join( Pacuser_._joursConges, JoinType.LEFT );
                //                Calendar calendar = new GregorianCalendar( );
                //                calendar.set( 2014, Calendar.APRIL, 3 );
                //                Date time = calendar.getTime( );
                // must convert into SQL date ? or remove second after day (hour, minutes, second)
                Predicate containDay = cb.equal( pacdateJoin.get( Pacdate_._date ), filter.getDayPresent( ) );
                Predicate isCongeDay = cb.equal( pacdateJoin.get( Pacdate_._type ), PacConstants.TYPE_DATE_CONGE );
                Predicate containCongeDay = cb.and( containDay, isCongeDay );

                //listPredicates.add( cb.not( cb.in( containCongeDay ) ) );
            }

            if ( !listPredicates.isEmpty( ) )
            {
                cq.where( listPredicates.toArray( new Predicate[listPredicates.size( )] ) );
            }
        }
    }

    @Override
    protected Class<Pacuser> getBeanClass( )
    {
        return Pacuser.class;
    }
}