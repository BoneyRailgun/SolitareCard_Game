import java.util.*;

//��Ϸ���ڲ�������洢��������
public class InsideData {
	// �����ƵĶ��У����ڶ��ƽ��г�ʼ��
	public static ArrayList<Card> allcard;
	// �����ƵĶ��и��������ڼ�¼�Ƶ�λ�ã�����õ���
	public static ArrayList<Card> getcard;
	// ������
	public static Discard discard;
	// ��ʱ��Ŵ�tablepile�Ƴ����ƶ�
	public static CardPile temppile;
	// ƥ����ƶѣ�һ���ĸ����������ֻ�ɫ
	public static SuitPile suitpile[];
	// �����ϵ��ƶѣ�һ���߸��������߶���
	public static TablePile tablepile[];

	// �������ڲ����ݽ��г�ʼ��
	public InsideData() {
		Card card;
		allcard = new ArrayList<Card>();
		getcard = new ArrayList<Card>();

		// ����52����
		for (int i = 0; i < 13; i++) {
			card = new Card("RP", i + 1);
			allcard.add(card);
			getcard.add(card);
		}

		for (int i = 0; i < 13; i++) {
			card = new Card("RD", i + 1);
			allcard.add(card);
			getcard.add(card);
		}

		for (int i = 0; i < 13; i++) {
			card = new Card("BP", i + 1);
			allcard.add(card);
			getcard.add(card);
		}

		for (int i = 0; i < 13; i++) {
			card = new Card("BF", i + 1);
			allcard.add(card);
			getcard.add(card);
		}

		// ϴ��
		Random random = new Random();
		for (int i = 0; i < 52; i++) {
			int j = Math.abs(random.nextInt() % 52);
			Card temp = allcard.get(i);
			allcard.set(i, allcard.get(j));
			allcard.set(j, temp);
		}

		// ��������������24���Ƽ��빩����
		discard = new Discard();
		for (int i = 0; i < 24; i++)

		{
			card = allcard.remove(0);
			card.setX(190);
			card.setY(20);
			discard.add(i, card);
		}

		// �����ʺ��ƶѣ���ʼʱû����
		suitpile = new SuitPile[4];
		for (int i = 0; i < 4; i++)
			suitpile[i] = new SuitPile();

		// ���������ƶѣ�һ������������������
		tablepile = new TablePile[7];
		for (int i = 0; i < 7; i++) {
			int j;
			tablepile[i] = new TablePile();
			for (j = 0; j <= i; j++) {
				card = allcard.remove(0);
				card.setX(20 + i * 170);
				card.setY(300 + j * 20);
				card.setLayer(j + 1);
				tablepile[i].add(card);
			}
		}

		// �����ݴ��ƶѣ�һ��ʼû����
		temppile = new CardPile();
	}

}
