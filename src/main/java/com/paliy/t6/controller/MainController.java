package com.paliy.t6.controller;

import com.paliy.t6.model.Book;
import com.paliy.t6.repos.BookRepo;
import com.paliy.t6.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private IBookService IbookService;

    @Autowired
    private BookRepo bookRepo;

    /*@RequestMapping("/")*/
    @GetMapping("/")
    public ModelAndView root(Map<String, Object> model){
        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);

        return new ModelAndView("main",model);
    }
    @GetMapping("/main")
    public ModelAndView main(@PathVariable("id") String id, Map<String, Object> model){
        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);

        return new ModelAndView("main",model);
    }
    @RequestMapping("/hw")
    public ModelAndView showHello(Map<String, Object> model){

        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);

        return new ModelAndView("hw",model);
    }
    @RequestMapping("/main")
    public ModelAndView bookTableMain(@PathVariable("id") String id,Map<String, Object> model){

        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);

        return new ModelAndView("main",model);
    }
/*  Message message = new Message(text, tag, user);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";*/
    @PostMapping("/main")
    public ModelAndView add(@RequestParam String title,
               @RequestParam String price,
               @RequestParam String author,
               @RequestParam String description, Map<String, Object> model) {

        if(title.isEmpty() || title.equals("")){
            model.put("notif","Title is empty");
            Iterable<Book> books = IbookService.findAll();
            model.put("books", books);
            return new ModelAndView("main",model);
        }
        Book book = new Book( title,price,author,description);

        bookRepo.save(book);

        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);

        return new ModelAndView("main",model);
    }

    Optional<Book> book;

    @PostMapping(value = "/remove")
    public String remove(@RequestParam("remove") int id, Map<String, Object> model) {
        book = bookRepo.findById(id);

        bookRepo.deleteById(id);
        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);
        return "/main";
    }

    @GetMapping(value = "/undelete")
    public String undel(Map<String, Object> model, Model modelAtr){
        if(book != null && book.isPresent()){
            Book b = book.get();
            bookRepo.save(new Book(b.getTitle(),b.getPrice(),b.getAuthor(),b.getDescription()));
            modelAtr.addAttribute("notif","Book Restored!");
            book = Optional.empty();
        } else {
            modelAtr.addAttribute("notif","Nothing to Restore!");
        }

        Iterable<Book> books = bookRepo.findAll();
        model.put("books", books);
        return "/main";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam("edit")int id, Map<String, Object> model){
        Book book = bookRepo.getOne(id);
        model.put("book", book);
        return "/edit";
    }

    @PostMapping(value = "/apply")
    public String applyEdit(@RequestParam String title,
                            @RequestParam String price,
                            @RequestParam String author,
                            @RequestParam String description,
                            @RequestParam("edit")int id, Map<String, Object> model){
        Book book = bookRepo.getOne(id);
        book.setTitle(title);
        book.setPrice(price);
        book.setAuthor(author);
        book.setDescription(description);

        bookRepo.save(book);
       // model.put("book", book);

        Iterable<Book> books = IbookService.findAll();
        model.put("books", books);
        return "/main";
    }


}
