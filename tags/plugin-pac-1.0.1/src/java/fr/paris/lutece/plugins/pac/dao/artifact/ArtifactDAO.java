package fr.paris.lutece.plugins.pac.dao.artifact;

import fr.paris.lutece.plugins.pac.bean.artifact.Artifact;
import fr.paris.lutece.plugins.pac.dao.AbstractPacDAO;

public class ArtifactDAO extends AbstractPacDAO<Integer, Artifact> implements IArtifactDAO
{

    @Override
    protected Class<Artifact> getBeanClass( )
    {
        return Artifact.class;
    }

}
