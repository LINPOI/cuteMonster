package Props;

public class Props {
	private int id;// 道具等級
	private String name;// 道具名稱
	private int level;// 道具等級
	private int value;// 道具價值
	private int location;//出現場地
	public Props() {
		// TODO Auto-generated constructor stub
	}

	public Props(int id,String name,int level) {
		this.id=id;
		this.name=name;
		this.level=level;
		this.value=level;
	}

	public Props(int id,String name,int level,int value) {
		this.id=id;
		this.name=name;
		this.level=level;
		this.value=level*value;
	}
}
