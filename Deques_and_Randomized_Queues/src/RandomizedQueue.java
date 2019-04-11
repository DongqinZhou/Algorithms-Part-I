import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int head, tail;

    public RandomizedQueue(){
        head = 0;
        tail = 0;
        q = (Item[]) new Object[1];
    }                 // construct an empty randomized queue
    public boolean isEmpty(){
        return size() == 0;
    }                 // is the randomized queue empty?
    public int size(){
        return tail - head;
    }                        // return the number of items on the randomized queue
    public void enqueue(Item item){
        if (item == null)
            throw new IllegalArgumentException("cannot enqueue null");
        if(size() == q.length)
            resize(2 * q.length);
        if(tail == q.length && size() < q.length)
            reshape();
        q[tail++] = item;
    }           // add the item
    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < tail; i++)
            copy[i] = q[head + i];
        q = copy;
    }
    private void reshape(){
        Item[] copy = q;
        for(int i = 0; i < tail; i++)
            copy[i] = q[head + i];
        q = copy;
    }
    public Item dequeue(){
        if (isEmpty())
            throw new NoSuchElementException("empty queue");
        Item item = q[head++];
        q[head - 1] = null;
        if (size() == q.length / 4)
            resize(q.length / 2);
        return item;
    }                    // remove and return a random item
    public Item sample(){
        if (isEmpty())
            throw new NoSuchElementException("empty queue");
        int s = StdRandom.uniform(size());
        return q[tail + s];
    }                     // return a random item (but do not remove it)
    public Iterator<Item> iterator()  {return new RandomizedQueueIterator();}        // return an independent iterator over items in random order
    private class RandomizedQueueIterator implements Iterator<Item>{
        int i = StdRandom.uniform(size());
        public boolean hasNext()  { return true; }
        public void remove()      { throw new UnsupportedOperationException("baby don't do this to me");  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no next item");
            return q[head + i];
        }
    }
    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(15);
        rq.enqueue(18);
        rq.enqueue(17);
        rq.enqueue(16);
        int size = rq.size();
        for(int i = 0; i < size; i++)
            StdOut.println(rq.sample());
    }   // unit testing (optional)
}





































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