package com.ite.pickon.domain.stock.service;

import com.ite.pickon.domain.stock.dto.StockReq;
import com.ite.pickon.domain.stock.mapper.StockMapper;
import com.ite.pickon.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ite.pickon.exception.ErrorCode.EXISTS_STOCK_AT_STORE;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockMapper stockMapper;

    @Override
    @Transactional
    public void updateStock(int storeId, String productId, int quantityChange) {
        stockMapper.updateStockQuantity(storeId, productId, quantityChange);
    }

    @Override
    @Transactional
    public void addStock(StockReq stockReq) {
        // 해당 지점에 기존에 재고가 등록되었는지 확인
        int count = stockMapper.checkStockExists(stockReq.getStoreId(), stockReq.getProductId());
        if (count>0) {
            throw new CustomException(EXISTS_STOCK_AT_STORE);
        }
        // 재고 생성
        stockMapper.insertStock(stockReq);
    }
}
