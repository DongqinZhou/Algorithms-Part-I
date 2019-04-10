import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first;
    private Node last;

    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    public Deque() { // construct an empty deque
        first = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty(){
        return size()==0;
    }                 // is the deque empty?
    public int size(){
        return size;
    }                        // return the number of items on the deque
    public void addFirst(Item item){                            // add the item to the front
        if (item == null)
            throw new IllegalArgumentException("cannot add null");
        if (size == 0){
            first = new Node();
            first.item = item;
            first.next = last;
            size++;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            size++;
        }
    }
    public void addLast(Item item)   {                  // add the item to the end
        if (item == null)
            throw new IllegalArgumentException("cannot add null");
        if (size ==0){
            last = new Node();
            last.item = item;
            last.previous = first;
            size++;
        }
        else{
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.previous = oldlast;
            size++;
        }
    }
    public Item removeFirst(){                      // remove and return the item from the front
        if (isEmpty())
            throw new NoSuchElementException("deque is empty, cannot remove");
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }
    public Item removeLast(){               // remove and return the item from the end
        if (isEmpty())
            throw new NoSuchElementException("deque is empty, cannot remove");
        Item item = last.item;
        last = last.previous;
        size--;
        return item;
    }
    public Iterator<Item> iterator(){return new DequeIterator();}       // return an iterator over items in order from front to end
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext()  { return current != null; }
        public void remove()      { throw new UnsupportedOperationException("baby don't do this to me");  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no next item");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) { // unit testing (optional)
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(15);
        deque.addLast(18);
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
    }
}
