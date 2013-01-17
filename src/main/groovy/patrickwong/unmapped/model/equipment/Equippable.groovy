package patrickwong.unmapped.model.equipment

import patrickwong.unmapped.model.PlayerCharacter

public class Equippable extends GameItem {
	String slotType
	Map<String, Integer> bonuses = null
	
	@Override
	public Equippable clone() {
		return new Equippable(key: this.key, name: this.name, desc: this.desc, actionInField: this.actionInField, actionInCombat: this.actionInCombat, stackSize: 1, stackable: this.stackable,
			slotType: this.slotType,
			bonuses: this.bonuses
		)
	}
	
	public static final List<String> defaultBonuses = [
		"resist_cutting",
		"resist_piercing",
		"resist_impact",
		"resist_bullet",
		"resist_elemental",
		
		"cause_cutting",
		"cause_piercing",
		"cause_impact",
		"cause_bullet",
		"cause_elemental",
		
		"accuracy_melee",
		"accuracy_ranged"
	]
	
	public static final Map<String, Equippable> armorDatabase = [
		// NOTE - LEATHER ARMOR
		"helmet_leather":new Equippable(key:"helmet_leather", name:"Leather Coif", desc:"Protects the head against minor cuts and scrapes",
			slotType:"helmet",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"bodyarmor_leather":new Equippable(key:"bodyarmor_leather", name:"Leather Armor", desc:"Covers the body from lesser cutting attacks",
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"overbodyarmor_leather":new Equippable(key:"overbodyarmor_leather", name:"Leather Jack", desc:"Covers the body from lesser cutting attacks",
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"armarmor_leather":new Equippable(key:"armarmor_leather", name:"Leather Bracer", desc:"Blocks slashes to the arm",
			slotType:"armarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"legarmor_leather":new Equippable(key:"legarmor_leather", name:"Leather Greaves", desc:"Protects the legs from stray slashes and arrows",
			slotType:"legarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"gloves_leather":new Equippable(key:"gloves_leather", name:"Leather Gloves", desc:"Good for gardening and for accidental brushes against swords",
			slotType:"gloves",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"boots_leather":new Equippable(key:"boots_leather", name:"Leather Boots", desc:"Protects the feet from stepping on sharp objects",
			slotType:"boots",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		
		// NOTE - MAIL ARMOR
		"helmet_mail":new Equippable(key:"helmet_mail", name:"Mail Coif", desc:"Protects the head against minor cuts and scrapes",
			slotType:"helmet",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"bodyarmor_mail":new Equippable(key:"bodyarmor_mail", name:"Mail Shirt", desc:"Covers the body from lesser cutting attacks",
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"overbodyarmor_mail":new Equippable(key:"overbodyarmor_mail", name:"Mail Hauberk", desc:"Covers the body from lesser cutting attacks",
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"armarmor_mail":new Equippable(key:"armarmor_mail", name:"Mail Sleeve", desc:"Blocks slashes to the arm",
			slotType:"armarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"legarmor_mail":new Equippable(key:"legarmor_mail", name:"Mail Leggings", desc:"Protects the legs from stray slashes and arrows",
			slotType:"legarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"gloves_mail":new Equippable(key:"gloves_mail", name:"Mail Mittens", desc:"Good for gardening and for accidental brushes against swords",
			slotType:"gloves",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"boots_mail":new Equippable(key:"boots_mail", name:"Mail Stockings", desc:"Protects the feet from stepping on sharp objects",
			slotType:"boots",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		
		// NOTE - BRIGANDINE ARMOR
		
		"helmet_brigandine":new Equippable(key:"helmet_brigandine", name:"Brigandine Coif", desc:"Protects the head against minor cuts and scrapes",
			slotType:"helmet",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"bodyarmor_brigandine":new Equippable(key:"bodyarmor_brigandine", name:"Brigandine Vest", desc:"Covers the body from lesser cutting attacks",
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"overbodyarmor_brigandine":new Equippable(key:"overbodyarmor_brigandine", name:"Brigandine Coat", desc:"Covers the body from lesser cutting attacks",
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"armarmor_brigandine":new Equippable(key:"armarmor_brigandine", name:"Brigandine Sleeve", desc:"Blocks slashes to the arm",
			slotType:"armarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"legarmor_brigandine":new Equippable(key:"legarmor_brigandine", name:"Brigandine Leggings", desc:"Protects the legs from stray slashes and arrows",
			slotType:"legarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"gloves_brigandine":new Equippable(key:"gloves_brigandine", name:"Brigandine Gloves", desc:"Good for gardening and for accidental brushes against swords",
			slotType:"gloves",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"boots_brigandine":new Equippable(key:"boots_brigandine", name:"Brigandine Boots", desc:"Protects the feet from stepping on sharp objects",
			slotType:"boots",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		)
		
		// NOTE - PLATE ARMOR
		
		// NOTE - CAULDRON-QUENCHED ARMOR
	]
}
