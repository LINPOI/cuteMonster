package finalproject2;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Monster {
	
	private String username = "";
	private String[] value_name = new String[] { // 字串數值
			"年齡", "攻擊力", "生命力", "智力", "火系", "冰系", "毒系", "幻影系" };
	private String[] slimestates_Name = new String[] { "飢餓度", "飢渴度", "心情指數", "健康度" };
	private int ID=1;
	private String name = "";
	private int age = 0;
	private int attack = 10;
	private int hp = 40;
	private int intelligence = 5;
	private int fire = 0;
	private int ice = 0;
	private int poison = 0;//毒
	private int illusion = 0;
	//private int[] value = new int[] { age, attack, hp, intelligence, fire, ice, poison, illusion };// 數值
	private int hungerValue = 50;
	private int thirstValue = 50;
	private int moodValue = 50;
	private int healthValue = 50;
	private boolean wing = false;// 有無翅膀
	
	public Monster(String username) {
		this.username = username;
		// TODO Auto-generated constructor stub
	}

	/*
	 * ID
	 */
	public int getID() {
		// System.out.println("getname getmonstername:"+name);
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}
	/*
	 * 名稱
	 */
	public String getName() {
		// System.out.println("getname getmonstername:"+name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 年齡
	 */
	public int getAge() {
		return age;
	}

	public void addAge(int age) {
		this.age+=age;
	}

	/*
	 * 翅膀
	 */
	public boolean getWing() {
		return wing;
	}

	public void setWing(boolean wing) {
		this.wing = wing;
	}

	/*
	 * 攻擊力
	 */
	public int getAttack() {
		return attack;
	}

	public void addAttack(int attack) {
		this.attack += attack;
	}

	/*
	 * 生命力
	 */
	public int getHP() {
		return hp;
	}
	public void setHP(int hp) {
		this.hp += hp;
	}
	public void addHP(int health) {
		this.hp += health;
	}

	/*
	 * 智力
	 */
	public int getIntelligence() {
		return intelligence;
	}

	public void addIntelligence(int intelligence) {
		this.intelligence += intelligence;
	}

	/*
	 * 火屬性
	 */
	public int getFire() {
		return fire;
	}

	public void addFire(int fire) {
		this.fire += fire;
	}

	/*
	 * 冰屬性
	 */
	public int getIce() {
		return ice;
	}

	public void addIce(int ice) {
		this.ice += ice;
	}

	/*
	 * 毒屬性
	 */
	public int getPoison() {

		return poison;
	}

	public void addPoison(int poison) {
		this.poison += poison;
	}

	/*
	 * 幻影系
	 */
	public int getIllusion() {
	
		return illusion;
	}

	public void addIllusion(int illusion) {
		this.illusion += illusion;
	}

	/*
	 * 數值名稱
	 */
	public String[] getValueName() {

		return value_name;
	}

	public String getValueName(int i) {
	
		if (i < value_name.length) {
			return value_name[i];
		} else {
			return "翅膀";
		}

	}

	/*
	 * 數值
	 */
	

	public void addValue(int[] ints) {
		attack += ints[0];
		hp += ints[1];
		intelligence += ints[2];
		fire += ints[3];
		ice += ints[4];
		poison += ints[5];
		illusion += ints[6];
	}
	public void addAllValue(int intt) {
		attack += intt;
		hp += intt;
		intelligence += intt;
		fire += intt;
		ice += intt;
		poison += intt;
		illusion += intt;
	}
	public int[] getValue() {
		int[] value=new int[] { age, attack, hp, intelligence, fire, ice, poison, illusion };// 數值
		return value;
	}

	public int getValue(int i) {
		int[] value=new int[] { age, attack, hp, intelligence, fire, ice, poison, illusion };// 數值
		return value[i];
	}
	
	/*
	 * 數值訊息
	 */
	public String getInf(int i) {
		String[] strings = new String[] { "隨事件成長", "可透過練武提升", "可透過練武提升", "可透過學習提升", "探索火山提升", "探索極地提升", "探索沼澤提升",
				"探索神殿提升", "透過飛行訓練" };
		return strings[i];
	}

	

	// 飢餓度
	public int getHungerValue() {
		return hungerValue;
	}
	public void setHungerValue(int hungerValue) {
		this.hungerValue = hungerValue;
	}
	public void addHungerValue(int hungerValue) {
		this.hungerValue += hungerValue;
	}

	// 飢渴度
	public int getThirstValue() {
		return thirstValue;
	}
	public void setThirstValue(int thirstValue) {
		this.thirstValue = thirstValue;
	}
	public void addThirstValue(int thirstValue) {
		this.thirstValue += thirstValue;
	}

	// 心情指數
	public int getMoodValue() {
		return moodValue;
	}
	public void setMoodValue(int moodValue) {
		this.moodValue = moodValue;
	}
	public void addMoodValue(int moodValue) {
		this.moodValue += moodValue;
	}

	// 健康值
	public int getHealthValue() {
		return healthValue;
	}
	public void setHealthValue(int healthValue) {
		this.healthValue = healthValue;
	}
	public void addHealthValue(int healthValue) {
		this.healthValue += healthValue;
	}

	public String[] getStates_name() {
		return slimestates_Name;
	}

	public String getStates_name(int i) {
		return slimestates_Name[i];
	}

	public void setSlimeState(int[] value) {
		hungerValue = value[0];
		thirstValue= value[1];
		moodValue= value[2];
		healthValue= value[3];
	}

	public int[] getStates() {
		return new int[] { hungerValue, thirstValue, moodValue, healthValue };// 數值
	}

	public int getStates(int i) {
		int[] slimestates=new int[] { hungerValue, thirstValue, moodValue, healthValue};
		return slimestates[i];
	}
	public boolean dead() {
		if(getHealthValue()<=0||getHungerValue()<=0||getThirstValue()<=0||getMoodValue()<=0) {
			return true;
		}
		return false;
	}
}
