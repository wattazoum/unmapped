package patrickwong.unmapped.outdoors.raubritter

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.HumanKnight
import patrickwong.unmapped.demon.HumanSergeant
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class CombatStateRaubritterMain extends CombatState {
	private boolean raubritterAlive
	
	Closure doVictory = {
		String victoryMessage = "Mortally wounded, the evil robber knight coughs out his life at your feet. His\nminions flee into the countryside, where they will be hunted down by locals.\nYou have won. You grin, clap each other on the back, and set about looting\nthe robber's castle. Taking what you can carry, you leave the remainder\nfor local peasants."
		int money = DiceRoller.binaryPool(300)
		GameState.getInstance().partyMoney += money
		victoryMessage += "\nYou find " + UnmappedMain.moneyAsString(money) + " worth of money and gear\nin the robber-knight's estate\n"
		GameState.getInstance().getParty().each {
			it.removeShock(100)
			it.removePain(10)
			it.removeWounds(1)
		}
		return victoryMessage
	}
	Closure afterVictory = {
		// we just came from a CombatWindow being closed
		InterfaceState.nextWindow = new OutsideWindow()
	}
	Closure afterRunning = {
		// we just came from a CombatWindow being closed
		if (raubritterAlive) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "The raubritter and his men laugh at you.\n'Not so heroic, eh?' they taunt.")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "The raubritter's death and ensuing combat has sewn just enough\nchaos to let you escape. However, the overall robbing operation\nis left intact.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public CombatStateRaubritterMain(boolean startAtMelee, boolean raubritterAlive) {
		this.raubritterAlive = raubritterAlive
		EnemyGroup raueberGruppe = new EnemyGroup(groupName: "Rauebergruppe 1")
		List<Enemy> theEnemies = new Vector<Enemy>()
		int fillerEnemies = DiceRoller.binaryPool(15)
		for (int i = 0; i < fillerEnemies; i++) {
			theEnemies.add(new HumanSergeant(name: ("Unteroffizier " + (i + 1))))
		}
		raueberGruppe.enemies = theEnemies
		if (startAtMelee) {
			raueberGruppe.distance = 0
		} else {
			raueberGruppe.distance = 4
		}
		
		EnemyGroup raubritterGruppe = new EnemyGroup(groupName: "Raubrittergruppe 1")
		List<Enemy> otherEnemies = new Vector<Enemy>()
		int moreEnemies = DiceRoller.binaryPool(5)
		for (int i = 0; i < moreEnemies; i++) {
			otherEnemies.add(new HumanSergeant(name: ("Bodyguard " + (i + 1))))
		}
		if (raubritterAlive) {
			otherEnemies.add(new HumanKnight(name: "Raubritter"))
		}
		raubritterGruppe.enemies = otherEnemies
		if (startAtMelee) {
			raubritterGruppe.distance = 0
		} else {
			raubritterGruppe.distance = 4
		}
		
		enemyGroups = [ raueberGruppe, raubritterGruppe ]
	}
}
