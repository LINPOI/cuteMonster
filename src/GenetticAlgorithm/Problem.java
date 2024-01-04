package GenetticAlgorithm;

import Props.Props;
import finalproject2.Account;

/*
 * 添加需解決
 */
public interface Problem<T>{
	public Chromosome<T>[] generateChromosomes(int size);//設定染色體
	public T[] mutationChromosomes();//變異染色體
	public void calculateFitness(Chromosome<T>[] chrs) ;//設定適應值
	public String output(Chromosome<T>[]chromosomes);
	public String check(Chromosome<T> chr);
	public double alive_value();
	public Account getAccount() ;
}