package patrickwong.unmapped.combat

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

public class CombatState {
	List<EnemyGroup> enemyGroups
	List<CombatDecision> decisions
	Integer currentCharacter
	Boolean inMelee
	Closure doVictory = {
		String victoryMessage = "The sociopaths lie dead before you.\n"
		int money = DiceRoller.binaryPool(100)
		GameState.getInstance().partyMoney += money
		victoryMessage += "They had " + UnmappedMain.moneyAsString(money) + " worth of money and gear on them.\n"
		for (PlayerCharacter pc : GameState.getInstance().getParty()) {
			pc.removeShock(100)
		}
		return victoryMessage
	}
	Closure afterVictory = {
		
	}
	Closure afterRunning = {
		
	}
	
	public CombatState() {
		enemyGroups = new Vector<EnemyGroup>()
		enemyGroups.add(new EnemyGroup())
		decisions = new Vector<CombatDecision>()
		currentCharacter = 0
		inMelee = false
	}
	
	public void cancelDecisions() {
		decisions = new Vector<CombatDecision>()
		currentCharacter = 0
	}
	
	public void removeDecision(String charName) {
		CombatDecision decisionToRemove = null
		for (CombatDecision cd : decisions) {
			if (cd.charName.equals(charName)) {
				decisionToRemove = cd
			}
		}
		decisions.remove(decisionToRemove)
	}
	
	public List<EnemyGroup> getAllGroupsInMelee() {
		List<EnemyGroup> meleeGroups = new Vector<EnemyGroup>()
		for (EnemyGroup eg : enemyGroups) {
			if ((eg.getDistance() <= 0) && (eg.hasValidEnemies())) {
				meleeGroups.add(eg)
			}
		}
		return meleeGroups
	}
	public List<EnemyGroup> getAllGroupsAtRange() {
		List<EnemyGroup> rangeGroups = new Vector<EnemyGroup>()
		for (EnemyGroup eg : enemyGroups) {
			if ((eg.getDistance() > 0) && (eg.hasValidEnemies())) {
				rangeGroups.add(eg)
			}
		}
		return rangeGroups
	}
	public EnemyGroup getFirstGroupInMelee() {
		List<EnemyGroup> meleeGroups = getAllGroupsInMelee()
		if (meleeGroups.size() <= 0) {
			return null
		}
		return getAllGroupsInMelee().get(0)
	}
	
	public EnemyGroup getRandomGroupInMelee() {
		List<EnemyGroup> meleeGroups = getAllGroupsInMelee()
		if (meleeGroups.size() <= 0) {
			return null
		}
		int rand = DiceRoller.nextInt(meleeGroups.size())
		return meleeGroups.get(rand)
	}
	
	public EnemyGroup getRandomGroupAtRange() {
		List<EnemyGroup> rangeGroups = getAllGroupsAtRange()
		if (rangeGroups.size() <= 0) {
			return null
		}
		int rand = DiceRoller.nextInt(rangeGroups.size())
		return rangeGroups.get(rand)
	}
	
	public List<Enemy> getAllEnemies() {
		List<Enemy> enemies = new Vector<Enemy>()
		enemyGroups.each {
			enemies.addAll(it.enemies)
		}
		return enemies
	}
	
	public int countEnemies() {
		int total = 0
		for (EnemyGroup eg : enemyGroups) {
			total += eg.enemies.size()
		}
		return total
	}
	
	public void addRange(int rangeMod) {
		for (EnemyGroup eg : enemyGroups) {
			eg.distance += rangeMod
		}
	}
	
	public void decreaseRange(int rangeMod) {
		for (EnemyGroup eg : enemyGroups) {
			eg.distance -= rangeMod
			if (eg.distance <= 0) {
				eg.distance = 0
			}
		}
	}
	
	public void setRange(int range) {
		for (EnemyGroup eg : enemyGroups) {
			eg.distance = range
			if (eg.distance <= 0) {
				eg.distance = 0
			}
		}
	}
}
