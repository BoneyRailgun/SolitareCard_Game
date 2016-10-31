import java.util.*;

//游戏的内部数据类存储所有数据
public class InsideData {
	// 所有牌的队列，用于对牌进行初始化
	public static ArrayList<Card> allcard;
	// 所有牌的队列副本，用于记录牌的位置，方便得到牌
	public static ArrayList<Card> getcard;
	// 供牌区
	public static Discard discard;
	// 暂时存放从tablepile移出的牌堆
	public static CardPile temppile;
	// 匹配的牌堆，一共四个，代表四种花色
	public static SuitPile suitpile[];
	// 桌面上的牌堆，一共七个，代表七堆牌
	public static TablePile tablepile[];

	// 对所有内部数据进行初始化
	public InsideData() {
		Card card;
		allcard = new ArrayList<Card>();
		getcard = new ArrayList<Card>();

		// 创建52张牌
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

		// 洗牌
		Random random = new Random();
		for (int i = 0; i < 52; i++) {
			int j = Math.abs(random.nextInt() % 52);
			Card temp = allcard.get(i);
			allcard.set(i, allcard.get(j));
			allcard.set(j, temp);
		}

		// 创建供牌区，将24张牌加入供牌区
		discard = new Discard();
		for (int i = 0; i < 24; i++)

		{
			card = allcard.remove(0);
			card.setX(190);
			card.setY(20);
			discard.add(i, card);
		}

		// 创建适合牌堆，开始时没有牌
		suitpile = new SuitPile[4];
		for (int i = 0; i < 4; i++)
			suitpile[i] = new SuitPile();

		// 创建桌面牌堆，一共从左到右牌依次增加
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

		// 创建暂存牌堆，一开始没有牌
		temppile = new CardPile();
	}

}
