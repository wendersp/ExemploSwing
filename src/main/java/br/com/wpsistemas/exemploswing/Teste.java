/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.exemploswing;

import br.com.wpsistemas.model.dao.UsuarioDao;
import br.com.wpsistemas.model.dao.entitades.Usuario;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author wender
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Usuario usuario = new Usuario();
//        usuario.setNome("Wender da Silva Paula");
//        usuario.setEmail("wendersp@gmail.com");
//        usuario.setUserName("wender");
//        usuario.setSenha("123456");
        
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            List<Usuario> lista = usuarioDao.pesquisar("Wend");
            for (Usuario usuario : lista) {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nome: " + usuario.getNome());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
}
