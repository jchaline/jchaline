package battle.csn.lucette;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import battle.csn.lucette.game.board.IBoard;
import battle.csn.lucette.game.bot.IBot;

@RunWith( JUnit4.class )
public class LucetteTest
{
    private ApplicationContext context ;

    protected final String ID_EQUIPE = "lucette";
    protected final String ID_EQUIPE_ADVERSE = "germaine";
    protected final String ID_PARTIE = "partie";
    
    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext( "SpringBeans.xml" );
    }

    /**
     * TODO : configurer avec spring
     * @return le plateau à tester
     */
    public IBoard getPlateau( )
    {
        assertNotNull( context );
        return context.getBean( IBoard.class );
    }

    public IBot getBot( )
    {
        assertNotNull( context );
        return context.getBean( IBot.class );
    }

    /**
     * Test les données de test :D
     */
    @Test
    public void testTest( )
    {
        assertTrue( StringUtils.isNotBlank( ID_EQUIPE_ADVERSE ) );
        assertTrue( StringUtils.isNotBlank( ID_EQUIPE ) );
        assertFalse( ID_EQUIPE.equals( ID_EQUIPE_ADVERSE ) );
    }
}
