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

        int n = fr.nextInt(), q = fr.nextInt();
        
        char[] tmp = fr.nextString().toCharArray();

        int[] C = new int[n];
        for(int i=0; i < n; i++) C[i] = tmp[i] - 'A';

        int[] M = new int[26];
        Arrays.fill(M, -1);
        M[C[0]] = 0;

        int[] L = new int[n];
        L[0] = 1;
        for(int i=1; i < n; i++) {
            L[i] = L[i - 1];
            if(M[C[i]] == -1 || C[i] != C[i - 1] && tl(M, C[i])) L[i]++;
            M[C[i]] = i;
        }

        Arrays.fill(M, n);
        M[C[n - 1]] = n - 1;

        int[] R = new int[n];
        R[n - 1] = 1;
        for(int i=n - 2; i >= 0; i--) {
            R[i] = R[i + 1];
            if(M[C[i]] == n || C[i] != C[i + 1] && tr(M, C[i])) R[i]++;
            M[C[i]] = i;
        }

        for(int i=0; i < q; i++) {
            int a = fr.nextInt(), b = fr.nextInt();
            int res = 0;
            if(a != 1) res += L[a - 2];
            if(b != n) res += R[b];
            System.out.println(res);
        }

    }

    public static boolean tl(int[] M, int c) {
        for(int i=0; i < c; i++) if(M[i] > M[c]) return true;
        return false;
    }

    public static boolean tr(int[] M, int c) {
        for(int i=0; i < c; i++) if(M[i] < M[c]) return true;
        return false;
    }

}
