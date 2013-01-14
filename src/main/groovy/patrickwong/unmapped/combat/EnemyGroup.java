package patrickwong.unmapped.combat;

import java.util.List;
import java.util.Vector;

import patrickwong.unmapped.DiceRoller;
import patrickwong.unmapped.enemy.DefaultEnemy;
import patrickwong.unmapped.enemy.Enemy;

/**
 * 
 * @author ssfsx17
 *
 * If extending this class, remember to override... like... almost everything.
 */
public class EnemyGroup {
	String groupName = "Group of default enemies";
	int distance = 2;
	List<Enemy> enemies = new Vector<Enemy>();
	
	public EnemyGroup() {
		groupName = "Default Group";
		distance = 2;
		enemies = new Vector<Enemy>();
		enemies.add(new DefaultEnemy("Default Enemy 1"));
		enemies.add(new DefaultEnemy("Default Enemy 2"));
		enemies.add(new DefaultEnemy("Default Enemy 3"));
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDistance() {
		return distance;
	}
	
	public boolean hasValidEnemies() {
		return (getValidEnemies().size() > 0);
	}
	
	public List<Enemy> getValidEnemies() {
		List<Enemy> validEnemies = new Vector<Enemy>();
		for (Enemy enemy : enemies) {
			if (!enemy.isDead()) {
				validEnemies.add(enemy);
			}
		}
		return validEnemies;
	}
	
	public Enemy getFirstValidEnemy() {
		List<Enemy> validEnemies = getValidEnemies();
		if (validEnemies.size() > 0) {
			return validEnemies.get(0);
		}
		return null;
	}
	
	public Enemy getRandomValidEnemy() {
		List<Enemy> validEnemies = getValidEnemies();
		if (validEnemies.size() > 0) {
			int rand = DiceRoller.nextInt(validEnemies.size());
			return validEnemies.get(rand);
		}
		return null;
	}
	
	public String getSummary() {
		String summary = "A " + groupName;
		
		if (distance > 0) {
			summary +=  " (" + distance + " rounds away)";
		} else {
			summary += " getting all up in yo face!";
		}
		summary += "\n";
		
		return summary;
	}
	
	public String getStatus() {
		String statusString;
		List<Enemy> validEnemies = getValidEnemies();
		if (validEnemies.size() <= 0) {
			statusString = groupName + " is wiped out\n";
		} else {
			statusString = getSummary();
			for (Enemy enemy : enemies) {
				if (!enemy.isDead()) {
					statusString += enemy.getStatus();
					statusString += "\n";
				}
			}
		}
		return statusString;
	}
	
	public String nextAction() {
		String nextActionString = groupName + " takes its turn.\n";
		if (distance > 0) {
			distance -= 1;
			nextActionString += groupName + " advances!\n";
			if (distance <= 0) {
				distance = 0;
			}
		} else {
			for (Enemy enemy : enemies) {
				if (!enemy.isDead()) {
					enemy.midRoundLogic();
					nextActionString += enemy.nextAction();
					nextActionString += "\n";
				}
			}
		}
		return nextActionString;
	}
}
