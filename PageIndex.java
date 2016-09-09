public class PageIndex {
	private MyLinkedList<WordEntry> listofwe;
	PageIndex(){
		listofwe=new MyLinkedList<WordEntry>();
	}
   
	  public MyLinkedList<WordEntry> getWordEntries(){
    	  return listofwe;
      }
      public void addPositionForWord(String str,Position p)
      { Node<WordEntry> top=listofwe.getTop();
        boolean flag=false; 
      	while(top!=null){
      		if(top.getElement().getWord().equals(str))
      			{top.getElement().addPosition(p);
      		    flag=true;
      			break;
      			}
      		top=top.getNext();
      	}
      	if(!flag)
      	{   WordEntry newwd=new WordEntry(str);
      	    newwd.addPosition(p);
      	    listofwe.Insert(newwd);
      	}    		  
      }
      public WordEntry getwordentryfromthispage(String st)
      {	Node<WordEntry> top1=listofwe.getTop();
      	while(top1!=null){
    		if(top1.getElement().getWord().equals(st))
    			return top1.getElement();
    		top1=top1.getNext();
    	}
    	return null;  
      }
}
