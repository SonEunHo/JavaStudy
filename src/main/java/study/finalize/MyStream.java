package study.finalize;

/**
 * Created by Nano.son on 2018. 3. 20.
 */
public class MyStream implements AutoCloseable{
    public MyStream() {
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyStream.close");
    }
}
