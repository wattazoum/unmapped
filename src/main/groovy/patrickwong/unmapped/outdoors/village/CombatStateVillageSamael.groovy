package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.EvilVillager
import patrickwong.unmapped.demon.MinionSamael
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.QuestPhase
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class CombatStateVillageSamael extends CombatState {
	boolean wholeVillage
	private static final QuestPhase hideoutSamael = new QuestPhase(key:"hideout", order:10, description:"Learned the location of the quiet mound")
	
	Closure doVictory = {
		String victoryMessage = "victory message (this is a bug)"
		if (wholeVillage) {
			victoryMessage = "Even though one of the creatures ought to be dead, it speaks,\n'Our special place, deep within the forest, on the quiet mound...'"
			GameState.getInstance().addQuestPhase("Samael", hideoutSamael)
		} else {
			victoryMessage = "You inspect the former human's home and find insane rants\ninscribed on all the walls in blood. One word stands out:\n'Samael' - perhaps there is a way to stop them all..."
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
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "You hear some kind of siren echoing through the village.\nWhen you look back, it is covered in impenetrable fog.")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "All of the other villagers follow you in your\nretreat, never to return again.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public CombatStateVillageSamael(boolean wholeVillage) {
		super()
		this.wholeVillage = wholeVillage
		if (wholeVillage) {
			List<Enemy> evilVillagers = new Vector<Enemy>()
			int fillerVillagers = DiceRoller.binaryPool(20)
			for (int i = 0; i < fillerVillagers; i++) {
				evilVillagers.add(new EvilVillager(name: ("Samael Cultist " + (i + 1))))
			}
			enemyGroups = [
				new EnemyGroup(groupName: "Samael Channelers", distance: 0,
					enemies: [
						new MinionSamael(name: "Samael Channeler 1"), new MinionSamael(name: "Samael Channeler 2"), new MinionSamael(name: "Samael Channeler 3")
					]
				),
				new EnemyGroup(groupName: "Samael Cultists", distance: 0, enemies: evilVillagers)
			]
		} else {
			enemyGroups = [
				new EnemyGroup(groupName: "Samael Channelers", distance: 0,
					enemies: [ new MinionSamael(name: "Samael Channeler 1") ]
				)
			]
		}
	}
}
