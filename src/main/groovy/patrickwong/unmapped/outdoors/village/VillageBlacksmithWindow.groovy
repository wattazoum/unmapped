package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.ItemDatabase
import patrickwong.unmapped.party.WhoWillWindow
import patrickwong.unmapped.shop.ShopItem
import patrickwong.unmapped.shop.ShopState
import patrickwong.unmapped.shop.ShopWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class VillageBlacksmithWindow extends Window {
	private static final int difficulty = 10
	private String vt
	private ShopState blacksmithShop
	public VillageBlacksmithWindow(String villageType) {
		super("Village - Blacksmith")
		vt = villageType
		blacksmithShop = genBlacksmithShop(vt)
		addComponent(new Label("The blacksmith must do all kinds of odd jobs around the village."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...buy something from him", blacksmithShopAction))
		addComponent(new Button("...sneak into his private quarters", spyOnBlacksmithAction))
		addComponent(new Button("...invoke the power of X to gain insight into him"))
		addComponent(new Button("...return to the village", new VillageAction(vt)))
	}
	private static ShopState genBlacksmithShop(String vt) {
		ShopState theShop = new ShopState(
			title: "Village - Blacksmith",
			description: "The blacksmith has a humble selection of spare items to sell",
			leaveShopAction: new VillageBlacksmithAction(vt),
			items: [
				new ShopItem(name:"Knife", actionName:"buy a knife", listedPrice: 12,
					check: { PlayerCharacter pc ->
						GameState.getInstance().partyMoney -= 12;
						pc.addItem(ItemDatabase.getGrippableItem("shortweapon_knife"))
						MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a knife")
						toBlacksmithShop()
					}
				),
				new ShopItem(name:"Spear", actionName:"buy a spear", listedPrice: 48,
					check: { PlayerCharacter pc ->
						GameState.getInstance().partyMoney -= 48;
						pc.addItem(ItemDatabase.getGrippableItem("spear_spear"))
						MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " buys a weapon", pc.name + " buys a spear")
						toBlacksmithShop()
					}
				),
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
						toBlacksmithShop()
					}
				)
			]
		)
		return theShop
	}
	private void toBlacksmithShop() {
		InterfaceState.nextWindow = new ShopWindow(blacksmithShop)
		UnmappedMain.closeCurrent()
	}
	def blacksmithShopAction = {
		toBlacksmithShop()
	} as Action
	
	private void examineBlacksmith(boolean succeeded) {
		if (succeeded) {
			if (vt.equalsIgnoreCase("lillith_blacksmith") || vt.equalsIgnoreCase("lillith_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the blacksmith - Success", "One of his secret tools is a strange rod with a bump on the end,\ncovered in barbed wire. It is bloodstained.")
			} else if (vt.equalsIgnoreCase("baphomet_blacksmith") || vt.equalsIgnoreCase("baphomet_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the blacksmith - Success", "He tests out an iron horn shaped like a ram's horn. The sound it makes\nis a dark guttural drone that disturbs the soul.")
			} else if (vt.equalsIgnoreCase("samael_blacksmith") || vt.equalsIgnoreCase("samael_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the blacksmith - Success", "Hanging on the wall are two strange items. The first is a great helmet\nshaped like a distorted pyramid, while the second is a\ngigantic butcher's cleaver too large for any mortal to wield.")
			} else if (vt.equalsIgnoreCase("akumasama_blacksmith") || vt.equalsIgnoreCase("akumasama_all")) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the blacksmith - Success", "His private quarters look more like an empty butcher's room, because of\nall the bloodstained meathooks hanging from the ceiling.\nHuman skeletons are piled in one corner.")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the blacksmith - Success", "The blacksmith drinks heavily, but within mortal boundaries.\nHe is quite average for a blacksmith, really.")
			}
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Examining the blacksmith - Failure", "You see the blacksmith pounding away repetitively\non a hunk of heated metal.")
		}
	}
	def spyOnBlacksmithCheck = { PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("DEX")
		result += pc.rollStat("WIT")
		result = (result / 2)
		result += pc.rollSkill("athletics")
		result += pc.rollSkill("stealth")
		examineBlacksmith(result > difficulty)
		InterfaceState.nextWindow = new VillageBlacksmithWindow(vt)
		UnmappedMain.closeCurrent()
	}
	def spyOnBlacksmithAction = {
		InterfaceState.nextWindow = new WhoWillWindow("spy on the blacksmith", spyOnBlacksmithCheck)
		UnmappedMain.closeCurrent()
	} as Action
}
