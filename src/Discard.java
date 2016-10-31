import java.util.*;

//供牌区，每次可以提供一张牌
public class Discard {
	private LinkedList<Card> discard;

	// 使用一个链表来存储
	public Discard() {
		discard = new LinkedList();
	}

	// 返回第index+1张牌
	public Card get(int index) {
		return discard.get(index);
	}

	// 将传入的牌加入到第index+1的位置
	public void add(int index, Card card) {
		discard.add(index, card);
	}

	//将第index+1张牌移出
	public Card delete(int index) {
		return discard.remove(index);
	}

	//得到供牌区牌的张数
	public int length() {
		return discard.size();
	}

}
