package fr.paris.lutece.plugins.pac.dao.pacdate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractFilter;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.bean.pacdate.PacdateFilter;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate_;
import fr.paris.lutece.plugins.pac.dao.AbstractPacDAO;
import fr.paris.lutece.portal.service.util.AppLogService;


public class PacdateDAO extends AbstractPacDAO<Integer, Pacdate> implements IPacdateDAO
{
    @Override
    protected void buildCriteriaQuery( AbstractFilter<Integer> abstractFilter, Root<Pacdate> root, CriteriaQuery<Pacdate> cq, CriteriaBuilder cb )
    {
        if ( abstractFilter instanceof PacdateFilter )
        {
            List<Predicate> listPredicates = new ArrayList<Predicate>( );

            // exemple for join
            //Join<Pacdate, Pacuser> pacuser = root.join( Pacdate_._idUser, JoinType.INNER );

            PacdateFilter filter = (PacdateFilter) abstractFilter;

            if ( filter.getIdOwner( ) != null )
            {
                listPredicates.add( cb.equal( root.get( Pacdate_._idOwner ), filter.getIdOwner( ) ) );
            }
            if ( StringUtils.isNotBlank( filter.getType( ) ) )
            {
                listPredicates.add( cb.equal( root.get( Pacdate_._type ), filter.getType( ) ) );
            }

            if ( filter.getDateMax( ) != null )
            {
                listPredicates.add( cb.lessThan( root.get( Pacdate_._date ), filter.getDateMax( ) ) );
            }
            
            if ( filter.getDateMin( ) != null )
            {
                listPredicates.add( cb.greaterThan( root.get( Pacdate_._date ), filter.getDateMin( ) ) );
            }

            if ( !listPredicates.isEmpty( ) )
            {
                cq.where( listPredicates.toArray( new Predicate[listPredicates.size( )] ) );
            }
        }
    }

    @Override
    public void removeWithOwnerId( Integer idOwner )
    {
        EntityManager em = getEM( );
        Query query = em.createNativeQuery( "DELETE FROM " + Pacdate.TABLE + " WHERE idOwner = " + idOwner );
        int resultCode = query.executeUpdate( );
        AppLogService.debug( "result remove : " + resultCode );
    }

    @Override
    protected Class<Pacdate> getBeanClass( )
    {
        return Pacdate.class;
    }

}
