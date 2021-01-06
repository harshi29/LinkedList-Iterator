package LinkedList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
    static class Entry<E> extends SinglyLinkedList.Entry<E> {
        Entry<E> prev;

        Entry(E x, Entry<E> next, Entry<E> prev) {
            super(x, next);
            this.prev = prev;
        }
    }

    public DoublyLinkedList() {
        head = new Entry<>(null,null, null);
        tail = head;
        size = 0;
    }

    public interface DoublyIterator<T> {
        boolean hasPrev();
        boolean hasNext();
        void remove();
        void add(T ent);
        T next();
        T prev();
    }

    public DLLIterator dllIterator() { return new DLLIterator(); }

    public class DLLIterator extends SLLIterator implements DoublyIterator<T>{

        DLLIterator(){
            super();
        }

       @Override
        public void remove(){
    	// if element not there
    	   if(!ready)
    		   throw new NoSuchElementException();
           if (cursor != tail)
               ((Entry)cursor.next).prev = ((Entry)cursor).prev;
           super.remove();
           return;
        }
       
    // Add new elements
       public void add(T x) {
           add(new Entry<>(x, null, null));
       }

       public void add(Entry<T> ent) {
           ent.next = cursor.next;
           cursor.next = ent ;
           ent.prev = ((Entry)cursor);
           prev = cursor;
           if (cursor != tail)
               ((Entry<T>) ent.next).prev = ent;
           else
               tail = ent;
           cursor = cursor.next;
           ready = false;
           size++;
       }

        public boolean hasPrev() {
            
            return ((Entry)cursor).prev != null;
        }

        public T prev() {
            cursor = ((Entry)cursor).prev;
            ready = true;
            
            if(cursor == head)
                ready = false;

            prev=((Entry) prev).prev;
            return cursor.element;
        }     

        
    }

    
    public void add(T x) {
        add(new Entry<T>(x, null, (Entry<T>) tail));
    }

    public static void main(String[] args) throws NoSuchElementException {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        DoublyIterator it = list.dllIterator();

        // adding elements in the Linked list
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
                        System.out.println( it.next());
                    } else {
                        break whileloop;
                    }
                    break; 
                case 2: // Move to previous element and print it
                    if (it.hasPrev())
                        System.out.println(it.prev());
                    else
                        break whileloop;
                    break;
                case 3: // Remove element and print the linked list
                    it.remove();
                    list.printList();
                    break;
                case 4: // Insert x before the element that will be returned by a call to next()
                    System.out.println("Enter the value of element:");
                    int x = in.nextInt();
                    it.add(Integer.valueOf(x));
                    list.printList();
                    break;
                
                case 5: // Print list
                    list.printList();
                default:  // Exit loop
                    break whileloop;
            }
        }
    }
}
