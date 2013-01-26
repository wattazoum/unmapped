package patrickwong.unmapped.town

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.outdoors.OutsideAction

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class EnterGateWindow extends Window {
	private int toll
	public EnterGateWindow() {
		super("Town - Entrance")
		addComponent(new Label("Before you lies the main town."))
		int partySize = GameState.getInstance().party.size()
		toll = 10 * partySize
		addComponent(new Label("With a party of $partySize the toll is " + UnmappedMain.moneyAsString(toll)))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...pay the toll and enter", payTollAction))
		addComponent(new Button("...talk to the guards about getting in for free"))
		addComponent(new Button("...blend in with four hooded meditating scholars"))
		addComponent(new Button("...throw a Potion of Sapkowski and sneak in during the panic"))
		addComponent(new Button("...invoke the power of X to make the guards let you pass"))
		addComponent(new Button("...draw weapons and attack the guards!"))
		addComponent(new Button("...return to the outside world", new OutsideAction()))
	}
	
	def payTollAction = {
		GameState.getInstance().partyMoney -= toll
		InterfaceState.nextWindow = new MainStreetWindow()
		UnmappedMain.closeCurrent()
	} as Action
}
