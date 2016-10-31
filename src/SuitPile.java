//匹配的牌堆，相同花色的牌可以按顺序依次叠放
public class SuitPile extends CardPile {
	//牌堆中的牌的花色
	private String kind = null;
	//牌堆最下面的牌的大小
	private int nowNumber=0;

	//调用父类构造器
	public SuitPile() {
		super();
	}

	//将牌加入牌堆，并更新最下面牌的大小
	public void add(Card card) {
		super.add(card);
		nowNumber=card.getNumber();
		if (kind == null)
			kind = card.getKind();
	}

	//将牌移出牌堆，更新最下面牌的大小，并返回移出的牌
	public Card delete() {
		Card card= super.delete();
		nowNumber--;
		if(isEmpty())
		kind=null;
	    return card;
	}
	
	//判断传入的牌是否可以加入牌堆
	public boolean isSuitable(Card card)
	{ if(isEmpty())
		{if(card.getNumber()==1)
		    return true;   
		else
			return false;
		}
	  else
	  {if(card.getKind().equals(kind)&&card.getNumber()==nowNumber+1)
		 return true;
	  else
		  return false;
	  }
	 }
	
	

}
