package map.objects.enemy;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import map.structure.MapStructure;
import util.Position;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;

class EnemyTest {

	@Test
	void ifDamageToEnemyIsLowerThanHP_EnemyShouldBeAlive() {
		//arrange
		Enemy enemy = new Tank("tank1", 100, 900);
		
		//act
		enemy.reduceHealthPoints(50);
		
		//assert
		assertTrue(enemy.isAlive());
	}
	
	@Test
	public void ifDamageToEnemyHigherThanHP_EnemyShouldBeDead() {

	}
	
	@Test
	public void whenEnemyMoves_ThePositionShouldChange() {

	}
}
