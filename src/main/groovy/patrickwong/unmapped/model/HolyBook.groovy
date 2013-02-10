package patrickwong.unmapped.model

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.Enemy
import patrickwong.unmapped.model.equipment.ItemCombatAction
import patrickwong.unmapped.model.equipment.ItemFieldAction

public class HolyBook implements Comparable {
	String key
	String shortName // for UI purposes
	String fullName
	String description
	ItemCombatAction actionInCombat = null
	ItemFieldAction actionInField = null
	
	public boolean usableInCombat() {
		return (actionInCombat != null)
	}
	
	public boolean usableInField() {
		return (actionInField != null)
	}
	
	public String useInField(PlayerCharacter user) {
		if (!usableInField()) {
			return null
		}
		return actionInField.useInField(user);
	}
	
	public String useInCombat(PlayerCharacter user, CombatState state) {
		if (!usableInCombat()) {
			return null
		}
		return actionInCombat.useInCombat(user, state)
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof HolyBook) {
			HolyBook bookToCompare = (HolyBook)o
			return key.compareToIgnoreCase(bookToCompare.key)
		} else {
			return key.compareToIgnoreCase(o)
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof HolyBook) {
			HolyBook bookToCompare = (HolyBook)o
			return key.equalsIgnoreCase(bookToCompare.key)
		} else {
			return key.compareToIgnoreCase(o)
		}
	}
	
	public static final Map<String, HolyBook> holyBooks = [
		"compassion":new HolyBook(key:"compassion",shortName:"Compassion",
			fullName: "Book of Compassion",
			description: ""
		),
		"downtrodden":new HolyBook(key:"downtrodden",shortName:"Downtrodden",
			fullName: "Book of the Downtrodden",
			description: ""
		),
		"equality":new HolyBook(key:"equality",shortName:"Equality",
			fullName: "Book of Equality",
			description: ""
		),
		"fellowship":new HolyBook(key:"fellowship",shortName:"Fellowship",
			fullName: "Book of Fellowship",
			description: ""
		),
		"foresight":new HolyBook(key:"foresight",shortName:"Foresight",
			fullName: "Book of Foresight",
			description: ""
		),
		"hindsight":new HolyBook(key:"hindsight",shortName:"Hindsight",
			fullName: "Book of Hindsight",
			description: ""
		),
		"priesthood":new HolyBook(key:"priesthood",shortName:"Priesthood",
			fullName: "Book of Priesthood",
			description: ""
		),
		"marriage":new HolyBook(key:"monogamy",shortName:"Monogamy",
			fullName: "Book of Monogamy",
			description: ""
		),
		"ecosystems":new HolyBook(key:"ecosystems",shortName:"Ecosystems",
			fullName: "Book of Ecosystems",
			description: ""
		),
		"judgement":new HolyBook(key:"judgement",shortName:"Judgement",
			fullName: "Book of Judgement",
			description: ""
		),
		"intercession":new HolyBook(key:"intercession",shortName:"Intercession",
			fullName: "Book of Intercession",
			description: ""
		),
		"mercy":new HolyBook(key:"mercy",shortName:"Mercy",
			fullName: "Book of Mercy",
			description: ""
		),
		"patience":new HolyBook(key:"patience",shortName:"Patience",
			fullName: "Book of Patience",
			description: ""
		),
		"hiddenmeaning":new HolyBook(key:"hiddenmeaning",shortName:"Hidden Meaning",
			fullName: "Book of Hidden Meaning",
			description: ""
		),
		"lord":new HolyBook(key:"lord",shortName:"Lord",
			fullName: "Book of the Lord",
			description: ""
		),
		"perseverance":new HolyBook(key:"perseverance", shortName:"Perseverance",
			fullName: "Book of Perseverance",
			description: "It can only be used out of combat.\nIt removes wounds from the user,\nbut adds incredible pain instead.",
			actionInField: { PlayerCharacter user ->
				if (user.isDead()) {
					return user.name + " is dead and cannot\ninvoke the Book of Perseverance."
				} else {
					user.removeWounds(DiceRoller.binaryPool(20))
					user.addPain(DiceRoller.binaryPool(60))
					return (user.name + " has some wounds removed\nbut receives great pain")
				}
			} as ItemFieldAction
		),
		"rolereversal":new HolyBook(key:"rolereversal",shortName:"Role Reversal",
			fullName: "Book of Role Reversal",
			description: ""
		),
		"resurrection":new HolyBook(key:"resurrection",shortName:"Resurrection",
			fullName: "Book of Resurrection",
			description: "It can only be used out of combat.\nIt heals the 'user' just enough to\nbe brought back from death, but\ninflicts great wounds on the rest\nof the party.",
			actionInField: { PlayerCharacter user ->
				if (user.isDead()) {
					GameState.getInstance().party.each {
						if (!(it.isDead()) || it.name.equalsIgnoreCase(user.name)) {
							it.removeWounds(10000)
							it.addWounds(user.getStatValue("HLT") - 1)
						}
					}
					return user.name + " is resurrected\nBut, the remaining party has suffered great wounds."
				} else {
					return user.name + " does not need to be resurrected."
				}
			} as ItemFieldAction
		),
		"hurricane":new HolyBook(key:"hurricane",shortName:"Hurricane",
			fullName: "Book of the Hurricane",
			description: "In combat, it summons a mighty wind that can blow enemies off their feet\nbut it also hits the user",
			actionInCombat: { PlayerCharacter user, CombatState state ->
				int result = user.rollStat("SIX") + user.rollStat("LOG")
				result = (result / 2)
				result += user.rollSkill("supernatural")
				result = (result / 5)
				List<Enemy> enemies = state.getAllEnemies()
				enemies.each {
					it.addImpactDamage(result)
				}
				user.addImpactDamage(result * 2)
				return (user.name + " invokes the Book of the Hurricane!")
			} as ItemCombatAction
		),
		"earthquake":new HolyBook(key:"earthquake",shortName:"Earthquake",
			fullName: "Book of the Earthquake",
			description: ""
		),
		"tsunami":new HolyBook(key:"tsunami",shortName:"Tsunami",
			fullName: "Book of the Tsunami",
			description: "In combat, it pushes all enemies further away but it also\ncrushes the whole party with water pressure.",
			actionInCombat: { PlayerCharacter user, CombatState state ->
				state.enemyGroups.each {
					it.distance += 2
				}
				GameState.getInstance().party.each {
					it.addImpactDamage(DiceRoller.binaryPool(120))
				}
				return (user.name + " invokes the Book of the Tsunami!")
			} as ItemCombatAction
		)
	]
}
