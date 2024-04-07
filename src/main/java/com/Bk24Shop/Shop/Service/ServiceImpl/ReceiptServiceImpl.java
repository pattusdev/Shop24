package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.dto.ReceiptDTO;
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.entity.Receipt;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.repository.ReceiptRepository;
import com.Bk24Shop.Shop.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    private final Map<Long, Receipt> receiptCache = new HashMap<>();

    @Override
    public HashMap<String, Object> createReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = new Receipt();
        receipt.setTimestamp(receiptDTO.getTimestamp());
        receipt.setOrder(receiptDTO.getOrder());
        HashMap<String, Object> map = new HashMap<>();
        try {
            Receipt createdReceipt = receiptRepository.save(receipt);
            map.put("Object", createdReceipt);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error3.code);
            error.setErrorMessage(Errors.error3.message);
            map.put("Object", error);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getAllReceipts() {
        HashMap<String, Object> map = new HashMap<>();
        List<Receipt> allReceipts = receiptRepository.findAll();
        if (allReceipts.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error3.code);
            error.setErrorMessage(Errors.error3.message);
            map.put("Object", error);
        } else {
            map.put("Object", allReceipts);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getReceiptById(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Receipt> findReceipt = receiptRepository.findById(id);
        if (findReceipt.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error3.code);
            error.setErrorMessage(Errors.error3.message);
            map.put("Object", error);
        } else {
            map.put("Object", findReceipt.get());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> deleteReceipt(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Receipt> findReceipt = receiptRepository.findById(id);
        if (findReceipt.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error3.code);
            error.setErrorMessage(Errors.error3.message);
            map.put("Object", error);
        } else {
            receiptRepository.deleteById(id);
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success);
        }
        return map;
    }
}
