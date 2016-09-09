
public class WordEntry {
	private String wd;
	private MyLinkedList<Position> post;
	private AVLTree pavl;
	WordEntry(){
		wd=null;
		post=new MyLinkedList<Position>();
		pavl=new AVLTree();
	}
	WordEntry(String word)
	{
		wd=word;
		post=new MyLinkedList<Position>();
		pavl=new AVLTree();
	}
	public String getWord()
	{
		return wd;
	}
	public void addPosition(Position position)
	{ post.Insert(position);
	  pavl.addElement(position.getPageEntry().getPageName(),position.getWordIndex(),position.getOtherwi());
	}
	public void addPositions(MyLinkedList<Position> positions)
	{ 
		Node<Position> top=positions.getTop();
		while(top!=null)
		{ 	post.Insert(top.getElement());
		  	top=top.getNext();	
		}
		
	}
	public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return post;
	}

}
