package ljh.game.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ljh.game.role.*;
import ljh.game.thing.*;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020年4月18日 下午9:26:33

 */
public class Main {
	
	private static int rounds = 0;
	
	public static void main(String[] args) {
		Room live, lobby, pub, study, bedroom;
	      
        //	制造房间
        live = new Room("101","客厅");
        lobby= new Room("102","大堂");
        pub = new Room("103","小酒吧");
        study = new Room("104","书房");
        bedroom = new Room("105","卧室");
        
        //	初始化房间的出口
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
        
        //创建一个人
        System.out.println("欢迎来到这个无聊的游戏！");
        System.out.println("首先请输入您的游戏ID和姓名：如007 张三");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] words = line.split(" ");
        Person person = new Person(words[0],words[1],20);
        
        person.setInitialRoom(live);
        System.out.println("你现在的位置是:"+person.getInitialRoom().toString());
		System.out.println("出口有："+person.getInitialRoom().getDirection());

        
        //创建妖怪
        Monster monster1 = new Monster("1","史莱姆",20);
        Room bornRoom = new Room();
        bornRoom = bornRoom.getRandomRoom(list);
        monster1.setBornRoom(bornRoom);
        
        //创建血瓶
        HealthPotion hp = new HealthPotion(0);
        hp.appear(list);
        
        Game game = new Game(person,monster1,hp);
        while(judge(game)) {
        	rounds++;
        	play(game,list);
        }
        System.out.println("感谢您的光临。再见！");
        in.close();
        

        

	}
	public static boolean judge(Game game) {
		Person person = game.getPerson();
		Monster monster = game.getMonster();
		if(person.getLifeValue() <= 0 &&monster.getLifeValue() > 0) {
			System.out.println("你被妖怪杀死了，游戏结束");
			return false;
		}
		else if(monster.getLifeValue() <= 0 && person.getLifeValue() > 0) {
			System.out.println("恭喜你杀死了妖怪，世界恢复了和平，游戏结束！");
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
		System.out.println("第"+rounds+"回合");
		if(rounds%3 == 0||!hp.isInit()) {
			System.out.println("血瓶刷新了！！");
    		hp.setId(rounds);
    		hp.setInit(true);
        	hp.appear(list);
    	}
		if(person.getInitialRoom().equals(monster1.getBornRoom())){
			System.out.println("不好了遇见怪物了！！");
			while(monster1.getLifeValue() >0 && person.getLifeValue() >0) {
				System.out.println("怪物的状态："+monster1.toString());
				System.out.println("玩家的状态："+person.toString());
				hp.showInformation();
				System.out.println("请选择战斗/逃跑");
				String chose = in.nextLine();
				if(chose.equals("战斗")) {
					System.out.println("战斗的方式有：砍/喝血瓶");
					System.out.println("输入战斗的方式：");
					String battleMethod = in.nextLine();
					if(battleMethod.equals("砍")) {
						person.chopMonster(monster1);
						System.out.println("你砍了怪物，怪物血量-10");
					}else if(battleMethod.equals("喝血瓶")) {
						if(person.getInitialRoom().equals(hp.getAppearRoom())) {
							person.drinkHealthPotion(person,hp);
							hp.setId(rounds);
				    		hp.setInit(true);
				        	hp.appear(list);
						}else {
							System.out.println("这个房间没有血瓶");
						}
					}
					Random ra = new Random();
					int i = ra.nextInt(3);
					if(i ==0) {
						monster1.bitePerson(person);
						System.out.println("怪物咬了你，生命值-20");
					}else if(i ==1||i ==2) {
						monster1.catchPerson(person);
						System.out.println("怪物抓了你，生命值-5");
					}
				}
				else if(chose.equals("逃跑")) {
					System.out.println("输入逃跑的方向："+person.getInitialRoom().getDirection());
					String dir = in.nextLine();
					person.walkRoom(dir);
					break;
				}	
			}

		}else {
			person.toString();
			System.out.println("请输入你要去的方向");
			System.out.println("=================");
			String dir = in.nextLine();
			person.walkRoom(dir);
		}
		
	}

}
