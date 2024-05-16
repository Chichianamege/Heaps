// Name: Chidera Anamege
// Class: CS 3305
// Term: Spring 2024
//	Instructor:  Carla McManus
//	Assignment:  7-Part-2-Heaps
import java.util.*;

public class dsassignment7parttwo {
    private static class heapMin {
        //  ArrayList is gonna hold our heap
        private ArrayList<Integer> heap;

        // Constructor for heapMin, initializes the heap ArrayList
        public heapMin() {
            heap = new ArrayList<>();
        }

        // This method inserts a value into the heap
        public void insert(int value) {
            // add the value to the end of the heap
            heap.add(value);
            int index = heap.size() - 1;
            while (index > 0) {
                // get the parent index
                int parentIndex = (index - 1) / 2;
                //  the parent is smaller than or equal to the value
                if (heap.get(parentIndex) <= heap.get(index))
                    break;
                // otherwise, we gotta swap them
                swap(index, parentIndex);
                index = parentIndex;
            }
        }

        // This method extracts the minimum value from the heap
        public int extractMin() {
            // if the heap is empty, throw an exception error
            if (heap.isEmpty()) {
                throw new NoSuchElementException("Heap is empty");
            }
            // get the minimum value (which is always at the root)
            int min = heap.get(0);
            // now we gotta fix the heap
            int lastElement = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                heapMethod();
            }
            return min;
        }

        // This private method rearranges the heap after extraction
        private void heapMethod() {
            int index = 0;
            int size = heap.size();
            // gotta loop through the heap to fix it
            while (index < size) {
                int leftMost = 2 * index + 1;
                int rightMost = 2 * index + 2;
                int smallest = index;
                // gotta find the smallest child
                if (leftMost < size && heap.get(leftMost) < heap.get(smallest))
                    smallest = leftMost;
                if (rightMost < size && heap.get(rightMost) < heap.get(smallest))
                    smallest = rightMost;
                // if the smallest child is smaller than the parent, swap them
                if (smallest != index) {
                    swap(index, smallest);
                    index = smallest;
                } else {
                    break;
                }
            }
        }

        // This private method swaps two elements in the heap
        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
    public static void main(String[] args) {
        // create a new heap
        heapMin heapMin = new heapMin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 5 numbers:");
        // loop through 5 times to get those numbers
        for (int i = 0; i < 5; i++) {
            int num = scanner.nextInt();
            heapMin.insert(num);
        }
        // now we got the numbers in the heap, print and sort
        System.out.println("Your numbers in sorted order:");
        for (int i = 0; i < 5; i++) {
            System.out.println(heapMin.extractMin());
        }
    }
}
