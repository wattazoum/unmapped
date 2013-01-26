package patrickwong.unmapped.outdoors.village

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.QuestPhase
import patrickwong.unmapped.outdoors.OutsideWindow
import patrickwong.unmapped.party.PartyMenuAction

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class VillageMeetingWindow extends Window {
	private String vt
	public VillageMeetingWindow(String villageType) {
		super("Village - Meeting Hall")
		vt = villageType
		addComponent(new Label("The village meeting hall is where\nall important announcements are made."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...accuse the elder of evil", accuseElderAction))
		addComponent(new Button("...accuse the blacksmith of evil", accuseBlacksmithAction))
		addComponent(new Button("...accuse the priest of evil", accusePriestAction))
		addComponent(new Button("...accuse everyone in the village of evil", accuseEveryoneAction))
		addComponent(new Button("...inspect your party", new PartyMenuAction(new VillageMeetingAction(vt))))
		addComponent(new Button("...return to the village", new VillageAction(vt)))
	}
	
	private static final QuestPhase beginLillith = new QuestPhase(key:"begin", order:0, description:"There is an evil being named 'Lillith'\nIt is perverse and lustful")
	private static final QuestPhase beginBaphomet = new QuestPhase(key:"begin", order:0, description:"There is an evil being named 'Baphomet'\nIt seeks to corrupt and twist religions")
	private static final QuestPhase beginSamael = new QuestPhase(key:"begin", order:0, description:"There is an evil being named 'Samael'\nIt dwells within the darkest parts of the soul")
	private static final QuestPhase beginAkuma = new QuestPhase(key:"begin", order:0, description:"There is an evil being named 'Akuma-sama'\nIt is violent and hungry for human flesh")
	
	def accuseElderAction = {
		if (vt.equalsIgnoreCase("lillith_elder")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the elder - correct", "The people gasp as the elder takes off all his clothes.\n'I just wanted to bring light into my life!' he screams.\nHe draws weapons and continues, 'Lillith curse you all!'")
			GameState.getInstance().addQuestPhase("Lillith", beginLillith)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageLillith(false))
		} else if (vt.equalsIgnoreCase("baphomet_elder")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the elder - correct", "The priest shouts, 'I should have known the elder was a heretic!'\nThe elder's head twists around as he belches blasphemies and draws weapons.")
			GameState.getInstance().addQuestPhase("Baphomet", beginBaphomet)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageBaphomet(false))
		} else if (vt.equalsIgnoreCase("samael_elder")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the elder - correct", "The elder's head starts twitching and vibrating back and forth\nviolently. Everyone else flees the scene.")
			GameState.getInstance().addQuestPhase("Samael", beginSamael)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageSamael(false))
		} else if (vt.equalsIgnoreCase("akumasama_elder")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the elder - correct", "The elder's body suddenly puffs up with muscle\nas he bites the head off one of the villagers.")
			GameState.getInstance().addQuestPhase("Akuma-sama", beginAkuma)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageAkuma(false))
		} else if (vt.equalsIgnoreCase("lillith_all") || vt.equalsIgnoreCase("baphomet_all") || vt.equalsIgnoreCase("samael_all") || vt.equalsIgnoreCase("akumasama_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the elder - wrong", "Everyone in the village looks at each other nervously, then at the elder.\nOne man says to you, 'Uhh, I just don't see it. You should leave.'")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the elder - wrong", "Everyone in the village vigorously defends the elder's honor, and\nyou are thrown out for being false accusers.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	} as Action
	
	def accuseBlacksmithAction = {
		if (vt.equalsIgnoreCase("lillith_blacksmith")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the blacksmith - correct", "A woman dripping blood between her legs steps forward\nand confirms, 'Aye, the blacksmith did this to me!'\nHe grunts and draws a weapon.")
			GameState.getInstance().addQuestPhase("Lillith", beginLillith)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageLillith(false))
		} else if (vt.equalsIgnoreCase("baphomet_blacksmith")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the blacksmith - correct", "The priest shouts, 'So that is why he never attended mass!'\nThe blacksmith draws weapons and snarls.")
			GameState.getInstance().addQuestPhase("Baphomet", beginBaphomet)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageBaphomet(false))
		} else if (vt.equalsIgnoreCase("samael_blacksmith")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the blacksmith - correct", "The blacksmith starts hyperventilating and breathing like a beast.\nEveryone else runs away terrified.")
			GameState.getInstance().addQuestPhase("Samael", beginSamael)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageSamael(false))
		} else if (vt.equalsIgnoreCase("akumasama_blacksmith")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the blacksmith - correct", "The blacksmith pulls out a knobbed metal club and knocks away\nother villagers who try to stop him. The rest flee in terror.")
			GameState.getInstance().addQuestPhase("Akuma-sama", beginAkuma)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageAkuma(false))
		} else if (vt.equalsIgnoreCase("lillith_all") || vt.equalsIgnoreCase("baphomet_all") || vt.equalsIgnoreCase("samael_all") || vt.equalsIgnoreCase("akumasama_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the blacksmith - wrong", "Everyone in the village protests, 'How could the blacksmith be evil?'\nThe elder states, 'He is such a nice guy, what is wrong?'\nYou are forced to leave the village.")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the blacksmith - wrong", "Everyone in the village vigorously defends the blacksmith's honor, and\nyou are thrown out for being false accusers.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	} as Action
	
	def accusePriestAction = {
		if (vt.equalsIgnoreCase("lillith_priest")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the priest - correct", "The priest rationalizes, 'The pope said it was okay!'\nThe other villagers will have none of it as they prepare to cast stones.\nThe priest then draws weapons.")
			GameState.getInstance().addQuestPhase("Lillith", beginLillith)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageLillith(false))
		} else if (vt.equalsIgnoreCase("baphomet_priest")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the priest - correct", "The elder wonders, 'How could our priest fall to such evil?'\nThe priest explains, 'There is secret knowledge not in the holy texts!'\nHe draws weapons and shoos out the other villagers.")
			GameState.getInstance().addQuestPhase("Baphomet", beginBaphomet)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageBaphomet(false))
		} else if (vt.equalsIgnoreCase("samael_priest")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the priest - correct", "The priest's head explodes, leaving some kind of\nstrange organic growth that bellows fog.\nThe blood-spattered villagers run away screaming.")
			GameState.getInstance().addQuestPhase("Samael", beginSamael)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageSamael(false))
		} else if (vt.equalsIgnoreCase("akumasama_priest")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the priest - correct", "'Oh Akuma-sama, give me strength!' screams the priest\nas his vestments are torn away by his inflating muscles.")
			GameState.getInstance().addQuestPhase("Akuma-sama", beginAkuma)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageAkuma(false))
		} else if (vt.equalsIgnoreCase("lillith_all") || vt.equalsIgnoreCase("baphomet_all") || vt.equalsIgnoreCase("samael_all") || vt.equalsIgnoreCase("akumasama_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the priest - wrong", "'The priest? Surely not the priest,' says one villager.\nAnother villager adds, 'The priest is holy and good. Right?'\nThey all agree, 'Right. You are wrong and you must leave now.'")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing the priest - wrong", "The priest breaks down in tears at the false accusation.\nThe elder tells you, 'Such defamation is surely not decent human behavior!'\nYou are shunned from this village.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	} as Action
	
	def accuseEveryoneAction = {
		if (vt.equalsIgnoreCase("lillith_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - correct", "The whole village takes up weapons as they ask you,\n'How do you not see the beauty of Lillith's ways?'")
			GameState.getInstance().addQuestPhase("Lillith", beginLillith)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageLillith(true))
		} else if (vt.equalsIgnoreCase("baphomet_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - correct", "Random villagers start sprouting ram horns and goat legs\nas the priest cuts his wrists to let out vile black ooze.\nThe elder smiles and offers, 'Let Baphomet embrace you!'")
			GameState.getInstance().addQuestPhase("Baphomet", beginBaphomet)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageBaphomet(true))
		} else if (vt.equalsIgnoreCase("samael_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - correct", "The village peels away and decays, revealing that the area was\nactually a bloody metal grate with fog pouring out from below.\nThe villagers were actually indescribable monsters.\nThe creature formerly known as the elder says,\n'Your blood shall resurrect Samael.'")
			GameState.getInstance().addQuestPhase("Samael", beginSamael)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageSamael(true))
		} else if (vt.equalsIgnoreCase("akumasama_all")) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - correct", "You narrowly avoid an axe flying by as the elder shouts,\n'Oh Akuma-sama! Forgive us for letting these fools\nlive too long!'")
			GameState.getInstance().addQuestPhase("Akuma-sama", beginAkuma)
			InterfaceState.nextWindow = new CombatWindow(new CombatStateVillageAkuma(true))
		} else if (vt.equalsIgnoreCase("lillith_elder") || vt.equalsIgnoreCase("baphomet_elder") || vt.equalsIgnoreCase("samael_elder") || (vt.equalsIgnoreCase("akumasama_elder"))) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - wrong", "The villagers shrug and say, 'Uhh... what?'\nThe elder adds, 'Yeah... hahahaha... totally wrong... heh heh heh...'\nOne villager finally says, 'You should leave.'")
			InterfaceState.nextWindow = new OutsideWindow()
		} else if (vt.equalsIgnoreCase("lillith_blacksmith") || vt.equalsIgnoreCase("baphomet_blacksmith") || vt.equalsIgnoreCase("samael_blacksmith") || (vt.equalsIgnoreCase("akumasama_blacksmith"))) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - wrong", "The villagers shrug and say, 'Uhh... what?'\nThe blacksmith looks around, but says nothing.\nOne villager finally says, 'You should leave.'")
			InterfaceState.nextWindow = new OutsideWindow()
		} else if (vt.equalsIgnoreCase("lillith_priest") || vt.equalsIgnoreCase("baphomet_priest") || vt.equalsIgnoreCase("samael_priest") || (vt.equalsIgnoreCase("akumasama_priest"))) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - wrong", "The villagers shrug and say, 'Uhh... what?'\nThe priest notes, 'Especially not me! Because I'm the priest!'\nOne villager finally says, 'You should leave.'")
			InterfaceState.nextWindow = new OutsideWindow()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Accusing everyone - wrong", "Everyone just stares at you blankly in disbelief.\nWhile some have grievances, they are totally mundane.\nYou are not welcome here ever again.")
			InterfaceState.nextWindow = new OutsideWindow()
		}
		UnmappedMain.closeCurrent()
	} as Action
	
}
