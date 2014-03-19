package fr.paris.lutece.plugins.pac.dao.pacuser;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.bean.pacdate.PacdateFilter;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate_;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.bean.pacuser.PacuserFilter;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser_;
import fr.paris.lutece.plugins.pac.dao.AbstractPacDAO;
import fr.paris.lutece.plugins.pac.dao.commons.SQLUtils;


/**
 * The Pacuser DAO class
 * @author jchaline
 */
public class PacuserDAO extends AbstractPacDAO<Integer, Pacuser> implements IPacuserDAO
{
    @Override
    protected void buildCriteriaQuery( GenericJPAFilter abstractFilter, Root root, CriteriaQuery cq, CriteriaBuilder cb )
    {
        if ( abstractFilter instanceof PacuserFilter )
        {
            List<Predicate> listPredicates = new ArrayList<Predicate>( );

            // exemple for join
            //Join<Pacdate, Pacuser> pacuser = root.join( Pacdate_._idUser, JoinType.INNER );

            PacuserFilter filter = (PacuserFilter) abstractFilter;

            if ( StringUtils.isNotBlank( filter.getNom( ) ) )
            {
                listPredicates.add( cb.like( root.get( Pacuser_._strNom ),
                        SQLUtils.addPercentEnclosing( filter.getNom( ) ) ) );
            }
            if ( StringUtils.isNotBlank( filter.getPrenom( ) ) )
            {
                listPredicates.add( cb.like( root.get( Pacuser_._strPrenom ),
                        SQLUtils.addPercentEnclosing( filter.getNom( ) ) ) );
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