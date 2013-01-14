package patrickwong.unmapped.combat;

import groovy.lang.Closure;

public class CombatDecision {
	String charName;
	Closure closure;
	
	public CombatDecision() {
		
	}
	
	public CombatDecision(String charName) {
		this.charName = charName;
	}
	
	public CombatDecision(Closure c) {
		setClosure(c);
	}
	
	public CombatDecision(String charName, Closure c) {
		this.charName = charName
		setClosure(c)
	}
	
	public String doAction() {
		return this.closure.call().toString();
	}
	
	public void setClosure(Closure c) {
		this.closure = c;
	}
	
}
