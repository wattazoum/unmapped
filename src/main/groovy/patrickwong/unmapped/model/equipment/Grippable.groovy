package patrickwong.unmapped.model.equipment

import java.util.Map;

import patrickwong.unmapped.model.PlayerCharacter

public class Grippable extends Equippable {
	String slotType = "grip"
	boolean melee = true
	boolean ranged = false
	boolean twoHanded = false
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
		"shortweapon_knife":new Grippable(key:"shortweapon_knife", name:"Knife", desc:"Simple cutting weapon",
			getAttackVerb: {
				PlayerCharacter pc ->
				return " slices "
			},
			rollAttackAccuracy: {
				PlayerCharacter pc ->
				int result = 0
				result += pc.rollStat("DEX") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("short_weapons")
				return result
			},
			rollAttackDamage: {
				PlayerCharacter pc ->
				int result = 0
				result += pc.rollStat("AGI") + pc.rollStat("DEX") + pc.rollBonuses("cause_cutting")
				result = (result / 2)
				return result
			},
			getAttackDamageType: { return "cutting" }
		),
		"swordstraightone_gladius":new Grippable(key:"swordstraightone_gladius", name:"Gladius", desc:"General-purpose light sword for close-up fighting",
			getAttackVerb: {
				PlayerCharacter pc ->
				return " slices "
			},
			rollAttackAccuracy: {
				PlayerCharacter pc ->
				int result = 0
				result += pc.rollStat("DEX") + pc.rollSkill("fighting") + pc.rollSkill("melee_fighting") + pc.rollSkill("swords_straight_one")
				return result
			},
			rollAttackDamage: {
				PlayerCharacter pc ->
				int result = 0
				result += pc.rollStat("AGI") + pc.rollStat("STR") + pc.rollBonuses("cause_cutting")
				result = (result / 2)
				return result
			},
			getAttackDamageType: { return "cutting" }
		)
	]
	
	// NOTE - SHIELDS
	public static Map<String, Grippable> getShieldDatabase() {
		return null
	}
}
