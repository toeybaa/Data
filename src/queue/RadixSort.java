package queue;

/**
 * Created by Peth on 2/27/17.
 */
public class RadixSort {
    int[] theArray;

    public RadixSort(int[] theArray) {
        this.theArray = theArray;
    }

    // Return the kth digit of v.
    // The least significant digit is 0.
    public int getKthDigit(int v, int k) {
        for (int i = 0; i < k; i++)
            v /= 10;
        return v % 10;
    }

    // Find the number of digits of a value v.
    public int numberOfDigit(int v) {
        int total = 1;
        while ((v / 10) > 0) {
            total++;
            v = v / 10;
        }
        return total;
    }

    // Get the number of digits of
    // the longest number in theArray.
    public int maxDigit() {
        int maxDigit = 1;
        for (int i = 0; i < theArray.length; i++) {
            int n = numberOfDigit(theArray[i]);
            if (n > maxDigit)
                maxDigit = n;
        }
        return maxDigit;
    }

    public void sort() throws Exception {
        int maxDigit = maxDigit();
        MyQueue[] allQueues = new MyQueue[10];

        // initialize all 10 queues
        for (int i = 0; i < 10; i++)
            allQueues[i] = new QueueLinkedList();

        // for each digit
        for (int k = 0; k < maxDigit; k++) {
            // for each data in array
            for (int i = 0; i < theArray.length; i++) {
                int value = theArray[i];
                MyQueue q = allQueues[getKthDigit(value, k)];
                q.insertLast(value);
            }

            // index of array when we put data in from each
            // queue.
            int j = 0;

            // for each queue
            for (int i = 0; i < 10; i++) {
                // empty each queue and output to theArray.
                while (!allQueues[i].isEmpty()) {
                    int data = allQueues[i].removeFirst();
                    theArray[j++] = data;
                }
            }
        } //end outer for
    } //end method
} //end class
