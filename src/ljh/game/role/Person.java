package ljh.game.role;

import java.util.Scanner;

import ljh.game.thing.*;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020年4月18日 下午8:38:00

 */
public class Person extends Role{
	private Room initialRoom;
	
	public Person() {
		super();
	}

	public Person(String id, String name, int lifeValue) {
		super(id, name, lifeValue);
	}



	public void setInitialRoom(Room room) {
		this.initialRoom = room;
	}

	public Room getInitialRoom() {
		return initialRoom;
	}

	public Person(Room initialRoom) {
		super();
		this.initialRoom = initialRoom;
	}

	public void chopMonster(Monster monster) {
		int lifeValue=monster.getLifeValue()-10;
		monster.setLifeValue(lifeValue);	
	}
	public void walkRoom(String dir) {
		Room nextRoom = initialRoom.getRoom(dir);
		if(nextRoom == null) {
			System.out.println("那里没有房间");
			System.out.println("可以选择的方向："+initialRoom.getDirection());
		}else {
			initialRoom = nextRoom;
			System.out.println("你现在在"+initialRoom);
			System.out.println("出口有："+initialRoom.getDirection());

			
		}
	}
	
	@Override
	public String toString() {
		return "你的id为:" + getId() + ", 姓名为:" + getName() + ", 生命值为:" + getLifeValue() ;
	}

	public void drinkHealthPotion(Person person,HealthPotion hp) {
		int lifeValue=person.getLifeValue()+20;
		if(lifeValue > 100) {
			person.setLifeValue(100);
			System.out.println("你喝了血瓶，已恢复满血，生命值+"+(120-lifeValue));
		}
		else {
			person.setLifeValue(lifeValue);
			System.out.println("你喝了血瓶，生命值+20");
		}
		hp.setInit(false);
	}

}
