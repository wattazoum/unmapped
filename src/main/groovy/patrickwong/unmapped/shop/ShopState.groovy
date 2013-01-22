package patrickwong.unmapped.shop

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.town.TavernWindow

import com.googlecode.lanterna.gui.Action

public class ShopState {
	String title = "default shop title"
	String description = "default shop description"
	Action leaveShopAction = {
		InterfaceState.nextWindow = new TavernWindow()
		UnmappedMain.closeCurrent()
	} as Action
	List<ShopItem> items = new Vector<ShopItem>()
}
