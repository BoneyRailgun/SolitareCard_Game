//ƥ����ƶѣ���ͬ��ɫ���ƿ��԰�˳�����ε���
public class SuitPile extends CardPile {
	//�ƶ��е��ƵĻ�ɫ
	private String kind = null;
	//�ƶ���������ƵĴ�С
	private int nowNumber=0;

	//���ø��๹����
	public SuitPile() {
		super();
	}

	//���Ƽ����ƶѣ��������������ƵĴ�С
	public void add(Card card) {
		super.add(card);
		nowNumber=card.getNumber();
		if (kind == null)
			kind = card.getKind();
	}

	//�����Ƴ��ƶѣ������������ƵĴ�С���������Ƴ�����
	public Card delete() {
		Card card= super.delete();
		nowNumber--;
		if(isEmpty())
		kind=null;
	    return card;
	}
	
	//�жϴ�������Ƿ���Լ����ƶ�
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
