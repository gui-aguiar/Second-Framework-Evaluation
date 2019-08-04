package br.com.irisflower;

import br.com.irisflower.controller.MainController;
import br.com.irisflower.model.ParamsModel;
import br.com.irisflower.view.MainWindow;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            ParamsModel params = new ParamsModel();

            MainWindow view = new MainWindow();

            MainController controller = new MainController(params, view);
            controller.Execute();
        });

    }

}
