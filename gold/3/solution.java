import java.io.BufferedReader;
import java.io.IOException;
 
public class Permutation {
    public static final long MOD = 1000000007;
    static int n;
    static int[] xs;
    static int[] ys;
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        xs = new int[n];
        ys = new int[n];
        for (int a = 0; a < n; a++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            xs[a] = Integer.parseInt(tokenizer.nextToken());
            ys[a] = Integer.parseInt(tokenizer.nextToken());
        }
        List<Triangle> triangles = new ArrayList<>();
        int[][][] inside = new int[n][n][n];
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    triangles.add(new Triangle(a, b, c));
                    for (int p = 0; p < n; p++) {
                        if (inside(a, b, c, p)) {
                            inside[a][b][c]++;
                        }
                    }
                }
            }
        }
        triangles.sort(Comparator.comparingInt(triangle -> -area2(triangle.a, triangle.b, triangle.c)));
        Triangle wholeTriangle = triangles.get(0);
        if (inside[wholeTriangle.a][wholeTriangle.b][wholeTriangle.c] == n) {
            long[][] choose = new long[n + 1][n + 1];
            long[] factorial = new long[n + 1];
            long[][] permutations = new long[n + 1][n + 1];
            for (int a = 0; a <= n; a++) {
                choose[a][0] = 1;
                if (a == 0) {
                    factorial[a] = 1;
                } else {
                    factorial[a] = (((long) a) * factorial[a - 1]) % MOD;
                }
                for (int b = 1; b <= a; b++) {
                    choose[a][b] = (choose[a - 1][b - 1] + choose[a - 1][b]) % MOD;
                }
                for (int b = 0; b <= a; b++) {
                    permutations[a][b] = (choose[a][b] * factorial[b]) % MOD;
                }
            }
            long[][][] dp = new long[n][n][n];
            long answer = 0;
            dp[wholeTriangle.a][wholeTriangle.b][wholeTriangle.c] = 1;
            for (Triangle triangle : triangles) {
                int a = triangle.a;
                int b = triangle.b;
                int c = triangle.c;
                answer += permutations[n - 3][inside[a][b][c] - 3] * dp[a][b][c];
                answer %= MOD;
                for (int p = 0; p < n; p++) {
                    if (p != a && p != b && p != c && inside(a, b, c, p)) {
                        for (int j = 0; j < 3; j++) {
                            int[] newPoints = {a, b, c};
                            newPoints[j] = p;
                            Arrays.sort(newPoints);
                            int d = newPoints[0];
                            int e = newPoints[1];
                            int f = newPoints[2];
                            dp[d][e][f] += permutations[n - inside[d][e][f] - 1][inside[a][b][c] - inside[d][e][f] - 1] * dp[a][b][c];
                            dp[d][e][f] %= MOD;
                        }
                    }
                }
            }
            answer *= 6L;
            answer %= MOD;
            System.out.println(answer);
        } else {
            System.out.println(0);
        }
    }
 
    static int area2(int a, int b, int c) {
        return Math.abs((xs[a] * ys[b]) + (xs[b] * ys[c]) + (xs[c] * ys[a]) - (xs[a] * ys[c]) - (xs[c] * ys[b]) - (xs[b] * ys[a]));
    }
 
    static boolean inside(int a, int b, int c, int p) {
        return area2(a, b, c) == area2(a, b, p) + area2(b, c, p) + area2(c, a, p);
    }
 
