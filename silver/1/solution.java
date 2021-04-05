import java.util.*;
import java.io.*;

public class Main {
    
    public static class ProblemOne {

        private BufferedReader br;
        private StringTokenizer st;

        public ProblemOne() {br = new BufferedReader(new InputStreamReader(System.in));}

        public ProblemOne(String fileName) {
            try {br = new BufferedReader(new FileReader(fileName));}
            catch(FileNotFoundException ee) {ee.printStackTrace();}
        }

        private String next() {
            while(st == null || !st.hasMoreElements())
                try {st = new StringTokenizer(br.readLine());}
                catch(IOException ee) {ee.printStackTrace();}
            return st.nextToken();
        }

        public int nextInt() {return Integer.parseInt(next());}

        public long nextLong() {return Long.parseLong(next());}

        public double nextDouble() {return Double.parseDouble(next());}

        public String nextString() {return next();}

        public String nextLine() {
            String str = "";
            try {str = br.readLine();}
            catch(IOException ee) {ee.printStackTrace();}
            return str;
        }

    }

    public static void main(String[] args) {
        
        ProblemOne fr = new ProblemOne();

        int n = fr.nextInt(), k = fr.nextInt();

        int[] P = new int[n];
        for(int i=0; i < n; i++) {
          P[i] = i;
        }
        
        Set<Integer>[] H = new HashSet[n];
        for(int i=0; i < n; i++) {
            H[i] = new HashSet<>();
            H[i].add(i);
        }

        for(int i=0; i < k; i++) {
            int a = fr.nextInt() - 1, b = fr.nextInt() - 1;
            swap(P, a, b);
            H[P[a]].add(a);
            H[P[b]].add(b);
        }

        int[] par = new int[n];
        for(int i=0; i < n; i++) 
          par[i] = i;

        for(int i=0; i < n; i++) 
          union(par, i, P[i]);
          
        for(int i=0; i < n; i++) 
          find(par, i);

        for(int i=0; i < n; i++) 
          H[par[i]].addAll(H[i]);

        for(int i=0; i < n; i++) 
          System.out.println(H[par[i]].size());

    }

    public static void swap(int[] A, int a, int b) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }

    public static void union(int[] par, int a, int b) {
        if((a = find(par, a)) == (b = find(par, b))) return;
        par[b] = a;
    }

    public static int find(int[] par, int a) {
        return (par[a] == a) ? a : (par[a] = find(par, par[a]));
    }

}
