package tools.generator;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import tools.Tool;
import tools.generator.bean.Plugin;
import tools.generator.service.GeneratorPropertiesService;
import tools.generator.service.TemplateService;
import tools.generator.utils.GeneratorConstants;
import utils.ToolsConstants;

@Service
public class BeanGeneratorTool implements Tool
{
    private static final String TOOL_ID = "generator";
    private static final String TOOL_NAME = "Generateur de fichiers";
    private static final Logger logger = Logger.getLogger( BeanGeneratorTool.class );

    @Override
    public int run( )
    {
        GeneratorPropertiesService.init( );
        TemplateService _templateService = new TemplateService( );

        String pathProjet = GeneratorPropertiesService.PATH_PROJECT;
        String separator = GeneratorConstants.PATH_SEPARATOR;
        String folderSource = GeneratorPropertiesService.getProperty( GeneratorConstants.KEY_FOLDER_SOURCE );

        String pathSources = pathProjet + separator + folderSource;
        _templateService.setPathFolderWithTemplate( pathSources );

        Plugin plugin = null;
        try
        {
            plugin = GeneratorPropertiesService.getPlugin( );
        }
        catch ( IllegalArgumentException e )
        {
            logger.error( e );
        }

        List<String> templates = _templateService.getTemplatesFiles( pathSources );
        List<String> files = _templateService.applyRulesForTemplates( plugin, templates );
        logger.debug( files );

        return ToolsConstants.STATUS_OK;

    }

    @Override
    public String getConf( )
    {
        return null;
    }

    @Override
    public String getId( )
    {
        return TOOL_ID;
    }

    @Override
    public String getName( )
    {
        return TOOL_NAME;
    }
}
