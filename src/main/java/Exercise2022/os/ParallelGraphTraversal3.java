package Exercise2022.os;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import com.google.common.collect.Lists;

public class ParallelGraphTraversal3 extends RecursiveTask<List<Integer>> {
    private static final int MAX_TASKS = 20;
    private Boolean[] visited;
    private List<Integer>[] G;
    private List<Integer> q;

    ParallelGraphTraversal3(List<Integer>[] G, Boolean[] visited, List<Integer> q) {
        this.visited = visited;
        this.G = G;
        this.q = q;
    }

    @Override
    protected List<Integer> compute() {
        if (q.size() > 16) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .map(ForkJoinTask::join)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } else {
            return nextFrontier();
        }

    }

    private static List<Integer>[] randomGraph(int size, int degree) {
        List<Integer>[] g = new ArrayList[size];
        double threshold = degree/((double) size);
        for (int i = 0; i < size; i++) {
            g[i] = new ArrayList<>();
            for (int j = 0; j < degree; j++) {
                Random random = new Random();
                int u = random.nextInt(size);
                if (u != i) {
                    g[i].add(u);
                }
            }
        }
        return g;
    }

    private List<ParallelGraphTraversal3> createSubtasks() {
        int numPartitions = Math.min(q.size(), MAX_TASKS);
        List<List<Integer>> subQ = Lists.partition(q, q.size() / numPartitions);
        return subQ.stream()
                    .map(subList -> new ParallelGraphTraversal3(G, visited, subList))
                    .collect(Collectors.toList());
    }

    public List<Integer> nextFrontier() {
        q.forEach(u -> visited[u] = true);
        return q.stream()
            .map(u -> G[u].stream()
                    .filter(v -> !visited[v])
                    .collect(Collectors.toList()))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public static void traverse(List<Integer>[] G) {
        Boolean[] visited = new Boolean[G.length];
        for (int i = 0; i < visited.length; visited[i++] = false);
        System.out.println("start:" + Arrays.stream(visited).allMatch(v -> v == true));
        int numCompnents = 0;
        for (int i = 0; i < G.length; i++) {
            if (!visited[i]) {
                List<Integer> q = Arrays.asList(i);
                while (!q.isEmpty()) {
                    ParallelGraphTraversal3 traverser = new ParallelGraphTraversal3(G, visited, q);
                    q = traverser.compute();
                }
                numCompnents++;
                System.out.println(numCompnents);
            }
        }
        System.out.println(Arrays.stream(visited).allMatch(v -> v == true));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer>[] G = randomGraph(1000, 6);
        traverse(G);
    }
}
