package ru.maltsev.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @RequestMapping(value = "/greeting")
    public String helloController(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
                                  Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
