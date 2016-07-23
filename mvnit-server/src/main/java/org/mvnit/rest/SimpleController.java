package org.mvnit.rest;

/**
 * Created by zhongjian on 7/24/16.
 */

import java.io.IOException;

import org.mvnit.utils.CoreUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/simple")
public class SimpleController {
    @RequestMapping("/index")
    @ResponseBody
    public String index() {

        return CoreUtil.formatString("test");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable String id) throws IOException {
        return id;
    }
}
