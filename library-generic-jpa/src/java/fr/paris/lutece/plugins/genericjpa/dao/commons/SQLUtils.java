package fr.paris.lutece.plugins.genericjpa.dao.commons;

public class SQLUtils
{
    /**
     * Add % before and after the expression
     * @param expr the expression ton enclose
     * @return the new enclosed
     */
    public static String addPercentEnclosing( String expr )
    {
        return "%" + expr + "%";
    }
}
