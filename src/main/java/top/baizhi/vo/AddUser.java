package top.baizhi.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class AddUser implements Serializable {
    private String username;
    private String phone;
    private String brief;
    private MultipartFile photo;
}
