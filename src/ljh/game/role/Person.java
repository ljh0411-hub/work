package ljh.game.role;

import java.util.Scanner;

import ljh.game.thing.*;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020��4��18�� ����8:38:00

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
			System.out.println("����û�з���");
			System.out.println("����ѡ��ķ���"+initialRoom.getDirection());
		}else {
			initialRoom = nextRoom;
			System.out.println("��������"+initialRoom);
			System.out.println("�����У�"+initialRoom.getDirection());

			
		}
	}
	
	@Override
	public String toString() {
		return "���idΪ:" + getId() + ", ����Ϊ:" + getName() + ", ����ֵΪ:" + getLifeValue() ;
	}

	public void drinkHealthPotion(Person person,HealthPotion hp) {
		int lifeValue=person.getLifeValue()+20;
		if(lifeValue > 100) {
			person.setLifeValue(100);
			System.out.println("�����Ѫƿ���ѻָ���Ѫ������ֵ+"+(120-lifeValue));
		}
		else {
			person.setLifeValue(lifeValue);
			System.out.println("�����Ѫƿ������ֵ+20");
		}
		hp.setInit(false);
	}

}
