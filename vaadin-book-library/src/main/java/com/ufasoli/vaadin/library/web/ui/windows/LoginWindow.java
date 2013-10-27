package com.ufasoli.vaadin.library.web.ui.windows;

import com.ufasoli.vaadin.library.spring.config.Application;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 16:32
 */
public class LoginWindow extends Window {

    private TextField username = new TextField("username");
    private Button validateButton;


    public LoginWindow() {

        setModal(true);
        setClosable(true);
        initButton();

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(username);
        layout.addComponent(validateButton);
        layout.setMargin(true);
        layout.setSpacing(true);

        username.focus();

        setCaption("Change username");
        setContent(layout);
    }


    private void initButton() {

        validateButton = new Button("Validate");
        validateButton.setImmediate(true);

        validateButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!username.getValue().isEmpty()) {
                    VaadinSession.getCurrent().setAttribute("username", username.getValue());
                    detach();
                    close();
                    UI.getCurrent().markAsDirtyRecursive();
                    UI.getCurrent().getPage().reload();


                }
            }
        });
        validateButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }


}
