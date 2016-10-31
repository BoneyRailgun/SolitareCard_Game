//�����ϵ��ƶ��࣬��ͬ��ɫ���ƿ��԰��մ�С˳�����ε���
public class TablePile extends CardPile {
	// ������һ���Ƶ���ɫ
	private String kind = null;

	// ������һ���ƵĴ�С
	private int nowNumber = 0;

	// ���� ���๹����
	public TablePile() {
		super();
	}

	// ���Ƽ����ƶѣ������������ƵĴ�С����ɫ
	public void add(Card card) {
		super.add(card);
		nowNumber = card.getNumber();
		kind = card.getKind().substring(0, 1);
	}

	// ������������Ƴ�����������������ƵĴ�С����ɫ
	public Card delete() {
		Card card = super.delete();
		if (isEmpty()) {
			kind = null;
			nowNumber = 0;
		} else {
			kind = getCard(length() - 1).getKind().substring(0, 1);
			nowNumber = getCard(length() - 1).getNumber();
		}
		return card;
	}

	// �жϴ�������Ƿ���Լ�������ƶ�
	public boolean isSuitable(Card card) {
		if (isEmpty()) {
			if (card.getNumber() == 13)
				return true;
			else
				return false;
		} else {
			if (!(card.getKind().substring(0, 1).equals(kind))
					&& card.getNumber() == nowNumber - 1)
				return true;
			else
				return false;
		}
	}

	// �õ��������ƵĴ�С
	public int getTopCardNumber() {
		return nowNumber;
	}

}
