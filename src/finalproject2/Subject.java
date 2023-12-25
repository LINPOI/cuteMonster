package finalproject2;

import java.util.ArrayList;
import java.util.List;

//被觀察者類
class Subject {
	private String[] strings;
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void setStrings( String[]  strings) {
		this.strings = strings;
		notifyObservers(); // 設定新值後通知所有觀察者
	}

	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(strings); // 更新所有觀察者
		}
	}
	public String[] getStrings() {
		return strings;
	}
}
