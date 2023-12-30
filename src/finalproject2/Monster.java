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
	private String name = "";
	private int age = 0;
	private int attack = 10;
	private int hp = 100;
	private int intelligence = 5;
	private int fire = 0;
	private int ice = 0;
	private int poison = 0;
	private int illusion = 0;
	private int[] value = new int[] { age, attack, hp, intelligence, fire, ice, poison, illusion };// 數值

	private int hungerValue = 50;
	private int thirstValue = 50;
	private int moodValue = 50;
	private int healthValue = 50;
	private int[] slimestates = new int[] { hungerValue, thirstValue, moodValue, healthValue };// 數值
	private RWFile rwFile = new RWFile();
	private boolean wing = false;// 有無翅膀

	public Monster(String username) {
		this.username = username;
		// TODO Auto-generated constructor stub
	}

	/*
	 * 名稱
	 */
	public String getName() {
		readMonster();
		// System.out.println("getname getmonstername:"+name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
		save();
	}

	/*
	 * 年齡
	 */
	public int getAge() {
		readMonster();
		return age;
	}

	public void setAge(int age) {
		this.age += age;
		save();
	}

	/*
	 * 翅膀
	 */
	public boolean getWing() {
		readMonster();
		return wing;
	}

	public void setWing(boolean wing) {
		this.wing = wing;
		save();
	}

	/*
	 * 攻擊力
	 */
	public int getAttack() {
		readMonster();
		return attack;
	}

	public void setAttack(int attack) {
		this.attack += attack;
		save();
	}

	/*
	 * 生命力
	 */
	public int getHP() {
		readMonster();
		return hp;
	}

	public void setHP(int health) {
		this.hp += health;
		save();
	}

	/*
	 * 智力
	 */
	public int getIntelligence() {
		readMonster();
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence += intelligence;
		save();
	}

	/*
	 * 火屬性
	 */
	public int getFire() {
		readMonster();
		return fire;
	}

	public void setFire(int fire) {
		this.fire += fire;
		save();
	}

	/*
	 * 冰屬性
	 */
	public int getIce() {
		readMonster();
		return ice;
	}

	public void setIce(int ice) {
		this.ice += ice;
		save();
	}

	/*
	 * 毒屬性
	 */
	public int getPoison() {
		readMonster();
		return poison;
	}

	public void setPoison(int poison) {
		this.poison += poison;
		save();
	}

	/*
	 * 幻影系
	 */
	public int getIllusion() {
		readMonster();
		return illusion;
	}

	public void setIllusion(int illusion) {
		this.illusion += illusion;
		save();
	}

	/*
	 * 數值名稱
	 */
	public String[] getValueName() {
		readMonster();
		return value_name;
	}

	public String getValueName(int i) {
		readMonster();
		if (i < value_name.length) {
			return value_name[i];
		} else {
			return "翅膀";
		}

	}

	/*
	 * 數值
	 */
	public void setValue(LinkedList<String> linkedList) {
		if (linkedList.size() != 0) {
			name = linkedList.get(1);
			for (int i = 2; i < linkedList.size() - 1; i++) {
				value[i - 2] = Integer.parseInt(linkedList.get(i));
				// System.out.println(value[i-2]);
			}
			wing = Boolean.parseBoolean(linkedList.get(10));
		}
		save();
	}

	public int[] getValue() {
		readMonster();
		return value;
	}

	public int getValue(int i) {
		readMonster();
		return value[i];
	}

	/*
	 * 數值訊息
	 */
	public String getInf(int i) {
		String[] strings = new String[] { "隨時間成長", "可透過練武提升", "可透過練武提升", "可透過學習提升", "探索火山提升", "探索極地提升", "探索沼澤提升",
				"探索神殿提升", "透過飛行訓練" };
		return strings[i];
	}

	public void save() {
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add(username);
		linkedList.add(name);
		for (int i = 0; i < getValue().length; i++) {
			linkedList.add(String.valueOf(getValue(i)));
		}
		linkedList.add(String.valueOf(wing));
		rwFile.saveToFile_monster(linkedList, name);

	}

	public void readMonster() {
		LinkedList<String> linkedList = rwFile.read_Monster(name);
		if (linkedList.size() != 0) {
			name = linkedList.get(1);
			for (int i = 2; i < linkedList.size() - 1; i++) {
				value[i - 2] = Integer.parseInt(linkedList.get(i));
			}
			wing = Boolean.parseBoolean(linkedList.get(linkedList.size() - 1));
		}

	}

	// 飢餓度
	public int getHungerValue() {
		return hungerValue;
	}

	public void setHungerValue(int hungerValue) {
		this.hungerValue = hungerValue;
	}

	// 飢渴度
	public int getThirstValue() {
		return thirstValue;
	}

	public void setThirstValue(int thirstValue) {
		this.thirstValue = thirstValue;
	}

	// 心情指數
	public int getMoodValue() {
		return moodValue;
	}

	public void setMoodValue(int moodValue) {
		this.moodValue = moodValue;
	}

	// 健康值
	public int getHealthValue() {
		return healthValue;
	}

	public void setHealthValue(int healthValue) {
		this.healthValue = healthValue;
	}
	public String[] getStates_name() {
		return slimestates_Name;
	}
	public String getStates_name(int i) {
		return slimestates_Name[i];
	}
	public void setSlimeState(int[] value) {
		slimestates=value;
	}
	public int[] getStates(){
		return slimestates;
	}
	public int getStates(int i){
		return slimestates[i];
	}
}
