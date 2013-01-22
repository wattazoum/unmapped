package patrickwong.unmapped.model

public class CharacterSkill implements Comparable<CharacterSkill> {
	String name = "default skill name"
	String codeName = "DSN"
	String levelupMessage = "is more skillful at something $name"
	long value = 0
	long exp = 0
	int skillDifficulty = 1
	int order = 0
	
	@Override
	public int compareTo(CharacterSkill skill) {
		return this.order.compareTo(skill.order)
	}
	
	public static List<CharacterSkill> getDefaultSkills() {
		List<CharacterSkill> skills = new Vector<CharacterSkill>()
		
		skills.add(new CharacterSkill(codeName: "fighting", order: 0, name: "Fighting", levelupMessage:"is better at overall fighting", skillDifficulty: 10))
		
		skills.add(new CharacterSkill(codeName: "melee_fighting", order: 1000, name: "Melee Fighting", levelupMessage:"is better at melee fighting", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "axes", order: 1010, name: "Axes", levelupMessage:"wields axes with more skill"))
		skills.add(new CharacterSkill(codeName: "blunt_weapons", order: 1020, name: "Blunt Weapons", levelupMessage:"wields blunt weapons with more skill"))
		skills.add(new CharacterSkill(codeName: "polearms", order: 1030, name: "Polearms", levelupMessage:"wields polearms with more skill"))
		skills.add(new CharacterSkill(codeName: "short_weapons", order: 1040, name: "Short Weapons", levelupMessage:"wields short weapons with more skill"))
		skills.add(new CharacterSkill(codeName: "spears", order: 1050, name: "Spears", levelupMessage:"wields spears with more skill"))
		skills.add(new CharacterSkill(codeName: "swords_slashing", order: 1060, name: "Swords, Slashing", levelupMessage:"is better with slashing swords"))
		skills.add(new CharacterSkill(codeName: "swords_straight_one", order: 1070, name: "Swords, Straight One-Handed", levelupMessage:"is better with straight one-handed swords"))
		skills.add(new CharacterSkill(codeName: "swords_straight_two", order: 1080, name: "Swords, Straight Two-Handed", levelupMessage:"is better with straight two-handed swords"))
		skills.add(new CharacterSkill(codeName: "unarmed", order: 1100, name: "Unarmed", levelupMessage:"is more skilled at unarmed combat"))
		skills.add(new CharacterSkill(codeName: "shield_bashing", order: 1110, name: "Shield", levelupMessage:"is more skilled at bashing with shields"))
		
		skills.add(new CharacterSkill(codeName: "melee_defense", order: 2000, name: "Melee Defense", levelupMessage:"is better at melee defense", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "melee_evasion", order: 2100, name: "Melee Evasion", levelupMessage:"is better at melee evasion"))
		skills.add(new CharacterSkill(codeName: "shield_blocking", order: 2200, name: "Shield Blocking", levelupMessage:"is more skilled at defending with shields"))
		skills.add(new CharacterSkill(codeName: "weaponcatching", order: 2210, name: "Weapon Catching", levelupMessage:"can block more weapons with bare hands"))
		skills.add(new CharacterSkill(codeName: "parrying", order: 2220, name: "Parrying", levelupMessage:"is more skilled at parrying"))
		
		skills.add(new CharacterSkill(codeName: "ranged_fighting", order: 3000, name: "Ranged Fighting", levelupMessage:"is better at ranged fighting", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "bows", order: 3010, name: "Bows", levelupMessage:"is more accurate with bows"))
		skills.add(new CharacterSkill(codeName: "crossbows", order: 3020, name: "Crossbows", levelupMessage:"is more accurate with crossbows"))
		skills.add(new CharacterSkill(codeName: "blowguns", order: 3030, name: "Blowguns", levelupMessage:"is more accurate with bowguns"))
		skills.add(new CharacterSkill(codeName: "throwing_weapons", order: 3030, name: "Throwing Weapons", levelupMessage:"is more skilled at throwing weapons"))
		skills.add(new CharacterSkill(codeName: "firearms", order: 3030, name: "Firearms", levelupMessage:"is more skilled with firearms"))
		
		skills.add(new CharacterSkill(codeName: "ranged_defense", order: 4000, name: "Ranged Defense", levelupMessage:"is better with ranged defense", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "ranged_evasion", order: 4100, name: "Ranged Evasion", levelupMessage:"can evade more ranged attacks"))
		skills.add(new CharacterSkill(codeName: "missile_deflecting", order: 4200, name: "Missile Deflecting", levelupMessage:"is more skilled at deflecting missiles"))
		skills.add(new CharacterSkill(codeName: "missile_catching", order: 4210, name: "Missile Catching", levelupMessage:"can catch more missiles with bare hands"))
		
		skills.add(new CharacterSkill(codeName: "socializing", order: 10000, name: "Socializing", levelupMessage:"is more skilled at socializing", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "gambling", order: 10100, name: "Gambling", levelupMessage:"is luckier at gambling"))
		skills.add(new CharacterSkill(codeName: "streetwise", order: 11000, name: "Streetwise", levelupMessage:"is more streetwise"))
		skills.add(new CharacterSkill(codeName: "etiquette", order: 12000, name: "Etiquette", levelupMessage:"possesses more etiquette"))
		
		skills.add(new CharacterSkill(codeName: "education", order: 20000, name: "Education", levelupMessage:"is more educated", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "ancient_languages", order: 20100, name: "Ancient Languages", levelupMessage:"is more knowledgeable about ancient languages"))
		skills.add(new CharacterSkill(codeName: "alchemy", order: 21000, name: "Alchemy", levelupMessage:"has unlocked more secrets of alchemy"))
		
		skills.add(new CharacterSkill(codeName: "supernatural", order: 30000, name: "Supernatural", levelupMessage:"knows more about the supernatural", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "exorcism", order: 30100, name: "Exorcism", levelupMessage:"is more consistent with exorcism"))
		
		skills.add(new CharacterSkill(codeName: "outdoors", order: 40000, name: "Outdoors", levelupMessage:"is more skilled with the outdoors", skillDifficulty: 5))
		skills.add(new CharacterSkill(codeName: "woodwise", order: 41000, name: "Woodwise", levelupMessage:"is more woodwise"))
		
		skills.add(new CharacterSkill(codeName: "crafting", order: 50000, name: "Crafting", levelupMessage:"is better at crafting", skillDifficulty: 5))
		
		skills = skills.sort()
		return skills
	}
	
	public void addValue(int valueToAdd) {
		this.value += valueToAdd
	}
	
	// Skills may be slower to increase because many skills may gain experience at once
	public boolean addExp(int expToAdd) {
		boolean levelupHappened = false
		exp += expToAdd
		while (exp > (value * skillDifficulty)) {
			exp -= (value * skillDifficulty)
			value += 1
			levelupHappened = true
		}
		return levelupHappened
	}
	
	public boolean addExp() {
		return addExp(1)
	}
}
