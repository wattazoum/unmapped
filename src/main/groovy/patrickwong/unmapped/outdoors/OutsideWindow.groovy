package patrickwong.unmapped.outdoors

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.mainmenu.MainMenuAction
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.outdoors.countryside.CountrysideEncounters
import patrickwong.unmapped.outdoors.raubritter.RaubritterEnterAction
import patrickwong.unmapped.outdoors.village.VillageAction
import patrickwong.unmapped.party.PartyMenuAction
import patrickwong.unmapped.party.camp.CampAction
import patrickwong.unmapped.party.camp.CampState
import patrickwong.unmapped.town.EnterGateAction

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class OutsideWindow extends Window {
	public OutsideWindow() {
		super("Outside")
		GameState.getInstance().currentLocation = "outside"
		addComponent(new Label("The landscape consists of big plains and big forests\nwith occasional castles and villages.\nThe main town stands large in the center."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...approach the town", new EnterGateAction()))
		addComponent(new Button("...explore the countryside", exploreCountrysideAction))
		addComponent(new Button("...visit one of the villages", new VillageAction()))
		addComponent(new Button("...march towards the imposing dark tower of a raubritter", new RaubritterEnterAction()))
		addComponent(new Button("...seek out a quiet lone building (not implemented)"))
		addComponent(new Button("...go within a deep forest (not implemented)"))
		addComponent(new Button("...camp out in the open", new CampAction(outdoorsCampState())))
		addComponent(new Button("...inspect your party", new PartyMenuAction(new OutsideAction())))
		addComponent(new Button("...return to the main menu to save or load the game", new MainMenuAction()))
	}
	
	Action exploreCountrysideAction = {
		InterfaceState.nextWindow = CountrysideEncounters.getEncounter()
		UnmappedMain.closeCurrent()
	} as Action
	
	private CampState outdoorsCampState() {
		CampState state = new CampState(campTitle: "Outside - Camping",
			campDesc: "Small animals pass through, and insects chirp loudly.",
			endCampAction: new OutsideAction(),
			amenities: ["outside"]
		)
		return state
	}
}
