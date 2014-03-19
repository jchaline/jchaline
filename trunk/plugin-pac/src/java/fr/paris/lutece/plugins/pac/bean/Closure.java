package fr.paris.lutece.plugins.pac.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.paris.lutece.portal.service.util.AppLogService;


public class Closure
{
    private Method _method;
    private Object[] _parameters;
    private Object _target;

    /**
     * Call method on target
     * @param targetOrParam the object to call method
     * @return the result of the method
     */
    public Object process( Object... targetOrParam )
    {
        Object result = null;
        try
        {
            result = _method.invoke( _target != null ? _target : targetOrParam[0], _target != null ? targetOrParam : _parameters );
        }
        catch ( IllegalAccessException e )
        {
            AppLogService.error( "IllegalAccessException while process Closure operation", e );
        }
        catch ( IllegalArgumentException e )
        {
            AppLogService.error( "IllegalArgumentException while process Closure operation", e );
        }
        catch ( InvocationTargetException e )
        {
            AppLogService.error( "InvocationTargetException while process Closure operation", e );
        }
        return result;
    }

    /**
     * Create closure with method and target which invoke
     * @param target the target which invoke
     * @param m the method to call
     */
    public Closure( Object target, Method m )
    {
        this._target = target;
        this._method = m;
    }

    /**
     * Create closure with method and parameters to use
     * @param m the method to call
     * @param param the parameters
     */
    public Closure( Method m, Object... param )
    {
        this._method = m;
        this._parameters = param;
    }

    /**
     * @return the method
     */
    public Method getMethod( )
    {
        return _method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod( Method method )
    {
        this._method = method;
    }

    /**
     * @return the parameters
     */
    public Object[] getParameters( )
    {
        return _parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters( Object[] parameters )
    {
        this._parameters = parameters;
    }

    /**
     * @return the target
     */
    public Object getTarget( )
    {
        return _target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget( Object target )
    {
        this._target = target;
    }
}
