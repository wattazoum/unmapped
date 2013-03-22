package patrickwong.unmapped.model.equipment

import patrickwong.unmapped.combat.CombatState;
import patrickwong.unmapped.model.PlayerCharacter

public class ItemDatabase {
	private static ItemDatabase databaseInstance = new ItemDatabase()
	private Map<String, GameItem> database
	private Map<String, Equippable> equippableDatabase
	private Map<String, Grippable> grippableDatabase
	
	private ItemDatabase() {
		database = [
			"painkiller":new GameItem(key:"painkiller", name:"Painkiller", desc:"Pill that should relieve pain.", baseValue: 12,
				actionInField: { PlayerCharacter user ->
					user.removeItem("painkiller")
					if (user.getPain() <= 0) {
						return user.name + " takes a painkiller just for the feeling"
					}
					user.removePain(10)
					return user.name + " swallows a painkiller"
				} as ItemFieldAction,
				actionInCombat: { PlayerCharacter user, CombatState state ->
					user.removeItem("painkiller")
					if (user.getPain() <= 0) {
						return user.name + " bites on a painkiller for the feeling of it"
					}
					user.removePain(5)
					return user.name + " tries to quickly swallow a painkiller"
				} as ItemCombatAction
			),
			
			"bandage":new GameItem(key:"bandage", name:"Bandage", desc:"For wrapping around wounds.", baseValue: 1,
				actionInField: { PlayerCharacter user ->
					user.removeItem("bandage")
					if (user.getWounds() <= 0) {
						return user.name + " wears a bandage for decoration"
					}
					user.removeWounds(3)
					return user.name + " applies a bandage"
				} as ItemFieldAction,
				actionInCombat: { PlayerCharacter user, CombatState state ->
					user.removeItem("bandage")
					if (user.getWounds() <= 0) {
						return user.name + " straps on a bandage for style"
					}
					user.removeWounds(1)
					return user.name + " hastily wraps a bandage"
				} as ItemCombatAction
			),
			
			// NOTE - SPECIAL ITEMS
			"special_nortoncoin":new GameItem(key:"special_nortoncoin", name:"Norton Coin", desc:"These ancient coins were minted by order of Emperor Norton. They can\nbe redeemed for special services in town. Speaking of Emperor Norton,\nnow that was a good cyning."),
			
			// NOTE - POTIONS
			
			"potion_witcher":new GameItem(key:"potion_witcher", name:"Potion of Witcher"),
			"potion_hexer":new GameItem(key:"potion_hexer", name:"Potion of Hexer"),
			"potion_sapkowski":new GameItem(key:"potion_sapkowski", name:"Potion of Sapkowski"),
			"potion_wiedzmin":new GameItem(key:"potion_wiedzmin", name:"Potion of Wiedzmin"),
			"potion_rivia":new GameItem(key:"potion_rivia", name:"Potion of Rivia"),
			"potion_merigold":new GameItem(key:"potion_merigold", name:"Potion of Merigold"),
			
			// NOTE - FINAL ITEM
			"last_item":new GameItem(key:"last_item", name:"The very last item in the DB", desc:"So that I avoid errors relating to an extra comma at the end")
		]
		equippableDatabase = Equippable.armorDatabase
		grippableDatabase = Grippable.weaponDatabase
	}
	
	public static GameItem getItem(String key) {
		return databaseInstance.database.get(key).clone()
	}
	public static Equippable getEquippableItem(String key) {
		return databaseInstance.equippableDatabase.get(key).clone()
	}
	public static Grippable getGrippableItem(String key) {
		return databaseInstance.grippableDatabase.get(key).clone()
	}
}
