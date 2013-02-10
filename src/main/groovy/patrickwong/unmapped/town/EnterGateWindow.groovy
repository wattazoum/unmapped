package patrickwong.unmapped.town

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.outdoors.OutsideAction
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class EnterGateWindow extends Window {
	private int toll
	private static final int difficulty = 30
	public EnterGateWindow() {
		super("Town - Entrance")
		addComponent(new Label("Before you lies the main town."))
		int partySize = GameState.getInstance().party.size()
		toll = 10 * partySize
		addComponent(new Label("With a party of $partySize the toll is " + UnmappedMain.moneyAsString(toll)))
		addComponent(new Label("You estimate that you are in good legal standing."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...pay the toll and enter", payTollAction))
		addComponent(new Button("...talk to the guards about getting in for free", talkGuardAction))
		addComponent(new Button("...blend in with four hooded meditating scholars", blendScholarAction))
		addComponent(new Button("...throw a Potion of Sapkowski and sneak in during the panic (not implemented)"))
		if (GameState.getInstance().hasBook("mercy")) {
			addComponent(new Button("...invoke the Book of Mercy to make the guards let you pass", bookOfMercyAction))
		}
		addComponent(new Button("...draw weapons and attack the guards! (not implemented)"))
		addComponent(new Button("...return to the outside world", new OutsideAction()))
	}
	
	Action payTollAction = {
		GameState.getInstance().partyMoney -= toll
		InterfaceState.nextWindow = new MainStreetWindow()
		UnmappedMain.closeCurrent()
	} as Action
	
	Action talkGuardAction = {
		InterfaceState.nextWindow = new WhoWillWindow("talk to the guards", talkGuardCheck)
		UnmappedMain.closeCurrent()
	} as Action
	def talkGuardCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("VER")
		result += pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("socializing")
		result += pc.rollSkill("streetwise")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Talking to guards - success", "You slap the guard on the shoulder and soon\nyou are conversing as if you were old friends.\nHe invites you to a good place to 'wash away the dust'")
			InterfaceState.nextWindow = new MainStreetWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Talking to - failure", "The guard laughs and says, 'Like I haven't\nheard that one before! Get back in line.'")
			InterfaceState.nextWindow = new EnterGateWindow()
		}
		UnmappedMain.closeCurrent()
	}
	
	Action blendScholarAction = {
		InterfaceState.nextWindow = new WhoWillWindow("blend in with the scholars", blendScholarCheck)
	} as Action
	def blendScholarCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("DEX")
		result += pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("athletics")
		result += pc.rollSkill("stealth")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Blending with the scholars - success", "The guards seem to think that everyone who wears\na cowl and is praying is a scholar, and you\npass unnoticed")
			InterfaceState.nextWindow = new MainStreetWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Blending with the scholars - failure", "You walk a little bit too fast and get separated\nfrom the scholars. You bump face-first into a guard,\nwho says, 'Ahem. Back of the line.'")
			InterfaceState.nextWindow = new EnterGateWindow()
		}
		UnmappedMain.closeCurrent()
	}
	
	Action bookOfMercyAction = {
		InterfaceState.nextWindow = new WhoWillWindow("invoke the Book of Mercy", bookOfMercyCheck)
		UnmappedMain.closeCurrent()
	} as Action
	def bookOfMercyCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("SIX") + pc.rollStat("VER")
		result = (result / 2)
		result += pc.rollSkill("supernatural") + pc.rollSkill("socializing")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Invoking the Book of Mercy - success!", "The guards' eyes glaze over. Suddenly, one of them blurts out,\n'That's such a sad story!' as he lets you pass.")
			InterfaceState.nextWindow = new MainStreetWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Invoking the Book of Mercy - failure", "The guards look at you quizzically, until the guard captain\nsays, 'Rules are rules. Back of the line.'")
			InterfaceState.nextWindow = new EnterGateWindow()
		}
		UnmappedMain.closeCurrent()
	}
}
