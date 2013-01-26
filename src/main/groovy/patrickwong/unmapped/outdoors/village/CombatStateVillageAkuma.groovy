package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.MinionAkuma
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.QuestPhase
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class CombatStateVillageAkuma extends CombatState {
	boolean wholeVillage
	private static final QuestPhase hideoutAkuma = new QuestPhase(key:"hideout", order:10, description:"Learned the location of Akuma-sama no sokuishiki")
	
	Closure doVictory = {
		String victoryMessage = "victory message (this is a bug)"
		if (wholeVillage) {
			victoryMessage = "The last minion reveals, 'Akuma-sama no sokuishiki will take place at\nthe shrine in the deep forest! He is waiting to kill you there!'"
			GameState.getInstance().addQuestPhase("Akuma-sama", hideoutAkuma)
		} else {
			victoryMessage = "Apparently, this former human served a being known as\n'Akuma-sama' - perhaps there is a way to stop them all..."
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
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "One of the minions shouts, 'Oh Akuma-sama,\nwe have won a great victory today!'")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "All of the other villagers follow you in your\nretreat, never to return again.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public CombatStateVillageAkuma(boolean wholeVillage) {
		super()
		this.wholeVillage = wholeVillage
		if (wholeVillage) {
			List<Enemy> evilVillagers = new Vector<Enemy>()
			int fillerVillagers = DiceRoller.binaryPool(20)
			for (int i = 0; i < fillerVillagers; i++) {
				evilVillagers.add(new Enemy(name: ("Akuma-sama no Dorei " + (i + 1))))
			}
			enemyGroups = [
				new EnemyGroup(groupName: "Akuma-sama no Kerai", distance: 0,
					enemies: [
						new MinionAkuma(name: "Akuma-sama no Kerai 1"), new MinionAkuma(name: "Akuma-sama no Kerai 2"), new MinionAkuma(name: "Akuma-sama no Kerai 3")
					]
				),
				new EnemyGroup(groupName: "Akuma-sama no Dorei", distance: 0, enemies: evilVillagers)
			]
		} else {
			enemyGroups = [
				new EnemyGroup(groupName: "Akuma-sama no Kerai", distance: 0,
					enemies: [ new MinionAkuma(name: "Akuma-sama no Kerai 1") ]
				)
			]
		}
	}
}
