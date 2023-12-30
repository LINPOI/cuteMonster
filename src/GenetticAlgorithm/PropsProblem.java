package GenetticAlgorithm;

import java.util.Random;
/*
 * 設定最優解=玩家積分該獲得的獎勵應該是好的，不該是全部都一等
 * 判斷積分最高價值組合
 */
public class PropsProblem implements Problem<Integer>{
	private Random random;
	public PropsProblem(Random random) {
		this.random=random;
	}
	@Override
	public Chromosome<Integer>[] generateChromosomes(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer generateGene(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateFitness(Chromosome<Integer>[] chrs) {
		// TODO Auto-generated method stub
		
	}


}
