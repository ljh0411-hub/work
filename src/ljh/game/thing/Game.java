package ljh.game.thing;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ljh.game.role.Monster;
import ljh.game.role.Person;

/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020年4月19日 下午2:01:31

 */
public class Game {
	private Person person;
	private Monster monster;
	private HealthPotion hp;
	
	public Game() {
		super();
	}
	public Game(Person person, Monster monster, HealthPotion hp) {
		super();
		this.person = person;
		this.monster = monster;
		this.hp = hp;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Monster getMonster() {
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	public HealthPotion getHp() {
		return hp;
	}
	public void setHp(HealthPotion hp) {
		this.hp = hp;
	}
	
	
}
