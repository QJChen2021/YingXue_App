package top.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("top.baizhi.dao")
@SpringBootApplication
public class YingxCzmApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxCzmApplication.class, args);
    }

}
