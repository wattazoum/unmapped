package patrickwong.unmapped.town

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.outdoors.OutsideAction
import patrickwong.unmapped.outdoors.OutsideWindow
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class ExitGateWindow extends Window {
	private static final int difficulty = 30
	public ExitGateWindow() {
		super("Town - Exit")
		addComponent(new Label("The gate is heavily guarded, but most of the guards\nare watching for people trying to get into the city\nillegally, not for those trying to leave."))
		addComponent(new Label("You estimate that you are in good legal standing."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...simply walk out the gate, right past the guards", new OutsideAction()))
		addComponent(new Button("...blend in with the crowd leaving the town", sneakOutAction))
		addComponent(new Button("...scale the walls at night when the guards aren't looking", climbOutAction))
		addComponent(new Button("...throw a Potion of Sapkowski to cause a panic, and flee in the chaos (not implemented)"))
		if (GameState.getInstance().hasBook("hiddenmeaning")) {
			addComponent(new Button("...invoke the Book of Hidden Meaning on the guards and pass unnoticed", hiddenMeaningAction))
		}
		addComponent(new Button("...draw weapons and attack the guards! (not implemented)"))
		addComponent(new Button("...return to the town's main street", new MainStreetAction()))
	}
	
	Action sneakOutAction = {
		InterfaceState.nextWindow = new WhoWillWindow("lead the way in sneaking out", sneakOutCheck)
		UnmappedMain.closeCurrent()
	} as Action
	def sneakOutCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("DEX")
		result += pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("athletics")
		result += pc.rollSkill("stealth")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Sneaking out of town - success", "There are so many people leaving town that the guards\nsimply can't keep track of them all.\nYou blend in with the crowd easily.")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Sneaking out of town - failure", "The guards point at you and shout, 'Hey!\nGo through outprocessing like everyone else!'")
			InterfaceState.nextWindow = new ExitGateWindow()
		}
		UnmappedMain.closeCurrent()
	}
	
	Action climbOutAction = {
		InterfaceState.nextWindow = new WhoWillWindow("lead the climb up the walls", climbOutCheck)
		UnmappedMain.closeCurrent()
	} as Action
	def climbOutCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("END")
		result += pc.rollStat("STR")
		result = (result / 2)
		result += pc.rollSkill("athletics")
		result += pc.rollSkill("climbing")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Climbing out of town - success", "You are able to swiftly scale the wall and\nrappel down the other side before anyone sees you.")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Climbing out of town - failure", "The guards help you reach the top of the wall, where a small\njail awaits. You are forced to wait with the drunkards\nand hysterics until next morning.")
			InterfaceState.nextWindow = new ExitGateWindow()
		}
		UnmappedMain.closeCurrent()
	}
	
	Action hiddenMeaningAction = {
		InterfaceState.nextWindow = new WhoWillWindow("invoke the Book of Hidden Meaning", hiddenMeaningCheck)
		UnmappedMain.closeCurrent()
	} as Action
	def hiddenMeaningCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("SIX") + pc.rollStat("LOG")
		result = (result / 2)
		result += pc.rollSkill("supernatural") + pc.rollSkill("stealth")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Invoking the Book of Hidden Meaning - success!", "You walk right out the gate, past the guards.\nThey look at you, then look back the other way.")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Invoking the Book of Hidden Meaning - failure", "At first it seems the guards will let you pass, but one of them\nstops you and says, 'Nobody likes a queue jumper. Back of\nthe line for you.'")
			InterfaceState.nextWindow = new ExitGateWindow()
		}
	}
}
