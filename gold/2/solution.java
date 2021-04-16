import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Portals {
    static int[] union;
 
    static int find(int u) {
        if (union[union[u]] != union[u]) {
            union[u] = find(union[u]);
        }
        return union[u];
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        union = new int[(2 * n) + 1];
        for (int p = 1; p <= 2 * n; p++) {
            union[p] = p;
        }
        List<Edge> edges = new ArrayList<>();
        for (int a = 1; a <= n; a++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            int cost = Integer.parseInt(tokenizer.nextToken());
            int[] portals = new int[4];
            for (int j = 0; j < 4; j++) {
                portals[j] = Integer.parseInt(tokenizer.nextToken());
            }
            edges.add(new Edge(portals[0], portals[1], 0));
            edges.add(new Edge(portals[2], portals[3], 0));
            edges.add(new Edge(portals[3], portals[0], cost));
        }
        edges.sort(Comparator.comparingInt(edge -> edge.cost));
        int answer = 0;
        for (Edge edge : edges) {
            int u = find(edge.a);
            int v = find(edge.b);
            if (u != v) {
                answer += edge.cost;
                union[u] = v;
            }
        }
        System.out.println(answer);
    }
 
    static class Edge {
        final int a;
        final int b;
        final int cost;
 
        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}
