import java.util.*;

//��������ÿ�ο����ṩһ����
public class Discard {
	private LinkedList<Card> discard;

	// ʹ��һ���������洢
	public Discard() {
		discard = new LinkedList();
	}

	// ���ص�index+1����
	public Card get(int index) {
		return discard.get(index);
	}

	// ��������Ƽ��뵽��index+1��λ��
	public void add(int index, Card card) {
		discard.add(index, card);
	}

	//����index+1�����Ƴ�
	public Card delete(int index) {
		return discard.remove(index);
	}

	//�õ��������Ƶ�����
	public int length() {
		return discard.size();
	}

}
