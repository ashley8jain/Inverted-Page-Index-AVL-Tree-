import java.io.*;
import java.util.*;

public class SearchEngine{
	private InvertedPageIndex ipi;
	private PageEntry updatingword=new PageEntry();
	private MySort ms=new MySort(); 
	public SearchEngine() {
		ipi=new InvertedPageIndex();
	}
public boolean checkPage(String pn)
{
    MySet<PageEntry> peset=ipi.getsetofpe();
    Node<PageEntry> tp=peset.getTop();
    while(tp!=null)
    {
    	if(tp.getElement().getPageName().equals(pn))
    		return true;
    	tp=tp.getNext();
    }
	return false;
}

	public void performAction(String actionMessage) throws IOException
    {try{String[] action=actionMessage.split(" ");
		if(action[0].equals("addPage")){
			PageEntry addpage=new PageEntry(action[1]);
			ipi.addPage(addpage);
		}
		else if(action[0].equals("queryFindPositionsOfWordInAPage")){
			System.out.print(actionMessage+": ");
			if(action[1].charAt(action[1].length()-1)=='s')
				action[1]=action[1].substring(0,action[1].length()-1);
			String updatedwrd=updatingword.update1(action[1]);
			MyLinkedList<Position>	listofpositionwhichcontainingword=ipi.FindPositionsOfWordInAPage(updatedwrd,action[2]);
			
			if(!checkPage(action[2]))
				throw new  IOException("Given Webpage is not found!!!");
			Node<Position> topofposition=listofpositionwhichcontainingword.getTop();
			if(topofposition==null)
				throw new IOException("Given word is not found in given page");
			while(topofposition!=null)
			{   System.out.print(topofposition.getElement().getWordIndex());
			    if(topofposition.getNext()!=null)
			    	System.out.print(",");
				topofposition=topofposition.getNext();
			}
			System.out.println("");
		}
		else if(action[0].equals("queryFindPagesWhichContainWord")){
			System.out.print(actionMessage+": ");
			
			if(action[1].charAt(action[1].length()-1)=='s')
				action[1]=action[1].substring(0,action[1].length()-1);
			String updatedword=updatingword.update1(action[1]);
			MySet<PageEntry> setofpageentrycontainingword=ipi.getPagesWhichContainsWord(updatedword);
			if(setofpageentrycontainingword==null)
				throw new IOException("Given word is not available in any pages!!!!");
			MySet<SearchResult> unsortedlist=new MySet<SearchResult>();
			Node<PageEntry> tp=setofpageentrycontainingword.getTop();
			
			while(tp!=null)
			{	
				SearchResult nw=new SearchResult(tp.getElement(),tp.getElement().rel(updatedword));
				unsortedlist.addElement(nw);
				tp=tp.getNext();
			}
			
			ArrayList<PageEntry> arrayofpageentry=ms.sortThisList(unsortedlist);
			int countt=0;
			while(countt!=arrayofpageentry.size())
			{	System.out.print(arrayofpageentry.get(countt).getPageName());
				if(countt+1!=arrayofpageentry.size()) //for print comma upto last
					System.out.print(",");
			countt++;	
			}			
			/*Node<PageEntry> topoflistofpageentry=setofpageentrycontainingword.getTop();
			while(topoflistofpageentry!=null)
			{
				System.out.print(topoflistofpageentry.getElement().getPageName());
				if(topoflistofpageentry.getNext()!=null)
					System.out.print(",");
				topoflistofpageentry=topoflistofpageentry.getNext();				
			}*/
			System.out.println("");
		}
		else if(action[0].equals("queryFindPagesWhichContainAllWords"))
		{ System.out.print(actionMessage+": ");
		  String[] allword=new String[action.length-1];
			for(int j=0;j<action.length-1;j++)
			{   if(action[j+1].charAt(action[j+1].length()-1)=='s')
				action[j+1]=action[j-1].substring(0,action[j+1].length()-1);
			    action[j+1]=updatingword.update1(action[j+1]);
				allword[j]=action[j+1];
			}
		MySet<PageEntry> setofpageentrycontainingallword=ipi.getPagesWhichContainsallWord(allword);
		if(setofpageentrycontainingallword==null)
			throw new IOException("Given all word is not available in any page!!!!");
		MySet<SearchResult> unsortedlist1=new MySet<SearchResult>();
		Node<PageEntry> tp1=setofpageentrycontainingallword.getTop();
		
		while(tp1!=null)
		{	
			SearchResult nw1=new SearchResult(tp1.getElement(),tp1.getElement().getRelevanceOfPage(allword, false));
			unsortedlist1.addElement(nw1);
			tp1=tp1.getNext();
		}
		
		ArrayList<PageEntry> arrayofpageentry1=ms.sortThisList(unsortedlist1);
		int counttt=0;
		while(counttt!=arrayofpageentry1.size())
		{	System.out.print(arrayofpageentry1.get(counttt).getPageName());
			if(counttt+1!=arrayofpageentry1.size()) //for print comma upto last
				System.out.print(",");
		counttt++;	
		}		
		/*Node<PageEntry> topofpageentry=setofpageentrycontainingallword.getTop();
		while(topofpageentry!=null)
		{
			System.out.print(topofpageentry.getElement().getPageName());
			if(topofpageentry.getNext()!=null)
				System.out.print(",");
			topofpageentry=topofpageentry.getNext();
		}*/
			System.out.println("");
		}
		else if(action[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{ 	System.out.print(actionMessage+": ");
			String[] anyword=new String[action.length-1];
				for(int j=0;j<action.length-1;j++)
				{   if(action[j+1].charAt(action[j+1].length()-1)=='s')
					action[j+1]=action[j-1].substring(0,action[j+1].length()-1);
					action[j+1]=updatingword.update1(action[j+1]);
					anyword[j]=action[j+1];
				}
			MySet<PageEntry> setofpageentrycontaininganyword=ipi.getPagesWhichContainsanyWord(anyword);
			if(setofpageentrycontaininganyword==null)
				throw new IOException("Given all word is not available in any page!!!!");
			MySet<SearchResult> unsortedlist2=new MySet<SearchResult>();
			Node<PageEntry> tp2=setofpageentrycontaininganyword.getTop();
	
			while(tp2!=null)
			{	
				SearchResult nw1=new SearchResult(tp2.getElement(),tp2.getElement().getRelevanceOfPage(anyword, false));
				unsortedlist2.addElement(nw1);
				tp2=tp2.getNext();
			}
	
			ArrayList<PageEntry> arrayofpageentry2=ms.sortThisList(unsortedlist2);
			int counttt=0;
			while(counttt!=arrayofpageentry2.size())
			{	System.out.print(arrayofpageentry2.get(counttt).getPageName());
				if(counttt+1!=arrayofpageentry2.size()) //for print comma upto last
					System.out.print(",");
				counttt++;	
			}	
		    System.out.println("");
		}
		else if(action[0].equals("queryFindPagesWhichContainPhrase"))
		{ System.out.print(actionMessage+": ");
		String[] phraseword=new String[action.length-1];
		for(int g=0;g<action.length-1;g++)
			phraseword[g]=action[g+1];
		MySet<SearchResult> setofpagecontainingphrase=ipi.getPagesWhichContainPhrase(phraseword);
		if(setofpagecontainingphrase==null)
			throw new IOException("Given phrase is not available in all pages!!!!");
		ArrayList<PageEntry> arrayofpageentry3=ms.sortThisList(setofpagecontainingphrase);
		int countttt=0;
		while(countttt!=arrayofpageentry3.size())
		{	System.out.print(arrayofpageentry3.get(countttt).getPageName());
			if(countttt+1!=arrayofpageentry3.size()) //for print comma upto last
				System.out.print(",");
			countttt++;	
		}	
	    System.out.println("");
		/*Node<PageEntry> topoflistofpage=setofpagecontainingphrase.getTop();
		if(topoflistofpage==null)
			throw new IOException("Given phrase is not available in all pages!!!!");
		//sort is not done,will do it later
		while(topoflistofpage!=null)
		{
			System.out.print(topoflistofpage.getElement().getPageName());
			if(topoflistofpage.getNext()!=null)
				System.out.print(",");
			topoflistofpage=topoflistofpage.getNext();				
		}
		  System.out.println("");*/
		}
		else{
			throw new IOException("Incorrect query!!!");
		}
	}
    catch(IOException ey)
        {
            System.out.println(ey.getMessage());
        }
    }
}
