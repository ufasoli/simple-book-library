package com.ufasoli.vaadin.library.web.ui.pages;

import com.ufasoli.vaadin.library.web.ui.windows.LoginWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 17:23
 */
@Theme("books")
public abstract class BasePage extends VerticalLayout implements View {

    protected Button changeUserButton;
    protected Label loggedAs;
    protected Navigator navigator;
    protected MenuBar navigationBar;
    protected MenuBar userInfobar;
    protected VerticalLayout menuLayout;

    public BasePage() {

        initMenuBars();

        addComponent(menuLayout);


    }


    private void initMenuBars() {

        menuLayout = new VerticalLayout();

        navigationBar = new MenuBar();
        navigationBar.setSizeFull();


        navigationBar.addItem("Home", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                navigator.navigateTo("");
            }
        });

        navigationBar.addItem("My Books", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                navigator.navigateTo("myBooks");
            }
        });


        String username = (String) VaadinSession.getCurrent().getAttribute("username");


        userInfobar = new MenuBar();
        userInfobar.setSizeFull();
        userInfobar.setHtmlContentAllowed(true);

        String loggedAs = String.format("Logged as : %s",
                username == null ? "<b>unknown</b>" : "<b>" + username + "</b>");


        MenuBar.MenuItem changeUser = userInfobar.addItem("Change Current User", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UI.getCurrent().addWindow(new LoginWindow());
            }
        });
        changeUser.setStyleName("fright");

        changeUser.addSeparator();

        MenuBar.MenuItem userLogged = userInfobar.addItem(loggedAs, null);
        userLogged.setStyleName("fright");
        userLogged.setEnabled(false);

        menuLayout.addComponent(userInfobar);
        menuLayout.addComponent(navigationBar);


    }

    private void initLoggedAs() {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");

        if (loggedAs == null) {
            loggedAs = new Label(username == null ? "<b>unknown</b>" : "<b>" + username + "</b>", ContentMode.HTML);
        } else {

            loggedAs.setValue(username == null ? "<b>unknown</b>" : "<b>" + username + "</b>");
        }

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        navigator = event.getNavigator();
        initLoggedAs();

    }
}
