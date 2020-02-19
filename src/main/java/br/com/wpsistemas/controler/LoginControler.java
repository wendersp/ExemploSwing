/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.controler;

import br.com.wpsistemas.model.dao.UsuarioDao;
import br.com.wpsistemas.model.dao.entitades.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;

/**
 *
 * @author wender
 */
public class LoginControler {

    private JDesktopPane desktopPane;

    private UsuarioDao usuarioDao;
    private Usuario usuario;
    
    public LoginControler() {

    }

    public LoginControler(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    /*
    * Evento clique botão NOVO formulario PessoaICons
     */
    public ActionListener alBotaoNovoPessoaICons = (ActionEvent e) -> {
//        abrirPessoaIFrm();
//        novo();
    };

    /*
    * Evento clique botão NOVO formulario PessoaICons
     */
    public ActionListener alBotaoEditarPessoaICons = (ActionEvent e) -> {
//        editar();
    };

    /*
    * Evento clique botão FECHAR formulario PessoaICons
     */
    public ActionListener alBotaoFecharPessoaICons = (ActionEvent e) -> {
//        fecharPessoaICons();
    };

    /*
    * Evento clique botão PESQUISAR formulario PessoaICons
     */
    public ActionListener alBotaoPesquisarPessoaICons = (ActionEvent e) -> {
//        pesquisar();
    };

    /*
    * Evento clique botão NOVO formulario PessoaIFrm 
     */
    public ActionListener alBotaoNovoPessoaIFrm = (ActionEvent e) -> {
//        novo();
    };

    /*
    * Evento clique botão SALVAR formulario PessoaIFrm 
     */
    public ActionListener alBotaoSalvarPessoaIFrm = (ActionEvent e) -> {
//        salvar();
    };

    /*
    * Evento clique botão EXCLUIR formulario PessoaIFrm 
     */
    public ActionListener alBotaoExcluirPessoaIFrm = (ActionEvent e) -> {
//        excluir();
    };

    /*
    * Evento clique botão PESQUISAR formulario PessoaIFrm 
     */
    public ActionListener alBotaoPesquisarPessoaIFrm = (ActionEvent e) -> {
//        abrirPessoaICons();
    };

    /*
    * Evento clique botão FECHAR formulario PessoaIFrm 
     */
    public ActionListener alBotaoFecharPessoaIFrm = (ActionEvent e) -> {
//        fecharPessoaIFrm();
    };

    public Usuario logar(String userName, String senha) throws Exception {
        if (userName == null) {
            throw new Exception("Usuario não foi informado");
        }
        if (senha == null) {
            throw new Exception("Senha não foi informada");
        }
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDao();
        }
        this.usuario = usuarioDao.login(userName, senha);
        if (this.usuario != null && this.usuario.getId() > 0) {
            return this.usuario;
        } else {
            throw new Exception("Usuario não encontrado, ou não tem permissão.");
        }

    }

}
