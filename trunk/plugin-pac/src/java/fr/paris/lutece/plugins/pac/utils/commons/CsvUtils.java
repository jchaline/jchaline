package fr.paris.lutece.plugins.pac.utils.commons;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

import au.com.bytecode.opencsv.CSVWriter;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.web.upload.MultipartHttpServletRequest;


/**
 * Utility class, process with csv files
 * @author jchaline
 * 
 */
public final class CsvUtils
{
    /**
     * Private default constructor
     */
    private CsvUtils( )
    {
    }

    /**
     * Get file from the request and extract into String[] list
     * @param request the http request
     * @param inputName the input field name
     * @return the datas list
     */
    public static List<List<String>> uploadFile( HttpServletRequest request, String inputName )
    {
        List<List<String>> datas = new ArrayList<List<String>>( );

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        FileItem file = multipartRequest.getFile( inputName );
        try
        {
            String fileContent = file.getString( PacConstants.PARAMETER_CSV_ENCODING );
            String[] lines = fileContent.split( "\n" );
            for ( String line : lines )
            {
                Collection<String> stringCollec = Arrays.asList( line.split( ";" ) );
                @SuppressWarnings( "unchecked" )
                Collection<String> stringCollecCorrected = CollectionUtils.collect( stringCollec, new Transformer( )
                {
                    public Object transform( Object o )
                    {
                        String result = "";
                        String strObject = (String) o;
                        if ( StringUtils.isNotBlank( strObject ) )
                        {
                            result = strObject.charAt( 0 ) == '"' ? strObject.substring( 1, ( strObject.length( ) - 1 ) )
                                    : strObject;
                        }
                        return result;
                    }
                } );
                datas.add( new ArrayList<String>( stringCollecCorrected ) );
            }
        }
        catch ( UnsupportedEncodingException e )
        {
            AppLogService.error( e );
        }

        return datas;
    }

    /**
     * Write CSV file into the response to download the file
     * @param response the http response
     * @param datas the data to export
     * @param fileName the file name
     * @throws IOException when errors occurs during the download
     */
    public static void downloadFile( HttpServletResponse response, List<String[]> datas, String fileName )
            throws IOException
    {
        String strCsvSeparator = PacConstants.PARAMETER_CSV_SEPARATOR;
        StringWriter strWriter = new StringWriter( );

        CSVWriter csvWriter = new CSVWriter( strWriter, strCsvSeparator.toCharArray( )[0] );
        csvWriter.writeAll( datas );
        OutputStream os = null;

        byte[] byteFileOutPut = null;
        try
        {
            byteFileOutPut = strWriter.toString( ).getBytes( PacConstants.PARAMETER_CSV_ENCODING );
            response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\"" );
            response.setHeader( "Pragma", "public" );
            response.setHeader( "Expires", "0" );
            response.setHeader( "Cache-Control", "must-revalidate,post-check=0,pre-check=0" );
            response.setContentType( "enctype=multipart/form-data" );

            os = response.getOutputStream( );
            os.write( byteFileOutPut );
            os.close( );

            csvWriter.close( );
            strWriter.close( );
        }
        catch ( UnsupportedEncodingException e1 )
        {
            AppLogService.error( e1 );
        }
        finally
        {
            if ( os != null )
            {
                try
                {
                    os.close( );
                }
                catch ( IOException e )
                {
                    AppLogService.error( e );
                }
            }
            if ( csvWriter != null )
            {
                try
                {
                    csvWriter.close( );
                }
                catch ( IOException e )
                {
                    AppLogService.error( e );
                }
            }
            if ( strWriter != null )
            {
                try
                {
                    strWriter.close( );
                }
                catch ( IOException e )
                {
                    AppLogService.error( e );
                }
            }
        }
    }
}
