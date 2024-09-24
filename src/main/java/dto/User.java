package dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String NAME;     // 이름

    @Getter
    @Valid
    @Email
    private String ID;       // 아이디(이메일) / UNIQUE, 중복 방지

    private String PASSWORD; // 비밀번호 길이 20자 제한
    private String NUMBER;   // 전화번호

}
