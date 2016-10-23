import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class ErdosRenyi
{
	private int[] id;
	private int count;
	
	public ErdosRenyi(int N)
	{
		count = N;
		id = new int[N];
		
		for(int i=0; i<N; i++)
		{
			id[i] = i;
		}
		
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
	
	public int count()
	{
		return count;
	}
	
	public static int linkCount(int N)
	{
		ErdosRenyi er = new ErdosRenyi(N);
	
		int link = 0;
		while(er.count() > 1)
		{
			int p = 0;
			int q = 0;
		
			p = StdRandom.uniform(0, N);
			q = StdRandom.uniform(0, N);
		
			if( !er.connected(p, q) )
			{
				er.union(p, q);
				link++;
			}
		}
		return link;
	}
	
	public static void main(String[] args)
	{
		int N = Integer.parseInt(args[0]);
		
		StdOut.println(ErdosRenyi.linkCount(N) + " components");
	}
}
