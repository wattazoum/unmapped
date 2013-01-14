package patrickwong.unmapped.model

import groovy.xml.MarkupBuilder
import patrickwong.unmapped.equipment.GameItem

public class XmlExporter {
	public static String makeXml() {
		GameState gs = GameState.getInstance()
		PlayerCharacter tc = gs.getTempCharacter()
		def xmlBuilder = new MarkupBuilder()
		
		String xml = xmlBuilder.gameState(partyMoney: gs.partyMoney,
			currentLocation: gs.currentLocation,
			currentRegion: gs.currentRegion,
			currentTime: gs.currentTime
		) {
			tempCharacter(order: tc.order, name: tc.name, gender: tc.gender, background: tc.background, childhood: tc.childhood, teenage: tc.teenage, adulthood: tc.adulthood, firstJob: tc.firstJob, hobby: tc.hobby) {
				for (CharacterStat st : tc.stats) {
					stat(shortName: st.shortName, value: st.value, exp: st.exp)
				}
				for (CharacterSkill sk : tc.skills) {
					skill(codeName: sk.codeName, value: sk.value, exp: sk.exp)
				}
			}
			for (PlayerCharacter pc : gs.party) {
				playerCharacter(order: pc.order, name: pc.name, gender: pc.gender, background: pc.background, childhood: pc.childhood, teenage: pc.teenage, adulthood: pc.adulthood, firstJob: pc.firstJob, hobby: pc.hobby) {
					for (CharacterStat st : pc.stats) {
						stat(shortName: st.shortName, value: st.value, exp: st.exp)
					}
					for (CharacterSkill sk : pc.skills) {
						skill(codeName: sk.codeName, value: sk.value, exp: sk.exp)
					}
					for (GameItem gi : pc.inventory) {
						item(key: gi.key, stackSize: gi.stackSize)
					}
				}
			}
		}
		
		return xml
	}
}
