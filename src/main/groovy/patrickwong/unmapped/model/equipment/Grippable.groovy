package patrickwong.unmapped.model.equipment

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.model.PlayerCharacter

public class Grippable extends Equippable {
	String slotType = "grip"
	boolean melee = true
	boolean ranged = false
	boolean twoHanded = false
	int attackWeight = 5
	int defenseWeight = 5
	// String
	Closure getAttackVerb = {
		PlayerCharacter pc ->
		return " punches "
	}
	// int
	Closure rollAttackAccuracy = {
		PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("DEX")
		result += pc.rollSkill("fighting")
		result += pc.rollSkill("melee_fighting")
		result += pc.rollSkill("unarmed")
		result += pc.rollBonuses("accuracy_melee")
		return result
	}
	// int
	Closure rollAttackDamage = {
		PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("STR")
		result += pc.rollStat("AGI")
		result += pc.rollBonuses("cause_impact")
		result = (result / 2)
		return result
	}
	// int
	Closure rollMeleeDefense = {
		PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("REF")
		result += pc.rollStat("TGH")
		result += pc.rollSkill("fighting")
		result += pc.rollSkill("melee_defense")
		result += pc.rollSkill("weaponcatching")
		result = (result / 3)
		return result
	}
	// int
	Closure rollRangedDefense = {
		PlayerCharacter pc ->
		int result = 0
		result += pc.rollStat("REF")
		result += pc.rollStat("TGH")
		result += pc.rollSkill("fighting")
		result += pc.rollSkill("ranged_defense")
		result += pc.rollSkill("missile_catching")
		result = (result / 3)
		return result
	}
	// String
	// Why is this a Closure rather than a String? Just in case a weapon needs some more advanced logic.
	Closure getAttackDamageType = { return "impact" }
	
	public static Grippable getRightFist() {
		return new Grippable(key:"right_fist", name:"Right Fist", desc:"This is your right hand, a fast but weak weapon", stackSize:0, stackable:false)
	}
	public static Grippable getLeftFist() {
		return new Grippable(key:"left_fist", name:"Left Fist", desc:"This is your left hand, a fast but weak weapon", stackSize:0, stackable: false)
	}
	
	// NOTE - MELEE WEAPONS
	public static Map<String, Grippable> weaponDatabase = [
		"shortweapon_knife":new Grippable(key:"shortweapon_knife", name:"Knife", desc:"Simple cutting weapon", baseValue: 6,
			getAttackVerb: { PlayerCharacter pc ->
				return " slices at "
			},
			rollAttackAccuracy: { PlayerCharacter pc ->
				int result = pc.rollStat("DEX") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("short_weapons")
				return result
			},
			rollAttackDamage: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollStat("DEX")
				result = (result / 2)
				result += pc.rollBonuses("cause_cutting") + DiceRoller.binaryPool(10)
				return result
			},
			getAttackDamageType: { return "cutting" },
			rollMeleeDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("melee_defense") + pc.rollSkill("parrying")
				result = (result / 2)
				return result
			},
			rollRangedDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("ranged_defense") + pc.rollSkill("missile_deflecting")
				result = (result / 2)
				return result
			},
			attackWeight: 20,
			defenseWeight: 10
		),
		"swordstraightone_gladius":new Grippable(key:"swordstraightone_gladius", name:"Gladius", desc:"General-purpose light sword for close-up fighting", baseValue: 240,
			getAttackVerb: { PlayerCharacter pc ->
				return " swings at "
			},
			rollAttackAccuracy: { PlayerCharacter pc ->
				int result = pc.rollStat("DEX") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("swords_straight_one")
				return result
			},
			rollAttackDamage: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollStat("STR")
				result = (result / 2)
				result += pc.rollBonuses("cause_cutting") + DiceRoller.binaryPool(20)
				return result
			},
			getAttackDamageType: { return "cutting" },
			rollMeleeDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("melee_defense") + pc.rollSkill("parrying")
				result = (result / 2)
				return result
			},
			rollRangedDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("ranged_defense") + pc.rollSkill("missile_deflecting")
				result = (result / 2)
				return result
			},
			attackWeight: 30,
			defenseWeight: 15
		),
		"swordstraighttwo_longsword":new Grippable(key:"swordstraighttwo_longsword", name:"Longsword", desc:"Big sword for slaying", twoHanded: true, baseValue: 480,
			getAttackVerb: { PlayerCharacter pc ->
				return " swings at "
			},
			rollAttackAccuracy: { PlayerCharacter pc ->
				int result = pc.rollStat("DEX") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("swords_straight_two")
				return result
			},
			rollAttackDamage: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollStat("STR")
				result = (result / 2)
				result += pc.rollBonuses("cause_cutting") + DiceRoller.binaryPool(40)
				return result
			},
			getAttackDamageType: { return "cutting" },
			rollMeleeDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("melee_defense") + pc.rollSkill("parrying")
				result = (result / 2)
				return result
			},
			rollRangedDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("ranged_defense") + pc.rollSkill("missile_deflecting")
				result = (result / 2)
				return result
			}
		),
		
		// NOTE - SHIELDS
		"shield_buckler":new Grippable(key:"shield_buckler", name:"Buckler", desc:"Small round shield for duelling", baseValue: 120,
			getAttackVerb: { PlayerCharacter pc ->
				return " thrusts a buckler at "
			},
			rollAttackAccuracy: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("shield_bashing")
				return result
			},
			rollAttackDamage: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollStat("STR")
				result = (result / 2)
				result += pc.rollBonuses("cause_impact")
				return result
			},
			getAttackDamageType: { return "impact" },
			rollMeleeDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("melee_defense") + pc.rollSkill("shield_blocking")
				result = (result / 2)
				return result
			},
			rollRangedDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("ranged_defense") + pc.rollSkill("shield_blocking")
				result = (result / 2)
				return result
			},
			attackWeight: 5,
			defenseWeight: 30
		),
		"shield_heater":new Grippable(key:"shield_heater", name:"Heater Shield", desc:"V-shaped shield for mounted combat", baseValue: 480,
			getAttackVerb: { PlayerCharacter pc ->
				return " shield-bashes at "
			},
			rollAttackAccuracy: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("shield_bashing")
				return result
			},
			rollAttackDamage: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollStat("STR")
				result = (result / 2)
				result += pc.rollBonuses("cause_impact")
				return result
			},
			getAttackDamageType: { return "impact" },
			rollMeleeDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("melee_defense") + pc.rollSkill("shield_blocking") + DiceRoller.binaryPool(20)
				result = (result / 2)
				return result
			},
			rollRangedDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("ranged_defense") + pc.rollSkill("shield_blocking") + DiceRoller.binaryPool(20)
				result = (result / 2)
				return result
			},
			attackWeight: 5,
			defenseWeight: 40
		),
		"shield_scutum":new Grippable(key:"shield_scutum", name:"Scutum Shield", desc:"Very large shield for excellent protection", baseValue: 960,
			getAttackVerb: { PlayerCharacter pc ->
				return " shield-bashes at "
			},
			rollAttackAccuracy: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("shield_bashing")
				return result
			},
			rollAttackDamage: { PlayerCharacter pc ->
				int result = pc.rollStat("AGI") + pc.rollStat("STR")
				result = (result / 2)
				result += pc.rollBonuses("cause_impact")
				return result
			},
			getAttackDamageType: { return "impact" },
			rollMeleeDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("melee_defense") + pc.rollSkill("shield_blocking") + DiceRoller.binaryPool(50)
				result = (result / 2)
				return result
			},
			rollRangedDefense: { PlayerCharacter pc ->
				int result = pc.rollStat("REF") + pc.rollSkill("fighting") + pc.rollSkill("ranged_defense") + pc.rollSkill("shield_blocking") + DiceRoller.binaryPool(50)
				result = (result / 2)
				return result
			},
			attackWeight: 1,
			defenseWeight: 60
		)
		
	]
	
	@Override
	public String getReadableName() {
		String readableName = super.getReadableName()
		if (twoHanded) {
			readableName += " (2H)"
		}
		return readableName
	}
	
	@Override
	public Grippable clone() {
		Grippable newItem = new Grippable(key: this.key, name: this.name, desc: this.desc, actionInField: this.actionInField, actionInCombat: this.actionInCombat, stackSize: 1, stackable: this.stackable, baseValue: this.baseValue,
			slotType: this.slotType,
			bonuses: this.bonuses,
			melee: this.melee,
			ranged: this.ranged,
			twoHanded: this.twoHanded,
			getAttackVerb: this.getAttackVerb,
			rollAttackAccuracy: this.rollAttackAccuracy,
			rollAttackDamage: this.rollAttackDamage,
			getAttackDamageType: this.getAttackDamageType,
			rollMeleeDefense: this.rollMeleeDefense,
			rollRangedDefense: this.rollRangedDefense,
			attackWeight: this.attackWeight,
			defenseWeight: this.defenseWeight
		)
		return newItem
	}
}
