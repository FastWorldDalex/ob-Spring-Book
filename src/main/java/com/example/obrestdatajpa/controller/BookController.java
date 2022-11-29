package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.models.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Buscar todos los libros
    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    //Buscar por ID
    @GetMapping("/api/Book/{id}")
    @ApiOperation("Buscar un libro por clave primaria ID Long")
    public ResponseEntity<Book> FindOneById( @ApiParam("Clave primaria tipo Long") @PathVariable Long id) {

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent())
            return ResponseEntity.ok(optionalBook.get()); //devuelve un ok(200) con el libro
        else return ResponseEntity.notFound().build(); //devuelve un not foun (404) sin nada


    }
    //Insertar

    @PostMapping("/api/book")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        if(book.getId() != null){
            log.warn("trying to create a book with id"); // mensaje que muestra en consola en formato warning
            return ResponseEntity.badRequest().build();
        }else{
            log.info("Create correct");
            Book result = bookRepository.save(book);
            return ResponseEntity.ok(result);
        }

    }

    //Actualizar
    @PutMapping("/api/book")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if (book.getId() ==null){
            log.warn("Trying to update  a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        log.info("Update correct");
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //Eliminar
    @ApiIgnore
    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable Long id){

        if (!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        log.info("Delete correct");
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("api/books")
    public ResponseEntity<Book> deleteall(){
        if (bookRepository.count()<=0){
            log.warn("Trying to delete all book");
            return ResponseEntity.notFound().build();
        }

        log.info("Delete correct");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
