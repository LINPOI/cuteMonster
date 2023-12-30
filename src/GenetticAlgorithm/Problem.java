package GenetticAlgorithm;
/*
 * 添加需解決
 */
public interface Problem<T>{
	public Chromosome<T>[] generateChromosomes(int size);//設定染色體
	public T generateGene(int index);//設定基因
	public void calculateFitness(Chromosome<T>[] chrs) ;//設定適應值
}