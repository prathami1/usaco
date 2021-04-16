import java.io.BufferedReader;
import java.io.IOException;
 
public class PairsOfCows {
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int[] last = new int[n + 1];
        long answer = 0;
        BinaryIndexTree bit = new BinaryIndexTree(n);
        for (int j = 1; j <= n; j++) {
            int k = Integer.parseInt(tokenizer.nextToken());
            if (last[k] != 0) {
                bit.update(last[k], -1L);
            }
            answer += bit.query(j) - bit.query(last[k]);
            last[k] = j;
            bit.update(j, 1L);
        }
        System.out.println(answer);
    }
 
    static class BinaryIndexTree {
        final int n;
        final long[] bit;
 
        BinaryIndexTree(int n) {
            this.n = n;
            this.bit = new long[n + 1];
        }
 
        void update(int j, long delta) {
            for (; j <= n; j += j & -j) {
                bit[j] += delta;
            }
        }
 
        long query(int j) {
            long res = 0;
            for (; j > 0; j -= j & -j) {
                res += bit[j];
            }
            return res;
        }
    }
}
