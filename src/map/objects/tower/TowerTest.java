package map.objects.tower;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import map.objects.enemy.Enemy;
import map.objects.enemy.Tank;
import util.Position;


class TowerTest {

	@Test
	void towerKillsEnemyIfItsInRange() {
		//arrange
		Tower t = new DefTower("t1", 100, 100, 1, new Position(0, 0));
		Enemy e = new Tank("tank", 5, 950);
		e.moveToPosition(new Position(50, 50));
		
		//act
		t.fire(Arrays.asList(e));
		
		//assert
		assertFalse(e.isAlive());
		assertTrue(e.isAlive()==false);
		assertEquals(false, e.isAlive());
		assertThat(e.isAlive(), is(not(equalTo((true)))));
	}
	
	@Test
	void towerDontShootEnemyIfItsNotInRange() {
		//arrange
		Tower t = new DefTower("t1", 100, 100, 1, new Position(0, 0));
		Enemy e = new Tank("tank", 5, 950);
		e.moveToPosition(new Position(150, 150));
		
		//act
		t.fire(Arrays.asList(e));
		
		//assert
		assertTrue(e.isAlive());
	}
}
