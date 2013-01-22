package patrickwong.unmapped.town;

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

public class GuildStreetWindow extends Window {
	public GuildStreetWindow() {
		super("Town - Guild Street");
		addComponent(new Label("On guild street, everyone looks busy."));
		addComponent(new Label("What interests you is..."));
		addComponent(new Button("...the weaponsmith guild", weaponShopAction));
		addComponent(new Button("...the shieldsmith guild", shieldShopAction));
		addComponent(new Button("...the armorer guild", armorShopAction));
		addComponent(new Button("...the tailor guild (not implemented)"));
		addComponent(new Button("...the physician guild", physicianShopAction));
		addComponent(new Button("...the professional chef guild (not implemented)"));
		addComponent(new Button("...martial arts schools", martialArtsSchoolAction));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new GuildStreetAction())));
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...caravan street", new CaravanStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
	// NOTE - WEAPON SHOP
	private static ShopState weaponShop = new ShopState(
		title: "Guild Street - Weapons",
		description: "Most of the weapons were commissioned by the nobles or by mercenary companies,\nbut there are still plenty available",
		leaveShopAction: new GuildStreetAction(),
		items: [
			new ShopItem(name:"Knife", actionName:"buy a knife", listedPrice: 12,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 12;
					pc.addItem(ItemDatabase.getGrippableItem("shortweapon_knife"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a knife")
					toArmorShop()
				}
			),
			new ShopItem(name:"Spear", actionName:"buy a spear", listedPrice: 48,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 48;
					pc.addItem(ItemDatabase.getGrippableItem("spear_spear"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a spear")
					toArmorShop()
				}
			),
			new ShopItem(name:"Warhammer", actionName:"buy a warhammer", listedPrice: 96,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 96;
					pc.addItem(ItemDatabase.getGrippableItem("blunt_warhammer"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a warhammer")
					toArmorShop()
				}
			),
			new ShopItem(name:"Gladius", actionName:"buy a gladius", listedPrice: 480,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 480;
					pc.addItem(ItemDatabase.getGrippableItem("swordstraightone_gladius"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a gladius")
					toArmorShop()
				}
			),
			new ShopItem(name:"Longsword (2H)", actionName:"buy a longsword", listedPrice: 960,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 960;
					pc.addItem(ItemDatabase.getGrippableItem("swordstraighttwo_longsword"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a longsword")
					toArmorShop()
				}
			)
		]
	)
	private static void toWeaponShop() {
		InterfaceState.nextWindow = new ShopWindow(weaponShop)
		UnmappedMain.closeCurrent()
	}
	def weaponShopAction = {
		toWeaponShop()
	} as Action
	
	// NOTE - SHIELD SHOP
	private static ShopState shieldShop = new ShopState(
		title: "Guild Street - Shields",
		description: "Custom painting is free",
		leaveShopAction: new GuildStreetAction(),
		items: [
			new ShopItem(name:"Buckler", actionName:"buy a buckler", listedPrice: 120,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 120;
					pc.addItem(ItemDatabase.getGrippableItem("shield_buckler"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a shield", pc.name + " buys a buckler")
					toShieldShop()
				}
			),
			new ShopItem(name:"Heater Shield", actionName:"buy a heater shield", listedPrice: 240,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 240;
					pc.addItem(ItemDatabase.getGrippableItem("shield_heater"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a shield", pc.name + " buys a heater shield")
					toShieldShop()
				}
			),
			new ShopItem(name:"Scutum Shield", actionName:"buy a scutum shield", listedPrice: 960,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 960;
					pc.addItem(ItemDatabase.getGrippableItem("shield_scutum"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a shield", pc.name + " buys a scutum shield")
					toShieldShop()
				}
			)
		]
	)
	private static void toShieldShop() {
		InterfaceState.nextWindow = new ShopWindow(shieldShop)
		UnmappedMain.closeCurrent()
	}
	def shieldShopAction = {
		toShieldShop()
	} as Action
	
	// NOTE - ARMOR SHOP
	private static ShopState armorShop = new ShopState(
		title: "Guild Street - Armor",
		description: "Armor is sold here in whole suits only, for simplified stock-keeping",
		leaveShopAction: new GuildStreetAction(),
		items: [
			new ShopItem(name:"Leather Armor", actionName:"buy leather armor", listedPrice: 120,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 120;
					pc.equipItem(ItemDatabase.getEquippableItem("helmet_leather"), "helmet");
					pc.equipItem(ItemDatabase.getEquippableItem("bodyarmor_leather"), "body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("overbodyarmor_leather"), "over body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_leather"), "left arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_leather"), "right arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("legarmor_leather"), "leg armor");
					pc.equipItem(ItemDatabase.getEquippableItem("gloves_leather"), "gloves");
					pc.equipItem(ItemDatabase.getEquippableItem("boots_leather"), "boots");
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys armor", pc.name + " buys a suit of leather armor");
					toArmorShop()
				}
			),
			new ShopItem(name:"Mail Armor", actionName:"buy mail armor", listedPrice: 240,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 240;
					pc.equipItem(ItemDatabase.getEquippableItem("helmet_mail"), "helmet");
					pc.equipItem(ItemDatabase.getEquippableItem("bodyarmor_mail"), "body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("overbodyarmor_mail"), "over body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_mail"), "left arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_mail"), "right arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("legarmor_mail"), "leg armor");
					pc.equipItem(ItemDatabase.getEquippableItem("gloves_mail"), "gloves");
					pc.equipItem(ItemDatabase.getEquippableItem("boots_mail"), "boots");
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys armor", pc.name + " buys a suit of mail armor");
					toArmorShop()
				}
			),
			new ShopItem(name:"Brigandine Armor", actionName:"buy brigandine armor", listedPrice: 480,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 480;
					pc.equipItem(ItemDatabase.getEquippableItem("helmet_brigandine"), "helmet");
					pc.equipItem(ItemDatabase.getEquippableItem("bodyarmor_brigandine"), "body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("overbodyarmor_brigandine"), "over body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_brigandine"), "left arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_brigandine"), "right arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("legarmor_brigandine"), "leg armor");
					pc.equipItem(ItemDatabase.getEquippableItem("gloves_brigandine"), "gloves");
					pc.equipItem(ItemDatabase.getEquippableItem("boots_brigandine"), "boots");
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys armor", pc.name + " buys a suit of brigandine armor");
					toArmorShop()
				}
			),
			new ShopItem(name:"Plate Armor", actionName:"buy brigandine armor", listedPrice: 2400,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 2400;
					pc.equipItem(ItemDatabase.getEquippableItem("helmet_plate"), "helmet");
					pc.equipItem(ItemDatabase.getEquippableItem("bodyarmor_plate"), "body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("overbodyarmor_plate"), "over body armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_plate"), "left arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("armarmor_plate"), "right arm armor");
					pc.equipItem(ItemDatabase.getEquippableItem("legarmor_plate"), "leg armor");
					pc.equipItem(ItemDatabase.getEquippableItem("gloves_plate"), "gloves");
					pc.equipItem(ItemDatabase.getEquippableItem("boots_plate"), "boots");
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys armor", pc.name + " buys a suit of plate armor");
					toArmorShop()
				}
			)
		]
	)
	private static void toArmorShop() {
		InterfaceState.nextWindow = new ShopWindow(armorShop)
		UnmappedMain.closeCurrent()
	}
	def armorShopAction = {
		toArmorShop()
	} as Action
	
	// NOTE - TAILOR GUILD
	
	// NOTE - PHYSICIAN GUILD
	private static ShopState physicianShop = new ShopState(
		title: "Guild Street - Physician Guild",
		description: "The physicians here hope that you know what you are doing",
		leaveShopAction: new GuildStreetAction(),
		items: [
			new ShopItem(name:"Bandage x 10", actionName:"buy a set of bandages", listedPrice: 12,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 12;
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					pc.addItem(ItemDatabase.getItem("bandage"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys medical supplies", pc.name + " buys a set of bandages")
					toPhysicianShop()
				}
			),
			new ShopItem(name:"Painkiller x 10", actionName:"buy a set of painkillers", listedPrice: 240,
				check: { PlayerCharacter pc ->
					GameState.getInstance().partyMoney -= 240;
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					pc.addItem(ItemDatabase.getItem("painkiller"))
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys medical supplies", pc.name + " buys a set of painkillers")
					toPhysicianShop()
				}
			)
		]
	)
	private static void toPhysicianShop() {
		InterfaceState.nextWindow = new ShopWindow(physicianShop)
		UnmappedMain.closeCurrent()
	}
	def physicianShopAction = {
		toPhysicianShop()
	} as Action
	
	// NOTE - PROFESSIONAL CHEF GUILD
	
	// NOTE - MARTIAL ARTS SCHOOL
	private static ShopState martialArtsSchool = new ShopState(
		title: "Guild Street - Martial Arts School",
		description: "It smells strongly of sweat and iron, and people are hitting pads\nhard enough to be heard from far away.",
		leaveShopAction: new GuildStreetAction(),
		items: [
			new ShopItem(name:"Punching Bag", actionName:"hit a punching bag for a while", listedPrice: 12,
				check: { PlayerCharacter pc ->
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " does martial arts training", pc.name + " hits a punching bag for a while")
					pc.statAddExp("STR", 20)
					pc.statAddExp("DEX", 5)
					pc.statAddExp("TGH", 5)
					pc.statAddExp("END", 5)
					toMartialArtsSchool()
				}
			),
			new ShopItem(name:"Jumprope", actionName:"go jumproping", listedPrice: 12,
				check: { PlayerCharacter pc ->
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " does martial arts training", pc.name + " goes jumproping for a while")
					pc.statAddExp("END", 20)
					pc.statAddExp("DEX", 5)
					pc.statAddExp("AGI", 5)
					pc.statAddExp("HLT", 5)
					toMartialArtsSchool()
				}
			),
			new ShopItem(name:"Hit and Dodge Pads", actionName:"hit and dodge pads", listedPrice: 24,
				check: { PlayerCharacter pc ->
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " does martial arts training", pc.name + " does routines with hitting and dodging pads")
					pc.statAddExp("DEX", 10)
					pc.statAddExp("AGI", 5)
					pc.skillAddExp("unarmed", 10)
					pc.skillAddExp("melee_evasion", 10)
					toMartialArtsSchool()
				}
			),
			new ShopItem(name:"Sparring", actionName:"go sparring", listedPrice: 48,
				check: { PlayerCharacter pc ->
					MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " does martial arts training", pc.name + " spars with the other people at the school")
					pc.statAddExp("TGH", 5)
					pc.statAddExp("END", 5)
					pc.skillAddExp("fighting", 10)
					pc.skillAddExp("melee_fighting", 10)
					pc.skillAddExp("unarmed", 10)
					pc.skillAddExp("melee_defense", 10)
					pc.skillAddExp("melee_evasion", 10)
					pc.skillAddExp("weaponcatching", 10)
					toMartialArtsSchool()
				}
			)
		]
	)
	private static void toMartialArtsSchool() {
		InterfaceState.nextWindow = new ShopWindow(martialArtsSchool)
		UnmappedMain.closeCurrent()
	}
	def martialArtsSchoolAction = {
		toMartialArtsSchool()
	} as Action
}
