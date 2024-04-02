package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Receipt;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.service.ReceiptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Tag(name = "Receipts", description = "Receipts management APIs")
@RestController
@RequestMapping("api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/create")
    public ResponseEntity createReceipt(@RequestBody Receipt receipt) {
        HashMap<String, Object> map = receiptService.createReceipt(receipt);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(map.get("Object"));
        }
    }

    @GetMapping
    public ResponseEntity getAllReceipts() {
        HashMap<String, Object> map = receiptService.getAllReceipts();
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }

    @GetMapping("delete/{receiptId}")
    public ResponseEntity deleteReceipt(@PathVariable Long receiptId) {
        HashMap<String, Object> map = receiptService.deleteReceipt(receiptId);
        if (map.containsKey("success")) {
            Success success = (Success) map.get("success");
            return ResponseEntity.ok(success);
        } else if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{receiptId}")
    public ResponseEntity getReceiptById(@PathVariable Long receiptId) {
        HashMap<String, Object> map = receiptService.getReceiptById(receiptId);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }
}
