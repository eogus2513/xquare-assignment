package com.xquare.assignment.domain.post.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdatePostRequest {

    @NotBlank(message = "title은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 50, message = "title은 50글자를 넘을 수 없습니다.")
    private String title;

    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 1000, message = "content는 1000글자를 넘을 수 없습니다.")
    private String content;
}
