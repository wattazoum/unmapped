package patrickwong.unmapped.town;

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.party.PartyMenuAction
import patrickwong.unmapped.party.camp.CampAction
import patrickwong.unmapped.party.camp.CampState
import patrickwong.unmapped.town.slumencounters.SlumencounterLowlivesAction

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.GUIScreen
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class SlumDistrictWindow extends Window {
	public SlumDistrictWindow() {
		super("Town - Slum District");
		addComponent(new Label("Homeless beggars cover the sides of the roads."));
		addComponent(new Label("You go to..."));
		addComponent(new Button("...pick a fight", new SlumencounterLowlivesAction()));
		addComponent(new Button("...get food from a corner store"));
		addComponent(new Button("...talk to a beggar"));
		addComponent(new Button("...find an abandoned building to stay in", new CampAction(abandonedBuildingCampState())));
		addComponent(new Button("...play drums with improvised objects"));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new SlumDistrictAction())));
		addComponent(new Button("...a quiet park", new QuietParkAction()));
		addComponent(new Button("...common market streets", new MarketStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
	
	private CampState abandonedBuildingCampState() {
		CampState state = new CampState(campTitle: "Abandoned Building",
			campDesc: "This building is littered with detritus and debris from years of neglect.\nStray animals make their nest here.",
			endCampAction: new SlumDistrictAction(),
			amenities: ["town"]
		)
		return state
	}
}
