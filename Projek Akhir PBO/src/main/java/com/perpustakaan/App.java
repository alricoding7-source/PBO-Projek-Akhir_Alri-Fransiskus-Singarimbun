package com.perpustakaan;

import com.perpustakaan.view.LoginForm;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm form = new LoginForm();
            form.setVisible(true);
        });
    }
}
