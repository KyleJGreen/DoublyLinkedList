// Test driver for the MyDoublyLinkedList class.
// by Jeff Ward

import java.util.*;

public class TestMyDoublyLinkedList {
  public static void main(String[] args) {
    MyAbstractSequentialList<String> list = new MyDoublyLinkedList<String>();
    try {
    	list.removeFirst();
    	System.out.println("Test 1 failed");
    	System.exit(1);
    }
    catch (NoSuchElementException ex) {
		System.out.println("Test 1 successful");
	}
    try {
    	list.removeLast();
    	System.out.println("Test 2 failed");
    	System.exit(1);
    }
    catch (NoSuchElementException ex) {
		System.out.println("Test 2 successful");
	}
   
    list.add("Alabama");
	list.add("Alaska");
 	list.add("Arizona"); 
	list.add("Arkansas");

	// The list should now be:
	// [Alabama, Alaska, Arizona, Arkansas]
	String listToString = list.toString();	
	if (listToString.equals("[Alabama, Alaska, Arizona, Arkansas]"))
		System.out.println("Test 3 successful");
	else {
		System.out.println("Test 3 failed");
		System.out.println(listToString);
		System.exit(1);
	}	
	 
	list.add(4, "California");
	list.add(0, "West Virginia");
	list.add(2, "Wisconsin");
	list.add(6, "Wyoming");
	
	// [West Virginia, Alabama, Wisconsin, Alaska, Arizona, Arkansas, Wyoming, California]	
	listToString = list.toString();
	if (listToString.equals("[West Virginia, Alabama, Wisconsin, Alaska, Arizona, Arkansas, Wyoming, California]"))
		System.out.println("Test 4 successful");
	else {
		System.out.println("Test 4 failed");
		System.out.println(listToString);
		System.exit(1);
	}
	
	list.remove(list.indexOf("West Virginia"));
	list.remove(list.indexOf("Wisconsin"));
	list.remove("Wyoming");
	
	// [Alabama, Alaska, Arizona, Arkansas, California]
	listToString = list.toString();	
	if (listToString.equals("[Alabama, Alaska, Arizona, Arkansas, California]"))
		System.out.println("Test 5 successful");
	else {
		System.out.println("Test 5 failed");
		System.out.println(listToString);
		System.exit(1);
	}
	
	// still [Alabama, Alaska, Arizona, Arkansas, California]"))
	StringBuilder iteratorToString = new StringBuilder();
	for (String s : list) {
		iteratorToString.append(s);
	}
	if (iteratorToString.toString().equals("AlabamaAlaskaArizonaArkansasCalifornia"))
		System.out.println("Test 6 successful");
	else {
		System.out.println("Test 6 failed");
		System.out.println(listToString);
		System.out.println(iteratorToString.toString());
		System.exit(1);
	}
	
	list.set(1, "Arkansas");
	
	// [Alabama, Arkansas, Arizona, Arkansas, California]
	listToString = list.toString();	
	if (listToString.equals("[Alabama, Arkansas, Arizona, Arkansas, California]"))
		System.out.println("Test 7 successful");
	else {
		System.out.println("Test 7 failed");
		System.out.println(listToString);
		System.exit(1);
	}
	
	if (!list.contains("Arizona"))
		throw new RuntimeException(); 
	if (list.contains("Alaska"))
		throw new RuntimeException(); 
	if (list.get(2) != "Arizona")
		throw new RuntimeException();
	if (list.indexOf("Arkansas") != 1)
		throw new RuntimeException();
	if (list.lastIndexOf("Arkansas") != 3)
		throw new RuntimeException();
	try {
		list.get(-1);
		System.out.println("Test 8 failed");
    	System.exit(1);
	}
	catch (IndexOutOfBoundsException ex) {
		System.out.println("Test 8 successful");
	}
	try {
		list.get(5);
		System.out.println("Test 9 failed");
    	System.exit(1);
	}
	catch (IndexOutOfBoundsException ex) {
		System.out.println("Test 9 successful");
	}
	try {
		list.set(-1, "Colorado");
		System.out.println("Test 10 failed");
    	System.exit(1);
	}
	catch (IndexOutOfBoundsException ex) {
		System.out.println("Test 10 successful");
	}
	try {
		list.set(5, "Connecticut");
		System.out.println("Test 11 failed");
    	System.exit(1);
	}
	catch (IndexOutOfBoundsException ex) {
		System.out.println("Test 11 successful");
	}
	try {
		list.remove(-1);
		System.out.println("Test 12 failed");
    	System.exit(1);
	}
	catch (IndexOutOfBoundsException ex) {
		System.out.println("Test 12 successful");
	}
	try {
		list.remove(5);
		System.out.println("Test 13 failed");
    	System.exit(1);
	}
	catch (IndexOutOfBoundsException ex) {
		System.out.println("Test 13 successful");
	}
	
	// still [Alabama, Arkansas, Arizona, Arkansas, California]
	iteratorToString = new StringBuilder();
	ListIterator<String> listIter = list.listIterator(0);
	try {
		listIter.previous();
		System.out.println("Test 14 failed");
		System.exit(1);		
	}
	catch (NoSuchElementException ex) {
		System.out.println("Test 14 successful");
	}
	while (listIter.hasNext())
		iteratorToString.append(listIter.next());	
	if (iteratorToString.toString().equals("AlabamaArkansasArizonaArkansasCalifornia"))
		System.out.println("Test 15 successful");
	else {
		System.out.println("Test 15 failed");
		System.out.println(iteratorToString.toString());
		System.exit(1);
	}
	try {
		listIter.next();
		System.out.println("Test 16 failed");
		System.exit(1);		
	}
	catch (NoSuchElementException ex) {
		System.out.println("Test 16 successful");
	}
	
	iteratorToString = new StringBuilder();
	while (listIter.hasPrevious())
		iteratorToString.append(listIter.previous());
		
	if (iteratorToString.toString().equals("CaliforniaArkansasArizonaArkansasAlabama"))
		System.out.println("Test 17 successful");
	else {
		System.out.println("Test 17 failed");
		System.out.println(iteratorToString.toString());
		System.exit(1);
	}
	
	int counter = 2;
	listIter = list.listIterator(counter);
	while (listIter.hasNext()) {
		if (listIter.nextIndex() != counter)
			throw new RuntimeException();
		if (listIter.previousIndex() != counter - 1)
			throw new RuntimeException();
		if (listIter.next() != list.get(counter))
			throw new RuntimeException();
		counter++;
	}
	while (listIter.hasPrevious()) {
		if (listIter.nextIndex() != counter)
			throw new RuntimeException();
		if (listIter.previousIndex() != counter - 1)
			throw new RuntimeException();
		if (listIter.previous() != list.get(counter - 1))
			throw new RuntimeException();
		counter--;
	}
	System.out.println("Test 18 successful");
	
	// still [Alabama, Arkansas, Arizona, Arkansas, California]
	while (listIter.hasNext())
		if (listIter.next().length() == 7)
			listIter.remove();

	while (listIter.hasNext())
		listIter.next();
	while (listIter.hasPrevious())
		if (listIter.previous() == "California")
			listIter.remove();
	
	// [Arkansas, Arkansas]		
	listToString = list.toString();	
	if (listToString.equals("[Arkansas, Arkansas]"))
		System.out.println("Test 19 successful");
	else {
		System.out.println("Test 19 failed");
		System.out.println("listToString = " + listToString);
		System.exit(1);
	}
	
	listIter = list.listIterator();
	listIter.next();
	listIter.set("Indiana");
	listIter.add("Kentucky");
	listIter.next();
	listIter.set("Ohio");
	
	// [Indiana, Kentucky, Ohio]
	if (list.size() != 3)
		throw new RuntimeException();
	if (list.getFirst() != "Indiana" || list.getLast() != "Ohio")
		throw new RuntimeException();		
	if (list.removeFirst() != "Indiana" || list.removeLast() != "Ohio")
		throw new RuntimeException();
	System.out.println("Test 20 successful");
		
	// [Kentucky]
	listToString = list.toString();	
	if (listToString.equals("[Kentucky]"))
		System.out.println("Test 21 successful");
	else {
		System.out.println("Test 21 failed");
		System.out.println("listToString = " + listToString);
		System.exit(1);
	}
	
	listIter = list.listIterator();
	
	try {
    	listIter.remove();
    	System.out.println("Test 22 failed");
		System.exit(1);
    }
    catch (IllegalStateException ex) {
    	System.out.println("Test 22 successful");
    }
	
	iteratorToString = new StringBuilder();
	iteratorToString.append(listIter.next());
    if (iteratorToString.toString().equals("Kentucky"))
		System.out.println("Test 23 successful");
	else {
		System.out.println("Test 23 failed");
		System.out.println("iteratorToString = " + iteratorToString);
		System.exit(1);
	}
    listIter.remove();
    try {
    	listIter.remove();
    	System.out.println("Test 24 failed");
		System.exit(1);
    }
    catch (IllegalStateException ex) {
    	System.out.println("Test 24 successful");
    }
    listIter.add("Michigan");
    try {
    	listIter.remove();
    	System.out.println("Test 25 failed");
		System.exit(1);
    }
    catch (IllegalStateException ex) {
    	System.out.println("Test 25 successful");
    }
    
    list.add(1, null);
    
    // [Michigan, null]
    if (list.indexOf("Michigan") != 0 || list.indexOf(null) != 1)
		throw new RuntimeException();
	System.out.println("Test 26 successful");
  }
}