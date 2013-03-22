package patrickwong.unmapped.outdoors.countryside

import patrickwong.unmapped.DiceRoller
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

public class JoustChallengeWindow extends Window {
	private int difficulty
	public JoustChallengeWindow() {
		super("Countryside - Joust Challenge")
		difficulty = DiceRoller.binaryPool(100)
		addComponent(new Label("A knight, fully outfitted for battle, calls out to you,\n'Ah, you look strong! Let us joust!'"))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...joust", joustAction))
		addComponent(new Button("...offer to duel him on foot instead", duelAction))
		if (GameState.getInstance().hasBook("sun")) {
			addComponent(new Button("...invoke the Book of the Sun to make him respect you", sunAction))
		}
		addComponent(new Button("...drink a Potion of Fatal Frame, then joust (not implemented)"))
		addComponent(new Button("...decline his challenge and move on", new OutsideAction()))
	}
	
	Closure joustCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("STR") + pc.rollStat("DEX") + pc.rollStat("REF") + pc.rollStat("TGH") + pc.rollStat("WIT") + pc.rollStat("SIX")
		result += pc.rollSkill("outdoors") + pc.rollSkill("riding") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("spears") + pc.rollSkill("polearms") + pc.rollSkill("melee_defense") + pc.rollSkill("shield_blocking")
		result = (result / 10)
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Jousting - Success", "You smash a wooden lance squarely into the knight's body, unseating\nhim and sending him flying. He gives you respect.")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Jousting - Failure", "You charge at each other and promptly find yourself flat on your back.\nThe knight rides away without a word.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
		UnmappedMain.closeCurrent()
	}
	Action joustAction = {
		InterfaceState.nextWindow = new WhoWillWindow("joust the knight", joustCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure duelCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("STR") + pc.rollStat("DEX") + pc.rollStat("REF") + pc.rollStat("TGH") + pc.rollStat("AGI") + pc.rollStat("END")
		result += pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("swords_straight_one") + pc.rollSkill("swords_straight_two") + pc.rollSkill("melee_defense") + pc.rollSkill("shield_blocking") + pc.rollSkill("shield_bashing") + pc.rollSkill("parrying")
		result = (result / 10)
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Duelling - Success", "You clash swords, shields, and armor for an incredibly long time.\nFinally, after knocking him in the helmet many times, he shouts,\n'I yield!' He then gives you respect.")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Duelling - Failure", "After a very long and hard-fought duel, he trips you to the ground\nand rubs the flat of his blade against your neck. You are bested.\nHe turns and walks away.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
		UnmappedMain.closeCurrent()
	}
	Action duelAction = {
		InterfaceState.nextWindow = new WhoWillWindow("duel the knight", duelCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure sunCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("SIX") + pc.rollStat("VER")
		result = (result / 2)
		result += pc.rollSkill("supernatural") + pc.rollSkill("illumination")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Invoking the Book of the Sun - Success!", "The knight raises his arms and shouts, 'Praise the sun!' He then\ngives you respect and leaves.")
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Invoking the Book of the Sun - Failure!", "The knight breathes heavily for several minutes, then suddenly blurts,\n'What am I doing here? Why am I talking to you?' He rides off and leaves you.")
		}
		InterfaceState.nextWindow = new OutsideWindow()
		UnmappedMain.closeCurrent()
	}
	Action sunAction = {
		InterfaceState.nextWindow = new WhoWillWindow("invoke the Book of the Sun", sunCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
