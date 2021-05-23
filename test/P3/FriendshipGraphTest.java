package p3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FriendshipGraphTest {

	/*
	 * 测试james和bryant建立朋友关系后bryant是否加入了james的朋友名单。
	 */
	@Test
	public void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person james = new Person("James");
		Person bryant = new Person("Bryant");
		graph.addVertex(james);
		graph.addVertex(bryant);
		graph.addEdge(james,bryant);
		graph.addEdge(bryant,james);
		assertEquals(bryant,james.Addressbook.get(james.Addressbook.size()-1));
	}
/*
 * 测试重复名字报错功能，并在整个图中加入一个人，判断是否成功加入
 */
	@Test
	public void testAddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person james = new Person("James");
		Person bryant = new Person("James");
		assertEquals(true,graph.addVertex(james));
		assertEquals(false,graph.addVertex(bryant));
		Person durant = new Person("Durant");
		graph.addVertex(durant);
		assertEquals(durant,graph.persons.get(graph.names.size()-1));
		
	}
/*
 * 建立一个简单的图，判断最短距离是否正确。
 */
	@Test
	public void testGetDistance() {
		FriendshipGraph graph = new FriendshipGraph();
		Person james = new Person("James");
		Person bryant = new Person("Bryant");
		Person curry = new Person("Curry");
		Person jordan = new Person("Jordan");
		Person durant= new Person("Durant");
		graph.addVertex(james);
		graph.addVertex(bryant);
		graph.addVertex(curry);
		graph.addVertex(jordan);
		graph.addVertex(durant);
		graph.addEdge(james,bryant);
		graph.addEdge(bryant,james);
		graph.addEdge(bryant,curry);
		graph.addEdge(curry,bryant);
		graph.addEdge(jordan,curry);
		graph.addEdge(curry,jordan);
		graph.addEdge(bryant,durant);
		graph.addEdge(durant,bryant);
		assertEquals(1,graph.getDistance(james,bryant));
		assertEquals(2,graph.getDistance(james,curry));
		assertEquals(3,graph.getDistance(james,jordan));
		assertEquals(0,graph.getDistance(james,james));
		assertEquals(2,graph.getDistance(james,durant));
	}

}
