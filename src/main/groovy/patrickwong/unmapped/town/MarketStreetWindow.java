package patrickwong.unmapped.town;

import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class MarketStreetWindow extends Window {
	public MarketStreetWindow() {
		super("Town - Market Streets");
		addComponent(new Label("These streets are full of commoners, merchants, and stalls full of things."));
		addComponent(new Label("You head over to..."));
		addComponent(new Button("...street food being openly cooked"));
		addComponent(new Button("...the farmer's market"));
		addComponent(new Button("...peddlers of trinkets"));
		addComponent(new Button("...common clothiers"));
		addComponent(new Button("...inspect your party", new PartyMenuAction()));
		addComponent(new Button("...the slums", new SlumDistrictAction()));
		addComponent(new Button("...caravan street", new CaravanStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
