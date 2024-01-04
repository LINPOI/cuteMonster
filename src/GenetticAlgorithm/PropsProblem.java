package GenetticAlgorithm;

import java.nio.channels.NonWritableChannelException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Props.Props;
import Props.PropsList;
import finalproject2.Account;
import finalproject2.Enemy;
import finalproject2.Monster;

/*
 * 設定最優解=玩家積分該獲得的獎勵應該是好的，不該是全部都一等
 * 判斷積分最高價值組合
 */
public class PropsProblem implements Problem<Props> {
	private Random random = null;
	private ArrayList<Props> propsLists = new PropsList();
	private Account account;// 刷新帳號
	private Account oldaccount;// 不更新帳號
	private int location;// 場地
	private double immunity;// 屬性抗性
	private int difficulty = 0;// 關卡難度
	private double totalScore = 0.0;
	private final int Initial_number_of_genes = 4;// 初始基因數量
	private final int Max_number_of_genes = 11;// 上限基因數量
	private double totalValue;
	public PropsProblem(Random random, Account account, int location, int difficulty) {
		this.random = random;
		this.account = account;
		this.oldaccount = account;
		this.location = location;
		this.difficulty = difficulty;
		immunity = account.monster.getIntelligence() * 0.05;

		if (location == Props.polar) {
			immunity += account.monster.getIce();
		} else if (location == Props.volcano) {
			immunity += account.monster.getFire();
		} else if (location == Props.swamp) {
			immunity += account.monster.getPoison();
		} else if (location == Props.temple) {
			immunity += account.monster.getIllusion();
		} else if (location == Props.wind_farm) {
			immunity += 5;
		} else if (location == Props.Training_Course) {
			immunity += 5;
		} else {
			System.out.println("where are here");
		}
		account.monster.addAttack((int) immunity);
		totalScore = monsterFight();
	}

	@Override
	public Chromosome<Props>[] generateChromosomes(int size) {
		/*
		 * 初始化族群設定 這裡設定了四個初始基因,size個染色體 並隨機給這些染色體初始基因(道具) 染色體[道具,道具,道具,道具]
		 */
		Props[] genes = new Props[Initial_number_of_genes];
		Chromosome<Props>[] chromosome = new Chromosome[size];
		for (int i = 0; i < chromosome.length; i++) {// 放入size個染色體
			for (int j = 0; j < genes.length; j++) {// 初始族群4個Chromosome[genes,genes,genes,genes]
				genes[j] = propsLists.get(random.nextInt(propsLists.size() - 13));// 隨機給裝備 13件為稀有道具變異取得
			}
			chromosome[i] = new Chromosome<Props>();
			chromosome[i].setGenes(genes);// 設定染色體基因
//			System.out.println("染色體" + i + ":[" + genes[0].getName() + "," + genes[1].getName() + ","
//					+ genes[2].getName() + "," + genes[3].getName() + "]");
		}
		return chromosome;
	}

	@Override
	public Props[] mutationChromosomes() {// 變異時用
		// TODO Auto-generated method stub
		Props[] genes = new Props[random.nextInt(Max_number_of_genes)+1];
		for (int j = 0; j < genes.length; j++) {
			genes[j] = propsLists.get(random.nextInt(propsLists.size()));// 隨機給裝備
		}
		return genes; // 獲得清單中隨機數把武器(包誇56階道具)
	}

	@Override
	public void calculateFitness(Chromosome<Props>[] chrs) {
		/*
		 * 
		 */

		totalValue = 0.0d;// 價值
		for (int i = 0; i < chrs.length; i++) {// 這一迴圈是從好幾組染色體來看一組染色體
			for (int j = 0; j < chrs[i].getChromosome().length; j++) {// 這一迴圈是從好幾組基因來看基因內部的內容
				if (chrs[i].getChromosome()[j].getLocation() == 0
						|| chrs[i].getChromosome()[j].getLocation() == location) {
					// 地圖判斷，此地圖可出現的道具
					totalValue += chrs[i].getChromosome()[j].getValue();
					if (totalValue > totalScore) {
						totalValue = 0.0;// 若染色體超過應有價值淘汰
						break;
					}
				} else {
					totalValue =0.0;// 若染色體出現不該出現的道具則無價值
					break;
				}
			}
		//	System.out.println("setFitnessValue:" + totalValue + "\ttotalScore:" + totalScore);
			double output= Math.floor(totalValue * 10) / 10;//取到小數點後一位
			if (totalValue != 0 && totalScore != 0) {
				output=totalValue/ totalScore;
			}else if(totalScore<0) {
				output=-1.0;
			}
			 //System.out.println("新setFitnessValue:"+totalValue);
			chrs[i].setFitnessValue(output);// 最後輸出差距，為1是最優解 
		}
	}

	public double monsterFight() {// 第一步戰鬥過程戰鬥玩再進入演算法
		/*
		 * 計算戰鬥後獲得的積分(用於判斷最優解的分數為多少)
		 */

		Enemy.setImmunity(immunity);

		/*
		 * 做判斷6等怪要很少見，怪物數量稀有度
		 */
		// 先設定怪獸數量=怪獸等級*10~10
		Enemy[] enemies = new Enemy[random.nextInt(account.monster.getAge() + 1) * 10 + 10];
		double lv2 = 0.8;// 2等怪出現率
		double lv3 = 0.5;// 等怪出現率
		double lv4 = 0.8;// 等怪出現率
		double lv5 = 0.7;// 等怪出現率
		double lv6 = 0.9;// 等怪出現率
		// System.out.println("進入");
		if (difficulty == 1) {// 關卡難度

			for (int i = 0; i < enemies.length; i++) {// 敵人數量
				if (random.nextDouble() > lv2) {// 怪物出現機率
					enemies[i] = Enemy.enemyLevel_2;
				} else {
					enemies[i] = Enemy.enemyLevel_1;
				}
				double add=CalculateFractions(enemies[i]);
				if(add!=-1.0) {
					totalScore += add;
				}else {
					return totalScore=-1.0;
				}
			}

		} else if (difficulty == 2) {
			for (int i = 0; i < enemies.length; i++) {
				double lv = random.nextDouble();
				if (lv > lv4) {
					enemies[i] = Enemy.enemyLevel_4;
				} else if (lv > lv3) {
					enemies[i] = Enemy.enemyLevel_3;
				} else {
					enemies[i] = Enemy.enemyLevel_2;
				}
				double add=CalculateFractions(enemies[i]);
				if(add!=-1.0) {
					totalScore += add;
				}else {
					return totalScore=-1.0;
				}
			}
		} else if (difficulty == 3) {

			for (int i = 0; i < enemies.length; i++) {
				double lv = random.nextDouble();
				if (lv > lv6) {
					enemies[i] = Enemy.enemyLevel_6;
				} else if (lv > lv5) {
					enemies[i] = Enemy.enemyLevel_5;
				} else {
					enemies[i] = Enemy.enemyLevel_4;
				}
				double add=CalculateFractions(enemies[i]);
				if(add!=-1.0) {
					totalScore += add;
				}else {
					return totalScore=-1.0;
					
				}
			}
		} else {
			System.out.println("錯誤的difficulty:" + difficulty);
		}
		//System.out.println("\t\t\t進入" + totalScore);
		double roundedNumber = Math.floor(totalScore * 10) / 10;
		return roundedNumber;
	}

	public double CalculateFractions(Enemy enemy) {
		/*
		 * 這裡進行戰鬥判斷，獲得積分
		 */
		// System.out.println("進入");
		double totalScore = 0;
		int output_slime = account.monster.getAttack() * account.monster.getHP();
		int output_enemy = (int) (enemy.getHP() * enemy.getAttack());
		if (output_slime >= output_enemy) {
			// System.out.println("殺敵");
			account.monster.addHP((int) (5 - enemy.getAttack()));// 每打死一個敵人回復5點血量
			// 判斷有沒有超出原始怪獸數值(生命最大值)
			if (account.monster.getHP() > oldaccount.monster.getHP())
				account.monster.setHP(oldaccount.monster.getHP());
			if (account.monster.getHP() <= 0) {
				// 沒命出局
				totalScore = -1.0;
				System.out.println("你死了");
				return totalScore;
			}
		} else {
			// 數值比對方差，戰敗
			totalScore = -1.0;
			System.out.println("你死了");
			return totalScore;
		}
		totalScore += enemy.getScore();
		return totalScore;
	}

	@Override
	public String output( Chromosome<Props>[] chromosomes) {
		// TODO Auto-generated method stub
		String string = "";
		for (int i = 0; i < chromosomes[0].getChromosome().length; i++) {
			if(chromosomes[0].getFitnessValue()==0.0) {
				string+="無獎勵";
				break;
			}
			if(chromosomes[0].getFitnessValue()<0.0) {
				string+="你死了";
				account.monster.setSlimeState(new int[] {0,0,0,0});
				break;
			}
			if (i != 0) {
				string += ",";
			}
			string += chromosomes[0].getChromosome()[i].getName();
		}
		
		return string;
		//System.out.print(string);
		//System.out.print("\t玩家積分:"+totalScore+"\t道具積分:"+totalValue);
		//System.out.println();
	}
	
	/*
	 * 檢查
	 */
	public String check(Chromosome<Props> chr) {
		String string="[";
		for(int i=0;i<chr.getChromosome().length;i++) {
			if (i != 0) {
				string+=",";
			}
			string+= chr.getChromosome()[i].getName();
		}
		string +="]\t" + chr.getFitnessValue();
		
		return string;
	}

	@Override
	public double alive_value() {
		// TODO Auto-generated method stub
		return totalScore;
	}
	public Account getAccount() {
		for(int i=0;i< account.monster.getStates().length;i++) {
			if(account.monster.getStates(i)>0) {
				account.monster.addHungerValue(-5);
				account.monster.addThirstValue(-5);
				account.monster.addMoodValue(+5);
				account.monster.addHealthValue(-5);
			}
		}
		
		return account;
	}
}
