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
		),
		
		// NOTE - CLOAKS
		"cloak_plain":new Equippable(key:"cloak_plain", name:"Cloak", desc:"An ordinary travelling cloak", baseValue: 12, slotType:"cloak"),
		"cloak_shawl":new Equippable(key:"cloak_shawl", name:"Shawl", desc:"Colorful and embroidered piece of art", baseValue: 12, slotType:"cloak"),
		"cloak_furtop":new Equippable(key:"cloak_furtop", name:"Fur-Topped Cloak", desc:"Keeps you warm in the north", baseValue: 44, slotType:"cloak"),
		"cloak_silkcape":new Equippable(key:"cloak_silkcape", name:"Silk Cape", desc:"Stylish cape for heroes", baseValue: 240, slotType:"cloak"),
		"cloak_backbanner":new Equippable(key:"cloak_backbanner", name:"Back Banner", desc:"Vertical military standard worn on the back", baseValue: 24, slotType:"cloak"),
		"cloak_decwings":new Equippable(key:"cloak_decwings", name:"Decorative Wings", desc:"They do not grant the power of flight", baseValue: 24, slotType:"cloak"),
		
		// NOTE - HATS
		
		"hat_farmer":new Equippable(key:"hat_farmer", name:"Farmer Hat", desc:"Straw hat that protects against the sun", baseValue: 10, slotType:"hat"),
		"hat_feathered":new Equippable(key:"hat_feathered", name:"Feathered Hat", desc:"Stereotypical spoony bard hat", baseValue: 10, slotType:"hat"),
		"hat_starcloth":new Equippable(key:"hat_starcloth", name:"Star Hat", desc:"Star-shaped cloth that somehow stays on the head", baseValue: 10, slotType:"hat"),
		"hat_yarmulke":new Equippable(key:"hat_yarmulke", name:"Yarmulke", desc:"It is preferred by people of a specific religion and/or race.", baseValue: 10, slotType:"hat"),
		"hat_dorag":new Equippable(key:"hat_dorag", name:"Do Rag", desc:"Works best in combination with a shaved head", baseValue: 10, slotType:"hat"),
		"hat_animalears":new Equippable(key:"hat_animalears", name:"Animal Ears", desc:"They are supposed to look really cute", baseValue: 48, slotType:"hat"),
		
		// NOTE - FOREHEAD
		
		"forehead_bandana":new Equippable(key:"forehead_bandana", name:"Bandana", desc:"The loose straps in back are blown by the wind", baseValue: 6, slotType:"forehead"),
		"forehead_steelband":new Equippable(key:"forehead_steelband", name:"Steel Headband", desc:"A favorite of warriors", baseValue: 20, slotType:"forehead"),
		"forehead_flagband":new Equippable(key:"forehead_flagband", name:"Flag Headband", desc:"The best headband... for bandits!", baseValue: 24, slotType:"forehead"),
		"forehead_diadem":new Equippable(key:"forehead_diadem", name:"Diadem", desc:"Golden band with valuable jewels", baseValue: 240, slotType:"forehead"),
		"forehead_numbertwo":new Equippable(key:"forehead_numbertwo", name:"Number Two Headband", desc:"It is a very good headband, but not number one", baseValue: 240, slotType:"forehead"),
		"forehead_numberone":new Equippable(key:"forehead_numberone", name:"Number One Headband", desc:"It signifies excellent fighting skill", baseValue: 5520, slotType:"forehead"),
		
		// NOTE - EYEWEAR
		
		"eyewear_eyepatch":new Equippable(key:"eyewear_eyepatch", name:"Eyepatch", desc:"Worn by veterans of any war, not just pirates", baseValue: 5, slotType:"eyewear"),
		"eyewear_flagblind":new Equippable(key:"eyewear_flagblind", name:"Flag Blindfold", desc:"It shows dedication to the homefront", baseValue: 12, slotType:"eyewear"),
		"eyewear_pincenez":new Equippable(key:"eyewear_pincenez", name:"Pince-Nez", desc:"Glasses held in place by the nose", baseValue: 480, slotType:"eyewear"),
		"eyewear_megane":new Equippable(key:"eyewear_megane", name:"Megane", desc:"Very large round glasses", baseValue: 480, slotType:"eyewear"),
		"eyewear_rectangle":new Equippable(key:"eyewear_rectangle", name:"Rectangle Glasses", desc:"This game takes place in some kind of fantasy world,\nso you are wearing these way before they were cool", baseValue: 480, slotType:"eyewear"),
		
		// NOTE - MOUTHGUARDS
		
		"mouthguard_coloredcloth":new Equippable(key:"mouthguard_coloredcloth", name:"Colored Cloth Mask", desc:"The color is bright and shows membership in a gang", baseValue: 6, slotType:"mouthguard"),
		"mouthguard_surgical":new Equippable(key:"mouthguard_surgical", name:"Surgical Mask", desc:"It stops the wearer's own diseases from getting out\nbut not the other way around", baseValue: 12, slotType:"mouthguard"),
		"mouthguard_boxing":new Equippable(key:"mouthguard_boxing", name:"Boxing Mouthguard", desc:"Prevents you from biting your tongue", baseValue: 12, slotType:"mouthguard"),
		"mouthguard_respirator":new Equippable(key:"mouthguard_respirator", name:"Respirator", desc:"The sound of breathing was once human, but not anymore", baseValue: 24, slotType:"mouthguard"),
		"mouthguard_gasmask":new Equippable(key:"mouthguard_gasmask", name:"Gas Mask", desc:"Extremely uncomfortable, but it works", baseValue: 240, slotType:"mouthguard"),
		
		// NOTE - NECKLACES
		
		"necklace_pendant":new Equippable(key:"necklace_pendant", name:"Pendant", desc:"Trinket. No effect, but fond memories comfort travellers.", baseValue: 5, slotType:"necklace"),
		"necklace_amulet":new Equippable(key:"necklace_amulet", name:"Amulet", desc:"Inscribed with a magical symbol for good luck", baseValue: 24, slotType:"necklace"),
		"necklace_leatherchoker":new Equippable(key:"necklace_leatherchoker", name:"Leather Choker", desc:"It is uncertain what the purpose of this is", baseValue: 24, slotType:"necklace"),
		"necklace_lacechoker":new Equippable(key:"necklace_lacechoker", name:"Lace Choker", desc:"Very fancy item", baseValue: 48, slotType:"necklace"),
		
		// NOTE - SCARVES
		
		"scarf_wool":new Equippable(key:"scarf_wool", name:"Wool Scarf", desc:"Keeps the neck very warm", baseValue: 12, slotType:"scarf"),
		"scarf_plaid":new Equippable(key:"scarf_plaid", name:"Plaid Scarf", desc:"Stylish and colorful scarf", baseValue: 24, slotType:"scarf"),
		
		// NOTE - TABARDS
		
		"tabard_military":new Equippable(key:"tabard_military", name:"Military Tabard", desc:"Bears the coat of arms of a family", baseValue: 240, slotType:"tabard"),
		"tabard_holy":new Equippable(key:"tabard_holy", name:"Holy Tabard", desc:"Signifies membership in a holy military order", baseValue: 240, slotType:"tabard"),
		
		// NOTE - UNDERSHIRTS
		
		"undershirt_waistbandages":new Equippable(key:"undershirt_waistbandages", name:"Waist Bandages", desc:"Delinquents and gangsters like these for some reason", baseValue: 4, slotType:"undershirt"),
		"undershirt_sarashi":new Equippable(key:"undershirt_sarashi", name:"Sarashi", desc:"Bandages wrapped around the body", baseValue: 4, slotType:"undershirt"),
		"undershirt_corset":new Equippable(key:"undershirt_corset", name:"Corset", desc:"Not good for breathing", baseValue: 24, slotType:"undershirt"),
		"undershirt_lacyundershirt":new Equippable(key:"undershirt_lacyundershirt", name:"Lacy Undershirt", desc:"It is very slightly see-through", baseValue: 48, slotType:"undershirt"),
		"undershirt_lacybra":new Equippable(key:"undershirt_lacybra", name:"Lacy Bra", desc:"Comes in black, red, white, and other colors", baseValue: 48, slotType:"undershirt"),
		"undershirt_oxygenbra":new Equippable(key:"undershirt_oxygenbra", name:"Oxygen Bra", desc:"Very restrictive, but athletes swear by it", baseValue: 48, slotType:"undershirt"),
		
		// NOTE - SHIRTS
		
		"shirt_wittyquote":new Equippable(key:"shirt_wittyquote", name:"Shirt with Witty Quote", desc:"Shark + Laser =\nShark cut in half by laser", baseValue: 24, slotType:"shirt"),
		"shirt_badgirl":new Equippable(key:"shirt_badgirl", name:"Bad Girl Shirt", desc:"It is a very tight-fitting shirt", baseValue: 24, slotType:"shirt"),
		"shirt_muscle":new Equippable(key:"shirt_muscle", name:"Muscle Shirt", desc:"Big biceps and shoulders are recommended", baseValue: 24, slotType:"shirt"),
		"shirt_tanktop":new Equippable(key:"shirt_tanktop", name:"Tanktop", desc:"Its other common name refers to domestic violence", baseValue: 12, slotType:"shirt"),
		"shirt_sailor":new Equippable(key:"shirt_sailor", name:"Sailor Shirt", desc:"It also has a small red sailor scarf\nand colorful collars", baseValue: 24, slotType:"shirt"),
		"shirt_lacybuttonup":new Equippable(key:"shirt_lacybutton", name:"Lacy Button-Up Shirt", desc:"It also has a rounded collar", baseValue: 48, slotType:"shirt"),
		"shirt_plaidbuttonup":new Equippable(key:"shirt_plaidbuttonup", name:"Plaid Button-Up", desc:"Often worn in combination with jeans", baseValue: 24, slotType:"shirt"),
		"shirt_tacticalbuttonup":new Equippable(key:"shirt_tacticalbuttonup", name:"Tactical Button-Up", desc:"It has pockets for all kinds of odd little things", baseValue: 24, slotType:"shirt"),
		
		// NOTE - DRESSES
		
		"dress_peasant":new Equippable(key:"dress_peasant", name:"Peasant Dress", desc:"Fits a variety of sizes", baseValue: 24, slotType:"dress"),
		"dress_austere":new Equippable(key:"dress_austere", name:"Austere Dress", desc:"Its design has a lot of straight lines and angles", baseValue: 48, slotType:"dress"),
		"dress_crystalblue":new Equippable(key:"dress_crystalblue", name:"Crystal Blue Dress", desc:"Good until midnight", baseValue: 5520, slotType:"dress"),
		"dress_gothloli":new Equippable(key:"dress_gothloli", name:"Gothloli Dress", desc:"Goes well with creepy eyes and a creepy smile", baseValue: 5520, slotType:"dress"),
		
		// NOTE - JACKETS
		
		"jacket_windbreaker":new Equippable(key:"jacket_windbreaker", name:"Windbreaker", desc:"Only effective in temperate climates", baseValue: 12, slotType:"jacket"),
		"jacket_hightech":new Equippable(key:"jacket_hightech", name:"High-Tech Jacket", desc:"The material permits air but not water", baseValue: 240, slotType:"jacket"),
		"jacket_knitted":new Equippable(key:"jacket_knitted", name:"Knitted Jacket", desc:"Also has very big wool-covered buttons", baseValue: 24, slotType:"jacket"),
		
		// NOTE - BELTS
		
		"belt_leather":new Equippable(key:"belt_leather", name:"Leather Belt", desc:"Basic affordable belt", baseValue: 6, slotType:"belt"),
		"belt_business":new Equippable(key:"belt_business", name:"Business Belt", desc:"The buckle is very shiny", baseValue: 24, slotType:"belt"),
		"belt_wide":new Equippable(key:"belt_wide", name:"Wide Belt", desc:"It is made to be seen", baseValue: 12, slotType:"belt"),
		"belt_martialarts":new Equippable(key:"belt_martialarts", name:"Martial Arts Belt", desc:"It doubles as an implement of strangulation", baseValue: 12, slotType:"belt"),
		"belt_champion":new Equippable(key:"belt_champion", name:"Champion Belt", desc:"This belt is the greatest!\nIt is the greatest of all time!\nDid you see what that belt did?", baseValue: 5520, slotType:"belt"),
		
		// NOTE - SHORTS
		
		"shorts_cottonpantsu":new Equippable(key:"shorts_cottonpantsu", name:"Cotton Pantsu", desc:"Guaranteed to be brand new or your money back", baseValue: 12, slotType:"shorts"),
		"shorts_lacypantsu":new Equippable(key:"shorts_lacypantsu", name:"Lacy Pantsu", desc:"They indicate an older age, which could be\neither a compliment or an insult", baseValue: 24, slotType:"shorts"),
		"shorts_elastictight":new Equippable(key:"shorts_elastictight", name:"Elastic Tight Shorts", desc:"For those who don't want to hang out", baseValue: 12, slotType:"shorts"),
		"shorts_wool":new Equippable(key:"shorts_wool", name:"Wool Shorts", desc:"Combines warmth, protection, and freedom", baseValue: 12, slotType:"shorts"),
		"shorts_fighting":new Equippable(key:"shorts_fighting", name:"Fighting Trunks", desc:"More for style and identity than anything else", baseValue: 12, slotType:"shorts"),
		
		// NOTE - PANTS
		
		"pants_jeans":new Equippable(key:"pants_jeans", name:"Jeans", desc:"Even angels and demons like to wear jeans", baseValue: 12, slotType:"pants"),
		"pants_carpenter":new Equippable(key:"pants_carpenter", name:"Carpenter Pants", desc:"Their only weak points are the insides of their pockets", baseValue: 48, slotType:"pants"),
		"pants_tactical":new Equippable(key:"pants_tactical", name:"Tactical Pants", desc:"Naturally, there are at least two slots for knives", baseValue: 24, slotType:"pants"),
		"pants_puffy":new Equippable(key:"pants_puffy", name:"Puffy Pants", desc:"The favorite of dancers who can't touch this", baseValue: 24, slotType:"pants"),
		"pants_yoga":new Equippable(key:"pants_yoga", name:"Yoga Pants", desc:"They do not actually help with stretching", baseValue: 48, slotType:"pants"),
		"pants_khakis":new Equippable(key:"pants_khakis", name:"Khakis", desc:"Accentuates sunlight and clear weather", baseValue: 12, slotType:"pants"),
		
		// NOTE - SKIRTS
		
		"skirt_peasant":new Equippable(key:"skirt_peasant", name:"Peasant Skirt", desc:"Plain brown-ish skirt for modesty", baseValue: 12, slotType:"skirt"),
		"skirt_sailor":new Equippable(key:"skirt_sailor", name:"Sailor Skirt", desc:"Always a dark color such as blue or red", baseValue: 12, slotType:"skirt"),
		"skirt_skirtshorts":new Equippable(key:"skirt_skirtshorts", name:"Skirt-Shorts", desc:"For athletes who don't want to look too masculine", baseValue: 12, slotType:"skirt"),
		
		// NOTE - SOCKS
		
		"socks_wool":new Equippable(key:"socks_wool", name:"Wool Socks", desc:"Keeps the feet warm through winter", baseValue: 12, slotType:"socks"),
		"socks_tightcotton":new Equippable(key:"socks_tightcotton", name:"Tight Cottons", desc:"Hopefully not too scandalous compared to nylons", baseValue: 24, slotType:"socks"),
		"socks_tightnylon":new Equippable(key:"socks_tightnylon", name:"Tight Nylons", desc:"Works well in combination with low muscle", baseValue: 24, slotType:"socks"),
		"socks_lacystockings":new Equippable(key:"socks_lacystockings", name:"Lacy Stockings", desc:"They need to be held up by a girdle", baseValue: 24, slotType:"socks"),
		"socks_bunchedcotton":new Equippable(key:"socks_bunchedcotton", name:"Bunched Cotton Socks", desc:"One author compared them to the feet of a clydesdale horse", baseValue: 24, slotType:"socks"),
		
		// NOTE - PIERCINGS
		
		"piercing_steelstud":new Equippable(key:"piercing_steelstud", name:"Steel Stud", desc:"Simple steel piercing", baseValue: 12, slotType:"piercing"),
		"piercing_gold":new Equippable(key:"piercing_gold", name:"Gold Piercing", desc:"Very expensive piercing", baseValue: 480, slotType:"piercing"),
		"piercing_chain":new Equippable(key:"piercing_chain", name:"Chain Piercing", desc:"Not practical for battle, more for religious purposes", baseValue: 24, slotType:"piercing"),
		
		// NOTE - RINGS
		
		"ring_steel":new Equippable(key:"ring_steel", name:"Steel Ring", desc:"Simple ring for decoration", baseValue: 12, slotType:"ring"),
		"ring_silver":new Equippable(key:"ring_silver", name:"Silver Ring", desc:"Very malleable ring", baseValue: 48, slotType:"ring"),
		"ring_gold":new Equippable(key:"ring_gold", name:"Gold Ring", desc:"Ring that indicates high status", baseValue: 480, slotType:"ring"),
		"ring_diamond":new Equippable(key:"ring_diamond", name:"Diamond Ring", desc:"The gemstone is made of one of the most common\nelements in ordinary dirt, compressed by heat pressure,\nand many children were murdered in the process of mining it", baseValue: 1, slotType:"ring"),
		
		// NOTE - STRAPS
		
		"strap_steel":new Equippable(key:"strap_steel", name:"Steel Strap", desc:"Just in case lesser warriors could not already see your muscles", baseValue: 12, slotType:"strap"),
		"strap_yellow":new Equippable(key:"strap_yellow", name:"Yellow Strap", desc:"Listen here brother, you want me to describe a strap?", baseValue: 12, slotType:"strap"),
		"strap_ultimate":new Equippable(key:"strap_ultimate", name:"Ultimate Strap", desc:"This strap is gonna hijack a plane\nand hit the skyscraper of your ego", baseValue: 12, slotType:"strap")
	]
}
