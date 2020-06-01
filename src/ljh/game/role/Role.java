package ljh.game.role;
/**
 * @description: 
 * @author: ljh
 * @date: Created in 2020年4月18日 下午8:33:53

 */
public class Role {
	private String id;
	private String name;
	private int lifeValue;
	public Role() {
		super();
	}
	public Role(String id, String name, int lifeValue) {
		super();
		this.id = id;
		this.name = name;
		this.lifeValue = lifeValue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLifeValue() {
		return lifeValue;
	}
	public void setLifeValue(int lifeValue) {
		this.lifeValue = lifeValue;
	}
	

}
