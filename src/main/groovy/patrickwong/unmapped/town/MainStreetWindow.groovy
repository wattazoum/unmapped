package patrickwong.unmapped.town;

import patrickwong.unmapped.MusicManager;
import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class MainStreetWindow extends Window {
	public MainStreetWindow() {
		super("Town - Main Street");
		MusicManager.play("beginning_town");
		addComponent(new Label("This is the main street, busy and full of people bumping into each other."));
		addComponent(new Label("From here you head off to..."));
		addComponent(new Button("...the central plaza", new CentralPlazaAction()));
		addComponent(new Button("...the market streets where daily essentials are", new MarketStreetAction()));
		addComponent(new Button("...guild halls and other buildings of business", new GuildStreetAction()));
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...slums and tenements forsaken by snobby folk", new SlumDistrictAction()));
		addComponent(new Button("...a quiet park in the town", new QuietParkAction()));
		addComponent(new Button("...caravans and wagon trains passing through", new CaravanStreetAction()));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new MainStreetAction())));
		addComponent(new Button("...the tavern, which is also a well-reputed inn", new TavernAction()));
		addComponent(new Button("...the front gate leading into the outside world"));
	}
}
