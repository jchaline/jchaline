package battle.csn.lucette.java8;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;


/**
 * This class provide way to use lambda expression ! pretty cool !
 * @author jchaline
 * 
 */
public class MethodMagic
{
    private static final Logger LOGGER = Logger.getLogger( MethodMagic.class );

    private Method _method;
    private Object _invoker;
    private Object[] _params;

    public MethodMagic( Method method, Object invoker, Object... params )
    {
        this.setMethod( method );
        this.setInvoker( invoker );
        this.setParams( params );
    }

    /**
     * 
     * @return
     */
    public Object invoke( )
    {
        Object result = null;
        try
        {
            result = _method.invoke( _invoker, _params );
        }
        catch ( IllegalAccessException e )
        {
            LOGGER.error( e );
        }
        catch ( IllegalArgumentException e )
        {
            LOGGER.error( e );
        }
        catch ( InvocationTargetException e )
        {
            LOGGER.error( e );
        }

        return result;
    }

    /**
     * @return the _method
     */
    public Method getMethod( )
    {
        return _method;
    }

    /**
     * @param _method the _method to set
     */
    public void setMethod( Method _method )
    {
        this._method = _method;
    }

    /**
     * @return the _invoker
     */
    public Object getInvoker( )
    {
        return _invoker;
    }

    /**
     * @param _invoker the _invoker to set
     */
    public void setInvoker( Object _invoker )
    {
        this._invoker = _invoker;
    }

    /**
     * @return the _params
     */
    public Object[] getParams( )
    {
        return _params;
    }

    /**
     * @param _params the _params to set
     */
    public void setParams( Object[] _params )
    {
        this._params = _params;
    }
}
