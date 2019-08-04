package br.com.irisflower.view;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    public InfoPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        String text =
                "<font face='Courier'>" +
                "<p><b>Esta aplicação tem por objetivo realizar a detecção da espécie de flor de íris baseada nas seguintes características:</b></p>" +
                "<ul>" +
                "<li>&nbsp;Tamanho e largura da sépala</li>" +
                "<li>&nbsp;Tamanho e largura da pétala</li>" +
                "</ul>" +
                "<p>O processo de detecção faz uso de técnicas de aprendizado de máquina para descobrir qual a espécie de flor de íris que mais " +
                "se aproximada das características informadas como parâmetros.</p>" +
                "</font>";

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(text);
        textPane.setEditable(false);
        add(textPane);
    }

}
