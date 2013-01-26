package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.MinionLillith
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.QuestPhase;
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class CombatStateVillageLillith extends CombatState {
	boolean wholeVillage
	private static final QuestPhase hideoutLillith = new QuestPhase(key:"hideout", order:10, description:"Learned the location of Lillith's Festival")
	
	Closure doVictory = {
		String victoryMessage = "victory message (this is a bug)"
		if (wholeVillage) {
			victoryMessage = "One of the women has an illuminated message tattooed on her belly\nexplaining how to find the Festival of Lillith in the forest"
			GameState.getInstance().addQuestPhase("Lillith", hideoutLillith)
		} else {
			victoryMessage = "On the corpse is a journal describing a relationship with a being named\n'Lillith' - perhaps there is a way to stop them all..."
		}
		int money = DiceRoller.binaryPool(100)
		GameState.getInstance().partyMoney += money
		victoryMessage += "\nYou find " + UnmappedMain.moneyAsString(money) + " worth of money and gear\n"
		GameState.getInstance().getParty().each {
			it.removeShock(100)
		}
		return victoryMessage
	}
	Closure afterVictory = {
		// we just came from a CombatWindow being closed
		InterfaceState.nextWindow = new OutsideWindow()
	}
	Closure afterRunning = {
		// we just came from a CombatWindow being closed
		if (wholeVillage) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "One of the minions of Lillith shoots some kind of smelly fluid\nat you as you run away and says, 'And don't come back!'")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "All of the other villagers follow you in your\nretreat, never to return again.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public CombatStateVillageLillith(boolean wholeVillage) {
		super()
		this.wholeVillage = wholeVillage
		if (wholeVillage) {
			List<Enemy> evilVillagers = new Vector<Enemy>()
			int fillerVillagers = DiceRoller.binaryPool(20)
			for (int i = 0; i < fillerVillagers; i++) {
				evilVillagers.add(new Enemy(name: ("Lillith Slave " + (i + 1))))
			}
			enemyGroups = [
				new EnemyGroup(groupName: "Lillith Dominators", distance: 0,
					enemies: [
						new MinionLillith(name: "Lillith Dominator 1"), new MinionLillith(name: "Lillith Dominator 2"), new MinionLillith(name: "Lillith Dominator 3")
					]
				),
				new EnemyGroup(groupName: "Lillith Slaves", distance: 0, enemies: evilVillagers)
			]
		} else {
			enemyGroups = [
				new EnemyGroup(groupName: "Lillith Dominators", distance: 0,
					enemies: [ new MinionLillith(name: "Lillith Dominator 1") ]
				)
			]
		}
	}
}
