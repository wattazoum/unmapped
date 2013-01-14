package patrickwong.unmapped.equipment

import patrickwong.unmapped.combat.CombatState;
import patrickwong.unmapped.model.PlayerCharacter

public class ItemDatabase {
	private static Map<String, GameItem> database = [
		"default_item":new GameItem(key:"default_item", name:"Default Item", desc:"This is a very basic type of item."),
		
		"painkiller":new GameItem(key:"painkiller", name:"Painkiller", desc:"Pill that should relieve pain.",
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
		
		"bandage":new GameItem(key:"bandage", name:"Bandage", desc:"For wrapping around wounds.",
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
		})
	]
	
	private ItemDatabase() {}
	
	public static GameItem getItem(String key) {
		return database.get(key).clone()
	}
}
