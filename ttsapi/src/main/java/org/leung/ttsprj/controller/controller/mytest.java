package org.leung.ttsprj.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class mytest {
    @RequestMapping(value="/helo3")
    public String getResponse() {
        return "成功";
    }

    /*
    @RequestMapping(value = "/resp", method = RequestMethod.GET)
    public resp getResponse(@RequestParam(value = "yid") int id,
                            @RequestParam(value = "yname", defaultValue = "lyt") String name, Model model) {

        return new resp(id, name);
    }

    @RequestMapping(value = "/hellojava")
    public ModelAndView showHelloName(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView();
        model.addObject("name", name);
        model.setViewName("hellojava");
        System.out.println(name);
        return model;
    }


    @RequestMapping(value = "/rabbit1")
    public String RabbitTest() {
        return "rabbit";
    }
     */

}

