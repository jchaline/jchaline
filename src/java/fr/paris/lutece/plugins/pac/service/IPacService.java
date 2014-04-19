package fr.paris.lutece.plugins.pac.service;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;

@Transactional
public interface IPacService<K, E extends GenericJPABean<K>> extends IPluginService<K,E>
{
    
}
