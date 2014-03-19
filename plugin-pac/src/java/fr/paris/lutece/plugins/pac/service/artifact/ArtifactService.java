package fr.paris.lutece.plugins.pac.service.artifact;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import fr.paris.lutece.plugins.pac.bean.artifact.Artifact;
import fr.paris.lutece.plugins.pac.dao.IPacDAO;
import fr.paris.lutece.plugins.pac.dao.artifact.IArtifactDAO;
import fr.paris.lutece.plugins.pac.service.AbstractPacService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;


public class ArtifactService extends AbstractPacService<Integer, Artifact> implements IArtifactService
{
    private static final Logger logger = Logger.getLogger( ArtifactService.class );

    @Inject
    @Named( PacConfigs.BEAN_ARTIFACT_DAO )
    private IArtifactDAO _daoArtifact;

    @Override
    public Artifact findByStrPrimaryKey( String primaryKey )
    {
        Artifact bean = null;
        try
        {
            Integer key = Integer.valueOf( primaryKey );
            bean = _daoArtifact.findById( key );
        }
        catch ( NumberFormatException e )
        {
            logger.error( "Error while getting Integer value of " + primaryKey, e );
        }
        return bean;
    }

    @Override
    public IPacDAO<Integer, Artifact> getPacDao( )
    {
        return _daoArtifact;
    }

}
