package patrickwong.unmapped.model

import patrickwong.unmapped.UnmappedMain


public class CharacterStat implements Comparable<CharacterStat> {
	String name = "default stat name"
	String shortName = "DSN"
	long value = 0
	long exp = 0
	int order = 0
	
	public void addExp(int amount) {
		UnmappedMain.log.debug("addExp(" + amount + ")")
		this.exp += amount
		while (this.exp > this.value) {
			UnmappedMain.log.debug("incrementing $shortName")
			this.exp = this.exp - this.value
			this.value += 1
			UnmappedMain.log.debug("value is now $value")
		}
	}
	
	@Override
	public int compareTo(CharacterStat characterStat) {
		return this.order.compareTo(characterStat.getOrder())
	}
	
	public static List<CharacterStat> getDefaultStats() {
		List<CharacterStat> defaultStats = new Vector<CharacterStat>()
		
		defaultStats.add(new CharacterStat(name: "Strength", shortName: "STR", order: 0))
		defaultStats.add(new CharacterStat(name: "Toughness", shortName: "TGH", order: 1))
		defaultStats.add(new CharacterStat(name: "Agility", shortName: "AGI", order: 2))
		defaultStats.add(new CharacterStat(name: "Dexterity", shortName: "DEX", order: 3))
		defaultStats.add(new CharacterStat(name: "Endurance", shortName: "END", order: 4))
		defaultStats.add(new CharacterStat(name: "Health", shortName: "HLT", order: 5))
		
		defaultStats.add(new CharacterStat(name: "Wittiness", shortName: "WIT", order: 6))
		defaultStats.add(new CharacterStat(name: "Reflexes", shortName: "REF", order: 7))
		defaultStats.add(new CharacterStat(name: "Logical", shortName: "LOG", order: 8))
		defaultStats.add(new CharacterStat(name: "Memory", shortName: "MEM", order: 9))
		defaultStats.add(new CharacterStat(name: "Verbal", shortName: "VER", order: 10))
		defaultStats.add(new CharacterStat(name: "Sixth Sense", shortName: "SIX", order: 11))
		
		defaultStats = defaultStats.sort()
		
		return defaultStats
	}
}
