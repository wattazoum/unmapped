package patrickwong.unmapped.town.slumencounters

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.combat.EnemyGroup
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.WhoWillWindow
import patrickwong.unmapped.town.SlumDistrictAction
import patrickwong.unmapped.town.SlumDistrictWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class SlumencounterLowlivesWindow extends Window {
	private CombatState state
	
	public SlumencounterLowlivesWindow() {
		super("Picking a fight - Lowlives")
		state = genCombat()
		addComponent(new Label("A bunch of lowlives come out to see what you are on about.\nThey are armed with simple blunt weapons."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...draw weapons immediately", genCombatAction(4)))
		addComponent(new Button("...walk up to them and start shoving them", genCombatAction(0)))
		addComponent(new Button("...threaten them because they better not mess with you", threatenAction))
		addComponent(new Button("...throw a Potion of Sapkowski to instagib them (not implemented)"))
		addComponent(new Button("...invoke the power of X to make them respect you (not implemented)"))
		addComponent(new Button("...leave them be, as they are clearly not worth it", new SlumDistrictAction()))
	}
	
	private Enemy genEnemy(String enemyName) {
		Enemy enemy = new Enemy(name: enemyName)
		return enemy
	}
	
	private EnemyGroup genEnemyGroup() {
		int quantity = DiceRoller.binaryPool(10)
		EnemyGroup group = new EnemyGroup(groupName: "Bunch of Lowlives", enemies: new Vector<Enemy>())
		for (int i = 1; i <= quantity; i++) {
			group.enemies.add(genEnemy("Lowlife " + i))
		}
		return group
	}
	
	private CombatState genCombat() {
		CombatState state = new CombatState()
		state.enemyGroups = [genEnemyGroup()]
		state.doVictory = {
			String victoryMessage = "The lowlives lie dead before you, bleeding out all over the street.\n"
			int moneyPool = state.countEnemies() * 20
			int money = DiceRoller.binaryPool(moneyPool)
			GameState.getInstance().partyMoney += money
			String moneyString = UnmappedMain.moneyAsString(money)
			victoryMessage += "They had " + moneyString + "\nworth of money and gear on them.\n"
			for (PlayerCharacter pc : GameState.getInstance().getParty()) {
				pc.removeShock(1000)
			}
			return victoryMessage
		}
		state.afterVictory = {
			// we just came from a CombatWindow being closed
			InterfaceState.nextWindow = new SlumDistrictWindow()
		}
		state.afterRunning = {
			// we just came from a CombatWindow being closed
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away from the lowlives", "The lowlives shout, 'You ain't so tough now bitches!'")
			InterfaceState.nextWindow = new SlumDistrictWindow()
		}
		return state
	}
	
	private Action genCombatAction(int startingDistance) {
		def toCombat = {
			state.setRange(startingDistance)
			InterfaceState.nextWindow = new CombatWindow(state)
			UnmappedMain.closeCurrent()
		} as Action
		return toCombat
	}
	
	def threatenAction = {
		InterfaceState.nextWindow = new WhoWillWindow("threaten the lowlives", threatenCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	def threatenCheck = {
		PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("VER")
		result += pc.rollStat("TGH")
		result = (result / 2)
		result += pc.rollSkill("socializing")
		result += pc.rollSkill("intimidate")
		if (result > 50) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Threatening the Lowlives - Success", "The lowlives back off when they see you mean business.")
			InterfaceState.nextWindow = new SlumDistrictWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Threatening the Lowlives - Failure", "The lowlives are unimpressed by your threats.\nOne of them shouts, 'You wanna do this? Let's do this!'")
			CombatState state = genCombat()
			state.setRange(0)
			InterfaceState.nextWindow = new CombatWindow(state)
		}
		UnmappedMain.closeCurrent()
	}
}
