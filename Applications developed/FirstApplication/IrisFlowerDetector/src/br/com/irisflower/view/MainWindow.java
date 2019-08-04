package br.com.irisflower.view;

import br.com.irisflower.model.ParamsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    public final static String INFO_PANEL = "InfoPanel";
    public final static String PARAMS_PANEL = "ParamsPanel";
    public final static String RESULT_PANEL = "ResultPanel";

    private JPanel _contentPanel;
    private InfoPanel _infoPanel;
    private ParamsPanel _paramsPanel;
    private ResultPanel _resultPanel;

    private CardLayout _contentCardLayout;

    private JButton _actionButton;
    private JButton _closeButton;

    public MainWindow() {
        initUI();
    }

    public JButton getActionButton() {
        return _actionButton;
    }

    public Object[] getParams() {
        return _paramsPanel.getFields();
    }

    public boolean validateParams() {
        boolean status = _paramsPanel.validateFields();

        if (!status)
            JOptionPane.showMessageDialog(this, "Todos os parâmetros devem ser informados.", "Erro", JOptionPane.ERROR_MESSAGE);

        return status;
    }

    public void showPanel(String panelName) {
        _contentCardLayout.show(_contentPanel, panelName);

        if (PARAMS_PANEL.equalsIgnoreCase(panelName))
            _paramsPanel.resetFields();

        else if (RESULT_PANEL.equalsIgnoreCase(panelName))
            _resultPanel.resetResult();
    }

    public void showPanel(String panelName, Object[] extraParams) {
        _contentCardLayout.show(_contentPanel, panelName);

        if (PARAMS_PANEL.equalsIgnoreCase(panelName))
            _paramsPanel.resetFields();

        else if (RESULT_PANEL.equalsIgnoreCase(panelName))
            _resultPanel.refreshResult((String)extraParams[0]);
    }

    private void initUI() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(500, 0));

        JLabel titleLabel = new JLabel("Detector de Espécide de Flor de Íris");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        Font titleFont = titleLabel.getFont();
        titleLabel.setFont(titleFont.deriveFont(titleFont.getStyle() | Font.BOLD));
        topPanel.add(titleLabel);

        ImageIcon titleIcon = new ImageIcon("src/resources/iris-flower.png");
        JLabel titleIconLabel = new JLabel(titleIcon);
        titleIconLabel.setMaximumSize(new Dimension(50, 50));
        titleIconLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(titleIconLabel, BorderLayout.EAST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        mainPanel.add(topPanel);

        _contentCardLayout = new CardLayout();

        _contentPanel = new JPanel(new BorderLayout());
        _contentPanel.setLayout(_contentCardLayout);

        _infoPanel = new InfoPanel();
        _paramsPanel = new ParamsPanel();
        _resultPanel = new ResultPanel();

        _contentPanel.add(_infoPanel, INFO_PANEL);
        _contentPanel.add(_paramsPanel, PARAMS_PANEL);
        _contentPanel.add(_resultPanel, RESULT_PANEL);

        mainPanel.add(_contentPanel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        _actionButton = new JButton();

        _closeButton = new JButton();
        _closeButton.setText("Fechar");
        _closeButton.setMnemonic(KeyEvent.VK_F);
        _closeButton.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        bottomPanel.add(_actionButton);
        bottomPanel.add(_closeButton);
        mainPanel.add(bottomPanel);

        bottomPanel.setMaximumSize(new Dimension(500, 0));

        setTitle("Detector de Espécie de Flor de Íris");
        setSize(500, 375);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

}
