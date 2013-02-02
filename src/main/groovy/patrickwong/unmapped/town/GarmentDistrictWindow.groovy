package patrickwong.unmapped.town

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.ItemDatabase
import patrickwong.unmapped.party.PartyMenuAction
import patrickwong.unmapped.shop.ShopItem
import patrickwong.unmapped.shop.ShopState
import patrickwong.unmapped.shop.ShopWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class GarmentDistrictWindow extends Window {
	public GarmentDistrictWindow() {
		super("Town - Garment District")
		addComponent(new Label("This is one of the busiest districts in town, rivalling the central plaza!\nThe quest to be well-dressed seems to never end."))
		addComponent(new Label("The first thing you need to try out is..."))
		addComponent(new Button("...the crowded but affordable clothing store", cheapShopAction))
		addComponent(new Button("...a fanciful little place named 'Smiling Death'", smilingDeathAction))
		addComponent(new Button("...the one-stop shop for servile maids and waitresses (not implemented)"))
		addComponent(new Button("...a sporty locale displaying punching bags and weights (not implemented)"))
		addComponent(new Button("...colorful paintings of bearded men with attitude (not implemented)"))
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...guild street", new GuildStreetAction()));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new GuildStreetAction())));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
	
	// NOTE - CHEAP SHOP
	private static ShopState cheapShop = new ShopState(
		title: "Garment District - Cheap Clothes",
		description: "This place is heavily-guarded by watchful eyes,\nto make sure nobody steals anything",
		leaveShopAction: new GarmentDistrictAction(),
		items: [
			new ShopItem(name:"Shawl", actionName:"buy a shawl", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("cloak_shawl"), "cloak")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a shawl"); toCheapShop()
				}
			),
			new ShopItem(name:"Farmer Hat", actionName:"buy a farmer hat", listedPrice: 20,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 20;
					pc.equipItem(ItemDatabase.getEquippableItem("hat_farmer"), "hat")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a farmer hat"); toCheapShop()
				}
			),
			new ShopItem(name:"Bandana", actionName:"buy a bandana", listedPrice: 12,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 12;
					pc.equipItem(ItemDatabase.getEquippableItem("forehead_bandana"), "forehead")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a bandana"); toCheapShop()
				}
			),
			new ShopItem(name:"Eyepatch", actionName:"buy an eyepatch", listedPrice: 10,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 10;
					pc.equipItem(ItemDatabase.getEquippableItem("eyewear_eyepatch"), "eyewear")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys an eyepatch"); toCheapShop()
				}
			),
			new ShopItem(name:"Pendant", actionName:"buy a pendant", listedPrice: 10,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 10;
					pc.equipItem(ItemDatabase.getEquippableItem("necklace_pendant"), "necklace")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a pendant"); toCheapShop()
				}
			),
			new ShopItem(name:"Wool Scarf", actionName:"buy a wool scarf", listedPrice: 10,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 10;
					pc.equipItem(ItemDatabase.getEquippableItem("scarf_wool"), "scarf")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a wool scarf"); toCheapShop()
				}
			),
			new ShopItem(name:"Tanktop", actionName:"buy a tanktop", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("shirt_tanktop"), "shirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a tanktop"); toCheapShop()
				}
			),
			new ShopItem(name:"Waist Bandages", actionName:"buy waist bandages", listedPrice: 10,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 10;
					pc.equipItem(ItemDatabase.getEquippableItem("undershirt_waistbandages"), "undershirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys waist bandages"); toCheapShop()
				}
			),
			new ShopItem(name:"Sarashi", actionName:"buy a sarashi", listedPrice: 10,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 10;
					pc.equipItem(ItemDatabase.getEquippableItem("undershirt_sarashi"), "undershirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a sarashi"); toCheapShop()
				}
			),
			new ShopItem(name:"Peasant Dress", actionName:"buy a peasant dress", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("dress_peasant"), "dress")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a peasant dress"); toCheapShop()
				}
			),
			new ShopItem(name:"Leather Belt", actionName:"buy a leather belt", listedPrice: 12,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 12;
					pc.equipItem(ItemDatabase.getEquippableItem("belt_leather"), "belt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a leather belt"); toCheapShop()
				}
			),
			new ShopItem(name:"Cotton Pantsu", actionName:"buy cotton pantsu", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("shorts_cottonpantsu"), "shorts")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys cotton pantsu"); toCheapShop()
				}
			),
			new ShopItem(name:"Elastic Tight Shorts", actionName:"buy elastic tight shorts", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("shorts_elastictight"), "shorts")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys elastic tight shorts"); toCheapShop()
				}
			),
			new ShopItem(name:"Peasant Skirt", actionName:"buy elastic tight shorts", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("skirt_peasant"), "skirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a peasant skirt"); toCheapShop()
				}
			),
			new ShopItem(name:"Wool Socks", actionName:"buy wool socks", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("socks_wool"), "socks")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys wool socks"); toCheapShop()
				}
			)
		]
	)
	private static void toCheapShop() {
		InterfaceState.nextWindow = new ShopWindow(cheapShop)
		UnmappedMain.closeCurrent()
	}
	def cheapShopAction = {
		toCheapShop()
	} as Action
	
	// NOTE - SMILING DEATH SHOP
	private static ShopState smilingDeathShop = new ShopState(
		title: "Garment District - Smiling Death",
		description: "It is permanently the day of the dead in here,\nwith competing holidays being given a deathly twist",
		leaveShopAction: new GarmentDistrictAction(),
		items: [
			new ShopItem(name:"Decorative Wings", actionName:"buy decorative wings", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("cloak_decwings"), "cloak")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys decorative wings"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Animal Ears", actionName:"buy animal ears", listedPrice: 96,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 96;
					pc.equipItem(ItemDatabase.getEquippableItem("hat_animalears"), "hat")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys animal ears"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Lace Choker", actionName:"buy a lace choker", listedPrice: 96,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 96;
					pc.equipItem(ItemDatabase.getEquippableItem("necklace_lacechoker"), "necklace")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a lace choker"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Corset", actionName:"buy a corset", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("undershirt_corset"), "undershirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a corset"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Lacy Undershirt", actionName:"buy a lacy undershirt", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("undershirt_lacyundershirt"), "undershirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a lacy undershirt"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Lacy Button-Up Shirt", actionName:"buy a lacy button-up shirt", listedPrice: 96,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 96;
					pc.equipItem(ItemDatabase.getEquippableItem("shirt_lacybutton"), "shirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a lacy button-up shirt"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Gothloli Dress", actionName:"buy a gothloli dress", listedPrice: 5520,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 5520;
					pc.equipItem(ItemDatabase.getEquippableItem("dress_gothloli"), "dress")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a gothloli dress"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Lacy Pantsu", actionName:"buy lacy pantsu", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("shorts_lacypantsu"), "shorts")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys lacy pantsu"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Tight Nylons", actionName:"buy tight nylons", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("socks_tightnylon"), "socks")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys tight nylons"); toSmilingDeath()
				}
			),
			new ShopItem(name:"Lacy Stockings", actionName:"buy lacy stockings", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("socks_lacystockings"), "socks")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys lacy stockings"); toSmilingDeath()
				}
			)
		]
	)
	private static void toSmilingDeath() {
		InterfaceState.nextWindow = new ShopWindow(smilingDeathShop)
		UnmappedMain.closeCurrent()
	}
	def smilingDeathAction = {
		toSmilingDeath()
	} as Action
}
