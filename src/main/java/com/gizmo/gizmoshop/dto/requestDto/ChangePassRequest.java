package com.gizmo.gizmoshop.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePassRequest {
    @NotBlank(message = "Mật khẩu cũ không được để trống")
    @Size(min = 6, message = "Mật khẩu cần tối thiểu 6 ký tự")
    private String oldPassword;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, message = "Mật khẩu cần tối thiểu 6 ký tự")
    private String newPassword;

    @NotBlank(message = "Mật khẩu xác nhận không được để trống")
    @Size(min = 6, message = "Mật khẩu cần tối thiểu 6 ký tự")
    private String confirmPassword;

    @NotBlank(message = "OTP Không được để trống")
    @Pattern(regexp = "\\d{6}", message = "OTP phải là 6 chữ số")
    private String otp;

}
