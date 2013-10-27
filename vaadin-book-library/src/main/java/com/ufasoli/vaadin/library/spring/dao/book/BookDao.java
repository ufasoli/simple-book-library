package com.ufasoli.vaadin.library.spring.dao.book;

import com.ufasoli.vaadin.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 15:51
 */
@Repository
public interface BookDao extends CrudRepository<Book,String>{

    public List<Book> findByUserBorrowed(String userBorrowed);
}
