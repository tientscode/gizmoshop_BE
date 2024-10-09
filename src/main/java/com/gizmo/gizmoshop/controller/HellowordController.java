package com.gizmo.gizmoshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowordController {

    @PreAuthorize("permitAll()")
    @GetMapping("/api/test")
    public String testApi(
    ) {
        return "Hello World";
    }

    @PostMapping("/api/updateProduct")
    @Operation(summary = "Cập nhật thông tin sản phẩm",
            description = "Cập nhật thông tin của sản phẩm trong cơ sở dữ liệu.",
            tags = {"Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sản phẩm được cập nhật thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })



    public String updateProduct(){
        return "Hello World";
    }

}
