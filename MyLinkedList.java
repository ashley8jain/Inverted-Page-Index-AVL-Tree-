import java.io.IOException;
public class MyLinkedList<M> {
	private Node<M> top;
	MyLinkedList()
	{
		top=null;
	}
	 Node<M> getTop()
	{
		return top;
	}
	public void Insert(M element)
	{   Node<M> insert=new Node<M>(element,top);
		top=insert;
	}
	public void Delete(M element) throws IOException
	{
		Node<M> p=top;
		  boolean flag=false;
		  if(p.getElement().equals(element))
		  {
			  top=p.getNext();
			  flag=true;
		  }
		  else
		  {
			  while(p.getNext()!=null)
			  {
				  Node<M> n=p.getNext();
				  if(n.getElement().equals(element))
				  {
					  p.setNext(n.getNext());
					  flag=true;
					  break;
				  }
				p=n;  
			  }
		  }
		  if(flag==false)
			  throw new IOException("Not Found");
	}

}
