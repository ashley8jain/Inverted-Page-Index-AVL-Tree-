import java.io.*;
public class PageEntry{
	private PageIndex pi;
	private String pagename;
	private AVLTree wavl;
	private BufferedReader br=null;	
	PageEntry()
	{
		pi=null;
		pagename=null;
		wavl=new AVLTree();
	}
	PageEntry(String pageName) throws IOException{
		pagename=pageName;
		pi=new PageIndex();
		wavl=new AVLTree();
			br=new BufferedReader(new FileReader("webpages/"+pageName));
		String Sentence;
		int j=1,k=1;
		while((Sentence=br.readLine())!=null)
		{
			String[] Word=update(Sentence);
			for(int i=0;i<Word.length;i++)
			{	
				if((!Word[i].matches("a|an|the|they|these|this|for|is|are|was|of|or|and|does|will|whoose")))
				    {	wavl.addElement(Word[i],j,k);
						pi.addPositionForWord(Word[i],new Position(this,j,k));
						k++;
				    }
						j++;
			}
		}
	}	
	public PageIndex getPageIndex(){
		return pi;
	}
	public String getPageName(){
		return pagename;
	}
	public String[] update(String line1){
		String line2=update1(line1);
		String[] words=line2.split("\\s+|\\t+");
	  for(int i=0;i<words.length;i++){
		  if(words[i].length()!=0&&!words[i].matches("was|this|is"))
			if(words[i].charAt(words[i].length()-1)=='s')
				words[i]=words[i].substring(0,words[i].length()-1);
			
		}
		return words;
	}
	public String update1(String line)
	{	line=line.toLowerCase();
		line = line.replace(",", " ");
		line = line.replace(".", " ");
		line = line.replace(";", " ");
		line = line.replace("!", " ");
    	line = line.replace("?", " ");
    	line = line.replace("#", " ");
    	line = line.replace("'", " ");
    	line = line.replace(":", " ");
    	line = line.replace("-"," ");
    	line = line.replace("{", " ");
    	line = line.replace("}", " ");
    	line = line.replace("(", " ");
    	line = line.replace(")", " ");
    	line = line.replace("[", " ");
    	line = line.replace("]", " ");
    	line = line.replace("<", " ");
    	line = line.replace(">", " ");
    	line = line.replace("=", " ");
    	line = line.replace("\"", " ");
		return line;
	}
	public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase)
	{	float r=0;
		if(doTheseWordsRepresentAPhrase==false)
		{	
			int count=0;
			while(count!=str.length)
			{
				r+=rel(str[count]);
				count++;
			}
		}
		else
		{
			
		}
		return r;
	}
	public float rel(String wd)
	{	float rv=0;
		int wrdindex;
		Node<WordEntry> top=pi.getWordEntries().getTop();
		while(top!=null)
		{	if(top.getElement().getWord().equals(wd))
				{Node<Position> toppos= top.getElement().getAllPositionsForThisWord().getTop();
					while(toppos!=null)
					{
						if(toppos.getElement().getPageEntry().getPageName().equals(this.pagename))
							{ wrdindex=toppos.getElement().getWordIndex();							
							rv+=(float)1/(wrdindex*wrdindex);
							}
						toppos=toppos.getNext();
					}
				}
			top=top.getNext();
		}
	return rv;	
	}
	public AVLTree getTree()
	{
		return wavl;
	}
}