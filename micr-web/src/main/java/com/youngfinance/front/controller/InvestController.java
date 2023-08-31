package com.youngfinance.front.controller;


import com.youngfinance.common.constants.RedisKey;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.front.view.RespResult;
import com.youngfinance.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 投资功能
 */

@Api(tags = "投资功能")
@RestController
@RequestMapping("/v1")
public class InvestController extends BaseController{

    // 投资排行榜
    @ApiOperation(value = "投资排行榜", notes = "取投资金额前三的手机号与投资金额信息")
    @GetMapping("/invest/rank")
    public RespResult showInvestRank() {
        // 从redis查询数据
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate.boundZSetOps(RedisKey.KEY_INVEST_RANK).reverseRangeWithScores(0, 2);

        List<RankView> rankList = new ArrayList<>();
        // 遍历set集合
        sets.forEach(tuple -> {
            // tuple.getValue(); // 手机号
            // tuple.getScore(); // 投资金额
            rankList.add(new RankView(CommonUtil.phoneShield(tuple.getValue()), tuple.getScore()));
        });

        RespResult result = RespResult.ok();
        result.setList(rankList);

        return result;
    }
}
