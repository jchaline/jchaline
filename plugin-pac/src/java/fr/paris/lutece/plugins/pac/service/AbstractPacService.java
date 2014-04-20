package fr.paris.lutece.plugins.pac.service;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean;
import fr.paris.lutece.plugins.genericjpa.service.AbstractService;


public abstract class AbstractPacService<K, E extends AbstractBean<K>> extends AbstractService<K,E> implements IPacService<K, E>
{

   

}
