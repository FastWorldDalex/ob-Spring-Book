package com.example.obrestdatajpa.repository;

import com.example.obrestdatajpa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
