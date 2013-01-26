package patrickwong.unmapped.model

public class QuestPhase implements Comparable {
	String key
	int order
	String description
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof QuestPhase) {
			QuestPhase possibleMatch = (QuestPhase)o
			return key.equalsIgnoreCase(possibleMatch.key)
		} else {
			return key.equalsIgnoreCase(o)
		}
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof QuestPhase) {
			QuestPhase phaseToCompare = (QuestPhase)o
			return order.compareTo(phaseToCompare.order)
		} else {
			return order.compareTo(o)
		}
	}
}
