/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author wender
 */
public class ConexaoBD {

    private String status;
    //atributo do tipo Connection
    private Connection conn = null;

    //Método de Conexão//
    public java.sql.Connection getConexao() {

        try {
            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//
            String serverName = "localhost";    //caminho do servidor do BD
            String mydatabase = "exemplo_swing";  //nome do seu banco de dados

            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";        //nome de um usuário de seu BD      
            String password = "saoluis";      //sua senha de acesso
            conn = DriverManager.getConnection(url, username, password);

            //Testa sua conexão//  
            if (conn != null) {
                status = "STATUS--->Conectado com sucesso!";
            } else {
                status = "STATUS--->Não foi possivel realizar conexão";
            }
            return conn;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            JOptionPane.showMessageDialog(null, "O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }

    //Método que retorna o status da sua conexão//
    public String statusConection() {
        return status;
    }

    //Método que fecha sua conexão//
    public boolean FecharConexao() {
        try {
            if (conn != null) {
                conn.close();
            }
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    //Método que reinicia sua conexão//
    public java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return getConexao();
    }

}
