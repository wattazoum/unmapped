package patrickwong.unmapped.model

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Combatant
import patrickwong.unmapped.combat.DefaultCombatUtil
import patrickwong.unmapped.model.equipment.Equippable
import patrickwong.unmapped.model.equipment.GameItem
import patrickwong.unmapped.model.equipment.Grippable

import com.googlecode.lanterna.gui.dialog.MessageBox


public class PlayerCharacter implements Comparable, Combatant {
	Integer order = 0
	String name = "Default Name"
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
	List<EquipSlot> equipment = EquipSlot.getDefaultEquipSlots()
	Grippable rightHand = Grippable.getRightFist()
	Grippable leftHand = Grippable.getLeftFist()
	boolean attackingWithRight = true
	boolean defendingWithRight = true
	
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
	
	// NOTE - FUNCTIONS FOR STATS
	
	public void statAddValue(String shortName, Integer amount) {
		CharacterStat stat = getStat(shortName)
		stat.value += amount
		if (!(UnmappedMain.suppressLevelupMessages)) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "$name improved a stat!", (name + " " + stat.levelupMessage))
		}
	}
	
	public void statAddExp(String shortName, Integer amount) {
		UnmappedMain.log.debug("statAddExp(" + shortName + ", "+ amount + ")")
		CharacterStat stat = getStat(shortName)
		if (stat.addExp(amount)) {
			if (!(UnmappedMain.suppressLevelupMessages)) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "$name improved a stat!", (name + " " + stat.levelupMessage))
			}
		}
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
	
	// NOTE - FUNCTIONS FOR SKILLS
	
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
			if (skill.addExp(amount)) {
				if (!(UnmappedMain.suppressLevelupMessages)) {
					MessageBox.showMessageBox(UnmappedMain.getGUI(), "$name improved a skill!", (name + " " + skill.levelupMessage))
				}
			}
		}
	}
	public void skillAddExp(String codeName) {
		skillAddExp(codeName, 1)
	}
	
	public void skillsAddExp(String[] codeNames, Integer amount) {
		for (String codeName : codeNames) {
			skillAddExp(codeName, amount)
		}
	}
	
	public void skillsAllAddExp(Integer amount) {
		for (CharacterSkill skill : skills) {
			if (skill.addExp(amount)) {
				if (!(UnmappedMain.suppressLevelupMessages)) {
					MessageBox.showMessageBox(UnmappedMain.getGUI(), "$name improved a skill!", (name + " " + skill.levelupMessage))
				}
			}
		}
	}
	
	public Integer rollSkill(String codeName) {
		skillAddExp(codeName)
		return DiceRoller.binaryPool(getSkillValue(codeName))
	}
	
	// NOTE - FUNCTIONS FOR INVENTORY AND EQUIPMENT
	
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
				UnmappedMain.log.debug("adding to item stack")
				possibleMatch.stackSize += 1
			} else {
				inventory.add(gi)
			}
		} else {
			inventory.add(gi)
		}
		inventory = inventory.sort()
	}
	public void addItemStack(GameItem gi) {
		GameItem possibleMatch = getItem(gi.key)
		if (possibleMatch != null) {
			if (possibleMatch.stackable) {
				UnmappedMain.log.debug("adding an item stack to another item stack")
				possibleMatch.stackSize += gi.stackSize
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
	public void removeItemStack(String key) {
		GameItem gi = getItem(key)
		inventory.remove(gi)
	}
	
	public GameItem takeItem(String key) {
		GameItem gi = getItem(key)
		if (gi != null) {
			if (gi.stackable && (gi.stackSize > 1)) {
				gi.stackSize -= 1
			} else {
				inventory.remove(gi)
			}
		}
		GameItem newItem = gi.clone()
		newItem.stackSize = 1
		return newItem
	}
	
	// NOTE - EQUIPMENT MANAGEMENT
	public List<Equippable> getEquippablesFromInventory(String slotType) {
		List<Equippable> matches = new Vector<Equippable>()
		for (GameItem gi : inventory) {
			if (gi instanceof Equippable) {
				Equippable possibleMatch = ((Equippable)gi)
				if (slotType.equalsIgnoreCase(possibleMatch.slotType)) {
					matches.add(possibleMatch)
				}
			}
		}
		if (matches.size() > 0) {
			return matches
		} else {
			return null
		}
	}
	public List<Grippable> getGrippablesFromInventory() {
		List<Grippable> matches = new Vector<Grippable>()
		for (GameItem gi : inventory) {
			if (gi instanceof Grippable) {
				matches.add(gi)
			}
		}
		if (matches.size() > 0) {
			return matches
		} else {
			return null
		}
		return matches
	}
	public List<EquipSlot> getPossibleSlots(Equippable gi) {
		List<EquipSlot> matches = new Vector<EquipSlot>()
		for (EquipSlot es : equipment) {
			if (gi.slotType.equalsIgnoreCase(es.slotType)) {
				matches.add(es)
			}
		}
		return matches.sort()
	}
	public EquipSlot getEquipSlot(String equipSlotKey) {
		for (EquipSlot slot : equipment) {
			if (equipSlotKey.equalsIgnoreCase(slot.key)) {
				return slot
			}
		}
		return null
	}
	public void unequipItem(String equipSlotKey) {
		EquipSlot equipSlot = getEquipSlot(equipSlotKey)
		if (equipSlot.slot != null) {
			addItem(equipSlot.slot)
			equipSlot.slot = null
		}
	}
	// If an item is two-handed, then the right hand puts it back in the inventory
	public void unequipRightHand() {
		if (rightHand.key.equalsIgnoreCase("right_fist")) {
			return
		} else if (rightHand.twoHanded) {
			addItem(rightHand)
			rightHand = Grippable.getRightFist()
			leftHand = Grippable.getLeftFist()
		} else {
			addItem(rightHand)
			rightHand = Grippable.getRightFist()
		}
	}
	public void unequipLeftHand() {
		if (leftHand.key.equalsIgnoreCase("left_fist")) {
			return
		} else if (leftHand.twoHanded) {
			unequipRightHand()
		} else {
			addItem(leftHand)
			leftHand = Grippable.getLeftFist()
		}
	}
	public void equipItem(Equippable item, String equipSlotKey) {
		EquipSlot equipSlot = getEquipSlot(equipSlotKey)
		unequipItem(equipSlotKey)
		equipSlot.slot = item
	}
	public void equipItemFromInventory(String itemKey, String equipSlotKey) {
		EquipSlot equipSlot = getEquipSlot(equipSlotKey)
		unequipItem(equipSlotKey)
		Equippable itemToEquip = ((Equippable)(takeItem(itemKey)))
		equipSlot.slot = itemToEquip
	}
	
	// NOTE - FUNCTIONS FOR EQUIPMENT BONUSES
	
	public int getBonusTotal(String bonusKey) {
		int bonusTotal = 0
		for (EquipSlot equipSlot : equipment) {
			if (equipSlot.slot != null) {
				if (equipSlot.slot.bonuses != null) {
					Integer possibleBonus = equipSlot.slot.bonuses.get(bonusKey)
					if (possibleBonus != null) {
						bonusTotal += possibleBonus
					}
				}
			}
		}
		return bonusTotal
	}
	
	public int rollBonuses(String bonusKey) {
		int result = 0
		result += DiceRoller.binaryPool(getBonusTotal(bonusKey))
		return result
	}
	
	// NOTE - FUNCTIONS FOR COMBAT
	
	public void chooseHandForAttack() {
		int rightHandWeight = rightHand.attackWeight
		int leftHandWeight = leftHand.attackWeight
		int total = rightHandWeight + leftHandWeight
		int rand = DiceRoller.nextInt(total)
		if (rand < rightHandWeight) {
			attackingWithRight = true
		} else {
			attackingWithRight = false
		}
	}
	
	public void chooseHandForDefense() {
		int rightHandWeight = rightHand.defenseWeight
		int leftHandWeight = leftHand.defenseWeight
		int total = rightHandWeight + leftHandWeight
		int rand = DiceRoller.nextInt(total)
		if (rand < rightHandWeight) {
			defendingWithRight = true
		} else {
			defendingWithRight = false
		}
	}
	
	public String getAttackVerb() {
		chooseHandForAttack()
		if (attackingWithRight) {
			return rightHand.getAttackVerb(this)
		} else {
			return leftHand.getAttackVerb(this)
		}
	}
	
	public Integer rollAttackAccuracy() {
		int result = 0
		if (attackingWithRight) {
			result += rightHand.rollAttackAccuracy(this);
		} else {
			result += leftHand.rollAttackAccuracy(this);
		}
		result -= shock
		return result
	}
	
	public Integer rollAttackDamage() {
		int result = 0
		if (attackingWithRight) {
			result += rightHand.rollAttackDamage(this)
		} else {
			result += leftHand.rollAttackDamage(this)
		}
		return result
	}
	
	public String getAttackDamageType() {
		if (attackingWithRight) {
			return rightHand.getAttackDamageType()
		} else {
			return leftHand.getAttackDamageType()
		}
	}
	
	public Integer rollMeleeEvade() {
		chooseHandForDefense()
		int result = 0
		result += rollStat("AGI")
		result += rollStat("REF")
		result += rollSkill("fighting")
		result += rollSkill("melee_defense")
		result += rollSkill("melee_evasion")
		result = (result / 2)
		if (defendingWithRight) {
			result += rightHand.rollMeleeDefense(this)
		} else {
			result += leftHand.rollMeleeDefense(this)
		}
		result -= shock
		return result
	}
	public Integer rollRangeEvade() {
		chooseHandForDefense()
		int result = 0
		result += rollStat("AGI")
		result += rollStat("REF")
		result += rollSkill("fighting")
		result += rollSkill("ranged_defense")
		result += rollSkill("ranged_evasion")
		result = (result / 2)
		if (defendingWithRight) {
			result += rightHand.rollRangedDefense(this)
		} else {
			result += leftHand.rollRangedDefense(this)
		}
		result -= shock
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
	public Integer rollCuttingResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		resistance += rollBonuses("resist_cutting")
		return resistance;
	}
	public Integer rollPiercingResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		resistance += rollBonuses("resist_piercing")
		return resistance;
	}
	public Integer rollImpactResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		resistance += rollBonuses("resist_impact")
		return resistance;
	}
	public Integer rollBulletResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		resistance += rollBonuses("resist_bullet")
		return resistance;
	}
	public Integer rollElementalResistance() {
		Integer resistance = 0
		resistance += rollStat("TGH")
		resistance += rollBonuses("resist_elemental")
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
		if (shock > 0) {
			shock -= rollStat("END")
		}
		if (shock < 0) {
			shock = 0
		}
	}
	public void endRoundLogic() {
		
	}
	
	public boolean isDead() {
		return (wounds > getStatValue("HLT"))
	}
	
	// NOTE - FUNCTIONS FOR CODING
	
	public int compareTo(Object o) {
		if (o instanceof PlayerCharacter) {
			return order.compareTo(((PlayerCharacter)o).getOrder())
		}
		return order.compareTo(o)
	}
	
	public int compareTo(PlayerCharacter pc) {
		return order.compareTo(pc.order)
	}
}
