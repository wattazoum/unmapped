package patrickwong.unmapped.model

public class CharacterSkill implements Comparable<CharacterSkill> {
	String name = "default skill name"
	String codeName = "DSN"
	long value = 0
	long exp = 0
	int order = 0
	
	@Override
	public int compareTo(CharacterSkill skill) {
		return this.order.compareTo(skill.order)
	}
	
	public static List<CharacterSkill> getDefaultSkills() {
		List<CharacterSkill> skills = new Vector<CharacterSkill>()
		
		skills.add(new CharacterSkill(codeName: "fighting", order: 0, name: "Fighting"))
		
		skills.add(new CharacterSkill(codeName: "melee_fighting", order: 1000, name: "Melee Fighting"))
		skills.add(new CharacterSkill(codeName: "axes", order: 1010, name: "Axes"))
		skills.add(new CharacterSkill(codeName: "blunt_weapons", order: 1020, name: "Blunt Weapons"))
		skills.add(new CharacterSkill(codeName: "polearms", order: 1030, name: "Polearms"))
		skills.add(new CharacterSkill(codeName: "short_weapons", order: 1040, name: "Short Weapons"))
		skills.add(new CharacterSkill(codeName: "spears", order: 1050, name: "Spears"))
		skills.add(new CharacterSkill(codeName: "swords_slashing", order: 1060, name: "Swords, Slashing"))
		skills.add(new CharacterSkill(codeName: "swords_straight_one", order: 1070, name: "Swords, Straight One-Handed"))
		skills.add(new CharacterSkill(codeName: "swords_straight_two", order: 1080, name: "Swords, Straight Two-Handed"))
		skills.add(new CharacterSkill(codeName: "unarmed", order: 1100, name: "Unarmed"))
		
		skills.add(new CharacterSkill(codeName: "melee_defense", order: 2000, name: "Melee Defense"))
		skills.add(new CharacterSkill(codeName: "melee_evasion", order: 2100, name: "Melee Evasion"))
		skills.add(new CharacterSkill(codeName: "shields", order: 2100, name: "Shields"))
		
		skills.add(new CharacterSkill(codeName: "ranged_fighting", order: 3000, name: "Ranged Fighting"))
		skills.add(new CharacterSkill(codeName: "bows", order: 3010, name: "Bows"))
		skills.add(new CharacterSkill(codeName: "crossbows", order: 3020, name: "Crossbows"))
		skills.add(new CharacterSkill(codeName: "blowguns", order: 3030, name: "Blowguns"))
		skills.add(new CharacterSkill(codeName: "throwing_weapons", order: 3030, name: "Throwing Weapons"))
		skills.add(new CharacterSkill(codeName: "firearms", order: 3030, name: "Firearms"))
		
		skills.add(new CharacterSkill(codeName: "ranged_defense", order: 4000, name: "Ranged Defense"))
		skills.add(new CharacterSkill(codeName: "ranged_evasion", order: 4100, name: "Ranged Evasion"))
		
		skills.add(new CharacterSkill(codeName: "socializing", order: 10000, name: "Socializing"))
		skills.add(new CharacterSkill(codeName: "gambling", order: 10100, name: "Gambling"))
		skills.add(new CharacterSkill(codeName: "streetwise", order: 11000, name: "Streetwise"))
		skills.add(new CharacterSkill(codeName: "etiquette", order: 12000, name: "Etiquette"))
		
		skills.add(new CharacterSkill(codeName: "education", order: 20000, name: "Education"))
		skills.add(new CharacterSkill(codeName: "ancient_languages", order: 20100, name: "Ancient Languages"))
		skills.add(new CharacterSkill(codeName: "alchemy", order: 21000, name: "Alchemy"))
		
		skills.add(new CharacterSkill(codeName: "supernatural", order: 30000, name: "Supernatural"))
		skills.add(new CharacterSkill(codeName: "exorcism", order: 30100, name: "Exorcism"))
		
		skills.add(new CharacterSkill(codeName: "outdoors", order: 40000, name: "Outdoors"))
		skills.add(new CharacterSkill(codeName: "woodwise", order: 41000, name: "Woodwise"))
		
		skills.add(new CharacterSkill(codeName: "crafting", order: 50000, name: "Crafting"))
		
		skills = skills.sort()
		return skills
	}
	
	public void addValue(int valueToAdd) {
		this.value += valueToAdd
	}
	
	// Skills are much slower to increase because many skills may gain experience at once
	public void addExp(int expToAdd) {
		exp += expToAdd
		while (exp > (value * 10)) {
			exp -= (value * 10)
			value += 1
		}
	}
	
	public void addExp() {
		addExp(1)
	}
}
