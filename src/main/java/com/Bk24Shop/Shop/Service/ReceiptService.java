package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.dto.ReceiptDTO;
import com.Bk24Shop.Shop.entity.Receipt;
import java.util.HashMap;

public interface ReceiptService {
    HashMap<String, Object> createReceipt(ReceiptDTO receipt);
    HashMap<String, Object> getAllReceipts();
    HashMap<String, Object> getReceiptById(Long id);
    HashMap<String, Object> deleteReceipt(Long id);
}
