package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class VillageElderWindow extends Window {
	private static final int difficulty = 10
	private String vt
	public VillageElderWindow(String villageType) {
		super("Village - Elder")
		vt = villageType
		addComponent(new Label("The elder is full of wisdom and gives advice to everyone in the village."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...talk to him", talkToElderAction))
		addComponent(new Button("...spy on him doing his daily business", spyOnElderAction))
		if (GameState.getInstance().hasBook("hindsight")) {
			addComponent(new Button("...invoke the Book of Hindsight to gain insight into him", bookOfHindsightAction))
		}
		addComponent(new Button("...return to the village", new VillageAction(vt)))
	}
	
	def talkToElderAction = {
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Talking to the elder", "The elder has little of import to tell you.\nThe church holds mass every morning,\nthe blacksmith may have some spare things to sell,\nand many of our young ones go to town for excitement.'")
	} as Action
	
	private void examineElder(boolean succeeded) {
		if (succeeded) {
			if (vt.equalsIgnoreCase("lillith_elder") || vt.equalsIgnoreCase("lillith_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the elder - Success", "You see the elder petting the head of a little girl as he says,\n'Time to make daddy happy, heh heh heh!'\nThe girl cries and sobs sorrowfully.")
			} else if (vt.equalsIgnoreCase("baphomet_elder") || vt.equalsIgnoreCase("baphomet_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the elder - Success", "The elder takes a strange apple from the hand of a serpentine woman\nand bites into it, smiling strangely.")
			} else if (vt.equalsIgnoreCase("samael_elder") || vt.equalsIgnoreCase("samael_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the elder - Success", "The elder sniffs white powder, then his eyes turn blank as he starts\nfreaking out about monsters. Fog billows from his mouth.")
			} else if (vt.equalsIgnoreCase("akumasama_elder") || vt.equalsIgnoreCase("akumasama_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the elder - Success", "The elder is writing in a diary with his own blood:\nMINAGOROSHI MINAGOROSHI MINAGOROSHI MINAGOROSHI MINAGOROSHI\nKOROSE KOROSE KOROSE KOROSE KOROSE KOROSE\nMUDA MUDA MUDA MUDA MUDA MUDA MUDA MUDA")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the elder - Success", "Sometimes the elder is a little bit too interested in\nwhen new couples are going to have kids, but he is otherwise\nperfectly normal.")
			}
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the elder - Failure", "You see the elder squatting behind his house,\ntaking a dump in a manure ditch")
		}
	}
	def spyOnElderCheck = { PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("DEX")
		result += pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("athletics")
		result += pc.rollSkill("stealth")
		examineElder(result > difficulty)
		InterfaceState.nextWindow = new VillageElderWindow(vt)
		UnmappedMain.closeCurrent()
	}
	def spyOnElderAction = {
		InterfaceState.nextWindow = new WhoWillWindow("spy on the elder", spyOnElderCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	def bookOfHindsightCheck = { PlayerCharacter pc ->
		int result = pc.rollStat("SIX") + pc.rollStat("MEM")
		result = (result / 2)
		result += pc.rollSkill("supernatural") + pc.rollSkill("fourdimensional")
		examineElder(result > difficulty)
		InterfaceState.nextWindow = new VillageElderWindow(vt)
		UnmappedMain.closeCurrent()
	}
	def bookOfHindsightAction = {
		InterfaceState.nextWindow = new WhoWillWindow("invoke the Book of Hindsight", bookOfHindsightCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
