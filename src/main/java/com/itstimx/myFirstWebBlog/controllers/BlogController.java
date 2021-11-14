package com.itstimx.myFirstWebBlog.controllers;

import com.itstimx.myFirstWebBlog.models.Post;
import com.itstimx.myFirstWebBlog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class  BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")//в get обрабатывается переход по сайту
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll(); //Вернёт все записи в табличке Post
        model.addAttribute("posts", posts);
        return "blog_main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog_add";
    }

    @PostMapping("/blog/add")//В Post обрабатывается форма кнопки, которая добавлена в blog_add.html в форме method="post"
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) { // получаем данные из blog_add.html <input name для title,  anons, full_text
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);//сохраняем объект класса post в БД
        return "redirect:/blog";//перенаправляем пользователя на страницу /blog
    }

    @GetMapping("/blog/{id}")//динамическое параметр {id}
    public String blogDetails(@PathVariable(value = "id") long id, Model model) { //@PathVariable указываем какой динамический параметр обрабатываем
        if(!postRepository.existsById(id)) { // true - если запись по определенному id была найдена, false - если запись по определенному id была ненайдена
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id); //вытаскиваем из БД одну запись
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post", res);
        return "blog_details";
    }

    @GetMapping("/blog/{id}/edit")//динамическое параметр {id}
    public String blogEdit(@PathVariable(value = "id") long id, Model model) { //@PathVariable указываем какой динамический параметр обрабатываем
        if(!postRepository.existsById(id)) { // true - если запись по определенному id была найдена, false - если запись по определенному id была ненайдена
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id); //вытаскиваем из БД одну запись
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post", res);
        return "blog_edit";
    }

    @PostMapping("/blog/{id}/edit")//В Post обрабатывается форма кнопки, которая добавлена в blog_add.html в форме method="post"
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) { // получаем данные из blog_add.html <input name для title,  anons, full_text
        Post post = postRepository.findById(id).orElseThrow();//orElseThrow выбрасывает исключение
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";//перенаправляем пользователя на страницу /blog
    }

    @PostMapping("/blog/{id}/remove")//В Post обрабатывается форма кнопки, которая добавлена в blog_add.html в форме method="post"
    public String blogPostRemove(@PathVariable(value = "id") long id, Model model) { // получаем данные из blog_add.html <input name для title,  anons, full_text
        Post post = postRepository.findById(id).orElseThrow();//orElseThrow выбрасывает исключение
        postRepository.delete(post);//Удаляем статью из БД
        return "redirect:/blog";//перенаправляем пользователя на страницу /blog
    }
}