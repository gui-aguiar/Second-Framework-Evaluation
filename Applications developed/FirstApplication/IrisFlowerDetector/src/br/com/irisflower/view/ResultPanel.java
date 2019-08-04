package br.com.irisflower.view;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JTextPane _textPane;

    public ResultPanel() {
        initUI();
    }

    public void refreshResult(String specie) {
        _textPane.setText(getTextPaneContent(specie));
    }

    public void resetResult() {
        _textPane.setText(getTextPaneContent());
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        _textPane = new JTextPane();
        _textPane.setContentType("text/html");
        _textPane.setText(getTextPaneContent());
        _textPane.setEditable(false);
        add(_textPane);
    }

    private String getTextPaneContent() {
        return getTextPaneContent(null);
    }

    private String getTextPaneContent(String specie) {
        String text =
                "<font face='Courier'>" +
                        "<p><b>Resultado do processo de detecção da espécie de flor de íris foi:</b></p>" +
                        "<p>&nbsp;<strong>%s</strong></p>" +
                        "<p>O processo de detecção faz uso de técnicas de aprendizado de máquina para descobrir qual a espécie de flor de íris que mais " +
                        "se aproximada das características informadas como parâmetros.</p>" +
                        "</font>";

        if (specie == null)
            return String.format(text, "desconhecida");

        return String.format(text, specie);
    }

}