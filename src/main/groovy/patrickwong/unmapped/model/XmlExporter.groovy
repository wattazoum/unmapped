package patrickwong.unmapped.model

import groovy.xml.MarkupBuilder
import patrickwong.unmapped.model.equipment.GameItem
import patrickwong.unmapped.model.equipment.Grippable

public class XmlExporter {
	public static String makeXml() {
		GameState gs = GameState.getInstance()
		PlayerCharacter tc = gs.getTempCharacter()
		// NOTE - it is very important that the MarkupBuilder is using a StringWriter for my purposes
		// Otherwise, the MarkupBuilder will print directly to console and leave nothing behind
		def writer = new StringWriter()
		def xmlBuilder = new MarkupBuilder(writer)
		
		xmlBuilder.gameState(
			gameName: gs.gameName,
			partyMoney: gs.partyMoney,
			currentLocation: gs.currentLocation,
			currentRegion: gs.currentRegion
		) {
			for (Quest qu : gs.quests) {
				quest(key: qu.key) {
					for (QuestPhase qp : qu.phases) {
						questPhase(key: qp.key, order: qp.order, description: qp.description)
					}
				}
			}
			for (String hb : gs.holyBooks) {
				holybook(key: hb)
			}
			for (PlayerCharacter pc : gs.party) {
				playerCharacter(order: pc.order, name: pc.name, gender: pc.gender, background: pc.background, childhood: pc.childhood, teenage: pc.teenage, adulthood: pc.adulthood, firstJob: pc.firstJob, hobby: pc.hobby, description: pc.description) {
					for (CharacterStat st : pc.stats) {
						stat(shortName: st.shortName, value: st.value, exp: st.exp)
					}
					for (CharacterSkill sk : pc.skills) {
						skill(codeName: sk.codeName, value: sk.value, exp: sk.exp)
					}
					for (GameItem gi : pc.inventory) {
						item(key: gi.key, stackSize: gi.stackSize)
					}
					for (EquipSlot es : pc.equipment) {
						if (es.isFilled()) {
							equip(slot: es.key, key: es.slot.key)
						}
					}
					if (!(pc.rightHand.key.equalsIgnoreCase(Grippable.getRightFist().key))) {
						rightHand(key: pc.rightHand.key)
					}
					if (!(pc.leftHand.key.equalsIgnoreCase(Grippable.getLeftFist().key))) {
						leftHand(key: pc.leftHand.key)
					}
					if (!(pc.rangedWeapon.key.equalsIgnoreCase(Grippable.getRockToss().key))) {
						rangedWeapon(key: pc.rangedWeapon.key)
					}
				}
			}
		}
		return writer.toString()
	}
}
