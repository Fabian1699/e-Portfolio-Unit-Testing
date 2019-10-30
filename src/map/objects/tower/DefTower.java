package map.objects.tower;

import java.util.UUID;

import util.Position;

public class DefTower extends Tower{
	private static final int costs = 100;
	private static final int damage = 1;
	private static final int range = 100;
	private static final int fireRate = 5;

	public DefTower(String label, int damage, int range, int fireRate, Position pos) {
		super(label, UUID.randomUUID(), costs, damage, range, fireRate, pos);
		
	}

}
