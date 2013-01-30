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
			}, actionInCombat: { PlayerCharacter user, CombatState state ->
				user.removeItem("painkiller")
				if (user.getPain() <= 0) {
					return user.name + " bites on a painkiller for the feeling of it"
				}
				user.removePain(5)
				return user.name + " tries to quickly swallow a painkiller"
			}),
			
			"bandage":new GameItem(key:"bandage", name:"Bandage", desc:"For wrapping around wounds.", baseValue: 1,
			actionInField: { PlayerCharacter user ->
				user.removeItem("bandage")
				if (user.getWounds() <= 0) {
					return user.name + " wears a bandage for decoration"
				}
				user.removeWounds(10)
				return user.name + " applies a bandage"
			}, actionInCombat: { PlayerCharacter user, CombatState state ->
				user.removeItem("bandage")
				if (user.getWounds() <= 0) {
					return user.name + " straps on a bandage for style"
				}
				user.removeWounds(5)
				return user.name + " hastily wraps a bandage"
			}),
			
			// NOTE - POTIONS
			
			"potion_witcher":new GameItem(key:"potion_witcher", name:"Potion of Witcher"),
			"potion_hexer":new GameItem(key:"potion_hexer", name:"Potion of Hexer"),
			"potion_sapkowski":new GameItem(key:"potion_sapkowski", name:"Potion of Sapkowski"),
			"potion_wiedzmin":new GameItem(key:"potion_wiedzmin", name:"Potion of Wiedzmin"),
			
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
