package br.com.irisflower.controller;

import br.com.irisflower.business.IrisClassifier;
import br.com.irisflower.model.ParamsModel;
import br.com.irisflower.view.MainWindow;

import java.awt.event.*;
import java.io.*;

public class MainController {

    private IrisClassifier _classifier;

    private ParamsModel _model;

    private MainWindow _view;

    private int _currentPage;

    public MainController(ParamsModel model, MainWindow view) {
        _classifier = new IrisClassifier((new File("src/resources/iris-random-tree.model")).getAbsolutePath());

        _model = model;

        _view = view;

        _currentPage = 0;

        initController();
    }

    public void Execute() {
        _view.setVisible(true);
    }

    private void initController() {
        _view.getActionButton().addActionListener(e -> actionPerformed());

        actionPerformed();
    }

    private void actionPerformed() {
        if (_currentPage == 0) {
            showPanel(MainWindow.INFO_PANEL);

            _currentPage++;

        } else if (_currentPage == 1) {
            showPanel(MainWindow.PARAMS_PANEL);

            _currentPage++;

        } else if (_currentPage == 2) {
            if (_view.validateParams()) {
                refreshParams(_view.getParams());

                String result = process();
                if (result != null) {
                    showPanel(MainWindow.RESULT_PANEL, new Object[] { result });

                    _currentPage--;
                }
            }
        }
    }

    private void showPanel(String panelName) {
        adjustActionButton(panelName);

        _view.showPanel(panelName);
    }

    private void showPanel(String panelName, Object[] extraParams) {
        adjustActionButton(panelName);

        _view.showPanel(panelName, extraParams);
    }

    private void adjustActionButton(String panelName) {
        if (MainWindow.INFO_PANEL.equalsIgnoreCase(panelName)) {
            _view.getActionButton().setText("Informar parâmetros");
            _view.getActionButton().setMnemonic(KeyEvent.VK_I);

        } else if (MainWindow.PARAMS_PANEL.equalsIgnoreCase(panelName)) {
            _view.getActionButton().setText("Detectar espécie");
            _view.getActionButton().setMnemonic(KeyEvent.VK_D);

        } else if (MainWindow.RESULT_PANEL.equalsIgnoreCase(panelName)) {
            _view.getActionButton().setText("Voltar & Informar parâmetros");
            _view.getActionButton().setMnemonic(KeyEvent.VK_I);
        }
    }

    private void refreshParams(Object[] params) {
        _model.setSepalLength(((Number)params[0]).doubleValue());
        _model.setSepalWidth(((Number)params[1]).doubleValue());

        _model.setPetalLength(((Number)params[2]).doubleValue());
        _model.setPetalWidth(((Number)params[3]).doubleValue());
    }

    private String process() {
        try {
            _classifier.initialize();

            return _classifier.predict(_model);
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

}
