package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.entity.Receipt;
import java.util.HashMap;

public interface ReceiptService {
    HashMap<String, Object> createReceipt(Receipt receipt);
    HashMap<String, Object> getAllReceipts();
    HashMap<String, Object> getReceiptById(Long receiptId);
    HashMap<String, Object> deleteReceipt(Long receiptId);
}
