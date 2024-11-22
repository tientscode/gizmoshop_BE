package com.gizmo.gizmoshop.controller;

import com.gizmo.gizmoshop.dto.reponseDto.ResponseWrapper;
import com.gizmo.gizmoshop.dto.reponseDto.RoleResponse;
import com.gizmo.gizmoshop.dto.reponseDto.SupplierDto;
import com.gizmo.gizmoshop.sercurity.UserPrincipal;
import com.gizmo.gizmoshop.service.SupplierService;
import com.gizmo.gizmoshop.service.WithdrawalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/public/supplier/t")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SupplierApi {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private WithdrawalHistoryService withdrawalHistoryService;


    @GetMapping("/info")
    @PreAuthorize("hasRole('ROLE_SUPPLIER')") // Chỉ cho phép ROLE_SUPPLIER truy cập
    public ResponseEntity<ResponseWrapper<SupplierDto>> supplierInfo(
            @AuthenticationPrincipal UserPrincipal user
            ) {
        SupplierDto info = supplierService.getInfo(user.getUserId());
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK, "Lấy thông tin của đối tác thành công",info));
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    public ResponseEntity<ResponseWrapper<SupplierDto>> withdraw(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody SupplierDto supplier
    ) {
       supplierService.withdraw(user.getUserId(),supplier);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK, "Rút tiền thành công",null));
    }


    //đơn hàng đã giao dịch của supplier theo trạng thái
    @GetMapping("/count-order-by-status")
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    public ResponseEntity<ResponseWrapper<SupplierDto>> OrderCountBySupplier(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestParam List<String> statusIds
    ) {
        SupplierDto count = supplierService.OrderCountBySupplier(user.getUserId(),statusIds);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK, "Lấy số lượng đơn hàng của đối tác thành công",count));
    }

    @GetMapping("/Order-Total-Price-By-Supplier")
    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    public ResponseEntity<ResponseWrapper<SupplierDto>> OrderTotalPriceBySupplier(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestParam List<String> statusIds,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
            ) {
        if (startDate == null || endDate == null) {
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfMonth = now.withDayOfMonth(1);
            LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());
            startDate = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
            endDate = Date.from(lastDayOfMonth.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
        }
        SupplierDto count = supplierService.OrderTotalPriceBySupplier(user.getUserId(),statusIds,startDate,endDate);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK, "Lấy số doanh thu của đối tác thành công",count));
    }

}
