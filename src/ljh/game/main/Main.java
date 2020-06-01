package ljh.game.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ljh.game.role.*;
import ljh.game.thing.*;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020��4��18�� ����9:26:33

 */
public class Main {
	
	private static int rounds = 0;
	
	public static void main(String[] args) {
		Room live, lobby, pub, study, bedroom;
	      
        //	���췿��
        live = new Room("101","����");
        lobby= new Room("102","����");
        pub = new Room("103","С�ư�");
        study = new Room("104","�鷿");
        bedroom = new Room("105","����");
        
        //	��ʼ������ĳ���
        live.setExits("east",lobby);
        live.setExits("south",study);
        live.setExits("west",pub);
        lobby.setExits("west",live);
        pub.setExits("east",live);
        study.setExits("north", live);
        study.setExits("east",bedroom);
        bedroom.setExits("west", study);
        
        ArrayList<Room> list = new ArrayList<>();
        list.add(live);
        list.add(lobby);
        list.add(pub);
        list.add(study);
        list.add(bedroom);
        
        //����һ����
        System.out.println("��ӭ����������ĵ���Ϸ��");
        System.out.println("����������������ϷID����������007 ����");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] words = line.split(" ");
        Person person = new Person(words[0],words[1],20);
        
        person.setInitialRoom(live);
        System.out.println("�����ڵ�λ����:"+person.getInitialRoom().toString());
		System.out.println("�����У�"+person.getInitialRoom().getDirection());

        
        //��������
        Monster monster1 = new Monster("1","ʷ��ķ",20);
        Room bornRoom = new Room();
        bornRoom = bornRoom.getRandomRoom(list);
        monster1.setBornRoom(bornRoom);
        
        //����Ѫƿ
        HealthPotion hp = new HealthPotion(0);
        hp.appear(list);
        
        Game game = new Game(person,monster1,hp);
        while(judge(game)) {
        	rounds++;
        	play(game,list);
        }
        System.out.println("��л���Ĺ��١��ټ���");
        in.close();
        

        

	}
	public static boolean judge(Game game) {
		Person person = game.getPerson();
		Monster monster = game.getMonster();
		if(person.getLifeValue() <= 0 &&monster.getLifeValue() > 0) {
			System.out.println("�㱻����ɱ���ˣ���Ϸ����");
			return false;
		}
		else if(monster.getLifeValue() <= 0 && person.getLifeValue() > 0) {
			System.out.println("��ϲ��ɱ�������֣�����ָ��˺�ƽ����Ϸ������");
			return false;
		}else {
			return true;
		}
	}
	
	public static void play(Game game,ArrayList<Room> list) {
		Person person = game.getPerson();
		Monster monster1 = game.getMonster();
		HealthPotion hp = game.getHp();
		Scanner in = new Scanner(System.in);
		System.out.println("��"+rounds+"�غ�");
		if(rounds%3 == 0||!hp.isInit()) {
			System.out.println("Ѫƿˢ���ˣ���");
    		hp.setId(rounds);
    		hp.setInit(true);
        	hp.appear(list);
    	}
		if(person.getInitialRoom().equals(monster1.getBornRoom())){
			System.out.println("���������������ˣ���");
			while(monster1.getLifeValue() >0 && person.getLifeValue() >0) {
				System.out.println("�����״̬��"+monster1.toString());
				System.out.println("��ҵ�״̬��"+person.toString());
				hp.showInformation();
				System.out.println("��ѡ��ս��/����");
				String chose = in.nextLine();
				if(chose.equals("ս��")) {
					System.out.println("ս���ķ�ʽ�У���/��Ѫƿ");
					System.out.println("����ս���ķ�ʽ��");
					String battleMethod = in.nextLine();
					if(battleMethod.equals("��")) {
						person.chopMonster(monster1);
						System.out.println("�㿳�˹������Ѫ��-10");
					}else if(battleMethod.equals("��Ѫƿ")) {
						if(person.getInitialRoom().equals(hp.getAppearRoom())) {
							person.drinkHealthPotion(person,hp);
							hp.setId(rounds);
				    		hp.setInit(true);
				        	hp.appear(list);
						}else {
							System.out.println("�������û��Ѫƿ");
						}
					}
					Random ra = new Random();
					int i = ra.nextInt(3);
					if(i ==0) {
						monster1.bitePerson(person);
						System.out.println("����ҧ���㣬����ֵ-20");
					}else if(i ==1||i ==2) {
						monster1.catchPerson(person);
						System.out.println("����ץ���㣬����ֵ-5");
					}
				}
				else if(chose.equals("����")) {
					System.out.println("�������ܵķ���"+person.getInitialRoom().getDirection());
					String dir = in.nextLine();
					person.walkRoom(dir);
					break;
				}	
			}

		}else {
			person.toString();
			System.out.println("��������Ҫȥ�ķ���");
			System.out.println("=================");
			String dir = in.nextLine();
			person.walkRoom(dir);
		}
		
	}

}
