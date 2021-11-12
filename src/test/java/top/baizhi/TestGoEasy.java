package top.baizhi;

import io.goeasy.GoEasy;
import org.junit.Test;

public class TestGoEasy {
    @Test
    public void testGoEasy(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-3caba4bc937d40679055db34fab563b1");
                goEasy.publish("my_channel", "Hello, GoEasy! 你好 ");
    }
}
