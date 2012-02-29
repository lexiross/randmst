import java.util.ArrayList;

import java.util.*;

/**
   This class implements a heap.
*/
public class MinHeap implements Heap
{
	private ArrayList<AdjListNode> elements;
   
		/**
	      Constructs an empty heap.
	   */
	   public MinHeap()
	   {
	      elements = new ArrayList<AdjListNode>();
	      elements.add(null); 
	   }
	
	   /**
	      Adds a new element to this heap.
	      @param newElement the element to add
	   */
	   public void insert(AdjListNode newElement)
	   {
	      // Add a new leaf
	      elements.add(null);
	      int index = elements.size() - 1;
	      
	      // Demote parents that are larger than the new element
	      while (index > 1 
	            && getParent(index).compareTo(newElement) > 0) 
	      {
	         elements.set(index, getParent(index));
	         index = getParentIndex(index);
	      }
	
	      // Store the new element into the vacant slot
	      elements.set(index, newElement);
	   }
	
	   /**
	      Gets the minimum element stored in this heap.
	      @return the minimum element
	   */
	   public AdjListNode peek()
	   {
	      return elements.get(1);
	   }
	
	   /**
	      Removes the minimum element from this heap.
	      @return the minimum element
	   */
	   public AdjListNode deleteMin()
	   {
	      AdjListNode minimum = elements.get(1);      
	
	      // Remove last element
	      int lastIndex = elements.size() - 1;
	      AdjListNode last = elements.remove(lastIndex);
	
	      if (lastIndex > 1)
	      {
	         elements.set(1, last);
	         fixHeap();     
	      }
	
	      return minimum;
	   }
	
	   /**
	      Turns the tree back into a heap, provided only the root 
	      node violates the heap condition.
	   */
	   private void fixHeap()
	   {
	      AdjListNode root = elements.get(1);
	
	      int lastIndex = elements.size() - 1;
	      // Promote children of removed root while they are larger than last      
	
	      int index = 1;
	      boolean more = true;
	      while (more)
	      {
	         int childIndex = getLeftChildIndex(index);
	         if (childIndex <= lastIndex)
	         {
	            // Get smaller child 
	
	            // Get left child first
	            AdjListNode child = getLeftChild(index);
	
	            // Use right child instead if it is smaller
	            if (getRightChildIndex(index) <= lastIndex 
	                  && getRightChild(index).compareTo(child) < 0)
	            {
	               childIndex = getRightChildIndex(index);
	               child = getRightChild(index);
	            }
	
	            // Check if larger child is smaller than root
	            if (child.compareTo(root) < 0) 
	            {
	               // Promote child
	               elements.set(index, child);
	               index = childIndex;
	            }
	            else
	            {
	               // Root is smaller than both children
	               more = false;
	            }
	         }
	         else 
	         {
	            // No children
	            more = false; 
	         }
	      }
	
	      // Store root element in vacant slot
	      elements.set(index, root);
	   }
	
	   /**
	      Returns the number of elements in this heap.
	   */
	   public int size()
	   {
	      return elements.size() - 1;
	   }
	
	   /**
	      Returns the index of the left child.
	      @param index the index of a node in this heap
	      @return the index of the left child of the given node
	   */
	   private static int getLeftChildIndex(int index)
	   {
	      return 2 * index;
	   }
	
	   /**
	      Returns the index of the right child.
	      @param index the index of a node in this heap
	      @return the index of the right child of the given node
	   */
	   private static int getRightChildIndex(int index)
	   {
	      return 2 * index + 1;
	   }
	
	   /**
	      Returns the index of the parent.
	      @param index the index of a node in this heap
	      @return the index of the parent of the given node
	   */
	   private static int getParentIndex(int index)
	   {
	      return index / 2;
	   }
	
	   /**
	      Returns the value of the left child.
	      @param index the index of a node in this heap
	      @return the value of the left child of the given node
	   */
	   private AdjListNode getLeftChild(int index)
	   {
	      return elements.get(2 * index);
	   }
	
	   /**
	      Returns the value of the right child.
	      @param index the index of a node in this heap
	      @return the value of the right child of the given node
	   */
	   private AdjListNode getRightChild(int index)
	   {
	      return elements.get(2 * index + 1);
	   }
	
	   /**
	      Returns the value of the parent.
	      @param index the index of a node in this heap
	      @return the value of the parent of the given node
	   */
	   private AdjListNode getParent(int index)
	   {
	      return elements.get(index / 2);
	   }
	
	
	@Override
	public boolean isEmpty() {
		return (elements.size() == 1);
	}
	
}