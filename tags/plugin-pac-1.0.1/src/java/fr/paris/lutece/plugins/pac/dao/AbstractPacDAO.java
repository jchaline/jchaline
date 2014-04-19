package fr.paris.lutece.plugins.pac.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.commons.PagedQuery;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;
import fr.paris.lutece.plugins.pac.service.PacPlugin;
import fr.paris.lutece.portal.service.jpa.JPALuteceDAO;


public abstract class AbstractPacDAO<K, E> extends JPALuteceDAO<K, E> implements IPacDAO<K, E>
{

    @Override
    public ResultList<E> findAll( PaginationProperties paginationProperties )
    {
        EntityManager em = getEM( );
        
        Query query = em.createQuery( "SELECT e FROM " + getEntityClassName( ) + " e " );
        Query countQuery = em.createQuery( "SELECT count(e) FROM " + getEntityClassName( ) + " e " );

        PagedQuery<E> pq = new PagedQuery<E>( query, countQuery, paginationProperties );

        return pq.getResultList( );
    }

    @Override
    public ResultList<E> find( GenericJPAFilter<K> filter, PaginationProperties paginationProperties )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );
        CriteriaQuery<E> cq = cb.createQuery( getBeanClass( ) );

        Root<E> root = cq.from( getBeanClass( ) );
        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        ResultList<E> resultList = createPagedQuery( em, cq, paginationProperties ).getResultList( );
        return resultList;
    }

    @Override
    public String getPluginName( )
    {
        return PacPlugin.PLUGIN_NAME;
    }
    
    /**
     * Add the order by parameter to the query.
     * 
     * @param filter the filter
     * @param root the offer root
     * @param query the criteria query
     * @param builder the criteria builder
     */
    protected void buildSortQuery( GenericJPAFilter<K> filter, Root<E> root, CriteriaQuery<E> query, CriteriaBuilder builder )
    {
        if ( ( filter.getOrders( ) != null ) && !filter.getOrders( ).isEmpty( ) )
        {
            List<Order> orderList = new LinkedList<Order>( );

            // get asc order
            for ( String order : filter.getOrders( ) )
            {
                if ( filter.getOrderAsc( ) )
                {
                    orderList.add( builder.asc( root.get( order ) ) );
                }
                else
                {
                    orderList.add( builder.desc( root.get( order ) ) );
                }
            }
            query.orderBy( orderList );
        }
    }
    
    /**
     * Generate count query from criteria query and return a paged query.
     * 
     * @param criteriaQuery criteria query
     * @param paginationProperties pagination data
     * @return query paged
     */
    protected PagedQuery<E> createPagedQuery( EntityManager em, CriteriaQuery<E> criteriaQuery,
            PaginationProperties paginationProperties )
    {
        // Generate count query
        CriteriaBuilder cb = em.getCriteriaBuilder( );
        CriteriaQuery<Long> countQuery = cb.createQuery( Long.class );
        countQuery.select( cb.count( countQuery.from( criteriaQuery.getResultType( ) ) ) );

        countQuery.getRoots( ).clear( );

        for ( Root<?> root : criteriaQuery.getRoots( ) )
        {
            countQuery.getRoots( ).add( root );
        }

        if ( criteriaQuery.getRestriction( ) != null )
        {
            countQuery.where( criteriaQuery.getRestriction( ) ).distinct( true );
        }

        // Create the paged query
        PagedQuery<E> pq = new PagedQuery<E>( em.createQuery( criteriaQuery ), em.createQuery( countQuery ),
                paginationProperties );

        return pq;
    }
    
    /**
     * the method to implement to add the constraint
     * 
     * @param filter the bean filter
     * @param root the table root
     * @param cq the criteria query
     * @param cb the criteria builder
     */
    protected void buildCriteriaQuery( GenericJPAFilter<K> filter, Root<E> root, CriteriaQuery<E> cq, CriteriaBuilder cb )
    {

    }
    
    protected abstract Class<E> getBeanClass( );

}
