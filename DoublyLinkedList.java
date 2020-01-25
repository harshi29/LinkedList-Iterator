package pxs180053;

import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

import pxs180053.SinglyLinkedList.Entry;
import pxs180053.SinglyLinkedList.SLLIterator;

public class DoublyLinkedList<T> extends pxs180053.SinglyLinkedList<T> {
	
	
    static class Entry<E> extends pxs180053.SinglyLinkedList.Entry<E> {
	Entry<E> prev;
	Entry<E> next;
	Entry(E x, Entry<E> nxt, Entry<E> prev) {
	    super(x, nxt);
	    this.prev = prev;
	}
    }
    
    Entry<T> head, tail; 

    public DoublyLinkedList()
    {
    	head = new Entry<>(null, null, null);
    	tail = head;
    	size = 0;	
    }
    
    public Iterator<T> iterator()
    { 
    	return new DLLIterator();
    }
    
    protected class DLLIterator implements Iterator<T> {
    	Entry<T> cursor, prev;
    	boolean ready;      // is item ready to be removed?
    	
    	DLLIterator() {
    	    cursor = head;
    	    prev = null;
    	    ready = false;
    	}
    	
    	public boolean hasNext() {
    	    return cursor.next != null;
    	}
    	
    	public T next() {
    	    prev = cursor;
    	    cursor = cursor.next;
    	    ready = true;
    	    return cursor.element;
    	}
    	
    	public void remove() {
    	    if(!ready) {
    		throw new NoSuchElementException();
    	    }
    	    prev.next = cursor.next;
    	    // Handle case when tail of a list is deleted
    	    if(cursor == tail) {
    		tail = prev;
    	    }
    	    cursor.next.prev = prev;
    	    cursor = prev;
    	    ready = false;  // Calling remove again without calling next will result in exception thrown
    	    size--;
    	}
    	
    	public boolean hasPrev()
    	{
    		return cursor.prev!=null;
    	}
    	
    	public T prev()
    	{
    		if (cursor == head || cursor.prev == head)
    			return null;
    		
    		cursor = prev;
    		prev = prev.prev;
    		return cursor.element;
    	}
    	
    	public void add(T x)
    	{
    		add(new Entry<>(x,null, null));
    	}
    	
    	public void add(Entry<T> ent)
    	{
    		ent.next = cursor.next;
    		cursor.next.prev = ent;
    		cursor.next = ent;
    		ent.prev = cursor;
    		size++;
    	}
    	
        } // End of DLLIterator class
    
    public void add(T x)
    {
    	add(new Entry<>(x, null,null));
    }
    
    public void add(Entry<T> ent)
    {
    	tail.next = ent;
    	ent.prev = tail;
    	tail = tail.next;
    	size++;
    }
    
    public static void main(String[] args)
    {
    	int n = 10;
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    	DoublyLinkedList.DLLIterator it = list. new DLLIterator();
    	
    	for(int i=1; i<=10; i++) {
            list.add(Integer.valueOf(i));
        }
    	
    	list.printList();
    	Scanner in = new Scanner(System.in);
    	whileloop:
    		while(in.hasNext()) {
    		    int com = in.nextInt();
    		    switch(com) {
    		    case 1:  // Move to next element and print it
    			if (it.hasNext()) {
    			    System.out.println(it.next());
    			} else {
    			    break whileloop;
    			}
    			break;
    		    case 2:  // Remove element
    			it.remove();
    			list.printList();
    			break;
    		    case 3:  // Move to previous element and print it
    		    	if (it.hasPrev())
    		    		System.out.println(it.prev());
    		    	else
    		    		break whileloop;
    		    break;
    		    case 4:
    		    	Scanner scanner = new Scanner(System.in);
    		    	it.add(scanner.nextInt());
    		    	
    		    default:  // Exit loop
    			 break whileloop;
    		    }
    		} // End of while loop
    	list.printList();
    	//System.out.println(list.size);
            
    }
   
    
    

    }
    
