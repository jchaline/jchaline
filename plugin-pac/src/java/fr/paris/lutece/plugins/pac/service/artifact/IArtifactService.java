package fr.paris.lutece.plugins.pac.service.artifact;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.pac.bean.artifact.Artifact;
import fr.paris.lutece.plugins.pac.service.IPacService;

@Transactional
public interface IArtifactService extends IPacService<Integer, Artifact>
{

}
