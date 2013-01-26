package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class VillagePriestWindow extends Window {
	private static final int difficulty = 10
	private String vt
	public VillagePriestWindow(String villageType) {
		super("Village - Priest")
		vt = villageType
		addComponent(new Label("The priest handles all marriages, births, and deaths.\nHe is also one of the few literate people here."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...talk to him", talkToPriestAction))
		addComponent(new Button("...infiltrate his private sanctum", spyOnPriestAction))
		addComponent(new Button("...invoke the power of X to gain insight into him"))
		addComponent(new Button("...return to the village", new VillageAction(vt)))
	}
	
	def talkToPriestAction = {
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Talking to the priest", "Cheerily, the priest says,\n'Our village is a happy one. I hold mass every day.'\n")
	} as Action
	
	private void examinePriest(boolean succeeded) {
		if (succeeded) {
			if (vt.equalsIgnoreCase("lillith_priest") || vt.equalsIgnoreCase("lillith_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the priest - Success", "The priest says to a choir-boy, 'What a lovely, lovely voice...'\nwhile undoing the choir-boy's clothing.")
			} else if (vt.equalsIgnoreCase("baphomet_priest") || vt.equalsIgnoreCase("baphomet_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the priest - Success", "The priest recites holy texts backwards in a distorted abnormally-deep voice,\nthen drinks wine until he passes out.")
			} else if (vt.equalsIgnoreCase("samael_priest") || vt.equalsIgnoreCase("samael_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the priest - Success", "The priest draws a symbol consisting of a circle with three smaller circles in it.\nThen you see that he is doing this with his own blood.")
			} else if (vt.equalsIgnoreCase("akumasama_priest") || vt.equalsIgnoreCase("akumasama_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the priest - Success", "The priest is dressing up a little girl in ritual clothes of white and red.\nHe then sharpens a dagger, points it at the girl, and says,\n'Akuma-sama awaits you...'")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the priest - Success", "")
			}
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the priest - Failure", "You see the blacksmith pounding away repetitively\non a hunk of heated metal.")
		}
	}
	def spyOnPriestCheck = { PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("DEX")
		result += pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("athletics")
		result += pc.rollSkill("stealth")
		examinePriest(result > difficulty)
		InterfaceState.nextWindow = new VillagePriestWindow(vt)
		UnmappedMain.closeCurrent()
	}
	def spyOnPriestAction = {
		InterfaceState.nextWindow = new WhoWillWindow("spy on the priest", spyOnPriestCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
