package patrickwong.unmapped.model

import patrickwong.unmapped.model.equipment.GameItem
import patrickwong.unmapped.model.equipment.ItemDatabase

public class XmlImporter {
	public static void importXml(String xml) {
		def data = new XmlParser().parseText(xml)
		GameState.wipeGameState()
		GameState state = GameState.getInstance()
		state.gameName = data.'@gameName'
		state.partyMoney = data.'@partyMoney'.toInteger()
		state.currentLocation = data.'@currentLocation'
		state.currentRegion = data.'@currentRegion'
		
		data.quest.each {
			Quest quest = new Quest(key: it.'@key')
			it.questPhase.each {
				QuestPhase phase = new QuestPhase(key: it.'@key', order: it.'@order'.toInteger(), description: it.'@description')
				quest.phases.add(phase)
			}
			quest.phases = quest.phases.sort()
			state.quests.add(quest)
		}
		state.quests = state.quests.sort()
		data.holybook.each {
			state.holyBooks.add(it.'@key')
		}
		data.playerCharacter.each {
			PlayerCharacter pc = new PlayerCharacter()
			pc.order = it.'@order'.toInteger()
			pc.name = it.'@name'
			pc.gender = it.'@gender'
			pc.background = it.'@background'
			pc.childhood = it.'@childhood'
			pc.teenage = it.'@teenage'
			pc.adulthood = it.'@adulthood'
			pc.firstJob = it.'@firstJob'
			pc.hobby = it.'@hobby'
			pc.description = it.'@description'
			
			it.stat.each {
				String shortName = it.'@shortName'
				pc.setStatValue(shortName, it.'@value'.toInteger())
				pc.setStatExp(shortName, it.'@exp'.toInteger())
			}
			it.skill.each {
				String codeName = it.'@codeName'
				pc.setSkillValue(codeName, it.'@value'.toInteger())
				pc.setSkillExp(codeName, it.'@exp'.toInteger())
			}
			it.item.each {
				String key = it.'@key'
				int stackSize = it.'@stackSize'.toInteger()
				GameItem gi = ItemDatabase.getItem(key)
				gi.stackSize = stackSize
				pc.addItemStack(gi)
			}
			pc.inventory = pc.inventory.sort()
			it.equip.each {
				String slot = it.'@slot'
				String key = it.'@key'
				pc.equipItem(ItemDatabase.getEquippableItem(key), slot)
			}
			// there can only be one rightHand or leftHand, but using the "each" feature saves a few logic checks
			it.rightHand.each {
				String key = it.'@key'
				pc.rightHand = ItemDatabase.getGrippableItem(key)
			}
			it.leftHand.each {
				String key = it.'@key'
				pc.leftHand = ItemDatabase.getGrippableItem(key)
			}
			it.rangedWeapon.each {
				String key = it.'@key'
				pc.rangedWeapon = ItemDatabase.getGrippableItem(key)
			}
			
			state.party.add(pc)
		}
		state.party = state.party.sort()
	}
}
