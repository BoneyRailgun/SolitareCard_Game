//�˿����࣬ÿһ���������һ����
public class Card {
	// �ƵĻ�ɫ
	private String kind;
	// �Ƶĵ���
	private int number;
	// �Ƶ�����
	private int x = 0, y = 0;
	// �����ڵĲ���
	private int layer = 0;

	// ����һ����
	public Card(String kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	// �����Ƶ�����
	public String getKind() {
		return kind;
	}

	// �����Ƶĵ���
	public int getNumber() {
		return number;
	}

	// �����Ƶ�λ�õĺ�����
	public int getX() {
		return x;
	}

	// �����Ƶ�λ�õ�������
	public int getY() {
		return y;
	}

	// �����Ƶ�λ�õĺ�����
	public void setX(int number) {
		x = number;
	}

	// �����Ƶ�λ�õ�������
	public void setY(int number) {
		y = number;
	}

	// ���������ڵĲ���
	public void setLayer(int layer) {
		this.layer = layer;
	}

	// ���������ڵĲ���
	public int getLayer() {
		return layer;
	}

}
