package patrickwong.unmapped.town;

import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class GuildStreetWindow extends Window {
	public GuildStreetWindow() {
		super("Town - Guild Street");
		addComponent(new Label("On guild street, everyone looks busy."));
		addComponent(new Label("What interests you is..."));
		addComponent(new Button("...the weaponsmith guild"));
		addComponent(new Button("...the armorer guild"));
		addComponent(new Button("...the tailor guild"));
		addComponent(new Button("...the travelling guild"));
		addComponent(new Button("...the professional chef guild"));
		addComponent(new Button("...martial arts schools"));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new GuildStreetAction())));
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...caravan street", new CaravanStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
