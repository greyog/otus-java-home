package target;

import annotation.MyLog;

public class TestLogging {

    @MyLog
    public void calculation(int param) {
        System.out.println("I've got param: " + param);
    }
}
