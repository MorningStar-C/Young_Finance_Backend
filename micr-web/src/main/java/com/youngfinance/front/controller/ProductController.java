package com.youngfinance.front.controller;

import com.youngfinance.api.model.ProductInfo;
import com.youngfinance.api.pojo.BidInfoProduct;
import com.youngfinance.api.pojo.MultiProduct;
import com.youngfinance.common.enums.RCode;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.front.view.PageInfo;
import com.youngfinance.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "理财产品功能")
@RestController
@RequestMapping("/v1")
public class ProductController extends BaseController{

    @ApiOperation(value = "首页三类产品列表", notes = "新手宝，优选宝，散标")
    @GetMapping("/product/index")
    public RespResult queryProductIndex() {
        RespResult result = RespResult.ok();

        MultiProduct products = productInfoSercvice.queryIndexPageProducts();

        result.setData(products);

        return result;
    }

    // 按产品类型分页查询
    @ApiOperation(value = "按产品类型进行分页查询", notes = "新手宝，优选宝，散标")
    @GetMapping("/product/list")
    public RespResult queryProductByType(@RequestParam("ptype") Integer pType,
                                         @RequestParam(value = "pageNo", required = false, defaultValue = "1")Integer pageNo,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "9")Integer pageSize) {

        RespResult result = RespResult.fail();
        if(pType != null && (pType == 0 || pType == 1 || pType == 2)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            Integer recordNums = productInfoSercvice.queryRecordNumsByType(pType);
            if(recordNums > 0) {
                List<ProductInfo> productInfos = productInfoSercvice.queryByTypeLimit(pType, pageNo, pageSize);
                result = RespResult.ok();
                result.setList(productInfos);

                PageInfo page = new PageInfo(pageNo, pageSize, recordNums);
                result.setPage(page);
            }
        }else {
            result.setRCode(RCode.REQUEST_PRODUCT_TYPE_ERROR);
        }
        return result;
    }

    // 查询某个产品的详情与投资记录
    @ApiOperation(value = "产品详情", notes = "查询某个产品的详细信息和5条投资记录")
    @GetMapping("/product/info")
    public RespResult queryProductDetail(@RequestParam("productId") Integer id) {
        RespResult result = RespResult.fail();
        if(id != null && id > 0) {
            ProductInfo productInfo = productInfoSercvice.queryById(id);
            if(productInfo != null) {
                List<BidInfoProduct> bidInfoProducts = investService.queryBidListByProductId(id, 1, 5);
                result = RespResult.ok();
                result.setData(productInfo);
                result.setList(bidInfoProducts);
            } else {
                result.setRCode(RCode.PRODUCT_OFFLINE_ERROR);
            }
        }
        return result;
    }
}
