//扑克牌类，每一个对象代表一张牌
public class Card {
	// 牌的花色
	private String kind;
	// 牌的点数
	private int number;
	// 牌的坐标
	private int x = 0, y = 0;
	// 牌所在的层数
	private int layer = 0;

	// 创建一张牌
	public Card(String kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	// 返回牌的种类
	public String getKind() {
		return kind;
	}

	// 返回牌的点数
	public int getNumber() {
		return number;
	}

	// 返回牌的位置的横坐标
	public int getX() {
		return x;
	}

	// 返回牌的位置的纵坐标
	public int getY() {
		return y;
	}

	// 设置牌的位置的横坐标
	public void setX(int number) {
		x = number;
	}

	// 设置牌的位置的纵坐标
	public void setY(int number) {
		y = number;
	}

	// 设置牌所在的层数
	public void setLayer(int layer) {
		this.layer = layer;
	}

	// 返回牌所在的层数
	public int getLayer() {
		return layer;
	}

}
