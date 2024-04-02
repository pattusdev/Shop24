package com.Bk24Shop.Shop.Service.ServiceImpl;

import com.Bk24Shop.Shop.Repository.ReceiptRepository;
import com.Bk24Shop.Shop.Service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

}
