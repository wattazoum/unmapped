package patrickwong.unmapped.town;

import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class CentralPlazaWindow extends Window {
	public CentralPlazaWindow() {
		super("Town - Central Plaza");
		addComponent(new Label("This is the central plaza."));
		addComponent(new Label("You go to..."));
		addComponent(new Button("...the palace"));
		addComponent(new Button("...the meeting hall"));
		addComponent(new Button("...the temple", new TempleAction()));
		addComponent(new Button("...the university"));
		addComponent(new Button("...read the posted notices"));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new CentralPlazaAction())));
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...a quiet park", new QuietParkAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
