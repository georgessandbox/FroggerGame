package hoboTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import HoboGame.HoboGame;
import HoboGame.HoboGame.Track;


public class gameTest {
	HoboGame game;
		
	@Before
	public void setUp() {
		
		game = new HoboGame();
		game.sketchPath();
		game.setup();
		}

	/* test if health initiated to 5*/
	@Test
	public void testHealth() {
		Assert.assertEquals(game.health, 5, 0.1);
	}
	
	/* test width & height accuracy*/
	@Test
	public void testWidthHeight() {
		Assert.assertEquals(game.width, 1000, 0.1);
		Assert.assertEquals(game.height, 480, 0.1);
	}
	
	
	/* test if player successfully able to intersects with any train on any tracks*/
	@Test
	public void testCollisionsAll() {
		game.player.x = 20;
		for (Track lane : game.tracks) {
			if (lane.aTrain != null ) {
				game.player.y = lane.aTrain.y ;
				lane.aTrain.x = 20 ;
				Assert.assertTrue(  game.player.intersects(lane.aTrain));
			}
		}
	}
	
	/* test if player successfully decrements health when hit by a certain train*/
	@Test
	public void testHealthDecrements() {
		game.player.x = 20;
		game.player.y = game.tracks.get(3).aTrain.y ;
		
		game.tracks.get(3).aTrain.x = 20 ;
		game.tracks.get(3).check(game.player);
		
		Assert.assertEquals( game.health , 4 );
	}
	
	/* test if player successfully decrements health when hit by any train on any track*/
	@Test
	public void testHealthDecrementsAll() {
		game.player.x = 20;
		int Healthshould = 4;
		for (Track lane : game.tracks) {
				if (lane.aTrain != null ) {
					game.player.y = lane.aTrain.y ;
					lane.aTrain.x = 20 ;
					lane.check(game.player);
					Assert.assertEquals( game.health , Healthshould--);
			}
		}
	}
	
	/* tests no outofbound exception is thrown by any index when player attempts to move anywhere*/
	@Test(expected = Test.None.class )
	public void testOutofBoundException() {
	    for(int x = 0 ; x>1000 ; x++) {
	    	game.player.x += 10;
		    game.player.y += 10;
	    	game.draw();
	    }
	    for(int x = 0 ; x>1000 ; x++) {
	    	game.player.x -= 10;
		    game.player.y -= 10;
	    	game.draw();
	    }
	    for(int x = 0 ; x>1000 ; x++) {
	    	game.player.x += 10;
		    game.player.y -= 10;
	    	game.draw();
	    }
	    for(int x = 0 ; x>1000 ; x++) {
	    	game.player.x -= 10;
		    game.player.y += 10;
	    	game.draw();
	    }
	}
	
	
	
	
	


}
