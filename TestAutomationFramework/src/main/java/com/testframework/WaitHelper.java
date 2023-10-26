package com.testframework;

public class WaitHelper {

    /**
     * For debug purposes during UI test writing.
     * Not to be used instead of a proper wait for an element.
     *
     * @param milliseconds how long to wait on the certain step
     */
    public static void waitForUserInteraction(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            //
        }
    }

}
