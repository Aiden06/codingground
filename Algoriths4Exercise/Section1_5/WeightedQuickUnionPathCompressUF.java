class WeightedQuickUnionPathCompressUF
{
	private int[] id;
	private int[] sz;
	private int count;
	
	public WeightedQuickUnionPathCompressUF(int N)
	{
		count = N;
		id = new int[N];
		sz = new int[N];
		
		for(int i=0; i<N; i++)
		{
			id[i] = i;
			sz[i] = 1;
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
		
	
		if(sz[proot] < sz[qroot])
		{
			while (p != id[p])
			{
				int pid = id[p];
				id[p] = qroot;
				p = pid;
			}
		
			id[proot] = qroot;
			sz[qroot] += sz[proot];		
		}
		else
		{
			while (q != id[q])
			{
				int qid = id[q];
				id[q] = proot;
				q = qid;
			}
				
			id[qroot] = proot;
			sz[proot] += sz[qroot];
		}
		
		count--;
	}
	
	public static void main(String[] args)
	{
		int N = StdIn.readInt();
		WeightedQuickUnionPathCompressUF uf = new WeightedQuickUnionPathCompressUF(N);
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


