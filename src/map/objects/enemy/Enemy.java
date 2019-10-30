package map.objects.enemy;

import java.util.UUID;

import map.structure.Field;
import map.structure.MapStructure;
import util.Position;
import java.util.TimerTask;

public abstract class Enemy{
	private String label;
	private UUID id ;
	private int hp;
	private int progress;
	private int speed;
	private int x;
	private int y;
	private boolean isAlive = true;
	private boolean reachedTarget =  false;
	private TimerTask task;
	private Field actualField;
	
	public Enemy(String label, UUID id, int hp, int speed) {
		this.label=label;
		this.id=id;
		this.hp=hp;
		this.speed=speed;
		this.progress=0;
	}

	public TimerTask getTask(){
		return task;
	}

	public void setTask(TimerTask task){
		this.task=task;
	}
	
	public int getHealthPoints() {
		return hp;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void reduceHealthPoints(int damage) {
		if(damage>=hp) {
			hp=0;
			isAlive=false;
		}else {
			hp-=damage;	
		}
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * This method moves an enemy on the first call to the spawn position
	 * All next calls are moving the enemy one pixel in the direction of the spawnpoint from the next Field on the path
	 * @param map
	 */
	public boolean move(MapStructure map) {
		if(actualField==null) {
			actualField=map.getFieldForEnemy(progress);
			progress++;
			moveToPosition(actualField.getSpawnPoint());
			return true;
		}
		if(actualField.getSpawnPoint().equals(getPosition())) {
			actualField = map.getFieldForEnemy(progress);
			progress++;
			if(actualField!=null) {
				System.out.println(label + " is moving to a new field [" + actualField.getFieldPositionX() + actualField.getFieldPositionY() + "]");
			}
		}
		
		if(actualField!=null) {
			Position pos = actualField.getSpawnPoint();
			if(pos.getX()-x<0) {
				moveTo(x-1, y);
			}else if(pos.getX()-x>0){
				moveTo(x+1, y);
			}else if(pos.getY()-y<0) {
				moveTo(x, y-1);
			}else {
				moveTo(x, y+1);
			}
			return true;
		}
		System.out.println(label + " reached the target");
		reachedTarget=true;
		return false;
	}

	/**
	 * the target is reached when an enemy reached the Spawnpoint of the last Field on the path
	 * @return
	 */
	public boolean reachedTarget() {
		return reachedTarget;
	}
	
	public Position getPosition() {
		return new Position(x, y);
	}
	
	public void moveToPosition(Position pos) {
		this.x=pos.getX();
		this.y=pos.getY();
	}
	
	public void moveTo(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public String getLabel() {
		return label;
	}
	
	public UUID getId() {
		return id;
	}
	
	public int getPositionX() {
		return x;
	}
	
	public int getPositionY() {
		return y;
	}
}
