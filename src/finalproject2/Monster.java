package finalproject2;

import java.util.Map;
import java.util.TreeMap;

public class Monster {
	private String[] valuename=new String[]{//字串數值
			"攻擊力","生命力","智力","火系","冰系","毒系","幻影系"
		};
	private int attack=10;
	private int health = 100;
    private int intelligence = 5;
    private int fire = 0;
    private int ice = 0;
    private int poison = 0;
    private int illusion = 0;
    private int[] value=new int[] {attack,health,intelligence,fire,ice,poison,illusion};//數值
    
	private boolean wing=false;//有無翅膀
	
	private Map<String, Integer> treeMap = new TreeMap<>();
	public Monster() {
		// TODO Auto-generated constructor stub
		for(int i=0;i<valuename.length;i++) {
			treeMap.put(valuename[i], value[i]);
		}
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
    public void setAttack(int attack) {
        this.attack += attack;
    }
    /*
     * 生命力
     */
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health += health;
    }
    /*
     * 智力
     */
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }
    /*
     * 火屬性
     */
    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire += fire;
    }

    /*
     * 冰屬性
     */
    public int getIce() {
        return ice;
    }

    public void setIce(int ice) {
        this.ice += ice;
    }

    /*
     * 毒屬性
     */
    public int getPoison() {
        return poison;
    }

    public void setPoison(int poison) {
        this.poison += poison;
    }

    /*
     * 幻影系
     */
    public int getIllusion() {
        return illusion;
    }

    public void setIllusion(int illusion) {
        this.illusion += illusion;
    }
    
    public String[] getValueName(){
    	return valuename;
    }
    public String getValueName(int i){
    	return valuename[i];
    }
    public int[] getValue(){
    	return value;
    }
    public int getValue(int i){
    	return value[i];
    }
}
