import java.util.*;

class Graph {
    private int V; // Number of vertices
    private int[][] adjacencyMatrix; // Adjacency matrix to store the edges and their weights
    private Map<String, Integer> cityIndices; // Map to store city names and their corresponding indices

    Graph(int V) {
        this.V = V;
        adjacencyMatrix = new int[V][V];
        cityIndices = new HashMap<>();
    }

    void addCity(String cityName) {
        if (cityIndices.size() < V) {
            cityIndices.put(cityName, cityIndices.size());
        } else {
            throw new IllegalArgumentException("Graph is full. Cannot add more cities.");
        }
    }

    void addEdge(String sourceCity, String destinationCity, int weight) {
        int sourceIndex = cityIndices.get(sourceCity);
        int destinationIndex = cityIndices.get(destinationCity);
        adjacencyMatrix[sourceIndex][destinationIndex] = weight;
        adjacencyMatrix[destinationIndex][sourceIndex] = weight; // If it's an undirected graph
    }

    void dijkstra(String sourceCity) {
        int sourceIndex = cityIndices.get(sourceCity);

        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[sourceIndex] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.add(new Edge(sourceIndex, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.destination;

            for (int v = 0; v < V; v++) {
                if (adjacencyMatrix[u][v] > 0) {
                    int weight = adjacencyMatrix[u][v];
                    if (distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                        pq.add(new Edge(v, distances[v]));
                    }
                }
            }
        }

        // Print shortest distances from the source to all destinations
        System.out.println("Shortest distances from " + sourceCity + " to all destinations:");
        for (Map.Entry<String, Integer> entry : cityIndices.entrySet()) {
            String city = entry.getKey();
            int index = entry.getValue();
            System.out.println(city + ": " + distances[index]);
        }
    }

    private static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of cities
        Graph graph = new Graph(V);

        // Add cities to the graph
        graph.addCity("Mumbai");
        graph.addCity("Delhi");
        graph.addCity("Bangalore");
        graph.addCity("Chennai");
        graph.addCity("Kolkata");

        // Adding edges and their weights
        graph.addEdge("Mumbai", "Delhi", 1500);
        graph.addEdge("Mumbai", "Bangalore", 1000);
        graph.addEdge("Delhi", "Bangalore", 2500);
        graph.addEdge("Delhi", "Chennai", 1600);
        graph.addEdge("Bangalore", "Chennai", 300);
        graph.addEdge("Bangalore", "Kolkata", 1500);
        graph.addEdge("Chennai", "Kolkata", 1100);

        String sourceCity = "Mumbai"; // Source city
        graph.dijkstra(sourceCity);
    }
}
