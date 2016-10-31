import java.util.*;
//�ƶ��࣬�����ƶѶ��Ǽ̳е�����࣬ʹ�ö�ջ�����ݽṹ
public class CardPile {

	private Stack<Card> cardpile;
	
	//����һ���µ��ƶ�
	public CardPile() {
		cardpile = new Stack();
	}
	
	//��������Ƽ����ջ
	public void add(Card card) {
		cardpile.push(card);
	}

	//����ջ��˵����Ƴ�������������
	public Card delete() {
		return cardpile.pop();
	}

	//���Զ�ջ�Ƿ�Ϊ��
	public boolean isEmpty() {
		return cardpile.isEmpty();
	}

	//�õ���ջ�е�index+1��Ԫ��
	public Card getCard(int index) {
		return cardpile.get(index);
	}

	//�õ���ջ�ĳ���
	public int length() {
		return cardpile.size();
	}

}
