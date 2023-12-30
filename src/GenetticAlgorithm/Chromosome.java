package GenetticAlgorithm;
/*
 * 設定基因
 */
public class Chromosome<T> {
	private T[] genes=null;//設定基因
	private double fitnessValue=0.0d;
	public Chromosome() {
		// TODO Auto-generated constructor stub
	}
	public void setGenes(T[] value) {
		genes =value.clone();//導入陣列
	}
	public T[] getChromosome() {
		return genes;
	}
	public void setFitnessValue(double value) {
		fitnessValue =value;//導入值
	}
	public double getFitnessValue() {
		return fitnessValue;
	}
}
