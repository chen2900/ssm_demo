package com.bjpowernode.controller;

import com.bjpowernode.beans.Value;
import com.bjpowernode.service.ValueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("value")
public class ValueController {

    @Autowired
    private ValueService valueService;

    /**
     * 显示所有的字典值，由于数量过多，进行分页的处理
     * @param pn 页码，默认为 1
     * @return
     */
    @RequestMapping("index.html")
    public String queryValues(@RequestParam(defaultValue = "1")Integer pn, Model model){
        //参数一:页码,参数二:每页显示多少数量。这一条语句之后的第一条查询语句会进行分页limit查询
        PageHelper.startPage(pn,5);
        List<Value> values = valueService.queryValues();
        //将查询出来的数据交给PageInfo对象封装处理（会将分页所需的所有参数进行封装:页数、总数、首页、下一页...）
        PageInfo<Value> pageInfo = new PageInfo<>(values);
        model.addAttribute("values",values);
        model.addAttribute("pageInfo",pageInfo);
        return "settings/dictionary/value/index";
    }

    /**
     * 单击保存按钮，新增字典值
     * @param value 用Value来接收前端传递过来的数据
     */
    @RequestMapping("save.do")
    public String saveValue(Value value){
        valueService.insertValue(value);
        return "redirect:/value/index.html";
    }

    /**
     * 修改前的准备：数据回显
     */
    @RequestMapping("edit.html")
    public String editBefore(String id,Model model){
        Value value = valueService.queryById(id);
        model.addAttribute("value",value);
        return "settings/dictionary/value/edit";
    }

    /**
     * 真正的修改数据库中的数据
     * @param value 前端传递过来要修改的数据
     */
    @RequestMapping("edit.do")
    public String editValue(Value value){
        valueService.updateValue(value);
        return "redirect:/value/index.html";
    }

    /**
     * 删除字典值
     */
    @RequestMapping("del.do")
    public String delValue(String[] id){
        valueService.deleteValue(id);
        return "redirect:/value/index.html";
    }

}
