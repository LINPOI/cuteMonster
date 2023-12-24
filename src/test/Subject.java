package test;

import java.util.ArrayList;
import java.util.List;

//被觀察者類
class Subject {
	private int value;
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void setValue(int value) {
		this.value = value;
		notifyObservers(); // 設定新值後通知所有觀察者
	}

	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(value); // 更新所有觀察者
		}
	}
	public int getValue() {
		return value;
	}
}
