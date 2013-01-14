package patrickwong.unmapped.combat

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.model.GameState;

public class CombatState {
	List<EnemyGroup> enemyGroups
	List<CombatDecision> decisions
	Integer currentCharacter
	Boolean inMelee
	
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
			if (eg.getDistance() <= 0) {
				meleeGroups.add(eg)
			}
		}
		return meleeGroups
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
	
	public String doVictory() {
		String victoryMessage = "The sociopaths lie dead before you.\n"
		int money = DiceRoller.binaryPool(100)
		GameState.getInstance().partyMoney += money
		victoryMessage += "They had $money worth of money and gear on them.\n"
		return victoryMessage
	}
}
