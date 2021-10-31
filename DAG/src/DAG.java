import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DAG {
	
	private int vertices;           	
	private int edges;                 	
	private ArrayList<Integer>[] adj;   //adj[v] = adjacency list for vertex v
	private int[] indegree;        		//Indegree[v] = indegree of vertex v
	private boolean marked[];			//Boolean list to track visited vertices
	private boolean stack[];			//Order that vertices were visited
	private boolean hasCycle;			//True if cycle is in graph
	
	@SuppressWarnings("unchecked")
	public DAG(int vertices)
	{
		if (vertices < 0) throw new IllegalArgumentException("Must be a positive number of vertices.");
	    this.vertices = vertices;
	    this.edges = 0;
	    indegree = new int[vertices];	
	    marked = new boolean[vertices];
	    stack = new boolean[vertices];
	    adj = (ArrayList<Integer>[]) new ArrayList[vertices];
	    for (int v = 0; v < vertices; v++) {
	        adj[v] = new ArrayList<Integer>();
	    }    
	}
	
	//Returns current vertex
		public int vertices() {
			return vertices;	
		}
		
		public int edges() {
	        return edges;
	    }

		
		public void addEdge(int v, int w)
		{
		    if((validateVertex(v)>0)&&(validateVertex(w)>0))
		    {
		    	adj[v].add(w);
		    	indegree[w]++;
		    	edges++;
		    }
		    else{
		    	System.out.println("Please enter vertices between 0 & n-1");
		    }
		    	
		}
		
		private int validateVertex(int v) {
	        if (v < 0 || v >= vertices)
	        	return -1;
	        else
	        	return 1;}

		
		//Returns amount of directed edges incident to vertex v
		public int indegree(int v) {
			if(validateVertex(v)<0){
				return -1;
			}
			else{
				return indegree[v];
			}
		}
		

		//Returns amount of directed edges from vertex v
		public int outdegree(int v) {
			if(validateVertex(v)<0){
				return -1;
			}
			else{
				return adj[v].size();
			}
		}

		//Returns the adjacent vertices to v
		public Iterable<Integer> adj(int v)
		{ return adj[v]; }
		
		public boolean hasCycle() {
	        return hasCycle;
	    }
		
		public void findCycle(int v) {
			marked[v] = true;
			stack[v] = true;
			for (int w : adj(v)) {
				if(!marked[w]) {
					findCycle(w);
				} else if (stack[w]) {
					hasCycle = true;
					return;
				}
			}
			stack[v] = false;
		 }	
		
		public int findLCA(int v, int w){
			findCycle(0);
			if(hasCycle){
			//Graph is not a DAG
				return -1;
			}
			
			//Reverses the DAG
			DAG backwards = reverse();
			
			//Locate the two points in the graph
			ArrayList<Integer> vPath = backwards.BFS(v);
			ArrayList<Integer> wPath = backwards.BFS(w);
			ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
			boolean found = false;
			
			//Return the first common ancestor found from arrayList
			for(int i = 0; i<vPath.size(); i++){
				for(int t = 0; t<wPath.size(); t++){		
					if(vPath.get(i)==wPath.get(t)){
						commonAncestors.add(vPath.get(i));	
						found = true;
					}
				}
			}
			
			if(found)
				return commonAncestors.get(0);
			else
				return -1;
		}
		//Traverse the graph backwards to find the LCA
	    public DAG reverse() {
	        DAG reverse = new DAG(vertices); 
	        for (int v = 0; v < vertices; v++) {
	            for (int w : adj(v)) {
	                reverse.addEdge(w, v);
	            }
	        }
	        return reverse;
	    }
	    
		public ArrayList<Integer> BFS(int s)
	    {
	        //Set boolean to mark all the vertices as unvisited
	        boolean visited[] = new boolean[vertices];
	 
	        LinkedList<Integer> queue = new LinkedList<Integer>();
	        ArrayList<Integer> order= new ArrayList<Integer>();
	 
	        visited[s]=true;
	        queue.add(s);
	        
	        while (queue.size() != 0) {
	            // Dequeue a vertex from queue and print it
	            s = queue.poll();           
	            order.add(s);
	            
	            Iterator<Integer> i = adj[s].listIterator();
	            while (i.hasNext()){
	            	int n = i.next();
	                if (!visited[n]){
	                   visited[n] = true;
	                   queue.add(n);
	                }
	            }
	        }
	        return order;   
	    }
		
}