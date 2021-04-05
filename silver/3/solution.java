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

        int n = fr.nextInt();

        // M[i][j]: val at (i, j)
        int[][] M = new int[n][n];
        for(int i=0; i < n; i++) for(int j=0; j < n; j++) M[i][j] = fr.nextInt();

        int h = 0;
        for(int i=0; i < n; i++) {
            int[] tmp = new int[2];
            for(int j=0; j < n; j++) tmp[j % 2] += M[i][j];
            h += Math.max(tmp[0], tmp[1]);
        }

        int v = 0;
        for(int j=0; j < n; j++) {
            int[] tmp = new int[2];
            for(int i=0; i < n; i++) tmp[i % 2] += M[i][j];
            v += Math.max(tmp[0], tmp[1]);
        }

        System.out.println(Math.max(h, v));

    }

}
