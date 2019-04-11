





































/*
import java.util.NoSuchElementException;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node{
        Item item;
        Node next;
    }
    public RandomizedQueue(){
        first = null;
        last = null;
        size = 0;
    }                 // construct an empty randomized queue
    public boolean isEmpty(){
        return size() == 0;
    }                 // is the randomized queue empty?
    public int size(){
        return size;
    }                        // return the number of items on the randomized queue
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("cannot enqueue null");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else   oldlast.next = last;
        size++;
    }         // add the item
    public Item dequeue(){
        if (isEmpty())
            throw new NoSuchElementException("empty queue");
        Item item = first.item;
        first = first.next;
        //if(isEmpty()) last = null;
        size--;
        return item;
    }                    // remove and return a random item
    public Item sample(){
        if (isEmpty())
            throw new NoSuchElementException("empty queue");
    }                     // return a random item (but do not remove it)
    public Iterator<Item> iterator()  {return new RandomizedQueueIterator();}        // return an independent iterator over items in random order
    private class RandomizedQueueIterator implements Iterator<Item>{
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
    public static void main(String[] args)   // unit testing (optional)
}
*/