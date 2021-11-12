package top.baizhi;

import org.junit.Test;
import top.baizhi.util.Oss;

public class TestOss {
    Oss oss = new Oss();
    @Test
    public void testdownload(){
        oss.download("2021110361cee8ce6602491892c15af934dbdc6d.png");
    }
}
