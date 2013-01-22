package patrickwong.unmapped.shop

import patrickwong.unmapped.model.equipment.GameItem

public class ShopItem {
	String name = "default shop item"
	String actionName = "buy the thing"
	int listedPrice = 10
	GameItem defaultProduct
	Closure check
}
