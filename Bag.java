import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Bag<T>
{
   /**
    * Construct an empty bag
    */
   public Bag()
   {
      // Size begins at 0 and our hashmap begins empty
      _size = 0;
      _bags = new HashMap<>();
   }

   /**
    * @param element
    *           to search for
    * @returns true if the element is in the Bag
    */
   public boolean contains(T element)
   {
      // Return the result of the search for the element.
      return _bags.containsKey(element);
   }

   /**
    * @param element
    *           to search for
    * @returns number of copies of the element in the Bag
    */
   public int countOf(T element)
   {
      // Return the counter value associated with the element.
      return _bags.get(element).get();
   }

   /**
    * @param element
    *           to add to the bag
    */
   public void add(T element)
   {
      // If the current bag contains the element then increment our bag size
      // counter and the element count.
      if (_bags.containsKey(element))
      {
         // Increment the counters since we just added the element.
         _bags.get(element).getAndIncrement();
         _size++;
      }
      else
      // Else if the current bag does not contain the element then put the
      // element in our hashmap and increment our bag size counter and the
      // element count.
      {
         // Add the element to the hashmap and add a new counter.
         _bags.put(element, new AtomicInteger());

         // Increment the counters since we just added the element.
         _bags.get(element).getAndIncrement();
         _size++;
      }
   }

   /**
    * Removes at most one instance from the bag
    * 
    * @param element
    *           to remove from the bag
    * @returns true if an element was removed, false if not in the bag
    */
   public boolean remove(T element)
   {
      // Assume we can't remove the element.
      boolean result = false;

      // If we have the key then remove it and keep the size accurate and the
      // keys in our hashmap updated.
      if (_bags.containsKey(element))
      {
         // First decrement the count value of the key
         _bags.get(element).getAndDecrement();

         // If our T counter has reached 0, then remove the key from the
         // collection.
         if (_bags.get(element).get() == 0)
         {
            // Remove the key.
            _bags.remove(element);
         }

         // Set result to true and decrement the size of this bag
         result = true;
         _size--;
      }

      // Finally return the result of the attempt to remove one of the element.
      return result;
   }

   /**
    * @returns true if the Bag is empty
    */
   public boolean isEmpty()
   {
      // Return a boolean comparison of the current size to 0, if it is empty
      // then true will be returned, otherwise false.
      return _size == 0;
   }

   /**
    * @returns number of elements in the Bag
    */
   public int size()
   {
      // Return the total count of the elements that have been added to this
      // hashmap.
      return _size;
   }

   // Attributes of a bag object
   private int                       _size;
   private HashMap<T, AtomicInteger> _bags;
}