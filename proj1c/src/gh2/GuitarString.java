package gh2;

// TODO: maybe more imports
import deque.*;
//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayDeque61B<>();
        int capacity = (int) Math.round(SR/frequency);
        for(int i = 0; i< capacity;i++){
            buffer.addLast(0.0);
        }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        int capacity = buffer.size();
        for(int i = 0;i<capacity;i++){
            buffer.removeFirst();
            buffer.addLast(Math.random()-0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        if(buffer.size() < 2){
            return;
        }else{
            double front = buffer.removeFirst();
            double next = buffer.get(0);
            double newDouble = ((front + next) / 2)*DECAY;
            buffer.addLast(newDouble);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (buffer.isEmpty()) {
            return 0.0; // 如果缓冲区为空，返回 0.0
        }
        return buffer.get(0); // 返回前端值
    }
}
    // TODO: Remove all comments that say TODO when you're done.
