package Props;

public class Props {
	private int id = 0;// 道具id
	private String name = "";// 道具名稱
	private int level = 1;// 道具等級
	private double value = 0.0;// 道具價值
	private int location = 0;// 出現場地
	private ItemEffect effect;//lambda 道具效果
	//------------------------------------
	public static final int all = 0;// 無
	public static final int polar = 1;// 極地
	public static final int volcano = 2;// 火山
	public static final int swamp = 3;// 沼澤
	public static final int temple = 4;// 神殿
	public static final int wind_farm = 5;// 風場
	public static final int Training_Course = 6;// 訓練場

	public static final int Location_Number=6;
	//----------------------------------
	public Props() {
		// TODO Auto-generated constructor stub
	}

	public Props(int id, String name, int level, ItemEffect effect) {// 通用道具
		this.id = id;
		this.name = name;
		this.level = level;
		this.value = CalculateValue(id,level, location, value);
	}

	public Props(int id, String name, int level, double value, ItemEffect effect) {// 特殊道具
		this.id = id;
		this.name = name;
		this.level = level;
		this.value = CalculateValue(id,level, location, value);
	}

	public Props(int id, String name, int level, int location, ItemEffect effect) {// 特殊道具
		this.id = id;
		this.name = name;
		this.level = level;
		this.value = CalculateValue(id,level, location, value);
		this.location = location;
	}

	public Props(int id, String name, int level, int location, double value, ItemEffect effect) {// 特殊道具
		this.id = id;
		this.name = name;
		this.level = level;
		this.value = CalculateValue(id,level, location, value);
		this.location = location;
	}

	/*
	 * 計算價值
	 */
	public double CalculateValue(int id,int level, int location, double value) {
		double add = 0.0;
		double value_up=Math.floor((id+ value)) / 10;
		
		if (location > 0) {
			add = 1.0;
		}

		switch (level) {
		case 1:
			return 1.0 + add * 1 + value_up;
		case 2:
			return 4.0 + add * 4 + value_up;
		case 3:
			return 9.0 + add * 9 + value_up;
		case 4:
			return 18.0 + add * 11 + value_up;
		case 5:
			return 30.0 + add * 15 +value_up;
		case 6:
			return 70.0 + add * 30 + value_up;
		default:
			throw new IllegalArgumentException("Unexpected value: " + level);
		}

	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public double getValue() {
		return value;
	}

	public int getLocation() {
		return location;
	}

	public void use() {//使用
		effect.applyEffect();
	}
}
