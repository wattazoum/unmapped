package patrickwong.unmapped.town;

import patrickwong.unmapped.party.PartyMenuAction;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class FinancialDistrictWindow extends Window {
	public FinancialDistrictWindow() {
		super("Town - Financial District");
		addComponent(new Label("The people in this district are riduclously-well-dressed."));
		addComponent(new Label("Your destination is..."));
		addComponent(new Button("...the banks"));
		addComponent(new Button("...the money-changers"));
		addComponent(new Button("...the lenders"));
		addComponent(new Button("...the counting houses"));
		addComponent(new Button("...the expensive restaurants"));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new FinancialDistrictAction())));
		addComponent(new Button("...central plaza", new CentralPlazaAction()));
		addComponent(new Button("...guild street", new GuildStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
}
