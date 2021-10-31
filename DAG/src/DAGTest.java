import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class DAGTest {

	@Test
	public void testLCAforEmpty() {
		DAG lca = new DAG(10);
		assertEquals(-1, lca.findLCA(1, 2));
	}
	
	@Test
	public void testDirectedGraph(){
		DAG newDAG = new DAG(10);
		newDAG.addEdge(1, 2);
		newDAG.addEdge(1, 3);
		newDAG.addEdge(3, 4);
		newDAG.addEdge(4, 5);
		newDAG.addEdge(4, 6);

		assertEquals("", 5, newDAG.edges());
		assertEquals("", 10, newDAG.vertices());
		String ans = "[5, 6]";
		assertEquals("",ans, newDAG.adj(4).toString());
	}
	
	@Test
	public void testCycle(){
		DAG newDAG = new DAG(20);
		newDAG.addEdge(0, 1);
		newDAG.addEdge(1, 2);
		newDAG.addEdge(2, 0);
		newDAG.addEdge(2, 3);
		newDAG.addEdge(3, 4);

		newDAG.findCycle(0);
		assertTrue(newDAG.hasCycle());
	}
	
	@Test
	public void testAcyclicGraph(){
		DAG newDAG = new DAG(5);
		newDAG.addEdge(0, 1);
		newDAG.addEdge(1, 2);
		newDAG.addEdge(2, 3);
		assertFalse(newDAG.hasCycle());
	}
	
	@Test
	public void testAddEdge(){
		DAG newDAG = new DAG(5);
		newDAG.addEdge(1, 2);
		newDAG.addEdge(-3, -4);
		newDAG.addEdge(2, 3);	
		assertEquals(2, newDAG.edges());
	}
		
	@Test
	public void testinDegree(){
		DAG newDAG= new DAG(5);
		assertEquals("", -1, newDAG.indegree(-3));
	}

	@Test
	public void testOutDegree(){
		DAG newDAG = new DAG(5);
		assertEquals("", -1, newDAG.outdegree(8));	
	}
	
	@Test
	public void testLCA(){
		DAG lca = new DAG(10);

		lca.addEdge(0, 1);
		lca.addEdge(1, 2);
		lca.addEdge(1, 3);
		lca.addEdge(2, 5);
		lca.addEdge(3, 4);
		lca.addEdge(4, 6);
		lca.addEdge(5, 6);
		lca.addEdge(6, 8);
		lca.addEdge(5, 7);
		lca.addEdge(7, 8);
		lca.addEdge(8, 9);

		assertEquals(1, lca.findLCA(5, 4));
		assertEquals(7, lca.findLCA(8, 7));
		assertEquals(6, lca.findLCA(6, 8));
		assertEquals(2, lca.findLCA(2,2));
	
	}
	
	@Test
	public void testNoCommonAncestors(){
		DAG lca2 = new DAG(11);
		lca2.addEdge(0, 1);
		lca2.addEdge(0, 2);
		lca2.addEdge(1, 2);
		lca2.addEdge(2, 3);
		lca2.addEdge(3, 4);
		lca2.addEdge(1, 5);
		lca2.addEdge(3, 5);

		assertEquals(2, lca2.findLCA(3, 2));
		assertEquals(3, lca2.findLCA(4, 5));

		//Check for no common ancestors
		assertEquals(-1, lca2.findLCA(7, 3));
	}
	
	@Test
	public void testLCANonDAG(){
		DAG lca3 = new DAG(11);

		lca3.addEdge(0, 1);
		lca3.addEdge(0, 2);
		lca3.addEdge(2, 3);
		lca3.addEdge(3, 0);
		lca3.addEdge(3, 4);

		//Should return -1 if graph is not a DAG
		assertEquals(-1, lca3.findLCA(2, 3));
		assertEquals(-1, lca3.findLCA(3, 4));
		assertEquals(-1, lca3.findLCA(1, 2));
		assertEquals(-1, lca3.findLCA(0, 3));
		assertEquals(-1, lca3.findLCA(1, 3));

	}
	
	
}