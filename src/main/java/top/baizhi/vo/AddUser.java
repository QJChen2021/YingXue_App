package top.baizhi.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddUser {
    private String username;
    private String phone;
    private String brief;
    private MultipartFile photo;
}
