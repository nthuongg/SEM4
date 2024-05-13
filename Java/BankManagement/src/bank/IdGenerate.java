package bank;

import java.util.concurrent.locks.ReentrantLock;

public class IdGenerate {
    private static Long id = 0L;
    private static ReentrantLock lock = new ReentrantLock();

    public static Long getNewID() {
        Long result;
        lock.lock(); //Khóa biến í lại chỉ cho duy nhất 1 thread được truy cập
        //để tránh tinnh trạng Data Racing !
        try {
            result = ++id; //Tăng rồi gán vào result
        } finally {
            lock.unlock(); // Tăng rồi thì mở lock cho thread khác cung truy cập
            // Nhiều như mình dùng xong WC thì đi ra thôi.
        }
        return result;
    }
    private IdGenerate() {}

}
