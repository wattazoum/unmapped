package patrickwong.unmapped.town;

import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class QuietParkWindow extends Window {
	public QuietParkWindow() {
		super("Town - Quiet Park");
		addComponent(new Label("There are few sounds here, except for a calming fountain."));
		addComponent(new Label("Here you can go to..."));
		addComponent(new Button("...pass the time"));
		addComponent(new Button("...talk to a homeless person"));
		addComponent(new Button("...run around the park"));
		addComponent(new Button("...read a book"));
		addComponent(new Button("...throw a coin in the fountain"));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new QuietParkAction())));
		addComponent(new Button("...the central plaza", new CentralPlazaAction()));
		addComponent(new Button("...the slums", new SlumDistrictAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
