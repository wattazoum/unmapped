package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.MinionBaphomet
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.QuestPhase;
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class CombatStateVillageBaphomet extends CombatState {
	boolean wholeVillage
	private static final QuestPhase hideoutBaphomet = new QuestPhase(key:"hideout", order:10, description:"Learned the location of the High Sabbat")
	
	Closure doVictory = {
		String victoryMessage = "victory message (this is a bug)"
		if (wholeVillage) {
			victoryMessage = "On one of their corpses is a map to a location deep in the forest,\nlabelled 'High Sabbat' - perhaps this is the center of Baphomet's power"
			GameState.getInstance().addQuestPhase("Baphomet", hideoutBaphomet)
		} else {
			victoryMessage = "This witchcraft practitioner served one known as \n'Baphomet' - perhaps there is a way to stop them all..."
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
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "The minions of Baphomet cast curses upon you,\nand one of them pledges to make your body rot")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "All of the other villagers follow you in your\nretreat, never to return again.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public CombatStateVillageBaphomet(boolean wholeVillage) {
		super()
		this.wholeVillage = wholeVillage
		if (wholeVillage) {
			List<Enemy> evilVillagers = new Vector<Enemy>()
			int fillerVillagers = DiceRoller.binaryPool(20)
			for (int i = 0; i < fillerVillagers; i++) {
				evilVillagers.add(new Enemy(name: ("Baphomet Thrall " + (i + 1))))
			}
			enemyGroups = [
				new EnemyGroup(groupName: "Baphomet Acolytes", distance: 0,
					enemies: [
						new MinionBaphomet(name: "Baphomet Acolyte 1"), new MinionBaphomet(name: "Baphomet Acolyte 2"), new MinionBaphomet(name: "Baphomet Acolyte 3")
					]
				),
				new EnemyGroup(groupName: "Baphomet Thralls", distance: 0, enemies: evilVillagers)
			]
		} else {
			enemyGroups = [
				new EnemyGroup(groupName: "Baphomet Acolytes", distance: 0,
					enemies: [ new MinionBaphomet(name: "Baphomet Acolyte 1") ]
				)
			]
		}
	}
}
