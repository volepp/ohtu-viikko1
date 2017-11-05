package ohtuesimerkki;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

	Reader readerStub = new Reader() {
		 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    @Before
    public void setUp() {
    	stats = new Statistics(readerStub);
    }
    
    @Test
    public void statsNotNull() {
    	assertNotNull(stats);
    }

    @Test
    public void findsRightPlayer() {
    	Player found = stats.search("Kurri");
    	Player correct = readerStub.getPlayers().get(2);
    	
    	assertNotNull(found);
    	assertEquals(correct.getName(), found.getName());
    }
    
    @Test
    public void returnsNullIfPlayerNotFound() {
    	assertNull(stats.search("Malkin"));
    }
    
    @Test
    public void findsTeamCorrectly() {
    	Player first = stats.team("DET").get(0);
    	
    	assertEquals(readerStub.getPlayers().get(3).getName(), first.getName());
    	
    	Player second = stats.team("EDM").get(1);
    	
    	assertEquals(readerStub.getPlayers().get(2).getName(), second.getName());
    }
    
    @Test
    public void topScorersAreCorrect() {
    	List<Player> topScorers = stats.topScorers(3);
    	
    	assertEquals(readerStub.getPlayers().get(4).getName(), topScorers.get(0).getName());
    	assertEquals(readerStub.getPlayers().get(1).getName(), topScorers.get(1).getName());
    	assertEquals(readerStub.getPlayers().get(3).getName(), topScorers.get(2).getName());
    }
    
}
