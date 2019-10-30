package map.matchfield;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Optional;

import map.objects.enemy.Enemy;
import map.objects.tower.Tower;
import map.structure.MapStructure;

public class MatchField {
	private List<Enemy> enemies;
	private List<Tower> towers;
	private Timer timer = new Timer();
	private MapStructure map;
	
	public MatchField(MapStructure map) {
		this.map=map;
		enemies = new ArrayList<>();
		towers = new ArrayList<>();
	}
	
	public void addTower(Tower tower) {
		towers.add(tower);
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	//not in use
	public boolean removeTower(UUID id) {
		Optional<Tower> tower = getTower(id);
		if(tower.isPresent()) {
			towers.remove(tower.get());
			return true;
		}
		return false;
	}

	//not in use
	public boolean removeEnemy(UUID id) {
		Optional<Enemy> enemy = getEnemy(id);
		if(enemy.isPresent()) {
			enemies.remove(enemy.get());
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		return !enemies.stream().filter(e -> !e.reachedTarget()).findAny().isPresent();
	}

	public Optional<Tower> getTower(UUID id) {
		return towers.stream().filter(e -> e.getId()==id).findAny();
	}

	public List<UUID> getTowers(){
		return towers.stream().map(Tower::getId).collect(Collectors.toList());
	}

	public Optional<Enemy> getEnemy(UUID id) {
		return enemies.stream().filter(e -> e.getId()==id).findAny();
	}

	public List<UUID> getEnemies(){
		return enemies.stream().map(Enemy::getId).collect(Collectors.toList());
	}

	/**
	 * all enemies are moving in an own thread. The speed is defined by the time this task is repeated.
	 * An enemy moves only one pixel each time. Speed is 1 second - (int) speed of the enemy
	 */
	public void moveEnemies() {
		enemies.stream().forEach(e -> {
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if(e.isAlive()) {
						e.move(map);
						if(isGameOver()){
							stopGame();
						}
					}
				}
			};
			e.setTask(task);
			timer.scheduleAtFixedRate(task, 0, 1000 - e.getSpeed());
		});
	}

	/**
	 * all towers are shooting in an own thread. The speed is defined by the time this task is repeated.
	 * int fireRate defines how much seconds the tower sleeps between two shoots
	 */
	public void fireTowers() {
		towers.stream().forEach(t -> {
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if(enemies.size()>0){
						t.fire(enemies);
						removeDeadEnemies();
					}else {
						stopTowers();
					}
				}
			};
			t.setTask(task);
			timer.scheduleAtFixedRate(task, 1000, t.getFireRate()*1000);
		});
	}
	
	
	private void removeDeadEnemies() {
		List<Enemy> deadEnemies= enemies.stream().filter(e -> !e.isAlive()).collect(Collectors.toList());
		for (Enemy enemy : deadEnemies) {
			enemy.getTask().cancel();
			System.out.println(enemy.getLabel() + " is dead now");
		}
		enemies.removeAll(deadEnemies);
	}
	
	public void stopTowers() {
		timer.cancel();
		System.out.println("No enemies left, towers are going to sleep");
	}
	
	public void stopGame() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(isGameOver()){
					timer.cancel();
					System.out.println("Game over, all enemies reached the target");
				}
			}
		},0,5000);
	}
}
