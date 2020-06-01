package ljh.game.thing;

import java.util.ArrayList;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020��4��18�� ����8:47:56

 */
public class HealthPotion {
	private int id;
	private boolean init = true;
	private Room appearRoom;

	public HealthPotion() {
		super();
	}

	public HealthPotion(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}
	
	
	public Room getAppearRoom() {
		return appearRoom;
	}

	public void setAppearRoom(Room appearRoom) {
		this.appearRoom = appearRoom;
	}

	public void appear(ArrayList<Room> list) {
		Room room = new Room();
		this.appearRoom = room.getRandomRoom(list);
	}
	
	public void showInformation() {
		System.out.println("Ѫƿ��IDΪ��"+id+",Ѫƿ���ڵķ���Ϊ��"+appearRoom);
	}
}
