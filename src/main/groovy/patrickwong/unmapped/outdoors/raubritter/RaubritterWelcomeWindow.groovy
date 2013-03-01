package patrickwong.unmapped.outdoors.raubritter

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.outdoors.OutsideWindow
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class RaubritterWelcomeWindow extends Window {
	private static final int difficulty = 40
	public RaubritterWelcomeWindow() {
		super("Raubritter Tower - Welcomed")
		addComponent(new Label("Grinning wolfishly, the raubritter meets with you inside his crude audience\nchamber. It contains a long wooden table with many foods arrayed on\nit - wood-fired boar, salted beef, breaded chicken, and the like. The\nmeat was extorted out of travellers, and the ale was stolen from villages.\nThe raubritter feats heartily with his tough, scarred men-at-arms.\nOffering some refreshment to you, he begins by saying,\n'Let us reason together.'"))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...try to convince him to surrender with a daring move", causeSurrenderAction))
		addComponent(new Button("...ask to spend the night, planning to sneak out of your room", stayNightAction))
		addComponent(new Button("...draw your weapons and attack immediately", attackImmediatelyAction))
		addComponent(new Button("...swiftly slit his throat", slitThroatAction))
		addComponent(new Button("...invoke the Book of X to force his surrender (not implemented)"))
		addComponent(new Button("...slip a Potion of Octagon into his beer (not implemented)"))
		addComponent(new Button("...ask permission to leave", askLeaveAction))
	}
	
	Action attackImmediatelyAction = {
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Attacking immediately", "The raubritter's sword leaps to his hand and his men do not seem\nsurprised by this turn of events. He bellows, 'You are very\nbrave... and very stupid! Slay me these idiots!'")
		InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure causeSurrenderCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("VER") + pc.rollStat("TGH")
		result = (result / 2)
		result += pc.rollSkill("socializing") + pc.rollSkill("intimidate")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Convincing the raubritter to surrender - Success", "'I surrender! Take my second-in-command instead!' he squeals.\nHis lieutenant shoots him in the head with a crossbow and shouts,\n'I am the new leader! Slay these do-gooders!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(false, false))
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Convincing the raubritter to surrender - Failure", "The raubritter's sword leaps to his hand as he calls to his men,\n'Slay me these idiots! Never surrender!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action causeSurrenderAction = {
		InterfaceState.nextWindow = new WhoWillWindow("convince the raubritter to surrender", causeSurrenderCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure stayNightCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("VER") + pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("socializing") + pc.rollSkill("etiquette")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Asking to spend the night - Success", "'Hmph. I suppose you need to rest after this feast we just had.'\nHe escorts you to a guest room and lets you sleep.")
			InterfaceState.nextWindow = new RaubritterSneakWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Asking to spend the night - Failure", "The raubritter ponders, 'Let you spend the night...\nso you can murder me in secret! Slay me these idiots!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action stayNightAction = {
		InterfaceState.nextWindow = new WhoWillWindow("ask the raubritter to stay the night", stayNightCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure slitThroatCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("AGI") + pc.rollStat("DEX")
		result = (result / 2)
		result += pc.rollSkill("stealth") + pc.rollSkill("short_weapons")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Slitting the raubritter's throat - Success", "The raubritter chokes on his own blood as he collapses to the ground.\nBy the time his men realize what has happened, you are already prepared\nfor a fight.")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(false, false))
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Slitting the raubritter's throat - Failure", "The raubritter grabs your hand as he asks,\n'Do you think you are the first one to try that?'\nHe shouts at his men, 'Slay me these idiots!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action slitThroatAction = {
		InterfaceState.nextWindow = new WhoWillWindow("try to slit the raubritter's throat", slitThroatCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	Closure askLeaveCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("VER") + pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("socializing") + pc.rollSkill("etiquette")
		if (result > difficulty) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Asking to leave - Success", "The raubritter's men escort you outside. They tell you,\n'And don't come back unless you have something good.'")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Asking to leave - Failure", "The raubritter draws his sword. 'You have seen where I live! Next, you\nwill bring some band of holy men to smite me!' He orders his men,\n'Slay me these idiots!'")
			InterfaceState.nextWindow = new CombatWindow(new CombatStateRaubritterMain(true, true))
		}
		UnmappedMain.closeCurrent()
	}
	Action askLeaveAction = {
		InterfaceState.nextWindow = new WhoWillWindow("ask for permission to leave", askLeaveCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
