/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.tableModel;

import br.com.wpsistemas.model.dao.entitades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wender
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> listaUsuario;
    private String[] colunas = new String[]{
        "Id", "Nome", "Email", "User Name"};

    /**
     * Creates a new instance of DevmediaTableModel
     *
     * @param listaUsuario
     */
    public UsuarioTableModel(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public UsuarioTableModel() {
        this.listaUsuario = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaUsuario.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public void setValueAt(Usuario aValue, int rowIndex) {
        Usuario usuario = listaUsuario.get(rowIndex);

        usuario.setId(aValue.getId());
        usuario.setNome(aValue.getNome());
        usuario.setEmail(aValue.getEmail());
        usuario.setUserName(aValue.getUserName());

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Usuario usuario = listaUsuario.get(rowIndex);

        switch (columnIndex) {
            case 0:
                usuario.setId(Integer.parseInt(aValue.toString()));
            case 1:
                usuario.setNome(aValue.toString());
            case 2:
                usuario.setEmail(aValue.toString());
            case 3:
                usuario.setUserName(aValue.toString());

            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuarioSelecionado = listaUsuario.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
            case 0:
                if (usuarioSelecionado.getId() != null) {
                    valueObject = String.valueOf(usuarioSelecionado.getId());
                } else {
                    valueObject = "";
                }
                break;
            case 1:
                valueObject = usuarioSelecionado.getNome();
                break;
            case 2:
                valueObject = usuarioSelecionado.getEmail();
                break;
            case 3:
                valueObject = usuarioSelecionado.getUserName();
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean Usuario.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     *
     * @param indiceLinha
     * @return
     */
    public Usuario getUsuario(int indiceLinha) {
        return listaUsuario.get(indiceLinha);
    }

    public void addUsuario(Usuario p) {
        listaUsuario.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeUsuario(int indiceLinha) {
        listaUsuario.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeUsuario(List<Usuario> novosUsuarioes) {
        limpar();
        int tamanhoAntigo = getRowCount();
        listaUsuario.addAll(novosUsuarioes);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        listaUsuario.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return listaUsuario.isEmpty();
    }

}
