package com.itstimx.myFirstWebBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
* Здесь каждая функция обрабатывает URL-адрес
*/

@Controller
public class MainController {

    @GetMapping("/")// вставляем URL-адрес и при переходе на страницу вызывается эта функция и HTML шаблон с именем, указаным в "". "/" - обначает главную страницу
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")// вставляем URL-адрес и при переходе на страницу вызывается эта функция и HTML шаблон с именем, указаным в "". "/" - обначает главную страницу
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "about";
    }

}