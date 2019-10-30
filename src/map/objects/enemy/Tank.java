package map.objects.enemy;

import java.util.UUID;

public class Tank extends Enemy{
	private final static int hp = 7;
	private final static int speed = 999;

	public Tank(String label, int hp, int speed) {
		super(label, UUID.randomUUID(), hp, speed);
		
	}
	

}
