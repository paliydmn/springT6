package com.paliy.t6.service;

import com.paliy.t6.model.Book;
import com.paliy.t6.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepo bRepo;

   // @Override
    public List<Book> findAll(){
        return (List<Book>) bRepo.findAll();
    }
}
