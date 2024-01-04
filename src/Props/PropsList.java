package Props;

import java.util.ArrayList;

import javax.sound.midi.VoiceStatus;

import finalproject2.Account;

public class PropsList extends ArrayList<Props>{
	/**
	 * 道具庫
	 */
	private static final long serialVersionUID = 1L;
	private Account account;

	public PropsList() {
		// TODO Auto-generated constructor stub
		stock();
	}
	public PropsList(Account account) {
		// TODO Auto-generated constructor stub
		this.account = account;
		stock();
	}
	public void stock() {
		
		int id = 0;
		int level = 1;
		final int value1 = 1;
		this.add(new Props(id++, "糖果+" + value1+"(飢餓度)", level,-5.0 ,() -> {
			account.monster.addHungerValue(value1);
		}));
		this.add(new Props(id++, "攻擊力+" + value1, level, () -> {
			account.monster.addAttack(value1);
		}));
		this.add(new Props(id++, "生命力+" + value1 * 2, level, () -> {
			account.monster.addHP(value1 * 2);
		}));
		this.add(new Props(id++, "智力+" + value1, level, () -> {
			account.monster.addIntelligence( value1);
		}));
		this.add(new Props(id++, "火系+" + value1, level, Props.volcano, () -> {
			account.monster.addFire(value1);
		}));
		this.add(new Props(id++, "冰系+" + value1, level, Props.polar, () -> {
			account.monster.addIce( value1);
		}));
		this.add(new Props(id++, "毒系+" + value1, level, Props.swamp, () -> {
			account.monster.addPoison(value1);
		}));
		this.add(new Props(id++, "幻影系+" + value1, level, Props.temple, () -> {
			account.monster.addIllusion( value1);
		}));
		this.add(new Props(id++, "小餅乾+" + value1 * 5 + "(飢餓度)", level, () -> {
			account.monster.addHungerValue(value1*5);
		}));
		this.add(new Props(id++, "一瓶水+" + value1 * 5 + "(飢渴度)", level, () -> {
			account.monster.addThirstValue(value1*5);
		}));
		this.add(new Props(id++, "小玩具+" + value1 * 5 + "(心情度)", level, () -> {
			account.monster.addMoodValue(value1*5);
		}));
		this.add(new Props(id++, "健康水+" + value1 * 5 + "(健康度)", level, () -> {
			account.monster.addHealthValue(value1*5);
		}));
		this.add(new Props(id++, "小刀+" + value1 * 3 + "攻擊力", level, 2.0, () -> {
			account.monster.addAttack(account.monster.getAttack() + value1*3);
		}));
		this.add(new Props(id++, "小甲+" + value1 * 4 + "生命力", level, 2.0, () -> {
			account.monster.addHP(account.monster.getHP() + value1 * 4);
		}));
		this.add(new Props(id++, "書+" + value1 * 6 + "智力", level, 3.0, () -> {
			account.monster.addIntelligence( value1*6);
		}));
		

		level = 2;
		final int value3 = 3;
		this.add(new Props(id++, "攻擊力+" + value3, level, () -> {
			account.monster.addAttack( value3);
		}));
		this.add(new Props(id++, "生命力+" + value3 * 2, level, () -> {
			account.monster.addHP(value3 * 2);
		}));
		this.add(new Props(id++, "智力+" + value3, level, () -> {
			account.monster.addIntelligence(value3);
		}));
		this.add(new Props(id++, "火系+" + value3, level, Props.volcano, () -> {
			account.monster.addFire(value3);
		}));
		this.add(new Props(id++, "冰系+" + value3, level, Props.polar, () -> {
			account.monster.addIce(value3);
		}));
		this.add(new Props(id++, "毒系+" + value3, level, Props.swamp, () -> {
			account.monster.addPoison( value3);
		}));
		this.add(new Props(id++, "幻影系+" + value3, level, Props.temple, () -> {
			account.monster.addIllusion(value3);
		}));
		this.add(new Props(id++, "豬肉+" + value3 * 5 + "飢餓度", level, () -> {
			account.monster.addHungerValue(value3*5);
		}));
		this.add(new Props(id++, "一桶水+" + value3 * 5 + "飢渴度", level, () -> {
			account.monster.addThirstValue(value3*5);
		}));
		this.add(new Props(id++, "彈力球+" + value3 * 5 + "心情度", level, () -> {
			account.monster.addMoodValue(value3*5);
		}));
		this.add(new Props(id++, "免疫注射+" + value3 * 5 + "健康度", level, () -> {
			account.monster.addHealthValue(value3*5);
		}));
		this.add(new Props(id++, "大刀+" + value3 * 2 + "攻擊力", level, 1.0, () -> {
			account.monster.addAttack(value3*2);
		}));
		this.add(new Props(id++, "盔甲+" + value3 * 4 + "防禦力", level, 5.0, () -> {
			account.monster.addHP( value3 * 4);
		}));
		this.add(new Props(id++, "無字天書+" + value3 * 4 + "智力", level, 7.0, () -> {
			account.monster.addIntelligence(value3*4);
		}));

		level = 3;
		final int value5 = 5;
		this.add(new Props(id++, "攻擊力+" + value5, level, () -> {
			account.monster.addAttack( value5);
		}));
		this.add(new Props(id++, "生命力+" + value5 * 2, level, () -> {
			account.monster.addHP( value5 * 2);
		}));
		this.add(new Props(id++, "智力+" + value5, level, () -> {
			account.monster.addIntelligence(account.monster.getIntelligence() + value5);
		}));
		this.add(new Props(id++, "火系+" + value5, level, Props.volcano, () -> {
			account.monster.addFire(value5);
		}));
		this.add(new Props(id++, "冰系+" + value5, level, Props.polar, () -> {
			account.monster.addIce( value5);
		}));
		this.add(new Props(id++, "毒系+" + value5, level, Props.swamp, () -> {
			account.monster.addPoison(value5);
		}));
		this.add(new Props(id++, "幻影系+" + value5, level, Props.temple, () -> {
			account.monster.addIllusion(value5);
		}));
		this.add(new Props(id++, "牛肉+" + value5 * 5 + "飢餓度", level, () -> {
			account.monster.addHungerValue(value5*5);
		}));
		this.add(new Props(id++, "飲水機+" + value5 * 5 + "飢渴度", level, () -> {
			account.monster.addThirstValue(value5*5);
		}));
		this.add(new Props(id++, "遊樂園+" + value5 * 5 + "心情度", level, () -> {
			account.monster.addMoodValue(value1*5);
		}));
		this.add(new Props(id++, "專業療程+" + value5 * 5 + "健康度", level, () -> {
			account.monster.addHealthValue(value5*5);
		}));

		level = 4;// 4階道具
		this.add(new Props(id++, "光劍+10攻擊力", level, Props.wind_farm, () -> {
			account.monster.addAttack(10);
		}));
		this.add(new Props(id++, "奧丁的西瓜刀+10攻擊力", level, Props.wind_farm, () -> {
			account.monster.addAttack(10);
		}));
		this.add(new Props(id++, "智力天賦+20智力", level, Props.wind_farm, () -> {
			account.monster.addIntelligence(20);
		}));

		level = 5;// 5階道具

		this.add(new Props(id++, "翅膀+30智力", level, Props.wind_farm, () -> {
			account.monster.addIntelligence(30);
		}));
		this.add(new Props(id++, "黑閃(當前攻擊力x2)", level, Props.temple, () -> {
			account.monster.addAttack(account.monster.getAttack());
		}));
		this.add(new Props(id++, "火神祝福 火系+30", level, Props.volcano, () -> {
			account.monster.addFire(30);
		}));
		this.add(new Props(id++, "冰天雪地 冰系+30", level, Props.polar, () -> {
			account.monster.addIce(30);
		}));
		this.add(new Props(id++, "史萊姆血液 生命力+75", level, () -> {
			account.monster.addHP(75);
		}));

		level = 6;// 6階道具
		this.add(new Props(id++, "poi的祝福+100全部數值", level, () -> {
			account.monster.addValue(100);
		}));
		this.add(new Props(id++, "神仙藥水 全部狀態補滿，非消耗品", level, 280.0, () -> {
			account.monster.setSlimeState(new int[] {100,100,100,100});
		}));
		this.add(new Props(id++, "普西芬妮的擁護 (死亡觸發)", level, Props.temple, 20.0, () -> {
			//缺戰鬥時血量計算
		}));
		this.add(new Props(id++, "朗基努斯之槍 (當前攻擊力x3)", level, () -> {
			account.monster.addAttack(account.monster.getAttack()*2);
		}));
		this.add(new Props(id++, "六眼 (當前智力x3)", level, () -> {
			account.monster.addIntelligence(account.monster.getIntelligence()*2);
		}));

	}
	 public ArrayList<Props> getArraylist() {
		return this;
	}
	public void showPropsList() {
		for (Props props : this) {
			System.out.println("ID:" + props.getID() + " 道具名稱:" + props.getName() + "\t\t道具價值:" + props.getValue());
		}
	}

	public static void main(String[] args) {
		PropsList propsList = new PropsList(new Account());
		propsList.showPropsList();
		
	}
}
