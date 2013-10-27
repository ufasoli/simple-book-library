package com.ufasoli.vaadin.library.web.ui.pages;

import com.ufasoli.vaadin.library.model.Book;
import com.ufasoli.vaadin.library.spring.dao.book.BookDao;
import com.ufasoli.vaadin.library.spring.util.SpringContextHelper;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 10:04
 */

public class BorrowedBooksPage extends BasePage {


    private BookDao bookDao;
    private Table availableBooks;
    private TextField filter = new TextField("Filter expression");
    private Button filterButton;
    private Button clearFilterButton;
    private GridLayout topLayout;


    BeanContainer<String, Book> booksContainer;

    public BorrowedBooksPage() {

        bookDao = SpringContextHelper.helper().getBean(BookDao.class);
        addComponent(new Label("<h2>List of available booksContainer in the library</h2>", ContentMode.HTML));
        initTopLayout();
        initTable();
        initMainLayout();

    }

    private void initTable() {

        if (availableBooks == null) {
            availableBooks = new Table();
            availableBooks.setPageLength(0);

        }
        String username = (String) UI.getCurrent().getSession().getAttribute("username");

        if (username != null) {
            loadDataIntoContainer(username);

            availableBooks.setCaption(String.format("Books borrowed by : %s", username));


            availableBooks.setContainerDataSource(booksContainer);

            availableBooks.setColumnHeader("bookId", "ID");
            availableBooks.setColumnHeader("bookCategory", "Category");
            availableBooks.setColumnHeader("bookTitle", "Title");
            availableBooks.setColumnHeader("borrowedOn", "Borrowed on");
            availableBooks.setColumnHeader("userBorrowed", "Borrowed by");
            availableBooks.setVisibleColumns("bookId", "bookCategory", "bookTitle", "borrowedOn", "userBorrowed");

            availableBooks.addGeneratedColumn("", new Table.ColumnGenerator() {
                @Override
                public Object generateCell(final Table source, final Object itemId, Object columnId) {

                    final BeanItem<Book> book = (BeanItem<Book>) source.getItem(itemId);
                    Button actionButton = new Button();
                    actionButton.setCaption("Return");
                    actionButton.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {

                            book.getBean().setBorrowedOn(null);
                            book.getBean().setUserBorrowed(null);
                            bookDao.save(book.getBean());
                            //update table
                            loadDataIntoContainer((String) UI.getCurrent().getSession().getAttribute("username"));

                        }
                    });

                    return actionButton;
                }
            });


            addComponent(availableBooks);

        }

    }

    private void initFilterButton() {
        filterButton = new Button("Apply Filter");
        filterButton.setImmediate(true);

        filterButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                booksContainer.removeAllContainerFilters();
                booksContainer.addContainerFilter(new SimpleStringFilter("bookTitle", filter.getValue(), true, false));

            }
        });
    }


    private void initTopLayout() {

        topLayout = new GridLayout(3, 1);
        topLayout.setMargin(true);
        topLayout.setSpacing(true);

        initFilterButton();
        initClearFilterButton();

        addComponent(clearFilterButton);


        topLayout.addComponent(filter, 0, 0);
        topLayout.addComponent(filterButton, 1, 0);
        topLayout.addComponent(clearFilterButton, 2, 0);


        topLayout.setComponentAlignment(clearFilterButton, Alignment.MIDDLE_CENTER);
        topLayout.setComponentAlignment(filterButton, Alignment.MIDDLE_CENTER);

        Panel panel = new Panel("Filter :");
        panel.setContent(topLayout);


    }


    private void initMainLayout() {
        setMargin(true);
        setSpacing(true);
        addComponent(topLayout);
        addComponent(availableBooks);

    }

    private void initClearFilterButton() {

        clearFilterButton = new Button("Clear filter");
        clearFilterButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                booksContainer.removeAllContainerFilters();

            }
        });
        clearFilterButton.setImmediate(true);
    }

    private void loadDataIntoContainer(String username) {

        if (booksContainer == null) {
            booksContainer = new BeanContainer<>(Book.class);
            booksContainer.setBeanIdProperty("bookId");
        }
        booksContainer.removeAllItems();
        booksContainer.addAll(bookDao.findByUserBorrowed(username));

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        super.enter(event);
        loadDataIntoContainer((String) UI.getCurrent().getSession().getAttribute("username"));
    }

}
