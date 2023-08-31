package com.youngfinance.dataservice.service;

import com.youngfinance.api.model.ProductInfo;
import com.youngfinance.api.pojo.MultiProduct;
import com.youngfinance.api.service.ProductInfoSercvice;
import com.youngfinance.common.constants.YLBConstant;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.dataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = ProductInfoSercvice.class, version = "1.0")
public class ProductServiceImpl implements ProductInfoSercvice {

    @Resource
    private ProductInfoMapper productInfoMapper;

    // 分页查询产品
    @Override
    public List<ProductInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize) {
        List<ProductInfo> products = new ArrayList<>();
        if(pType == 0 || pType == 1 || pType == 2) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            products = productInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }
        return products;
    }

    @Override
    public Integer queryRecordNumsByType(Integer pType) {
        Integer counts = 0;
        if(pType == 0 || pType == 1 || pType == 2) {
            counts = productInfoMapper.selectCountByType(pType);
        }
        return counts;
    }

    @Override
    public MultiProduct queryIndexPageProducts() {

        MultiProduct result = new MultiProduct();

        List<ProductInfo> xinlist = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_XINSHOUBAO, 0, 1);
        List<ProductInfo> sanlist = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_SANBIAO, 0, 3);
        List<ProductInfo> youlist = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_YOUXUAN, 0, 3);

        result.setXinShouBao(xinlist);
        result.setSanBiao(sanlist);
        result.setYouXuan(youlist);

        return result;
    }

    @Override
    public ProductInfo queryById(Integer id) {
        ProductInfo productInfo = null;
        if(id != null && id > 0) {
            productInfo = productInfoMapper.selectByPrimaryKey(id);
        }
        return productInfo;
    }


}
