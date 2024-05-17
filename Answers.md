# First Question

**The Output**: 

    Thread was interrupted!
    Thread will be finished here!!!

When you try to interrupt a running thread that is has been put to sleep by sleep() method, an InterruptedException will be thrown. In this examplpe, this exception is caught and therefore the code in the catch block will be executed and hence we'll see the first line of output. After that, the finally block will be executed.

# Second Question

**The Output**

    Running in: main

invoking a method of a runnable object directly without initiating it in a thread, will make the method to be ran sequentially, like any other method. This means that there no new threads will be made.

# Third Question

**The Output**

    Running in: Thread-0
    Back to: main

When join() is called, the main method will pause, and it will wait until that thread is done running; then, the main thread continues to execute