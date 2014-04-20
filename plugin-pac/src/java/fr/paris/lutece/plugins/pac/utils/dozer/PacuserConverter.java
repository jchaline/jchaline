package fr.paris.lutece.plugins.pac.utils.dozer;

import org.dozer.Mapper;

import fr.paris.lutece.plugins.genericjpa.dao.commons.ResultList;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.dto.pacuser.PacuserDTO;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;
import fr.paris.lutece.portal.service.spring.SpringContextService;


/**
 * The Pacuser converter between bean and DTO
 * @author jchaline
 */
public class PacuserConverter extends GenericJPADozerConverter<PacuserDTO, Pacuser>
{

    private PacuserConverter( )
    {
    }

    public PacuserDTO convertEntity( Pacuser source )
    {
        Mapper mapper = (Mapper) SpringContextService.getBean( PacConfigs.BEAN_DOZER_MAPPER );
        return mapper.map( source, PacuserDTO.class );
    }

    public ResultList<PacuserDTO> convertEntities( ResultList<Pacuser> findAll )
    {
        ResultList<PacuserDTO> ret;
        if ( findAll != null )
        {
            ret = new ResultList<PacuserDTO>( );
            for ( Pacuser pacuser : findAll )
            {
                ret.add( convertEntity( pacuser ) );
            }
            ret.setTotalResult( findAll.getTotalResult( ) );
        }
        else
        {
            ret = null;
        }

        return ret;
    }

}