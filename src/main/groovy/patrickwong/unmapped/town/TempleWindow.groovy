package patrickwong.unmapped.town

import patrickwong.unmapped.MusicManager
import patrickwong.unmapped.party.PartyMenuAction
import patrickwong.unmapped.party.camp.CampAction
import patrickwong.unmapped.party.camp.CampState

import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class TempleWindow extends Window {
	public TempleWindow() {
		super("Temple")
		MusicManager.play("beginning_temple")
		addComponent(new Label("The temple is a quiet place where many dedicate their lives to mysteries."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...take sanctuary here", new CampAction(templeSanctuaryCampState())))
		addComponent(new Button("...inspect your party", new PartyMenuAction(new TempleAction())))
		addComponent(new Button("...exit to the central plaza", new CentralPlazaAction()))
	}
	
	private CampState templeSanctuaryCampState() {
		CampState state = new CampState(campTitle: "Temple Sanctuary",
			campDesc: "The quarters are minimal, with simple straw beds. The food is humble porridge.\nHomeless families and diseased beggars share the space with you.",
			endCampAction: new TempleAction(),
			amenities: ["town", "temple", "quiet"]
		)
		return state
	}
}
