package patrickwong.unmapped.model

import patrickwong.unmapped.model.equipment.Equippable;

public class EquipSlot implements Comparable {
	String key = "default equip slot"
	String slotType = "whatever"
	int order = 0
	Equippable slot = null
	
	public static List<EquipSlot> getDefaultEquipSlots() {
		List<EquipSlot> slots = new Vector<EquipSlot>()
		
		slots.add(new EquipSlot(key:"helmet", slotType:"helmet", order:100))
		slots.add(new EquipSlot(key:"body armor", slotType:"bodyarmor", order:101))
		slots.add(new EquipSlot(key:"over body armor", slotType:"overbodyarmor", order:102))
		slots.add(new EquipSlot(key:"left arm armor", slotType:"armarmor", order:103))
		slots.add(new EquipSlot(key:"right arm armor", slotType:"armarmor", order:104))
		slots.add(new EquipSlot(key:"leg armor", slotType:"legarmor", order:105))
		slots.add(new EquipSlot(key:"gloves", slotType:"gloves", order:106))
		slots.add(new EquipSlot(key:"boots", slotType:"boots", order:107))
		
		slots.add(new EquipSlot(key:"cloak", slotType:"cloak", order:200))
		slots.add(new EquipSlot(key:"hat", slotType:"hat", order:201))
		slots.add(new EquipSlot(key:"forehead", slotType:"forehead", order:202))
		slots.add(new EquipSlot(key:"eyewear", slotType:"eyewear", order:203))
		slots.add(new EquipSlot(key:"mouthguard", slotType:"mouthguard", order:204))
		slots.add(new EquipSlot(key:"necklace", slotType:"necklace", order:205))
		slots.add(new EquipSlot(key:"scarf", slotType:"scarf", order:206))
		slots.add(new EquipSlot(key:"tabard", slotType:"tabard", order:207))
		slots.add(new EquipSlot(key:"undershirt", slotType:"undershirt", order:208))
		slots.add(new EquipSlot(key:"shirt", slotType:"shirt", order:209))
		slots.add(new EquipSlot(key:"dress", slotType:"dress", order:210))
		slots.add(new EquipSlot(key:"jacket", slotType:"jacket", order:211))
		slots.add(new EquipSlot(key:"belt", slotType:"belt", order:212))
		slots.add(new EquipSlot(key:"shorts", slotType:"shorts", order:213))
		slots.add(new EquipSlot(key:"pants", slotType:"pants", order:214))
		slots.add(new EquipSlot(key:"skirt", slotType:"skirt", order:215))
		slots.add(new EquipSlot(key:"socks", slotType:"socks", order:216))
		
		slots.add(new EquipSlot(key:"left ear", slotType:"piercing", order:300))
		slots.add(new EquipSlot(key:"right ear", slotType:"piercing", order:301))
		slots.add(new EquipSlot(key:"nose", slotType:"piercing", order:302))
		slots.add(new EquipSlot(key:"tongue", slotType:"piercing", order:303))
		slots.add(new EquipSlot(key:"upper lip", slotType:"piercing", order:304))
		slots.add(new EquipSlot(key:"lower lip", slotType:"piercing", order:304))
		slots.add(new EquipSlot(key:"belly-button", slotType:"piercing", order:305))
		
		slots.add(new EquipSlot(key:"left thumb", slotType:"ring", order:400))
		slots.add(new EquipSlot(key:"right thumb", slotType:"ring", order:401))
		slots.add(new EquipSlot(key:"left index", slotType:"ring", order:402))
		slots.add(new EquipSlot(key:"right index", slotType:"ring", order:403))
		slots.add(new EquipSlot(key:"left middle", slotType:"ring", order:404))
		slots.add(new EquipSlot(key:"right middle", slotType:"ring", order:405))
		slots.add(new EquipSlot(key:"left ring", slotType:"ring", order:406))
		slots.add(new EquipSlot(key:"right ring", slotType:"ring", order:407))
		slots.add(new EquipSlot(key:"left pinky", slotType:"ring", order:408))
		slots.add(new EquipSlot(key:"right pinky", slotType:"ring", order:409))
		
		slots.add(new EquipSlot(key:"left wrist", slotType:"strap", order:500))
		slots.add(new EquipSlot(key:"right wrist", slotType:"strap", order:501))
		slots.add(new EquipSlot(key:"left forearm", slotType:"strap", order:502))
		slots.add(new EquipSlot(key:"right forearm", slotType:"strap", order:503))
		slots.add(new EquipSlot(key:"left bicep", slotType:"strap", order:504))
		slots.add(new EquipSlot(key:"right bicep", slotType:"strap", order:505))
		slots.add(new EquipSlot(key:"left ankle", slotType:"strap", order:506))
		slots.add(new EquipSlot(key:"right ankle", slotType:"strap", order:507))
		
		slots = slots.sort()
		return slots
	}
	
	public boolean isFilled() {
		return (slot != null)
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof EquipSlot) {
			EquipSlot otherSlot = ((EquipSlot)o)
			return key.equalsIgnoreCase(otherSlot.key)
		} else {
			return key.equalsIgnoreCase(o.toString())
		}
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof EquipSlot) {
			EquipSlot otherSlot = ((EquipSlot)o)
			return order.compareTo(otherSlot.order)
		} else {
			return order.compareTo(o)
		}
	}
}
