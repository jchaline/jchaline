/*
 * Copyright (c) 2002-2010, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.pac.utils.messages;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Permet d'insérer différents type de message dans un template
 * @author jchaline
 * 
 */
public class SessionMessage implements Serializable
{
    /**
     * CODE FOR FLASH MESSAGE DISPLAY
     */
    public static final String CODE_INFORMATION = "CODE_INFORMATION";
    /**
     * CODE FOR FLASH MESSAGE DISPLAY
     */
    public static final String CODE_ERREUR = "CODE_ERREUR";
    /**
     * CODE FOR MODAL DISPLAY
     */
    public static final String CODE_ALERTE = "CODE_ALERTE";

    public static final String[] CODES = { CODE_INFORMATION, CODE_ERREUR, CODE_ALERTE };

    /**
     * Mark used to display message
     */
    public static final String MARK_SESSION_MESSAGE = "sessionMessage";

    private static final long serialVersionUID = 8439370641244779814L;

    /**
     * List of the messages
     */
    private Map<String, List<String>> _messages = new HashMap<String, List<String>>( );

    /**
     * Constructeur par defaut
     */
    public SessionMessage( )
    {
        for ( String code : CODES )
        {
            this.getMessages( ).put( code, new LinkedList<String>( ) );
        }
    }

    /**
     * Add a message in the object in session, message in session clean while
     * generate a page
     * @param request the request
     * @param code the code
     * @param keyI18N the I18N key of the message
     */
    public static void pushMessage( HttpServletRequest request, String code, String keyI18N )
    {
        SessionMessage messageInSession = (SessionMessage) request.getSession( ).getAttribute( MARK_SESSION_MESSAGE );

        if ( messageInSession == null )
        {
            messageInSession = new SessionMessage( );
        }

        messageInSession.addMessage( code, keyI18N );
        request.getSession( ).setAttribute( MARK_SESSION_MESSAGE, messageInSession );
    }

    /**
     * Get and remove message from session
     * @param request the http request
     * @return the message object
     */
    public static SessionMessage popMessage( HttpServletRequest request )
    {
        SessionMessage messageInSession = (SessionMessage) request.getSession( ).getAttribute( MARK_SESSION_MESSAGE );
        if ( messageInSession != null )
        {
            request.getSession( ).removeAttribute( MARK_SESSION_MESSAGE );
        }
        return messageInSession;
    }

    /**
     * remove all entry
     */
    public void clean( )
    {
        for ( String code : CODES )
        {
            this.getMessages( ).get( code ).clear( );
        }
    }

    /**
     * Ajoute un message ou modifie une alerte/confirmation
     * @param code the message code
     * @param message the message
     */
    public void addMessage( String code, String message )
    {
        this.getMessages( ).get( code ).add( message );
    }

    /**
     * @param messages the _messages to set
     */
    public void setMessages( Map<String, List<String>> messages )
    {
        this._messages = messages;
    }

    /**
     * @return the _messages
     */
    public Map<String, List<String>> getMessages( )
    {
        return _messages;
    }
}
