
public class Position{
	private PageEntry pe;
	private int wi;
	private int wiamongnonconnectingword;
	public Position(PageEntry p,int wordIndex,int wianc)
	{  pe=p;
	   wi=wordIndex;
	   wiamongnonconnectingword=wianc;
	}
	public PageEntry getPageEntry(){
		return pe;
	}
	public int getWordIndex(){
		return wi;
	}
	public int getOtherwi(){
		return wiamongnonconnectingword;
	}
}
