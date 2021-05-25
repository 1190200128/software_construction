package p3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/*
 * 建立一个五个人简单的交际网
 */
public class FriendshipGraph {
	public ArrayList<Person> persons = new ArrayList<Person>();
	public ArrayList<String> names= new ArrayList<String>();
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		 Person rachel = new Person("Rachel");
		 Person ross = new Person("Ross");
		 Person ben = new Person("Ben");
		 Person kramer = new Person("Kramer");
		 graph.addVertex(rachel);
		graph.addVertex(ross);
		 graph.addVertex(ben);
		 graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben));
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		//should print -1
	}
	/*
	 * 建立交际关系
	 */
	public boolean addEdge(Person m,Person n)
	{
		if(m.name == n.name)
		{
			System.out.println("错误，存在有导入的两个人是同一个人的问题");
			return false;
		}
		boolean result = m.ifIsFriend(n);
		if(result == true)
		{
			return true;
		}
		m.makeFriend(n);
		
		return true;
		
	}
	/*
在建立的交际网中添加新的人，同时不能有重复名字
	*/
	public boolean addVertex(Person someone)
	{
		String name = someone.returnName();
		if(names.contains(name))
		{
			System.out.println("有名字重复，违反每个名字独特的原则");
			return false;
		}
		persons.add(someone);
		names.add(name);
		return true;
	}
	/*
	 * 利用BFS计算交际图中两点最短距离
	 */
	public int getDistance(Person m, Person n )
	{
		if((m.ifIsFriend(n))&&(n.ifIsFriend(m)))
		{
			return 1;
		}
		if(m.name == n.name)
		{
			return 0;
		}
		Queue <Person> myqueue =new LinkedList<Person>();
		Map<Person,Integer> map =new HashMap<>();
		myqueue.offer(m);
		map.put(m, 0);
		while(!myqueue.isEmpty())
		{
			Person firstOne = myqueue.poll();
			int distance=map.get(firstOne);
			List<Person> friendsList = firstOne. Addressbook;
			for(Person i : friendsList)
			{
				if(map.containsKey(i)==false)
				{
					map.put(i, distance+1);
					myqueue.offer(i);
					if(i==n)
					{
						return map.get(n);
					}
				}
			}
			
		}
		return -1;
	}

}
