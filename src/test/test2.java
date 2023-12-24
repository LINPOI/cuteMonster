package test;

public class test2 {
	public class ClassA {
	    private int data;

	    public ClassA(int data) {
	        this.data = data;
	    }

	    public int getData() {
	        return data;
	    }
	}

	public class ClassB {
	    private ClassA instanceA;

	    public ClassB() {
	        int someData = 10;
	        instanceA = new ClassA(someData);
	    }

	    public void useDataFromA() {
	        int dataFromA = instanceA.getData();
	        // 使用来自ClassA的数据
	    }
	}
}

