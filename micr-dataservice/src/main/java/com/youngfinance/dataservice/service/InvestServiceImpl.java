package com.youngfinance.dataservice.service;

import com.youngfinance.api.pojo.BidInfoProduct;
import com.youngfinance.api.service.InvestService;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.dataservice.mapper.BidInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = InvestService.class, version = "1.0")
public class InvestServiceImpl implements InvestService {

    @Resource
    private BidInfoMapper bidInfoMapper;

    @Override
    public List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize) {
        List<BidInfoProduct> bidList = new ArrayList<>();
        if(productId != null && productId > 0) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            bidList = bidInfoMapper.selectByProductId(productId, offset, pageSize);
        }

        return bidList;
    }
}
