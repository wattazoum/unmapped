package patrickwong.unmapped.outdoors.raubritter

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.HumanSergeant
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class CombatStateRaubritterChallenge extends CombatState {
	private int wavesRemaining
	Closure doVictory = {
		String victoryMessage = "The raubritter's men have been defeated, and you are once again at the\nbase of the Raubritter's tower."
		int money = DiceRoller.binaryPool(150)
		GameState.getInstance().partyMoney += money
		victoryMessage += "\nYou find " + UnmappedMain.moneyAsString(money) + " worth of money and gear\n"
		GameState.getInstance().getParty().each {
			it.removeShock(100)
			it.removePain(10)
			it.removeWounds(1)
		}
		return victoryMessage
	}
	Closure afterVictory = {
		// we just came from a CombatWindow being closed
		InterfaceState.nextWindow = new RaubritterChallengeWindow(wavesRemaining - 1)
	}
	Closure afterRunning = {
		// we just came from a CombatWindow being closed
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "The raubritter and his men laugh at you.\n'What happened to all that talk?' they taunt.")
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public CombatStateRaubritterChallenge(int wavesRemaining) {
		super()
		this.wavesRemaining = wavesRemaining
		List<Enemy> theEnemies = new Vector<Enemy>()
		int fillerEnemies = DiceRoller.binaryPool(15)
		for (int i = 0; i < fillerEnemies; i++) {
			theEnemies.add(new HumanSergeant(name: ("Unteroffizier " + (i + 1))))
		}
		enemyGroups = [
			new EnemyGroup(groupName: "Rauebergruppe", distance: 0, enemies: theEnemies)
		]
	}
}
