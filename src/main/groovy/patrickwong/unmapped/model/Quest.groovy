package patrickwong.unmapped.model

public class Quest implements Comparable {
	String key
	List<QuestPhase> phases = new Vector<QuestPhase>()
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Quest) {
			Quest questToEqual = (Quest)o
			return key.equalsIgnoreCase(questToEqual.key)
		} else {
			return key.equalsIgnoreCase(o)
		}
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Quest) {
			Quest questToCompare = (Quest)o
			return key.compareToIgnoreCase(questToCompare.key)
		} else {
			return key.compareToIgnoreCase(o)
		}
	}
	
	public boolean hasPhase(String key) {
		phases.each {
			if (it.key.equalsIgnoreCase(key)) {
				return true
			}
		}
		return false
	}
	
	public QuestPhase getPhase(String key) {
		phases.each {
			if (it.key.equalsIgnoreCase(key)) {
				return it
			}
		}
		return null
	}
}
