package target;

import annotation.Log;

public class TestLogging {

    @Log
    public void calculation(int param) {
        System.out.println("I've got param: " + param);
    }
}
