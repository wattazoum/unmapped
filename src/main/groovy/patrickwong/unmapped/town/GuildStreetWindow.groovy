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
		addComponent(new Button("...the weaponsmith guild"));
		addComponent(new Button("...the shieldsmith guild"));
		addComponent(new Button("...the armorer guild", armorShopAction));
		addComponent(new Button("...the tailor guild"));
		addComponent(new Button("...the physician guild"));
		addComponent(new Button("...the professional chef guild"));
		addComponent(new Button("...martial arts schools"));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new GuildStreetAction())));
		addComponent(new Button("...the financial district", new FinancialDistrictAction()));
		addComponent(new Button("...caravan street", new CaravanStreetAction()));
		addComponent(new Button("...main street", new MainStreetAction()));
	}
	// NOTE - WEAPON SHOP
	
	// NOTE - SHIELD SHOP
	
	// NOTE - ARMOR SHOP
	private static ShopState armorShop = new ShopState(
		title: "Guild Street - Weapons",
		description: "Most of the weapons were commissioned by the nobles or by mercenary companies,\nbut there are still plenty available",
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
	
	// NOTE - PROFESSIONAL CHEF GUILD
	
	// NOTE - MARTIAL ARTS SCHOOL
}
