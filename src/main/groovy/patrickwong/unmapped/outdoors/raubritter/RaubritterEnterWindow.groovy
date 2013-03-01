package patrickwong.unmapped.outdoors.raubritter

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.outdoors.OutsideAction
import patrickwong.unmapped.outdoors.OutsideWindow
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class RaubritterEnterWindow extends Window {
	private static final int difficulty = 30
	
	public RaubritterEnterWindow() {
		super("Raubritter Tower - Entrance")
		addComponent(new Label("Its spire outlined against the sky, the tower of the robber knight is\nimpressive. Lights gleam from upper windows."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...ask politely to come inside", askAction))
		addComponent(new Button("...lay siege, attacking anyone entering or leaving", new RaubritterSiegeAction(1 + DiceRoller.binaryPool(8))))
		addComponent(new Button("...challenge the raubritter to direct combat", challengeAction))
		addComponent(new Button("...sneak into the tower after dark", sneakAction))
		addComponent(new Button("...storm the tower head-on", stormAction))
		addComponent(new Button("...throw a Potion of Rivia at the gate (not implemented)"))
		addComponent(new Button("...invoke the Book of X to compel the guards to open the gate"))
		addComponent(new Button("...go away", new OutsideAction()))
	}
	
	Closure askCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("VER") + pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("socializing") + pc.rollSkill("streetwise")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Asking to come in - Success", "Smiling, the raubritter comes out and welcomes you inside. 'Even in our\nwild lands, we know of you. Please accept my hospitality.'\nYou step within the robber knight's tower.")
			InterfaceState.nextWindow = new RaubritterWelcomeWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Asking to come in - Failure", "The raubritter barks, 'Screw you, I got mine!' As ballistae and catapults are\nrolled into position, you decide to escape with your life.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	}
	Action askAction = {
		InterfaceState.nextWindow = new WhoWillWindow("ask to come inside", askCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure challengeCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("VER") + pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("socializing") + pc.rollSkill("intimidate")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Challenging the raubritter - Success", "The raubritter and his personal guard leap down from the walls to face you!")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Challenging the raubritter - Failure", "The raubritter scoffs, 'You are not worth the trouble.'")
			InterfaceState.nextWindow = new RaubritterChallengeWindow(1 + DiceRoller.binaryPool(3))
		}
		UnmappedMain.closeCurrent()
	}
	Action challengeAction = {
		InterfaceState.nextWindow = new WhoWillWindow("challenge the raubritter", challengeCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure sneakCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("DEX") + pc.rollStat("AGI")
		result = (result / 2)
		result += pc.rollSkill("stealth") + pc.rollSkill("climbing")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Climbing into the tower - Success", "You swiftly ascend the walls and slip into the raubritter's tower\nbefore anyone can see you.")
			InterfaceState.nextWindow = new RaubritterSneakWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Climbing into the tower - Failure", "Your rope catches onto the helmet of a guard, who promptly cuts it. You\nflee from the raubritter's tower before they can bring their\nfirepower to bear on you.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	}
	Action sneakAction = {
		InterfaceState.nextWindow = new WhoWillWindow("climb up the tower first", sneakCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure stormCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("STR") + pc.rollStat("AGI")
		result = (result / 2)
		result += pc.rollSkill("athletics") + pc.rollSkill("climbing")
		if (result > (difficulty + DiceRoller.nextInt(40))) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Storming the tower - Success", "You evade all manner of arrows and other projectiles as you\ncrash into the raubritter's personal quarters.")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Storming the tower - Failure", "The guards easily push back any ropes or ladders you bring against\nthe tower. Once they roll in the boiling oil and the heavier\ncrossbows, you decide to flee with your lives.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	}
	Action stormAction = {
		InterfaceState.nextWindow = new WhoWillWindow("lead the charge against the tower", stormCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
