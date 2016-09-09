
public class SearchResult {
	private PageEntry pe;
	private float r;
	SearchResult(PageEntry p,float re)
	{
		pe=p;
		r=re;
		
	}
	public PageEntry getPageEntry()
	{
		return pe;
	}
	public float getRelevance()
	{
		return r;
	}
	public int compareTo(SearchResult otherObject)
	{	if(this.r-otherObject.getRelevance()<0)
			return -1;
		else if(this.r-otherObject.getRelevance()>0)
			return 1;
		else
			return 0;	
	}
}
