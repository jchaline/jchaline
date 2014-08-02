package tools.generator.service;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import services.FileService;
import tools.generator.bean.Attribut;
import tools.generator.bean.Bean;
import tools.generator.bean.Plugin;
import tools.generator.utils.GeneratorConstants;


public class GeneratorPropertiesService
{
    public static final String PATH_PROJECT = System.getProperty( "user.dir" );
    public final static String FOLDER_CONFIG_NAME = "\\conf";
    public final static String FOLDER_OVERRIDE_NAME = "\\override";
    public static final String PROPERTIES_PATTERN = "([^\\s]+(\\.(?i)(properties))$)";

    private static Properties _properties = new Properties( );

    private GeneratorPropertiesService( )
    {

    }

    /**
     * The initialization class, ensure the load of the properties files
     */
    public static void init( )
    {
        List<String> findPropertiesFiles = findPropertiesFiles( );
        for ( String filePath : findPropertiesFiles )
        {
            initFileProperties( filePath );
        }
        List<String> findOverrideFiles = findOverrideFiles( );
        for ( String filePath : findOverrideFiles )
        {
            initFileProperties( filePath );
        }
    }

    /**
     * Generate Plugin object
     * @param properties the properties with beans
     * @return the plugin object
     */
    public static Plugin getPlugin( ) 
    {
        Plugin plugin = new Plugin( _properties.getProperty( GeneratorConstants.KEY_PLUGIN_NAME ) );
        plugin.setName( _properties.getProperty( GeneratorConstants.KEY_PLUGIN_NAME ) );
        plugin.setAuthorName( _properties.getProperty( GeneratorConstants.KEY_PLUGIN_AUTHOR ) );

        List<Bean> beanList = getBeanList( );

        plugin.setBeanList( beanList );

        return plugin;
    }

    /**
     * Get bean list from the config file
     * @return the list of bean for the plugin
     */
    private static List<Bean> getBeanList( )
    {
        List<Bean> beanList = new LinkedList<Bean>( );

        String pathBeansFile = PATH_PROJECT + FOLDER_CONFIG_NAME + GeneratorConstants.PATH_SEPARATOR
                + _properties.getProperty( GeneratorConstants.KEY_BEANS_FILE_NAME );
        List<String> linesOfBeanFile = FileService.read( pathBeansFile );
        linesOfBeanFile = filterLine( linesOfBeanFile );

        for ( String line : linesOfBeanFile )
        {
            Bean createBeanFromLine = createBeanFromLine( line );
            beanList.add( createBeanFromLine );
        }
        return beanList;
    }

    /**
     * filter line, say if we should keep a line
     * @param line the line to test
     * @return the line to keep
     */
    public static List<String> filterLine( List<String> lines )
    {
        List<String> toKeep = new LinkedList<String>( );
        for ( String line : lines )
        {
            if ( line.length( ) > 0 && line.charAt( 0 ) != '#' )
            {
                toKeep.add( line );
            }
        }
        return toKeep;
    }

    /**
     * Create a bean with the information in the line given
     * @param line the line with the bean information
     * @return the bean which correspond
     */
    public static Bean createBeanFromLine( String line )
    {
        String[] lineSplit = line.split( ";" );

        String[] beanInfo = lineSplit[0].split( "," );
        String beanName = beanInfo[0];

        Bean bean = new Bean( beanName );

        for ( int i = 1; i < lineSplit.length; i++ )
        {
            Attribut a = ( createAttribut( lineSplit[i] ) );
            bean.add( a.getName( ), a );
        }

        return bean;
    }

    /**
     * Create the object attribut from a substring take in the bean file
     * @param strAttribut the substring with the bean's attributs
     * @return the object Attribut
     */
    public static Attribut createAttribut( String strAttribut )
    {
        String[] arrayAttribut = strAttribut.split( "," );
        String typeAttribut = arrayAttribut[0];
        String nameAttribut = arrayAttribut[1];
        boolean isFiltered = arrayAttribut.length == 3 && arrayAttribut[2].endsWith( "true" );
        Attribut attribut = new Attribut( typeAttribut, nameAttribut, isFiltered );
        return attribut;
    }

    /**
     * Load the properties file
     * @return the properties object
     */
    public static void initFileProperties( String filePath )
    {
        try
        {
            _properties.load( new FileInputStream( filePath ) );
        }
        catch ( Exception e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * 
     * @param keyPathProject
     * @return
     */
    public static String getProperty( String keyPathProject )
    {
        return _properties.getProperty( keyPathProject );
    }

    /**
     * Find all the properties file to use
     * @return
     */
    private static List<String> findPropertiesFiles( )
    {
       return FileService.findFiles( 1, PATH_PROJECT + FOLDER_CONFIG_NAME, PROPERTIES_PATTERN );
    }

    /**
     * Find all the OVERRIDE properties file to use
     * @return
     */
    private static List<String> findOverrideFiles( )
    {
        return FileService.findFiles( 1, PATH_PROJECT + FOLDER_CONFIG_NAME + FOLDER_OVERRIDE_NAME, PROPERTIES_PATTERN );
    }

    /**
     * Call the containsKey method of the Properties
     * @param key the property key
     * @return true if the property exist, false otherwise
     */
    public static boolean containsKey( String key )
    {
        return _properties.containsKey( key );
    }
}
