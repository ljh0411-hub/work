package ljh.game.role;

import java.util.ArrayList;
import java.util.Collections;

import ljh.game.thing.Room;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020年4月18日 下午8:42:03

 */
public class Monster extends Role {
	private Room bornRoom;
	
	
	public Room getBornRoom() {
		return bornRoom;
	}

	public void setBornRoom(Room bornRoom) {
		this.bornRoom = bornRoom;
	}

	public Monster(String id, String name, int lifeValue) {
		super(id, name, lifeValue);
	}
	
	
	@Override
	public String toString() {
		return "怪物Id为：" + getId() + ", 名字为：" + getName() + ", 血量为：" + getLifeValue();
	}

	public void bitePerson(Person person) {
		int lifeValue=person.getLifeValue()-20;
		person.setLifeValue(lifeValue);
	}
	public void catchPerson(Person person) {
		int lifeValue=person.getLifeValue()-5;
		person.setLifeValue(lifeValue);
	}
	
	
	
	
}
