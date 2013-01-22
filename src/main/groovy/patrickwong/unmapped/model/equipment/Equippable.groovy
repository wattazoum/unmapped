package patrickwong.unmapped.model.equipment

import patrickwong.unmapped.model.PlayerCharacter

public class Equippable extends GameItem {
	String slotType
	Map<String, Integer> bonuses = null
	
	@Override
	public Equippable clone() {
		return new Equippable(key: this.key, name: this.name, desc: this.desc, actionInField: this.actionInField, actionInCombat: this.actionInCombat, stackSize: 1, stackable: this.stackable, baseValue: this.baseValue,
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
		"helmet_leather":new Equippable(key:"helmet_leather", name:"Leather Coif", desc:"Protects the head against minor cuts and scrapes", baseValue: 10,
			slotType:"helmet",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"bodyarmor_leather":new Equippable(key:"bodyarmor_leather", name:"Leather Armor", desc:"Covers the body from lesser cutting attacks", baseValue: 10,
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"overbodyarmor_leather":new Equippable(key:"overbodyarmor_leather", name:"Leather Jack", desc:"Covers the body from lesser cutting attacks", baseValue: 10,
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"armarmor_leather":new Equippable(key:"armarmor_leather", name:"Leather Bracer", desc:"Blocks slashes to the arm", baseValue: 10,
			slotType:"armarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"legarmor_leather":new Equippable(key:"legarmor_leather", name:"Leather Greaves", desc:"Protects the legs from stray slashes and arrows", baseValue: 10,
			slotType:"legarmor",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"gloves_leather":new Equippable(key:"gloves_leather", name:"Leather Gloves", desc:"Good for gardening and for accidental brushes against swords", baseValue: 10,
			slotType:"gloves",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		"boots_leather":new Equippable(key:"boots_leather", name:"Leather Boots", desc:"Protects the feet from stepping on sharp objects", baseValue: 10,
			slotType:"boots",
			bonuses: ["resist_cutting":4, "resist_piercing":2, "resist_impact":1]
		),
		
		// NOTE - MAIL ARMOR
		"helmet_mail":new Equippable(key:"helmet_mail", name:"Mail Coif", desc:"Protects the head against minor cuts and scrapes", baseValue: 20,
			slotType:"helmet",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"bodyarmor_mail":new Equippable(key:"bodyarmor_mail", name:"Mail Shirt", desc:"Covers the body from lesser cutting attacks", baseValue: 20,
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"overbodyarmor_mail":new Equippable(key:"overbodyarmor_mail", name:"Mail Hauberk", desc:"Covers the body from lesser cutting attacks", baseValue: 20,
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"armarmor_mail":new Equippable(key:"armarmor_mail", name:"Mail Sleeve", desc:"Blocks slashes to the arm", baseValue: 20,
			slotType:"armarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"legarmor_mail":new Equippable(key:"legarmor_mail", name:"Mail Leggings", desc:"Protects the legs from stray slashes and arrows", baseValue: 20,
			slotType:"legarmor",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"gloves_mail":new Equippable(key:"gloves_mail", name:"Mail Mittens", desc:"Good for gardening and for accidental brushes against swords", baseValue: 20,
			slotType:"gloves",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		"boots_mail":new Equippable(key:"boots_mail", name:"Mail Stockings", desc:"Protects the feet from stepping on sharp objects", baseValue: 20,
			slotType:"boots",
			bonuses: ["resist_cutting":6, "resist_piercing":3, "resist_impact":2, "resist_bullet":1]
		),
		
		// NOTE - BRIGANDINE ARMOR
		
		"helmet_brigandine":new Equippable(key:"helmet_brigandine", name:"Brigandine Coif", desc:"Protects the head against minor cuts and scrapes", baseValue: 50,
			slotType:"helmet",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"bodyarmor_brigandine":new Equippable(key:"bodyarmor_brigandine", name:"Brigandine Vest", desc:"Covers the body from lesser cutting attacks", baseValue: 50,
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"overbodyarmor_brigandine":new Equippable(key:"overbodyarmor_brigandine", name:"Brigandine Coat", desc:"Covers the body from lesser cutting attacks", baseValue: 50,
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"armarmor_brigandine":new Equippable(key:"armarmor_brigandine", name:"Brigandine Sleeve", desc:"Blocks slashes to the arm", baseValue: 50,
			slotType:"armarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"legarmor_brigandine":new Equippable(key:"legarmor_brigandine", name:"Brigandine Leggings", desc:"Protects the legs from stray slashes and arrows", baseValue: 50,
			slotType:"legarmor",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"gloves_brigandine":new Equippable(key:"gloves_brigandine", name:"Brigandine Gloves", desc:"Good for gardening and for accidental brushes against swords", baseValue: 50,
			slotType:"gloves",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		"boots_brigandine":new Equippable(key:"boots_brigandine", name:"Brigandine Boots", desc:"Protects the feet from stepping on sharp objects", baseValue: 50,
			slotType:"boots",
			bonuses: ["resist_cutting":8, "resist_piercing":4, "resist_impact":2, "resist_bullet":2]
		),
		
		// NOTE - PLATE ARMOR
		"helmet_plate":new Equippable(key:"helmet_plate", name:"Plate Helmet", desc:"Protects the head against minor cuts and scrapes", baseValue: 200,
			slotType:"helmet",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		),
		"bodyarmor_plate":new Equippable(key:"bodyarmor_plate", name:"Plate Cuirass", desc:"Covers the body from lesser cutting attacks", baseValue: 200,
			slotType:"bodyarmor",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		),
		"overbodyarmor_plate":new Equippable(key:"overbodyarmor_plate", name:"Plate Lorica", desc:"Covers the body from lesser cutting attacks", baseValue: 200,
			slotType:"overbodyarmor",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		),
		"armarmor_plate":new Equippable(key:"armarmor_plate", name:"Plate Pauldron", desc:"Blocks slashes to the arm", baseValue: 200,
			slotType:"armarmor",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		),
		"legarmor_plate":new Equippable(key:"legarmor_plate", name:"Plate Greaves", desc:"Protects the legs from stray slashes and arrows", baseValue: 200,
			slotType:"legarmor",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		),
		"gloves_plate":new Equippable(key:"gloves_plate", name:"Plate Gauntlets", desc:"Good for gardening and for accidental brushes against swords", baseValue: 200,
			slotType:"gloves",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		),
		"boots_plate":new Equippable(key:"boots_plate", name:"Plate Sabatons", desc:"Protects the feet from stepping on sharp objects", baseValue: 200,
			slotType:"boots",
			bonuses: ["resist_cutting":10, "resist_piercing":5, "resist_impact":3, "resist_bullet":3]
		)
	]
	
	public static final Map<String, Equippable> clothingDatabase = [
		// NOTE - CLOAKS
		"cloak_plain":new Equippable(key:"cloak_plain", name:"Cloak", desc:"An ordinary travelling cloak", baseValue: 12, slotType:"cloak"),
		"cloak_furtop":new Equippable(key:"cloak_furtop", name:"Fur-Topped Cloak", desc:"Keeps you warm in the north", baseValue: 44, slotType:"cloak"),
		"cloak_silkcape":new Equippable(key:"cloak_silkcape", name:"Silk Cape", desc:"Stylish cape for heroes", baseValue: 240, slotType:"cloak"),
		"cloak_backbanner":new Equippable(key:"cloak_backbanner", name:"Back Banner", desc:"Vertical military standard worn on the back", baseValue: 24, slotType:"cloak"),
		
		// NOTE - HATS
		
		"hat_farmer":new Equippable(key:"hat_farmer", name:"Farmer Hat", desc:"Straw hat that protects against the sun", baseValue: 10, slotType:"hat"),
		"hat_feathered":new Equippable(key:"hat_feathered", name:"Feathered Hat", desc:"Stereotypical spoony bard hat", baseValue: 10, slotType:"hat"),
		"hat_starcloth":new Equippable(key:"hat_starcloth", name:"Star Hat", desc:"Star-shaped cloth that somehow stays on the head", baseValue: 10, slotType:"hat"),
		"hat_yarmulke":new Equippable(key:"hat_yarmulke", name:"Yarmulke", desc:"It is preferred by people of a specific religion and/or race.", baseValue: 10, slotType:"hat"),
		"hat_dorag":new Equippable(key:"hat_dorag", name:"Do Rag", desc:"Works best in combination with a shaved head", baseValue: 10, slotType:"hat"),
		
		// NOTE - FOREHEAD
		
		"forehead_steelband":new Equippable(key:"forehead_steelband", name:"Steel Headband", desc:"A favorite of warriors", baseValue: 20, slotType:"forehead"),
		"forehead_diadem":new Equippable(key:"forehead_diadem", name:"Diadem", desc:"Golden band with valuable jewels", baseValue: 240, slotType:"forehead"),
		"forehead_numbertwo":new Equippable(key:"forehead_numbertwo", name:"Number Two Headband", desc:"It is a very good headband, but not number one", baseValue: 240, slotType:"forehead"),
		"forehead_numberone":new Equippable(key:"forehead_numberone", name:"Number One Headband", desc:"It signifies excellent fighting skill", baseValue: 5520, slotType:"forehead"),
		
		// NOTE - EYEWEAR
		
		"eyewear_pincenez":new Equippable(key:"eyewear_pincenez", name:"Pince-Nez", desc:"Glasses held in place by the nose", baseValue: 480, slotType:"eyewear"),
		"eyewear_eyepatch":new Equippable(key:"eyewear_eyepatch", name:"Eyepatch", desc:"Worn by veterans of any war, not just pirates", baseValue: 5, slotType:"eyewear"),
		"eyewear_rectangle":new Equippable(key:"eyewear_rectangle", name:"Rectangle Glasses", desc:"This game takes place in some kind of fantasy world,\nso you are wearing these way before they were cool", baseValue: 480, slotType:"eyewear"),
		
		// NOTE - NECKLACES
		
		"necklace_pendant":new Equippable(key:"necklace_pendant", name:"Pendant", desc:"Trinket. No effect, but fond memories comfort travellers.", baseValue: 5, slotType:"necklace"),
		"necklace_amulet":new Equippable(key:"necklace_amulet", name:"Amulet", desc:"Inscribed with a magical symbol for good luck", baseValue: 24, slotType:"necklace"),
		"necklace_leatherchoker":new Equippable(key:"necklace_leatherchoker", name:"Leather Choker", desc:"It is uncertain what the purpose of this is", baseValue: 24, slotType:"necklace"),
		"necklace_lacechoker":new Equippable(key:"necklace_lacechoker", name:"Lace Choker", desc:"Very fancy item", baseValue: 48, slotType:"necklace"),
		
		// NOTE - SCARVES
		
		"scarf_wool":new Equippable(key:"scarf_wool", name:"Wool Scarf", desc:"Keeps the neck very warm", baseValue: 24, slotType:"scarf"),
		"scarf_plaid":new Equippable(key:"scarf_plaid", name:"Plaid Scarf", desc:"Stylish and colorful scarf", baseValue: 48, slotType:"scarf"),
		
		// NOTE - TABARDS
		
		"tabard_military":new Equippable(key:"tabard_military", name:"Military Tabard", desc:"Bears the coat of arms of a family", baseValue: 240, slotType:"tabard"),
		"tabard_holy":new Equippable(key:"tabard_holy", name:"Holy Tabard", desc:"Signifies membership in a holy military order", baseValue: 240, slotType:"tabard"),
		
		// NOTE - UNDERSHIRTS
		
		"undershirt_sarashi":new Equippable(key:"undershirt_sarashi", name:"Sarashi", desc:"Bandages wrapped around the body", baseValue: 4, slotType:"undershirt"),
		"undershirt_corset":new Equippable(key:"undershirt_corset", name:"Corset", desc:"Not good for breathing", baseValue: 24, slotType:"undershirt"),
		"undershirt_lacybra":new Equippable(key:"undershirt_lacybra", name:"Lacy Bra", desc:"Comes in black, red, white, and other colors", baseValue: 48, slotType:"undershirt"),
		
		// NOTE - SHIRTS
		
		"shirt_wittyquote":new Equippable(key:"shirt_wittyquote", name:"Shirt with Witty Quote", desc:"Shark + Laser =\nShark cut in half by laser", baseValue: 24, slotType:"shirt"),
		
		// NOTE - DRESSES
		
		"dress_crystalblue":new Equippable(key:"dress_crystalblue", name:"Crystal Blue Dress", desc:"Good until midnight", baseValue: 5520, slotType:"dress"),
		"dress_peasant":new Equippable(key:"dress_peasant", name:"Peasant Dress", desc:"Fits a variety of sizes", baseValue: 240, slotType:"dress"),
		"dress_gothloli":new Equippable(key:"dress_gothloli", name:"Gothloli Dress", desc:"Goes well with creepy eyes", baseValue: 5520, slotType:"dress"),
		
		// NOTE - PIERCINGS
		
		"piercing_stud":new Equippable(key:"piercing_stud", name:"Stud", desc:"Simple steel piercing", baseValue: 12, slotType:"piercing"),
		"piercing_gold":new Equippable(key:"piercing_gold", name:"Gold Piercing", desc:"Very expensive piercing", baseValue: 480, slotType:"piercing"),
		"piercing_chain":new Equippable(key:"piercing_chain", name:"Chain Piercing", desc:"Not practical for battle", baseValue: 24, slotType:"piercing"),
		
		// NOTE - RINGS
		
		"ring_steel":new Equippable(key:"ring_steel", name:"Steel Ring", desc:"Simple ring for decoration", baseValue: 12, slotType:"ring"),
		"ring_silver":new Equippable(key:"ring_silver", name:"Silver Ring", desc:"Very malleable ring", baseValue: 48, slotType:"ring"),
		"ring_gold":new Equippable(key:"ring_gold", name:"Gold Ring", desc:"Ring that indicates high status", baseValue: 480, slotType:"ring"),
		"ring_diamond":new Equippable(key:"ring_diamond", name:"Diamond Ring", desc:"The gemstone is made of one of the most common\nelements in the dirt, compressed by pressure", baseValue: 1, slotType:"ring"),
		
		// NOTE - STRAPS
		
		"strap_yellow":new Equippable(key:"strap_yellow", name:"Yellow Strap", desc:"Listen here brother, you want me to describe a strap?", baseValue: 12, slotType:"strap"),
		"strap_ultimate":new Equippable(key:"strap_ultimate", name:"Ultimate Strap", desc:"This strap is gonna hijack a plane\nand hit the skyscraper of your ego", baseValue: 12, slotType:"strap")
	]
}
