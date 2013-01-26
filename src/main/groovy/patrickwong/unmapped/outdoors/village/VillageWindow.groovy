package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.outdoors.OutsideAction
import patrickwong.unmapped.party.PartyMenuAction

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class VillageWindow extends Window {
	private String vt
	
	public VillageWindow(String villageType) {
		super("Village")
		vt = villageType
		addComponent(new Label("This farming village sends its surplus grain to the town\nin exchange for protection and special goods."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...visit the village elder", villageElderAction))
		addComponent(new Button("...get something from the village blacksmith", new VillageBlacksmithAction(vt)))
		addComponent(new Button("...converse with the village priest", villagePriestAction))
		addComponent(new Button("...go to the meeting hall to make an announcement", new VillageMeetingAction(vt)))
		addComponent(new Button("...inspect your party", new PartyMenuAction(new VillageAction(vt))))
		addComponent(new Button("...leave the village", new OutsideAction()))
	}
	
	def villageElderAction = {
		InterfaceState.nextWindow = new VillageElderWindow(vt)
		UnmappedMain.closeCurrent()
	} as Action
	
	def villagePriestAction = {
		InterfaceState.nextWindow = new VillagePriestWindow(vt)
		UnmappedMain.closeCurrent()
	} as Action
}
