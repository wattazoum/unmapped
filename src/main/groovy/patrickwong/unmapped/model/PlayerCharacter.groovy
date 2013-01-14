package patrickwong.unmapped.model

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Combatant
import patrickwong.unmapped.combat.DefaultCombatUtil
import patrickwong.unmapped.equipment.GameItem


public class PlayerCharacter implements Comparable, Combatant {
	Integer order = 0
	String name = new String("Default Name")
	String gender = "male"
	String background = "default background"
	String childhood = "default childhood"
	String teenage = "default teenage"
	String adulthood = "default adulthood"
	String firstJob = "default first job"
	String hobby = "default hobby"
	Boolean usingRangedWeapon = false
	
	Integer shock = 0
	Integer pain = 0
	Integer wounds = 0
	
	List<CharacterStat> stats = CharacterStat.getDefaultStats()
	List<CharacterSkill> skills = CharacterSkill.getDefaultSkills()
	List<GameItem> inventory = new Vector<GameItem>()
	
	public String summaryString() {
		String summaryString = """${name}
${gender}
Background: ${background}
Childhood: ${childhood}
Teenage: ${teenage}
Adulthood: ${adulthood}
First Job: ${firstJob}
Hobby: ${hobby}"""
		summaryString += "\n\n"
		summaryString += getStatus()
	}
	
	public void statAddValue(String shortName, Integer amount) {
		CharacterStat stat = getStat(shortName)
		stat.value += amount
	}
	
	public void statAddExp(String shortName, Integer amount) {
		UnmappedMain.log.debug("statAddExp(" + shortName + ", "+ amount + ")")
		CharacterStat stat = getStat(shortName)
		stat.addExp(amount)
	}
	
	public void statAddExp(String shortName) {
		statAddExp(shortName, 1)
	}
	
	public void statsAddExp(String[] shortNames, Integer amount) {
		for (String shortName : shortNames) {
			statAddExp(shortName, amount)
		}
	}
	
	public void statsAllAddExp(Integer amount) {
		for (CharacterStat stat : stats) {
			stat.addExp(amount)
		}
	}
	
	public CharacterStat getStat(String shortName) {
		for (CharacterStat characterStat : stats) {
			if (shortName.equalsIgnoreCase(characterStat.shortName)) {
				return characterStat
			}
		}
		return null
	}
	
	public Integer getStatValue(String shortName) {
		return getStat(shortName).getValue()
	}
	
	public Integer rollStat(String shortName) {
		statAddExp(shortName)
		return DiceRoller.binaryPool(getStatValue(shortName))
	}
	
	public CharacterSkill getSkill(String codeName) {
		for (CharacterSkill skill : skills) {
			if (codeName.equalsIgnoreCase(skill.codeName)) {
				return skill
			}
		}
		return null
	}
	
	public Integer getSkillValue(String codeName) {
		CharacterSkill skill = getSkill(codeName)
		if (skill != null) {
			return skill.value
		}
		return 0
	}
	
	public void skillAddExp(String codeName, Integer amount) {
		CharacterSkill skill = getSkill(codeName)
		if (skill != null) {
			skill.addExp(amount)
		}
	}
	
	public void skillsAddExp(String[] codeNames, Integer amount) {
		for (String codeName : codeNames) {
			skillAddExp(codeName)
		}
	}
	
	public void skillsAllAddExp(Integer amount) {
		for (CharacterSkill skill : skills) {
			skill.addExp(amount)
		}
	}
	
	public Integer rollSkill(String codeName) {
		return DiceRoller.binaryPool(getSkillValue(codeName))
	}
	
	public GameItem getItem(String key) {
		for (GameItem gi : inventory) {
			if (key.equalsIgnoreCase(gi.key)) {
				return gi
			}
		}
		return null
	}
	public void addItem(GameItem gi) {
		GameItem possibleMatch = getItem(gi.key)
		if (possibleMatch != null) {
			if (possibleMatch.stackable) {
				UnmappedMain.log.info("adding to item stack")
				possibleMatch.stackSize += 1
			} else {
				inventory.add(gi)
			}
		} else {
			inventory.add(gi)
		}
		inventory = inventory.sort()
	}
	public void removeItem(String key) {
		GameItem gi = getItem(key)
		if (gi != null) {
			if (gi.stackable && (gi.stackSize > 1)) {
				gi.stackSize -= 1
			} else {
				inventory.remove(gi)
			}
		}
	}
	
	public String getAttackVerb() {
		String attackString = " attacks "
		return attackString
	}
	
	public int compareTo(Object o) {
		if (o instanceof PlayerCharacter) {
			return order.compareTo(((PlayerCharacter)o).getOrder())
		}
		return order.compareTo(o)
	}
	
	public int compareTo(PlayerCharacter pc) {
		return order.compareTo(pc.order)
	}
	
	public Integer rollAttackAccuracy() {
		int result = 0
		result += rollStat("DEX")
		result += rollSkill("fighting")
		result += rollSkill("melee_fighting")
		result += rollSkill("unarmed")
		return result
	}
	
	public Integer rollAttackDamage() {
		int result = 0
		result += rollStat("STR")
		return result
	}
	
	public String getAttackDamageType() {
		return "impact"
	}
	
	public Integer rollMeleeEvade() {
		int result = 0
		result += rollStat("AGI")
		result += rollSkill("fighting")
		result += rollSkill("melee_defense")
		result += rollSkill("melee_evasion")
		return result
	}
	public Integer rollRangeEvade() {
		int result = 0
		result += rollStat("AGI")
		result += rollSkill("fighting")
		result += rollSkill("ranged_defense")
		result += rollSkill("ranged_evasion")
		return result
	}
	public void addShock(Integer shock) {
		this.shock += shock
	}
	public void addPain(Integer pain) {
		this.pain += pain
	}
	public void addWounds(Integer wounds) {
		this.wounds += wounds
	}
	public Integer getShock() {
		return shock
	}
	
	public Integer getPain() {
		return pain
	}
	
	public Integer getWounds() {
		return wounds
	}
	public void removeShock(Integer shock) {
		this.shock -= shock
		if (this.shock < 0) {
			this.shock = 0
		}
	}
	public void removePain(Integer pain) {
		this.pain -= pain
		if (this.pain < 0) {
			this.pain = 0
		}
	}
	public void removeWounds(Integer wounds) {
		this.wounds -= wounds
		if (this.wounds < 0) {
			this.wounds = 0
		}
	}
	public Integer getCuttingResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		return resistance;
	}
	public Integer getPiercingResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		return resistance;
	}
	public Integer getImpactResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		return resistance;
	}
	public Integer getBulletResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		return resistance;
	}
	public Integer getElementalResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		return resistance;
	}
	
	public void addCuttingDamage(Integer damage) {
		DefaultCombatUtil.addCuttingDamage(damage, this);
	}
	public void addPiercingDamage(Integer damage) {
		DefaultCombatUtil.addPiercingDamage(damage, this);
	}
	public void addImpactDamage(Integer damage) {
		DefaultCombatUtil.addImpactDamage(damage, this);
	}
	public void addBulletDamage(Integer damage) {
		DefaultCombatUtil.addBulletDamage(damage, this);
	}
	public void addElementalDamage(Integer damage) {
		DefaultCombatUtil.addElementalDamage(damage, this);
	}
	public String getStatus() {
		return DefaultCombatUtil.getStatus(this)
	}
	public String getKilledString() {
		if (gender.equalsIgnoreCase("male")) {
			return ", killing him!"
		} else if (gender.equalsIgnoreCase("female")) {
			return ", killing her!"
		} else if (gender.equalsIgnoreCase("plural")) {
			return ", killing them!"
		} else {
			return ", killing it!"
		}
	}
	public void beginRoundLogic() {
		shock += pain
	}
	public void midRoundLogic() {
		shock -= getStatValue("TGH")
		if (shock < 0) {
			shock = 0
		}
	}
	public void endRoundLogic() {
		
	}
	
	public boolean isDead() {
		return (wounds > getStatValue("HLT"))
	}
}
