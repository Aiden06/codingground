class QuickUnionPathCompressUF
{
	private int[] id;
	private int count;
	
	public QuickUnionPathCompressUF(int N)
	{
		count = N;
		id = new int[N];
		
		for(int i=0; i<N; i++)
		{
			id[i] = i;
		}
		
	}
	
	public int count()
	{
		return count;
	}
	
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}
	
	private int find(int p)
	{
		while (p != id[p]) p = id[p];
		return p; 
	}
	
	public void union(int p, int q)
	{
		int proot = find(p);
		int qroot = find(q);
		
		if( proot == qroot) return;
		
		while (p != id[p])
		{
			int pid = id[p];
			id[p] = qroot;
			p = pid;
		}
		
		while (q != id[q])
		{
			int qid = id[q];
			id[q] = qroot;
			q = qid;
		}
		
		id[proot] = qroot;
		
		count--;
	}
	
	public static void main(String[] args)
	{
		int N = StdIn.readInt();
		QuickUnionPathCompressUF uf = new QuickUnionPathCompressUF(N);
		while (!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(uf.count() + " components");
	}
}
