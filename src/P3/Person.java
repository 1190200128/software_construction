package p3;
import java.util.ArrayList;
public class Person {

	public String name;
	public ArrayList<Person>  Addressbook = new ArrayList<Person>();
	public Person(String newName)
	{
		name = newName;
	}
 	public void makeFriend(Person newFreind)
 	{
 		Addressbook.add(newFreind);
 	}
    public boolean ifIsFriend(Person someone )
    {
    	if(Addressbook.contains(someone))
    	{
    		return true;
    	}
    	else {
    		
    		return false;	
    		
		}
    }
    public String returnName()
    {
    	return name;
    }
}
