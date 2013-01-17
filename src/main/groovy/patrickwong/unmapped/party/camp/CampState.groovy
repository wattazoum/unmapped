package patrickwong.unmapped.party.camp

import patrickwong.unmapped.UnmappedMain

import com.googlecode.lanterna.gui.Action

public class CampState {
	String campTitle = "default camp"
	String campDesc = "this looks like a nice place to lay down"
	Action endCampAction = null
	List<CampDecision> decisions = new Vector<CampDecision>()
	List<String> amenities = new Vector<String>()
	Closure specialNote = null
	Closure onEndDay = null
	Closure canContinueStaying = null
	Closure onKickedOut = null
	
	public CampDecision getDecision(String charName) {
		for (CampDecision decision : decisions) {
			if (decision.charName.equalsIgnoreCase(charName)) {
				UnmappedMain.log.debug("existing camp decision found for $charName")
				return decision
			}
		}
		UnmappedMain.log.debug("camp decision not found for $charName - returning null")
		return null
	}
	
	public void setDecision(CampDecision decision) {
		CampDecision decisionToRemove = getDecision(decision.charName)
		if (decisionToRemove != null) {
			UnmappedMain.log.debug("removing old camp decision for " + decision.charName)
			decisions.remove(decisionToRemove)
		}
		decisions.add(decision)
	}
	
	public boolean hasAmenity(String key) {
		for (String amenity : amenities) {
			if (key.equalsIgnoreCase(amenity)) {
				return true
			}
		}
		return false
	}
}
