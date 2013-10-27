package com.ufasoli.vaadin.library.web.ui;

import com.ufasoli.vaadin.library.model.Book;
import com.ufasoli.vaadin.library.spring.util.SpringContextHelper;
import com.ufasoli.vaadin.library.web.ui.pages.BookListPage;
import com.ufasoli.vaadin.library.web.ui.pages.BorrowedBooksPage;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.servlet.annotation.WebServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 10:02
 */
@Theme("books")
public class BookLibraryUI extends UI {

     private String currentUser ="unknown";

    @WebServlet(urlPatterns = {"/VAADIN/*", "/book-library/*"})
    @VaadinServletConfiguration(ui = BookLibraryUI.class, productionMode = false)
    public static class BookLibraryServlet extends VaadinServlet {

    }


    private Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        System.out.println("Displaying BookLibraryUI");

        navigator = new Navigator(this, this);
        navigator.addView("", new BookListPage());
        navigator.addView("myBooks", new BorrowedBooksPage());


        MongoTemplate template = (MongoTemplate) SpringContextHelper.helper().getBean("mongoTemplate");

        template.collectionExists(Book.class);

    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
