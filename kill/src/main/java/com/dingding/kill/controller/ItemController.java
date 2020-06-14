package com.dingding.kill.controller;

import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.entity.ItemKill;
import com.dingding.kill.service.base.ItemKillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author liudingding
 * @ClassName ItemKillController
 * @description
 * @date 2020/3/31 16:31
 * Version 1.0
 */
@Controller
@Slf4j
public class ItemController {

    @Autowired
    private ItemKillService itemKillService;

    /**
     * 待商品秒杀列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String itemKillList(Model model){
        try {
            List<ItemKill> itemKillList = itemKillService.selectAll();
            model.addAttribute("list", itemKillList);
        } catch (Exception e) {
            log.error("获取待秒杀商品异常：{}", e.getMessage());
            return "redirect:/base/error";
        }
        return "list";
    }

    /**
     * 获取待秒杀商品详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer id, Model model){
        if (id == null || id <= 0){
            return "redirect:/base/error";
        }
        try {
            ItemKill detail = itemKillService.selectById(id);
            model.addAttribute("detail", detail);
        } catch (Exception e) {
            log.error("获取详情页发生异常", e.getMessage());
            return "redirect:/base/error";
        }
        return "info";
    }

    @RequestMapping(value = "/success/detail/{orderNo}", method = RequestMethod.GET)
    public String successDetail(@PathVariable String orderNo, Model model) {
        log.info("订单编号：{}", orderNo);
        if (StringUtils.isBlank(orderNo)){
            return "error";
        }
        try {
            KillSuccessUserInfo killSuccessUserInfo = itemKillService.selectByCode(orderNo);
            log.info("订单信息：{}", killSuccessUserInfo);
            if (killSuccessUserInfo != null) {
                model.addAttribute("detail", killSuccessUserInfo);
            }
        } catch (Exception e) {
            log.error("获取详情页发生异常", e.getMessage());
            return "redirect:/base/error";
        }
        return "orderInfo";
    }


}
