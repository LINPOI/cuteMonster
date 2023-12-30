package finalproject2;

import java.util.ArrayList;
import java.util.List;

//被觀察者類
class Subject {
	private String[] strings;
	private int[] ints;
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(Observer observer) {//登入觀察者
		observers.add(observer);
	}

	public void setStrings( String[]  strings) {
		this.strings = strings;
		notifyObservers(); // 設定新值後通知所有觀察者
	}
	public String[] getStrings() {
		return strings;
	}
	
	public void setStates( int[]  ints) {
		this.ints = ints;
		notifyObservers(); // 設定新值後通知所有觀察者
	}
	public int[] getInts() {
		return ints;
	}
	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(strings); // 更新所有觀察者
			observer.updataInt(ints);
		}
	}
	/*
	 * 有人set之後其他人有加入的會收到
	 */
}
