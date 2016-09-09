import java.util.*;
public class MySort{
	
	public ArrayList<PageEntry> sortThisList(MySet<SearchResult> listOfSortableEntries)
	{	ArrayList<SearchResult> sortedlist=new ArrayList<SearchResult>();  
		Node<SearchResult> top=listOfSortableEntries.getTop();
		while(top!=null)
		{   
			sortedlist.add(top.getElement());			
			top=top.getNext();
		}
		/*for(int k=0;k<=sortedlist.size()-1;k++)
		{
		System.out.println(sortedlist.get(k).r);
		}*/
		//sorting by using bubble sort
		if(sortedlist.size()>1)
		{for(int i=1;i<=sortedlist.size()-1;i++)
		{	
				for(int j=0;j<=sortedlist.size()-1-i;j++)
				{	
				if(sortedlist.get(j).compareTo(sortedlist.get(j+1))<0)
					{ 
					SearchResult tmp=sortedlist.remove(j);
					SearchResult tmp1=sortedlist.remove(j);
					sortedlist.add(j,tmp1);
					sortedlist.add(j+1,tmp);
					}				
				}
		}
		}
		ArrayList<PageEntry> sortedpe=new ArrayList<PageEntry>();
		for(int k=0;k<=sortedlist.size()-1;k++)
			{sortedpe.add(sortedlist.get(k).getPageEntry());
			//System.out.println(sortedlist.get(k).r);
			}
		return sortedpe;
	}
}
