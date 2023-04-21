import java.util.*;

public class GraphI7<E> {

	public class Edge<E> {

		private final int weight;
		private final E dest;

		public Edge(E dest) {
			this.dest = dest;
			weight = 0;
		}

		public Edge(E dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}

		public int getWeight() {
			return weight;
		}

		public E getDest() {
			return dest;
		}

	}

	private Map<E, List<Edge<E>>> map;

	public GraphI7() {
		map = new HashMap<>();
	}

	public void addVertex(E vertex) {
		map.putIfAbsent(vertex, new ArrayList<>());
	}

	public Set<E> getVertecies() {
		return map.keySet();
	}

	public void addEdgeWeighted(E source, E dest, int weight) {

		map.get(source).add(new Edge<E>(dest, weight));
		map.get(dest).add(new Edge<>(source, weight));

	}

	public List<Edge<E>> getEdges(E data) {
		return map.get(data);
	}

	public void reprioritize(Queue<E> pq, E entry) {
		pq.remove(entry);
		pq.add(entry);
	}

	public void primsMST(E source) {

		Map<E, E> parents = new HashMap<E, E>();
		Map<E, Integer> dists = new HashMap<>();
		Queue<E> pq = new PriorityQueue<E>(Comparator.comparing(dists::get));

		/* Your solution here! */
		dists.put(source, 0); 

		for (E vertex : map.keySet()) {
			if (!vertex.equals(source)) {
				dists.put(vertex, Integer.MAX_VALUE);
			}
			parents.put(vertex, null);
			pq.offer(vertex);
		}

		while (!pq.isEmpty()) {
			E minVertex = pq.poll();

			for (Edge<E> edge : getEdges(minVertex)) {

				E adj = edge.getDest();

				if (pq.contains(adj) && edge.getWeight() < dists.get(minVertex)) {
					parents.put(adj, minVertex);
					dists.put(adj, edge.getWeight());
					reprioritize(pq, adj);
				}
			}

			for (Map.Entry<E, E> entry : parents.entrySet()) {
				System.out.printf("%s, %s\n", entry.getKey(), entry.getValue());
			}
		}
	}

	public void dijkstraShortestPath(E source, E dest) {

		Map<E, E> parents = new HashMap<>();
		Map<E, Integer> dists = new HashMap<>();
		Queue<E> pq = new PriorityQueue<>(Comparator.comparing(dists::get));

		dists.put(source, 0);
		for (E vertex : map.keySet()) {
			if (!vertex.equals(source)) {
				dists.put(vertex, Integer.MAX_VALUE);
			}
			parents.put(vertex, null);
			pq.offer(vertex);
		}

		while (!pq.isEmpty()) {

			E minVertex = pq.poll();

			for (Edge<E> edge : map.get(minVertex)) {
				E adj = edge.getDest();

				int pathWeight = dists.get(minVertex) + edge.getWeight();

				if (pq.contains(adj) && pathWeight < dists.get(adj)) {
					pq.remove(adj);
					parents.put(adj, minVertex);
					dists.put(adj, pathWeight);
					pq.offer(adj);
				}

				printSP(source, dest, parents, dists);
			}
		}
	}

	private void printSP(E source, E dest, Map<E, E> parents, Map<E, Integer> dists) {

		List<E> path = new ArrayList<>();

		E curr = dest;
		while (!path.contains(source)) {
			path.add(0, curr);
			curr = parents.get(curr);
		}
		System.out.printf("The shortest path is: %s\nTotal travel distance: %d\n", path, dists.get(dest));
	}
}
