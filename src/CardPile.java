import java.util.*;
//牌堆类，所有牌堆都是继承的这个类，使用堆栈的数据结构
public class CardPile {

	private Stack<Card> cardpile;
	
	//生成一个新的牌堆
	public CardPile() {
		cardpile = new Stack();
	}
	
	//将传入的牌加入堆栈
	public void add(Card card) {
		cardpile.push(card);
	}

	//将堆栈最顶端的牌移出并返回这张牌
	public Card delete() {
		return cardpile.pop();
	}

	//测试堆栈是否为空
	public boolean isEmpty() {
		return cardpile.isEmpty();
	}

	//得到堆栈中低index+1个元素
	public Card getCard(int index) {
		return cardpile.get(index);
	}

	//得到堆栈的长度
	public int length() {
		return cardpile.size();
	}

}
