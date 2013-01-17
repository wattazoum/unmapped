package patrickwong.unmapped.model

import patrickwong.unmapped.UnmappedMain


public class CharacterStat implements Comparable<CharacterStat> {
	String name = "default stat name"
	String shortName = "DSN"
	String levelupMessage = "gets better at something!"
	long value = 0
	long exp = 0
	int order = 0
	
	public boolean addExp(int amount) {
		UnmappedMain.log.debug("addExp(" + amount + ")")
		boolean levelupHappened = false
		this.exp += amount
		while (this.exp > this.value) {
			UnmappedMain.log.debug("incrementing $shortName")
			this.exp = this.exp - this.value
			this.value += 1
			UnmappedMain.log.debug("value is now $value")
			levelupHappened = true
		}
		return levelupHappened
	}
	
	@Override
	public int compareTo(CharacterStat characterStat) {
		return this.order.compareTo(characterStat.getOrder())
	}
	
	public static List<CharacterStat> getDefaultStats() {
		List<CharacterStat> defaultStats = new Vector<CharacterStat>()
		
		defaultStats.add(new CharacterStat(name: "Strength", shortName: "STR", levelupMessage:"is stronger", order: 0))
		defaultStats.add(new CharacterStat(name: "Toughness", shortName: "TGH", levelupMessage:"is tougher", order: 1))
		defaultStats.add(new CharacterStat(name: "Agility", shortName: "AGI", levelupMessage:"is more agile", order: 2))
		defaultStats.add(new CharacterStat(name: "Dexterity", shortName: "DEX", levelupMessage:"is more dextrous", order: 3))
		defaultStats.add(new CharacterStat(name: "Endurance", shortName: "END", levelupMessage:"has more endurance", order: 4))
		defaultStats.add(new CharacterStat(name: "Health", shortName: "HLT", levelupMessage:"is healthier", order: 5))
		
		defaultStats.add(new CharacterStat(name: "Wittiness", shortName: "WIT", levelupMessage:"is wittier", order: 6))
		defaultStats.add(new CharacterStat(name: "Reflexes", shortName: "REF", levelupMessage:"has better reflexes", order: 7))
		defaultStats.add(new CharacterStat(name: "Logical", shortName: "LOG", levelupMessage:"is more logical", order: 8))
		defaultStats.add(new CharacterStat(name: "Memory", shortName: "MEM", levelupMessage:"has better memory", order: 9))
		defaultStats.add(new CharacterStat(name: "Verbal", shortName: "VER", levelupMessage:"has better verbal abilities", order: 10))
		defaultStats.add(new CharacterStat(name: "Sixth Sense", shortName: "SIX", levelupMessage:"gains a sharper sixth sense", order: 11))
		
		defaultStats = defaultStats.sort()
		
		return defaultStats
	}
}
