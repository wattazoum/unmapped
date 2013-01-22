package patrickwong.unmapped.shop

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.Panel;

public class ShopWindow extends Window {
	private ShopState state
	private List<ShopItem> items
	public ShopWindow(ShopState shopState) {
		super(shopState.title)
		state = shopState
		items = state.items
		addComponent(new Label(state.description))
		addComponent(new Label("The party currently has " + UnmappedMain.partyMoneyString()))
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
		for (ShopItem item : items) {
			leftColumn.addComponent(new Button(item.name, genShopItemAction(item)))
			rightColumn.addComponent(new Label(UnmappedMain.moneyAsString(item.listedPrice)))
		}
		mainPanel.addComponent(leftColumn);
		mainPanel.addComponent(rightColumn);
		addComponent(mainPanel);
		addComponent(new Button("Leave", state.leaveShopAction))
	}
	
	private Action genShopItemAction(ShopItem item) {
		def theAction = {
			InterfaceState.nextWindow = new WhoWillWindow(item.actionName, item.check)
			UnmappedMain.closeCurrent()
		} as Action
		return theAction
	}
}
