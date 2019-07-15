package com.paliy.t6.repos;

import com.paliy.t6.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    @Override
    List<Book> findAll();


}
