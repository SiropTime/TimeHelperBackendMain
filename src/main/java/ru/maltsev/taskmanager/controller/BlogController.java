package ru.maltsev.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.maltsev.taskmanager.utility.PostRepository;

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
