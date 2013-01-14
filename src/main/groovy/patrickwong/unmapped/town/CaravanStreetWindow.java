package patrickwong.unmapped.town;

import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class CaravanStreetWindow extends Window {
	public CaravanStreetWindow() {
		super("Town - Caravan Street");
		addComponent(new Label("Armed guards watch over herds of horses, wagons, and carts."));
		addComponent(new Label("You go to..."));
		addComponent(new Button("...trade with one of the caravans"));
		addComponent(new Button("...ask to travel with a caravan"));
		addComponent(new Button("...offer your services as a guard"));
		addComponent(new Button("...try exotic food from distant lands"));
		addComponent(new Button("...inspect your party", new PartyMenuAction()));
		addComponent(new Button("...guild street", new GuildStreetAction()));
		addComponent(new Button("...common market streets", new MarketStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
