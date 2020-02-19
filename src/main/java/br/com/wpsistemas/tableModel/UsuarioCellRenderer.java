package br.com.wpsistemas.tableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class UsuarioCellRenderer extends DefaultTableCellRenderer {

    public UsuarioCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(javax.swing.JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        Color corFundoZebrado = new Color(240, 240, 240);
        Color corFundoNormal = new Color(255, 255, 230);

        label.setFont(new java.awt.Font("Tahoma", 0, 12));

        if ((row % 2) == 0) {
            label.setBackground(corFundoNormal);
        } else {
            label.setBackground(corFundoZebrado);
        }

        if (isSelected) {
            label.setBackground(new Color(65, 105, 225));
        }

        switch (column) {
            case 0:
                label.setHorizontalAlignment(CENTER);
                break;
            case 1:
                label.setHorizontalAlignment(LEFT);
                break;
            case 2:
                label.setHorizontalAlignment(LEFT);
                break;
            case 3:
                label.setHorizontalAlignment(LEFT);
                break;
            /*else if (column == 4) {
        label.setHorizontalAlignment(LEFT);
        } /*else if (column == 5) {
        label.setHorizontalAlignment(RIGHT);
        } else if (column == 6) {
        label.setHorizontalAlignment(CENTER);
        }*/
//            case 3:
//                label.setHorizontalAlignment(LEFT);
//                break;
            default:
                break;
        }

        return label;
    }
}
