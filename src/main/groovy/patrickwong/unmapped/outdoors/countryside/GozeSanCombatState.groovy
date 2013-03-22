package patrickwong.unmapped.outdoors.countryside

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.demon.GozeAssassin
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.dialog.MessageBox

public class GozeSanCombatState extends CombatState {
	Closure doVictory = {
		String victoryMessage = "The blind assassin woman has finally been defeated. Inside her begging box, you find the contract against your life and a few coins.\n"
		int money = DiceRoller.binaryPool(20)
		GameState.getInstance().partyMoney += money
		victoryMessage += "\nThe coins were worth " + UnmappedMain.moneyAsString(money)
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
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "Fortunately, your would-be assassin is blind, and thus cannot\ngive chase through the rough terrain.")
		InterfaceState.nextWindow = new OutsideWindow()
	}
	
	public GozeSanCombatState() {
		super()
		enemyGroups = [
			new EnemyGroup(groupName: "Blind Assassin", distance: 1, meleeWeight: 30, enemies: [new GozeAssassin(name:"Goze-san")])
		]
	}
}
