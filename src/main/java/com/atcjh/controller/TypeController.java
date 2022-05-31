package com.atcjh.controller;

import com.atcjh.beans.Type;
import com.atcjh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 进行首页，默认调用此方法展示所有的字典类型
     *
     * @param model 前后端传递数据
     */
    @RequestMapping("index.html")
    public String queryTypes(Model model) {
        List<Type> types = typeService.queryTypes();
        model.addAttribute("types", types);
        return "settings/dictionary/type/index";
    }

    /**
     * 字典类型新增时，检查新增的字典类型是否存在，返回给前端结果
     * @param id 要新增的字典类型
     */
    @RequestMapping("check.do")
    @ResponseBody
    public Map checkTypeExist(String id) {
        return new HashMap() {{
            //字典类型存在的话，根据id会查询到数据，此条件判断就会返回false，反之为true
            put("repeat", typeService.queryTypeById(id) != null);
        }};
    }

    /**
     * 新增字典类型
     * @param type 对字典类型的内容进行封装(用Type对象来接收参数)
     */
    @RequestMapping("save.do")
    public String saveType(Type type) {
        typeService.insertType(type);
        return "redirect:/settings/dictionary/index.html";
    }

    /**
     * 单击编辑按钮，要进入到修改页面，同时将要修改的字典类型数据填充到对应地方
     */
    @RequestMapping("edit.html")
    public String editBefore(String id, Model model) {
        Type type = typeService.queryTypeById(id);
        model.addAttribute("type", type);
        return "settings/dictionary/type/edit";
    }

    /**
     * 单击更新按钮时，进行字典类型内容的修改
     */
    @RequestMapping("edit.do")
    public String editType(Type type) {
        typeService.updateType(type);
        return "redirect:/settings/dictionary/index.html";
    }

    /**
     * 删除之前进行检查，看字典类型是否被引用
     */
    @RequestMapping("checkDel.do")
    @ResponseBody
    public List<String> checkDel(String[] id) {
        return typeService.checkDel(id);
    }

    /**
     * 检查完毕，可以进行删除没有被引用的字典类型
     */
    @RequestMapping("del.do")
    public String delType(String[] id) {
        typeService.deleteTypes(id);
        return "redirect:/settings/dictionary/index.html";
    }


    /**
     * 关联删除数据，同时删除字典类型对应的字典值
     */
    @RequestMapping("delForce.do")
    public String delForceType(String[] id) {
        typeService.delForceType(id);
        return "redirect:/settings/dictionary/index.html";
    }

    /**
     * 获取所有的字典类型值填充给下拉列表
     */
    @RequestMapping("types.json")
    @ResponseBody
    public List<Type> queryTypes() {
        return typeService.queryTypes();
    }
}
