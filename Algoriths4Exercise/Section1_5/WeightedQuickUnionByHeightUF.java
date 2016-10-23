class WeightedQuickUnionByHeightUF
{
	private int[] id;
	private int[] sz;
	private int count;
	
	public WeightedQuickUnionByHeightUF(int N)
	{
		count = N;
		id = new int[N];
		ht = new int[N];
		
		for(int i=0; i<N; i++)
		{
			id[i] = i;
			ht[i] = 0;
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
		
		if(proot == qroot) return;
		
		if(ht[proot] == ht[qroot])
		{
			id[proot] = qroot;
			ht[qroot]++;
		}		
		else if(ht[proot] < ht[qroot])
		{
			id[proot] = qroot;
		}
		else
		{
			id[qroot] = proot;
		}
		
		count--;
	}
	
	public static void main(String[] args)
	{
		int N = StdIn.readInt();
		WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(N);
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


