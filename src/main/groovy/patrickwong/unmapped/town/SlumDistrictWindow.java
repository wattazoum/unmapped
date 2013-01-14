package patrickwong.unmapped.town;

import patrickwong.unmapped.combat.CombatState;
import patrickwong.unmapped.combat.CombatWindow;
import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class SlumDistrictWindow extends Window {
	public SlumDistrictWindow() {
		super("Town - Slum District");
		addComponent(new Label("Homeless beggars cover the sides of the roads."));
		addComponent(new Label("You go to..."));
		addComponent(new Button("...pick a fight", new Action() {
			@Override
			public void doAction() {
				getOwner().showWindow(new CombatWindow(new CombatState()), GUIScreen.Position.CENTER);
			}
		}));
		addComponent(new Button("...get food from a corner store"));
		addComponent(new Button("...talk to a beggar"));
		addComponent(new Button("...find an abandoned building"));
		addComponent(new Button("...play drums with improvised objects"));
		addComponent(new Button("...inspect your party", new PartyMenuAction()));
		addComponent(new Button("...a quiet park", new QuietParkAction()));
		addComponent(new Button("...common market streets", new MarketStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
