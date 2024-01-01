package GenetticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import Props.Props;
import finalproject2.Account;
/*
 * 
 */

public class GenetticAlgorithm<T> {
	private Chromosome<T>[] chromosomes = null;// 有x個染色體(chromosome)
	private int chromosomeSize = 4;
	private Chromosome<T>[] springs = new Chromosome[4];// 子代二對染色體=4個子代
	private int generationSize = 300;// 迭代次數
	private double crossoverRate = 0.75;// 交配率0.75
	private double mutaionRate = 0.3;// 突變率

	Random random = new Random();
	Problem<T> problem;

	public GenetticAlgorithm(Account account, int location, int difficulty) {
		// TODO Auto-generated constructor stub
		problem = (Problem<T>) new PropsProblem(random, account, location, difficulty);// 給值
		random.setSeed(2);

		generatiChromosome();// 第一步生成染色體

		// repeat
		int numberOfGeneration = 1;
		int i = 1;
		while (numberOfGeneration <= generationSize) {
			if (problem.alive() < 0) {
				chromosomes[0].setFitnessValue(-1.0);
				break;
			}
			calFitnessValue();// 2評估適應值
			// System.out.println("2站正常");
			selection();// 3選擇
//			System.out.println("----------------------selection--------------------------");
//			i=1;
//			for(Chromosome<T> chr:springs) {
//			System.out.println("springs "+i+""+
//					problem.check(chr));
//			i++;
//			}
			crossover();// 4交配
//			System.out.println("----------------------crossover--------------------------");
//			i=1;
//			for(Chromosome<T> chr:springs) {
//			System.out.println("springs "+i+""+
//					problem.check(chr));
//			i++;
//			}
			mutation();// 5突變
//			System.out.println("----------------------mutation--------------------------");
//			i=1;
//			for(Chromosome<T> chr:springs) {
//			System.out.println("springs "+i+""+
//					problem.check(chr));
//			i++;
//			}

			calSpringFitnessValue();

			updatingPopulation();// 6篩選最好
//			System.out.println("----------------------結果--------------------------");
//			System.out.println("第" + numberOfGeneration + "次迭代，最佳解為:[" + problem.output(chromosomes) + "]\t"
//					+chromosomes[0].getFitnessValue());
			numberOfGeneration++;
		}
		System.out.println("第" + generationSize + "次迭代，最佳解為:[" + problem.output(chromosomes) + "]\t"
				+chromosomes[0].getFitnessValue());
	}

	// 編碼
	public void generatiChromosome() {
		chromosomes = problem.generateChromosomes(chromosomeSize);

	}

	// 適應值
	private void calFitnessValue() {
		problem.calculateFitness(chromosomes);
	}

	// 子代適應值(突變後重新計算)
	private void calSpringFitnessValue() {
		problem.calculateFitness(springs);
		// System.out.println("2:"+springs[2].getChromosome().length+",3:"+springs[3].getChromosome().length);
	}

	private void selection() {// 選擇
		LinkedList<Chromosome> population = new LinkedList<Chromosome>();// 重新建立一個人群
		for (Chromosome chr : chromosomes) {
			population.add(chr);// 把現在的染色體放進去
		}

		for (int i = 0; i < springs.length; i++) {
			Chromosome chr = population.remove(random.nextInt(population.size()));// 隨機移除群體並加入成為子代
			springs[i] = new Chromosome();// 初始化子代每個染色體
			springs[i].setGenes((T[]) chr.getChromosome());// 子代每個染色體獲得基因
			springs[i].setFitnessValue(chr.getFitnessValue());// 子代每個染色體獲得適應值

		}
//		System.out.println("0:" + springs[0].getChromosome().length + ",1:" + springs[1].getChromosome().length + ",2:"
//				+ springs[2].getChromosome().length + ",3:" + springs[3].getChromosome().length);// 問題出在這裡
	}

	private void crossover() {// 交配

		for (int i = 0; i < springs.length; i = i + 2) {// 交配需要兩條
			if (random.nextDouble() < crossoverRate) {// 隨機數值看看能不能交配

				twoPointChange(springs[i], springs[i + 1]);

			}
		}
	}

	private void twoPointChange(Chromosome chr1, Chromosome chr2) {// 雙點交換

		int first = 0;
		int second = 0;
		int length1 = chr1.getChromosome().length;
		int length2 = chr2.getChromosome().length;
		if (length1 > length2) {// 找比較短的那個
			first = random.nextInt(length2);// 隨機數，小於染色體長度
			second = random.nextInt(length2);// 隨機數，小於染色體長度
		} else {
			first = random.nextInt(length1);// 隨機數，小於染色體長度
			second = random.nextInt(length1);// 隨機數，小於染色體長度
		}

		if (first > second) {// 第二條要大於第一條
			int temp = first;
			first = second;
			second = temp;
		}
		for (int i = first; i < second; i++) {// 交換，交換範圍是兩個隨機數[first,second]中的基因
			Object temp = chr1.getChromosome()[i];
			chr1.getChromosome()[i] = chr2.getChromosome()[i];
			chr2.getChromosome()[i] = temp;
		}
	}

	private void mutation() {// 突變
		for (Chromosome spr : springs) {
			for (int i = 0; i < spr.getChromosome().length; i++) {// 子代所有染色體
				if (random.nextDouble() < mutaionRate) {// 隨機數值計算突變率
					spr.setGenes(problem.mutationChromosomes());

				}
			}
		}
	}

	private void updatingPopulation() {// 更新族群
		ArrayList<Chromosome> pop = new ArrayList<Chromosome>();
		for (Chromosome chr : chromosomes) {
			pop.add(chr);// 新增進新鏈結串列
		}
		for (Chromosome chr : springs) {
			pop.add(chr);// 新增進新鏈結串列
		}
		Collections.sort(pop, new Comparator<Chromosome>() {// 搜尋演算法

			@Override
			public int compare(Chromosome o1, Chromosome o2) {//
				// TODO Auto-generated method stub
				if (o1.getFitnessValue() > o2.getFitnessValue()) {
					return -1;// 交換
				} else if (o1.getFitnessValue() < o2.getFitnessValue()) {
					return 1;// 不交換
				} else {
					return 0;
				}
				// 最後好的都到前面了
			}

		});
		chromosomes = new Chromosome[chromosomeSize];// 最後留下新族群數量
		for (int i = 0; i < chromosomes.length; i++) {
			chromosomes[i] = new Chromosome();
			Chromosome chr = pop.remove(0);// 將最前面的染色體丟到新的染色體中
			chromosomes[i].setGenes((T[]) chr.getChromosome());// 拿到最新染色體的基因
			chromosomes[i].setFitnessValue(chr.getFitnessValue());// 拿到最新染色體的適應值
		}
	}

	public String getString() {
		return problem.output(chromosomes);
	}
	public  Chromosome<T> getlastChromosome(){
		
		return chromosomes[0];
	}
	public static void main(String[] args) {
		GenetticAlgorithm<Props> gAlgorithm = new GenetticAlgorithm<Props>(new Account(), 1, 1);
	}
}
