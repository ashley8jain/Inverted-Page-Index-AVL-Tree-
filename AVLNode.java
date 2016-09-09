public class AVLNode{
	String data;
	int wiamongnonconnectingword;
	int wi;
	int height;
	AVLNode left;
	AVLNode right;
	AVLNode(String p,int w,int x)
	{
		data=p;		
		height=1;
		wi=w;
		wiamongnonconnectingword=x;
	}
	public int leftsubtreehght()
	{
		if(left==null)
			return 0;
		return left.height;
	}
	public int rightsubtreehght()
	{
		if(right==null)
			return 0;
		return right.height;
	}	
	public int getWimnc()
	{
		return wiamongnonconnectingword;
	}
	public int getWin()
	{
		return wi;
	}
}