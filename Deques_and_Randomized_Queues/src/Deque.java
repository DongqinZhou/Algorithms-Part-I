import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first;
    
    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    public Deque() {
        first = null;
        size = 0;
    }                          // construct an empty deque
    public boolean isEmpty(){
        return size()==0;
    }                 // is the deque empty?
    public int size(){
        return size;
    }                        // return the number of items on the deque
    public void addFirst(Item item){
        throw new IllegalArgumentException("cannot add null");
    }      // add the item to the front
    public void addLast(Item item)   {
        throw new IllegalArgumentException("cannot add null");
    }        // add the item to the end
    public Item removeFirst(){
        if (isEmpty())
            throw new NoSuchElementException("deque is empty, cannot remove");
        Item item = first.item;
        return item;
    }                // remove and return the item from the front
    public Item removeLast(){
        if (isEmpty())
            throw new NoSuchElementException("deque is empty, cannot remove");
        Item item = first.item;
        return item;
    }               // remove and return the item from the end
    public Iterator<Item> iterator(){return new DequeIterator();}       // return an iterator over items in order from front to end
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no next item");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {

    }  // unit testing (optional)
}
