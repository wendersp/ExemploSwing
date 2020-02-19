
package br.com.wpsistemas.tableModel;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class UsuarioColumnModel extends DefaultTableColumnModel {
 
    public UsuarioColumnModel(FontMetrics fm) {
        addColumn(criaColuna(0, 80, fm, false, "Codigo"));
        addColumn(criaColuna(1, 200, fm, false, "Nome"));
        addColumn(criaColuna(2, 150, fm, false, "Email"));
        addColumn(criaColuna(3, 80, fm, false, "User Name"));
        
        
    }

    private TableColumn criaColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {
        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new UsuarioCellRenderer());
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);
        if (!resizable) {
            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizable);
        return col;
    }
}