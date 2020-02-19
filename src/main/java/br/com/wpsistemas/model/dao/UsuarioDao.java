/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.model.dao;

import br.com.wpsistemas.model.dao.entitades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wender
 */
public class UsuarioDao {

    private ConexaoBD conexaoBD;
    private Connection conn;
    private Usuario usuario;

    public UsuarioDao() {

    }

    private void iniciarConexaoBD() {
        if (conexaoBD == null) {
            conexaoBD = new ConexaoBD();
        }
        if (conn == null) {
            conn = this.conexaoBD.getConexao();
        }
        System.out.println(conexaoBD.statusConection());
    }

    public void salvar(Usuario usuario) throws Exception {
        this.usuario = usuario;
        if (this.usuario.getId() == 0) {
            insert();
        } else {
            update();
        }
    }

    private void insert() throws Exception {
        try {
            iniciarConexaoBD();
            String sqlInsert = "INSERT INTO usuario (nome, email, user_name, senha) VALUE (?,?,?,?)";
            PreparedStatement pstmt = this.conn.prepareStatement(sqlInsert);
            pstmt.setString(1, this.usuario.getNome());
            pstmt.setString(2, this.usuario.getEmail());
            pstmt.setString(3, this.usuario.getUserName());
            pstmt.setString(4, this.usuario.getSenha());
            pstmt.execute();
        } catch (SQLException ex) {
            throw new Exception("Error ao Incluir o Usuario: " + ex.getMessage());
        }

    }

    private void update() throws Exception {
        try {
            iniciarConexaoBD();
            String sqlUpdate = "UPDATE usuario SET nome = ?, email = ?, user_name = ?, senha = ? WHERE id = ?";
            PreparedStatement pstmt = this.conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, this.usuario.getNome());
            pstmt.setString(2, this.usuario.getEmail());
            pstmt.setString(3, this.usuario.getUserName());
            pstmt.setString(4, this.usuario.getSenha());
            pstmt.setInt(5, this.usuario.getId());
            pstmt.execute();
        } catch (SQLException ex) {
            throw new Exception("Error ao Alterar o Usuario: " + ex.getMessage());
        }
    }

    public void excluir(Usuario usuario) throws Exception {
        this.usuario = usuario;
        if ((this.usuario == null) || (this.usuario.getId() == 0)) {
            throw new Exception("Usuario não Existe...");
        }
        try {
            iniciarConexaoBD();
            String sqlDelete = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement pstmt = this.conn.prepareStatement(sqlDelete);
            pstmt.setInt(5, this.usuario.getId());
            pstmt.execute();
        } catch (SQLException ex) {
            throw new Exception("Error ao excluir o Usuario: " + ex.getMessage());
        }

    }

    public Usuario pesquisar(int id) throws Exception {
        try {
            iniciarConexaoBD();
            String sqlSelect = "SELECT * FROM usuario WHERE id = ? LIMIT 1";
            PreparedStatement pstmt = this.conn.prepareStatement(sqlSelect);
            pstmt.setInt(1, this.usuario.getId());
            ResultSet resultado = pstmt.executeQuery();
            this.usuario = new Usuario();
            if (resultado.next()) {
                this.usuario.setId(resultado.getInt("id"));
                this.usuario.setNome(resultado.getString("nome"));
                this.usuario.setEmail(resultado.getString("email"));
                this.usuario.setUserName(resultado.getString("user_name"));
                this.usuario.setSenha(resultado.getString("senha"));
            }
            return this.usuario;
        } catch (SQLException ex) {
            throw new Exception("Error ao pesquisar o Usuario: " + ex.getMessage());
        }

    }

    public List pesquisar(String nome) throws Exception {
        try {
            iniciarConexaoBD();
            String sqlSelect = "SELECT * FROM usuario WHERE nome LIKE ? ";
            PreparedStatement pstmt = this.conn.prepareStatement(sqlSelect);
            pstmt.setString(1, nome + "%");
            ResultSet resultado = pstmt.executeQuery();
            List listUsuario = new ArrayList();
            while (resultado.next()) {
                this.usuario = new Usuario();
                this.usuario.setId(resultado.getInt("id"));
                this.usuario.setNome(resultado.getString("nome"));
                this.usuario.setEmail(resultado.getString("email"));
                this.usuario.setUserName(resultado.getString("user_name"));
                this.usuario.setSenha(resultado.getString("senha"));
                listUsuario.add(this.usuario);
            }
            return listUsuario;
        } catch (SQLException ex) {
            throw new Exception("Error ao pesquisar o Usuario: " + ex.getMessage());
        }

    }

    public Usuario login(String userName, String senha) throws Exception {
        try {
            iniciarConexaoBD();
            String sqlSelect = "SELECT * FROM usuario WHERE user_name = ? AND senha = ? LIMIT 1";
            PreparedStatement pstmt = this.conn.prepareStatement(sqlSelect);
            pstmt.setString(1, userName);
            pstmt.setString(2, senha);
            ResultSet resultado = pstmt.executeQuery();
            this.usuario = new Usuario();
            if (resultado.next()) {
                this.usuario.setId(resultado.getInt("id"));
                this.usuario.setNome(resultado.getString("nome"));
                this.usuario.setEmail(resultado.getString("email"));
                this.usuario.setUserName(resultado.getString("user_name"));
                this.usuario.setSenha(resultado.getString("senha"));
            }
            return this.usuario;
        } catch (SQLException ex) {
            throw new Exception("Error ao pesquisar o Usuario: " + ex.getMessage());
        }

    }

}
