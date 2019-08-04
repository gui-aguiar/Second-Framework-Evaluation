package br.com.irisflower.view;

import javax.swing.*;
import javax.swing.text.*;

import java.math.*;
import java.text.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;

public class ParamsPanel extends JPanel {

    private JFormattedTextField _sepalLengthField;
    private JFormattedTextField _sepalWidthField;

    private JFormattedTextField _petalLengthField;
    private JFormattedTextField _petalWidthField;

    public ParamsPanel() {
        initUI();
    }

    public Object[] getFields() {
        return new Object[] {
                _sepalLengthField.getValue(),
                _sepalWidthField.getValue(),
                _petalLengthField.getValue(),
                _petalWidthField.getValue()
        };
    }

    public boolean validateFields() {
        return (
                _sepalLengthField.getValue() != null &&
                _sepalWidthField.getValue() != null &&
                _petalLengthField.getValue() != null &&
                _petalWidthField.getValue() != null
        );
    }

    public void resetFields() {
        _sepalLengthField.setText("");
        _sepalWidthField.setText("");

        _petalLengthField.setText("");
        _petalWidthField.setText("");
    }

    private void initUI() {
        GroupLayout layout = new GroupLayout(this);

        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(1);
        numberFormat.setMaximumIntegerDigits(1);
        numberFormat.setMinimumFractionDigits(1);
        numberFormat.setMaximumFractionDigits(1);
        numberFormat.setRoundingMode(RoundingMode.FLOOR);

        JLabel sepalLengthLabel = new JLabel("Tamanho da sépala:");
        JLabel sepalWidthLabel = new JLabel("Largura da sépala:");

        _sepalLengthField = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(numberFormat)));
        _sepalLengthField.setColumns(10);
        sepalLengthLabel.setLabelFor(_sepalLengthField);
        _sepalWidthField = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(numberFormat)));
        _sepalWidthField.setColumns(10);
        sepalWidthLabel.setLabelFor(_sepalWidthField);

        JLabel petalLengthLabel = new JLabel("Tamanho da pétala:");
        JLabel petalWidthLabel = new JLabel("Largura da pétala:");

        _petalLengthField = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(numberFormat)));
        _petalLengthField.setColumns(10);
        petalLengthLabel.setLabelFor(_petalLengthField);
        _petalWidthField = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(numberFormat)));
        _petalWidthField.setColumns(10);
        petalWidthLabel.setLabelFor(_petalWidthField);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(sepalLengthLabel)
                        .addComponent(sepalWidthLabel)
                        .addComponent(petalLengthLabel)
                        .addComponent(petalWidthLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(_sepalLengthField)
                        .addComponent(_sepalWidthField)
                        .addComponent(_petalLengthField)
                        .addComponent(_petalWidthField))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(sepalLengthLabel)
                        .addComponent(_sepalLengthField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(sepalWidthLabel)
                        .addComponent(_sepalWidthField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(petalLengthLabel)
                        .addComponent(_petalLengthField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(petalWidthLabel)
                        .addComponent(_petalWidthField))
        );
    }

}
