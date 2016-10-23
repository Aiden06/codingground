import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class RandomGrid
{
    Connection[] con;
    
    private class Connection
    {
        int p;
        int q;
        
        public Connection(int p, int q)
        {
            this.p = p;
            this.q = q;
        }
        
        public int getp(){return p;}
        public int getq(){return q;}
    }
    
    RandomGrid(int N)
    {
        con = new Connection[N*(N-1)*2];
        int j = 0;
        
        for(int i=0; i<N*N-1; i++)
        {
            if((i+1)%N != 0)
            {
                if( StdRandom.uniform(2) == 0)
                {
                    con[j++] = new Connection(i, i+1);
                }
                else
                {
                    con[j++] = new Connection(i+1, i);
                }
            }
            
            if(i<(N-1)*N)
            {
                if( StdRandom.uniform(2) == 0)
                {
                    con[j++] = new Connection(i, i+N);
                }
                else
                {
                    con[j++] = new Connection(i+N, i);
                }
            }
        }
        
        StdRandom.shuffle(con);
    }
    
    public Connection[] getCon()
    {
        return con;
    }
    
    public static Connection[] generate(int N)
    {
        RandomGrid rg = new RandomGrid(N);
        return rg.getCon();
    }
    
    public static void main(String args[])
    {
        int N = Integer.parseInt(args[0]);
        
        Connection[] con = RandomGrid.generate(N);
        
        for(Connection c : con)
        {
            StdOut.println("(" + c.getp() + "," + c.getq() + ")");
        }
    }
}