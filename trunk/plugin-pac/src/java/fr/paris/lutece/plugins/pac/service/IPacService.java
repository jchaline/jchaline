package fr.paris.lutece.plugins.pac.service;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean;
import fr.paris.lutece.plugins.genericjpa.service.IPluginService;

@Transactional
public interface IPacService<K, E extends AbstractBean<K>> extends IPluginService<K,E>
{
    
}
