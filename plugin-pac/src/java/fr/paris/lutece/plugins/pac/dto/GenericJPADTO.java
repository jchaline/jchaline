package fr.paris.lutece.plugins.pac.dto;

import org.dozer.Mapper;

import fr.paris.lutece.portal.service.spring.SpringContextService;


public abstract class GenericJPADTO<K,E>
{
    static protected Mapper _dozerMapper = (Mapper) SpringContextService.getBean( "mapper" );
    
    /**
     * @return the id
     */
    public abstract K getId( );

    /**
     * Set the primary key
     * @param key the primary key
     */
    public abstract void setId( K key );

    /**
     * Convert DTO to bean object to save in database
     * @return the bean object
     */
    public abstract E convert( );
}
