public class HashEntry{
	private WordEntry wd;
	private HashEntry next;
		HashEntry()
		{
			wd=null;
			next=null;
		}
		public HashEntry(WordEntry we)
		{
			wd=we;
		}
		public WordEntry getWE()
		{
			return wd;
		}
		public HashEntry getNext()
		{
			return next;
		}
		public void setNext(HashEntry nexthe)
		{
			next=nexthe;
		}
		public void setWD(WordEntry set)
		{
			wd=set;
		}
}