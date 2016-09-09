
public class InvertedPageIndex {
	private MyHashTable mht;
	private MySet<PageEntry> pset;
	
	InvertedPageIndex(){
		pset=new MySet<PageEntry>();
		mht=new MyHashTable();
	}
	public void addPage(PageEntry p){
		pset.addElement(p);
		Node<WordEntry> topoflistofword=p.getPageIndex().getWordEntries().getTop();
		while(topoflistofword!=null){
			mht.addPositionsForWord(topoflistofword.getElement());
			topoflistofword=topoflistofword.getNext();
		}	
	}
	
	public MySet<PageEntry> getPagesWhichContainsWord(String str)
	{
		MySet<PageEntry> pagewhichcontainingwords=new MySet<PageEntry>();
		WordEntry foundwe=mht.getWordentryfromhashtable(str);
		if(foundwe==null)
			return null;
		Node<Position> top=foundwe.getAllPositionsForThisWord().getTop();
		while(top!=null)
		{
			pagewhichcontainingwords.addElement(top.getElement().getPageEntry());
			top=top.getNext();
		}		
		return pagewhichcontainingwords;
	}
	public MyLinkedList<Position> FindPositionsOfWordInAPage(String wd,String pagename)
	{   MyLinkedList<Position> listofposition=new MyLinkedList<Position>();
		WordEntry foundwde=mht.getWordentryfromhashtable(wd);
		
		if(foundwde==null)
			return null;
		Node<Position> tmp=foundwde.getAllPositionsForThisWord().getTop();
		while(tmp!=null)
		{
			if(tmp.getElement().getPageEntry().getPageName().equals(pagename))
				listofposition.Insert(tmp.getElement());
			tmp=tmp.getNext();
		}
		return listofposition;
	}
	public MySet<PageEntry> getsetofpe()
	{
		return pset;
	}

	public MySet<PageEntry> getPagesWhichContainsallWord(String str1[])
	{	
		MySet<PageEntry> pagewhichcontainingallwords=new MySet<PageEntry>();	
		if(getPagesWhichContainsWord(str1[0])==null)
			return null;
		pagewhichcontainingallwords=getPagesWhichContainsWord(str1[0]);
		if(str1.length>1)
		{
			for(int i=1;i<str1.length;i++)
			{
				MySet<PageEntry> pagewhichcontainingoneword=getPagesWhichContainsWord(str1[i]);
				if(pagewhichcontainingoneword==null)
					return null;
				else
					pagewhichcontainingallwords=pagewhichcontainingallwords.intersection(pagewhichcontainingoneword);
			}
		}
		return pagewhichcontainingallwords;
	}
	public MySet<PageEntry> getPagesWhichContainsanyWord(String str2[])
	{
		MySet<PageEntry> pagewhichcontaininganyword=new MySet<PageEntry>();	
		pagewhichcontaininganyword=getPagesWhichContainsWord(str2[0]);
		if(str2.length>1)
		{
			for(int i=1;i<str2.length;i++)
			{
				MySet<PageEntry> pagewhichcontainingoneword=getPagesWhichContainsWord(str2[i]);
				pagewhichcontaininganyword=pagewhichcontaininganyword.union(pagewhichcontainingoneword);
			}
		}
		return pagewhichcontaininganyword;
	}
	public MySet<SearchResult> getPagesWhichContainPhrase(String str[])
	{	MySet<SearchResult> finale= new MySet<SearchResult>();
	MySet<PageEntry> pagecontainallword=getPagesWhichContainsallWord(str);
	if(pagecontainallword==null)
		return null;
	Node<PageEntry> top= pagecontainallword.getTop();
	while(top!=null)
	{    										float rv=0.0f;
												Node<Position> valid = FindPositionsOfWordInAPage(str[0],top.getElement().getPageName()).getTop();
												boolean b=false;
												while(valid!=null)
												{	int frrv=valid.getElement().getWordIndex();
													int check= valid.getElement().getOtherwi();	
													float nextfirstrv=0.0f;
													for(int i=1;i<str.length;i++)
													{
														AVLNode fd= top.getElement().getTree().findElement(check+i);							
														if(fd!=null)
															if(fd.data.equals(str[i]))
															{	nextfirstrv+=(float)1/(fd.getWin()*fd.getWin());
																b=true;
															}
															else
															{	b=false;
																break;
															}
													}
													if(b)
													{	rv=(float)(1/(frrv*frrv));
														rv=rv+nextfirstrv;
													}
													valid=valid.getNext();
												}
													if(rv!=0)
														finale.addElement(new SearchResult(top.getElement(),rv));
													top=top.getNext();
	}
	return finale;
	}	

}
