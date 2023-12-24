package test;

public class MainClass implements DataChangeListener {
    private int data = 10;
    private SubClass subClass;

    public MainClass() {
        subClass = new SubClass(this); // 将主类实例传递给副类
    }

    public void increaseData() {
        data++;
        System.out.println("Main Class: " + data);
    }

    public int getData() {
        return data;
    }

    public static void main(String[] args) {
        MainClass main = new MainClass();
        main.start();
    }

    public void start() {
        // 模拟按下按钮后的操作
        subClass.buttonPressed();
    }

    @Override
    public void onDataChanged(int newData) {
        // 在副类数据更改时触发
        data = newData;
        System.out.println("Main Class Updated: " + data);
    }
}
