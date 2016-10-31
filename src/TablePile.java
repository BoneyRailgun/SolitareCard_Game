//桌面上的牌堆类，不同颜色的牌可以按照大小顺序依次叠放
public class TablePile extends CardPile {
	// 最下面一张牌的颜色
	private String kind = null;

	// 最下面一张牌的大小
	private int nowNumber = 0;

	// 调用 父类构造器
	public TablePile() {
		super();
	}

	// 将牌加入牌堆，更新最下面牌的大小及花色
	public void add(Card card) {
		super.add(card);
		nowNumber = card.getNumber();
		kind = card.getKind().substring(0, 1);
	}

	// 将最下面的牌移出，并更新最下面的牌的大小及花色
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

	// 判断传入的牌是否可以加入这个牌堆
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

	// 得到最下面牌的大小
	public int getTopCardNumber() {
		return nowNumber;
	}

}
