package patrickwong.unmapped.outdoors.raubritter

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.outdoors.OutsideWindow
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class RaubritterSneakWindow extends Window {
	private static final int difficulty = 50
	public RaubritterSneakWindow() {
		super("Raubritter Tower - Sneaking Around")
		addComponent(new Label("This is the inner sanctum of the raubritter's tower. Although it is\nnight, there are still guards with torches patrolling the halls."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...assassinate the raubritter in his sleep", assassinateAction))
		addComponent(new Button("...plunder one of the treasure chests", plunderAction))
		addComponent(new Button("...exfiltrate from the tower and escape", exfiltrateAction))
	}
	
	Closure assassinateCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("AGI") + pc.rollStat("DEX")
		result = (result / 2)
		result += pc.rollSkill("stealth") + pc.rollSkill("short_weapons")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Assassinating the raubritter - Success", "You catch the raubritter sleeping in bed with a little boy who was\nrecently castrated. The raubritter dies easily, although the\ncastrati wakes up screaming loudly. Bodyguards rush to the scene.")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(false, false))
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Assassinating the raubritter - Failure", "The raubritter reaches up and grabs your hand as he asks,\n'Do you think you are the first one to try that?'\nHe bellows loudly, 'Slay me these idiots!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action assassinateAction = {
		InterfaceState.nextWindow = new WhoWillWindow("assassinate the raubritter", assassinateCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure plunderCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("DEX") + pc.rollStat("LOG")
		result = (result / 2)
		result += pc.rollSkill("stealth") + pc.rollSkill("locksmithing")
		if (result > (difficulty + DiceRoller.nextInt(40))) {
			int amount = DiceRoller.binaryPool(200)
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Plundering a chest - Success", "The heavy lock melts in your hands, and the treasure chest\nyields its bounty silently. It has\n" + UnmappedMain.moneyAsString(amount))
			GameState.getInstance().partyMoney += amount
			InterfaceState.nextWindow = new RaubritterSneakWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Plundering a chest - Failure", "The lock is much more difficult than you had expected, as if some\nkind of random number god wanted to make your life miserable. While\nyou are still fiddling with it, the ceiling slides open to reveal the\nraubritter whispering, 'Now here is your pay for the day...\nHey! Slay me these idiots!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action plunderAction = {
		InterfaceState.nextWindow = new WhoWillWindow("pick the lock on a treasure chest", plunderCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure exfiltrateCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("DEX") + pc.rollStat("AGI")
		result = (result / 2)
		result += pc.rollSkill("stealth") + pc.rollSkill("climbing")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Exfiltration - Success", "You rappel off the raubritter's tower and hit the ground\nwithout a sound. You are long gone before the guards reach\nyour part of the walls.")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Exfiltration - Failure", "As you descend past a window, the raubritter opens it to get\nsome fresh air. He looks you in the eyes as he shouts, 'Intruders!\nSlay me these idiots!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action exfiltrateAction = {
		InterfaceState.nextWindow = new WhoWillWindow("go first in exfiltrating from the tower", exfiltrateCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
