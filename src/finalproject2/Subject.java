package finalproject2;

import java.util.ArrayList;
import java.util.List;

//被觀察者類
class Subject {

	private Account account;
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(Observer observer) {//登入觀察者
		observers.add(observer);
	}


	public void setAccount( Account account) {
		this.account = account;
		notifyObservers(); // 設定新值後通知所有觀察者
	}
	public Account getAccount() {
		return account;
	}
	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.updataAccount(account);
		}
	}
	
	/*
	 * 有人set之後其他人有加入的會收到
	 */
}
