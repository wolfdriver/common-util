package wolfdriver.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sku")
public class SkuController {


    /**
     * 新增
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        System.out.println("add");
        return "add";
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update() {
        System.out.println("update");
        return "update";
    }


    /**
     * 获取
     */
    @RequestMapping("/get")
    @ResponseBody
    public String get() {
        System.out.println("get");
        return "get";
    }

}
