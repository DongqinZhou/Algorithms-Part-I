public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    public Deque() {

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
    public Item removeFirst()                // remove and return the item from the front
    public Item removeLast()               // remove and return the item from the end
    public Iterator<Item> iterator() ;        // return an iterator over items in order from front to end
    public static void main(String[] args)   // unit testing (optional)
}
