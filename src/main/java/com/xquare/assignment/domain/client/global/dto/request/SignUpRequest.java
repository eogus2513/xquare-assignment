package com.xquare.assignment.domain.client.global.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "account_id는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 6, max = 20, message = "account_id는 6글자 이상, 20글자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,30}$\n",
            message = "password는 영문, 숫자, 특수기호가 포함되어야 하며 8글자 이상 30글자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 5, message = "name은 1글자 이상, 5글자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "profile_image_url은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String profileImageUrl;
}
