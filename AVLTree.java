public class AVLTree{
	private AVLNode root;
	AVLTree()
	{
		root=null;
	}
	public void addElement(String p,int woin,int otherwoin)
	{
		if(root==null)
		{
			root = new AVLNode(p,woin,otherwoin);
		}
		else 
		{
			MyLinkedList<AVLNode> stack = new MyLinkedList<AVLNode>();
			AVLNode top = root;
			boolean flag=true; 
			while(top!=null){
				top.height++;
				stack.Insert(top);
				if(top.getWimnc()>otherwoin)
				{
					top=top.left;
					 flag=false;
				}
				else 
				{
					top=top.right;
					 flag=true;
				}
			}
			if(flag)
			{
				stack.getTop().getElement().right=new AVLNode(p,woin,otherwoin);
			}
			else
			{
				stack.getTop().getElement().left=new AVLNode(p,woin,otherwoin);
			}
			Node<AVLNode> ahead= stack.getTop();
			while(ahead!=null)
			{
				int l=ahead.getElement().leftsubtreehght();
				int r= ahead.getElement().rightsubtreehght();
				if(l>1+r)
				{
					AVLNode z=ahead.getElement();
					AVLNode y= z.left;
					ahead=ahead.getNext();
					if(y.getWimnc()>otherwoin)
					{
						z.left=y.right;
						y.right=z;
						z.height-=2;
						if(ahead==null)
						{
							root=y;
						}
						else
						{
							if(ahead.getElement().getWimnc()>otherwoin)
							{
								ahead.getElement().left=y;
							}
							else
							{
								ahead.getElement().right=y;
							}
						}
					}
					else
					{
						AVLNode x= y.right;
						y.right=x.left;
						z.left=x.right;
						x.left=y;
						x.right=z;
						x.height++;
						y.height--;
						z.height-=2;
						if(ahead==null)
						{
							root=x;
						}
						else
						{
							if(ahead.getElement().getWimnc()>otherwoin)
							{
								ahead.getElement().left=x;
							}
							else
							{
								ahead.getElement().right=x;
							}
						}
					}
					while(ahead!=null)
					{
							ahead.getElement().height--;
							ahead=ahead.getNext();
					}
				}
				if(r>1+l)
				{
					AVLNode z=ahead.getElement();
					AVLNode y= z.right;
					ahead=ahead.getNext();
					if(y.getWimnc()<otherwoin)
					{
						z.right=y.left;
						y.left=z;
						z.height-=2;
						if(ahead==null)
						{
							root=y;
						}
						else
						{
							if(ahead.getElement().getWimnc()>otherwoin)
							{
								ahead.getElement().left=y;
							}
							else
							{
								ahead.getElement().right=y;
							}
						}
					while(ahead!=null)
					{
						ahead.getElement().height--;
						ahead=ahead.getNext();
					}
				}
					else
					{
						AVLNode x= y.left;
						y.left=x.right;
						z.right=x.left;
						x.right=y;
						x.left=z;
						x.height++;
						y.height--;
						z.height-=2;
						if(ahead==null)
						{
							root=x;
						}
						else
						{
							if(ahead.getElement().getWimnc()>otherwoin)
							{
								ahead.getElement().left=x;
							}
							else
							{
								ahead.getElement().right=x;
							}
						}
						while(ahead!=null)
						{
							ahead.getElement().height--;
							ahead=ahead.getNext();
						}
					}					
				}
				if(ahead!=null)
				ahead=ahead.getNext();
			}
		}
	}
	void printinorder(AVLNode t)
	{
		if(t!=null)
		{
			printinorder(t.left);
			System.out.println(t.data+": "+t.getWimnc());
			printinorder(t.right);
		}
	}
	public AVLNode findElement(int otherwoin)
	{
		AVLNode top= root;
		AVLNode found=null;
		while(top!=null)
		{
			if(top.getWimnc()<otherwoin)
			{
				top=top.right;
			}
			else
			{
				if(top.getWimnc()==otherwoin)
					return top;
				found=top;
				top=top.left;
			}
		}
		return found;
	}
}