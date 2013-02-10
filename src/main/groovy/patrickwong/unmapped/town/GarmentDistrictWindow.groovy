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
		addComponent(new Button("...purple statues of angels holding firearms", saintsWorldAction))
		addComponent(new Button("...a fanciful little place named 'Smiling Death'", smilingDeathAction))
		addComponent(new Button("...the store for utilitarian work clothes", utilitarianAction))
		addComponent(new Button("...the one-stop shop for servile maids and waitresses (not implemented)"))
		addComponent(new Button("...an excessively-bright-colored and cutesy store (not implemented)"))
		addComponent(new Button("...a sporty locale displaying punching bags and weights (not implemented)"))
		addComponent(new Button("...colorful paintings of bearded men with guitars and attitude (not implemented)"))
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...guild street", new GuildStreetAction()));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new GarmentDistrictAction())));
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
	
	// NOTE - SAINTS WORLD SHOP
	private static ShopState saintsWorldShop = new ShopState(
		title: "Garment District - Saints World",
		description: "The walls, ceiling, and floors shake in unison at loud music\nblaring from a massive set of speakers",
		leaveShopAction: new GarmentDistrictAction(),
		items: [
			new ShopItem(name:"Do-Rag", actionName:"buy a do-rag", listedPrice: 20,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 20;
					pc.equipItem(ItemDatabase.getEquippableItem("hat_dorag"), "hat")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a do-rag"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Big Bandana", actionName:"buy a big bandana", listedPrice: 20,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 20;
					pc.equipItem(ItemDatabase.getEquippableItem("hat_bigbandana"), "hat")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a big bandana"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Baseball Cap", actionName:"buy a baseball cap", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("hat_baseball"), "hat")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a baseball cap"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Flag Headband", actionName:"buy a flag headband", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("forehead_flagband"), "forehead")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a flag headband"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Flag Blindfold", actionName:"buy a flag blindfold", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("eyewear_flagblind"), "eyewear")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a flag blindfold"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Colored Cloth Mask", actionName:"buy a colored cloth mask", listedPrice: 12,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 12;
					pc.equipItem(ItemDatabase.getEquippableItem("mouthguard_coloredcloth"), "mouthguard")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a colored cloth mask"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Crucifix Chain", actionName:"buy a crucifix chain", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("necklace_crucifix"), "necklace")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a crucifix chain"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Muscle Shirt", actionName:"buy a muscle shirt", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("shirt_muscle"), "shirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a muscle shirt"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Tanktop", actionName:"buy a tanktop", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("shirt_tanktop"), "shirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a tanktop"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Sports Team Jacket", actionName:"buy a sports team jacket", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("jacket_sportteam"), "jacket")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a sports team jacket"); toSaintsWorld()
				}
			),
			new ShopItem(name:"Puffy Pants", actionName:"buy puffy paints", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("pants_puffy"), "pants")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys puffy pants"); toSaintsWorld()
				}
			)
		]
	)
	private static void toSaintsWorld() {
		InterfaceState.nextWindow = new ShopWindow(saintsWorldShop)
		UnmappedMain.closeCurrent()
	}
	def saintsWorldAction = {
		toSaintsWorld()
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
			new ShopItem(name:"Mini Wings", actionName:"buy mini wings", listedPrice: 96,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 96;
					pc.equipItem(ItemDatabase.getEquippableItem("hat_miniwings"), "hat")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys mini wings"); toSmilingDeath()
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
			),
			new ShopItem(name:"Striped Stockings", actionName:"buy striped stockings", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("socks_stripedstockings"), "socks")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys striped stockings"); toSmilingDeath()
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
	
	// NOTE - UTILITARIAN SHOP
	private static ShopState utilitarianShop = new ShopState(
		title: "Garment District - Utilitarianism",
		description: "This store reeks of iron and copper. The beams in the ceiling are exposed.\nA poster on a wall exhorts that 'We can do it!'",
		leaveShopAction: new GarmentDistrictAction(),
		items: [
			new ShopItem(name:"Respirator", actionName:"buy a respirator", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("mouthguard_respirator"), "mouthguard")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a respirator"); toUtilitarian()
				}
			),
			new ShopItem(name:"Gas Mask", actionName:"buy a gas mask", listedPrice: 480,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 480;
					pc.equipItem(ItemDatabase.getEquippableItem("mouthguard_gasmask"), "mouthguard")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a gas mask"); toUtilitarian()
				}
			),
			new ShopItem(name:"Butcher Apron", actionName:"buy a butcher apron", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("tabard_butcher"), "tabard")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a butcher apron"); toUtilitarian()
				}
			),
			new ShopItem(name:"Plaid Button-Up", actionName:"buy a plaid button-up", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("shirt_plaidbuttonup"), "shirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a plaid button-up"); toUtilitarian()
				}
			),
			new ShopItem(name:"Tactical Button-Up", actionName:"buy a tactical button-up", listedPrice: 48,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 48;
					pc.equipItem(ItemDatabase.getEquippableItem("shirt_tacticalbuttonup"), "shirt")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a tactical button-up"); toUtilitarian()
				}
			),
			new ShopItem(name:"High-Tech Jacket", actionName:"buy a high-tech jacket", listedPrice: 480,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 480;
					pc.equipItem(ItemDatabase.getEquippableItem("jacket_hightech"), "jacket")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys a high-tech jacket"); toUtilitarian()
				}
			),
			new ShopItem(name:"Wool Shorts", actionName:"buy wool shorts", listedPrice: 24,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 24;
					pc.equipItem(ItemDatabase.getEquippableItem("shorts_wool"), "shorts")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys wool shorts"); toUtilitarian()
				}
			),
			new ShopItem(name:"Carpenter Pants", actionName:"buy carpenter pants", listedPrice: 96,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 96;
					pc.equipItem(ItemDatabase.getEquippableItem("pants_carpenter"), "pants")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys carpenter pants"); toUtilitarian()
				}
			),
			new ShopItem(name:"Tactical Pants", actionName:"buy tactical pants", listedPrice: 96,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 96;
					pc.equipItem(ItemDatabase.getEquippableItem("pants_tactical"), "pants")
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys clothes", pc.name + " buys tactical pants"); toUtilitarian()
				}
			)
		]
	)
	private static void toUtilitarian() {
		InterfaceState.nextWindow = new ShopWindow(smilingDeathShop)
		UnmappedMain.closeCurrent()
	}
	def utilitarianAction = {
		toUtilitarian()
	} as Action
	
	// NOTE - SERVILE SHOP
	
	// NOTE - SCHOOLGIRL SHOP
}
