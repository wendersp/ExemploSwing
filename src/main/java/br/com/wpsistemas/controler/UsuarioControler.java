/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.controler;

import br.com.wpsistemas.model.dao.UsuarioDao;
import br.com.wpsistemas.model.dao.entitades.Usuario;
import br.com.wpsistemas.view.UsuarioICons;
import br.com.wpsistemas.view.UsuarioIFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author wender
 */
public class UsuarioControler {

    private UsuarioIFrm usuarioIFrm;
    private UsuarioICons usuarioICons;
    private final JDesktopPane desktopPane;

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private List<Usuario> listaUsuario;

    public UsuarioControler(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    /*
    * Evento clique botão NOVO formulario UsuarioICons
     */
    public ActionListener alBotaoNovoUsuarioICons = (ActionEvent e) -> {
        abrirUsuarioIFrm();
        novo();
    };

    /*
    * Evento clique botão NOVO formulario UsuarioICons
     */
    public ActionListener alBotaoEditarUsuarioICons = (ActionEvent e) -> {
        editar();
    };

    /*
    * Evento clique botão FECHAR formulario UsuarioICons
     */
    public ActionListener alBotaoFecharUsuarioICons = (ActionEvent e) -> {
        fecharUsuarioICons();
    };

    /*
    * Evento clique botão PESQUISAR formulario UsuarioICons
     */
    public ActionListener alBotaoPesquisarUsuarioICons = (ActionEvent e) -> {
        pesquisar();
    };

    /*
    * Evento clique botão NOVO formulario UsuarioIFrm 
     */
    public ActionListener alBotaoNovoUsuarioIFrm = (ActionEvent e) -> {
        novo();
    };

    /*
    * Evento clique botão SALVAR formulario UsuarioIFrm 
     */
    public ActionListener alBotaoSalvarUsuarioIFrm = (ActionEvent e) -> {
        salvar();
    };

    /*
    * Evento clique botão EXCLUIR formulario UsuarioIFrm 
     */
    public ActionListener alBotaoExcluirUsuarioIFrm = (ActionEvent e) -> {
        excluir();
    };

    /*
    * Evento clique botão PESQUISAR formulario UsuarioIFrm 
     */
    public ActionListener alBotaoPesquisarUsuarioIFrm = (ActionEvent e) -> {

    };

    /*
    * Evento clique botão FECHAR formulario UsuarioIFrm 
     */
    public ActionListener alBotaoFecharUsuarioIFrm = (ActionEvent e) -> {
        fecharUsuarioIFrm();
    };

    /**
     * Evento clique JTable Usuario
     */
    public MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                usuarioICons.statusBotoes(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
            }
            if (e.getClickCount() == 2) {
                editar();
            }
        }
    };

    public void abrirUsuarioIFrm() {
        if (usuarioIFrm == null) {
            usuarioIFrm = new UsuarioIFrm();
            this.desktopPane.add(usuarioIFrm);
            usuarioIFrm.setActionListenerJbtnNovo(alBotaoNovoUsuarioIFrm);
            usuarioIFrm.setActionListenerJbtnSalvar(alBotaoSalvarUsuarioIFrm);
            usuarioIFrm.setActionListenerJbtnExcluir(alBotaoExcluirUsuarioIFrm);
            usuarioIFrm.setActionListenerJbtnFechar(alBotaoFecharUsuarioIFrm);
            usuarioIFrm.show();
        } else if (usuarioIFrm.isClosed()) {
            this.desktopPane.add(usuarioIFrm);
            usuarioIFrm.toFront();
            usuarioIFrm.show();
            usuarioIFrm.centralizarTela();
        } else {
            usuarioIFrm.centralizarTela();
            usuarioIFrm.toFront();
            usuarioIFrm.show();
        }
    }

    public void fecharUsuarioIFrm() {
        if (usuarioIFrm != null) {
            usuarioIFrm.dispose();
            usuarioIFrm = null;
        }
    }

    public void abrirUsuarioICons() {
        if (usuarioICons == null) {
            usuarioICons = new UsuarioICons();
            usuarioICons.setActionListenerJbtnNovo(alBotaoNovoUsuarioICons);
            usuarioICons.setActionListenerJbtnEditar(alBotaoEditarUsuarioICons);
            usuarioICons.setActionListenerJbtnFechar(alBotaoFecharUsuarioICons);
            usuarioICons.setActionListenerJbtnPesquisar(alBotaoPesquisarUsuarioICons);
            usuarioICons.setMouseListenerJTblUsuario(mouseAdapter);
            this.desktopPane.add(usuarioICons);
            usuarioICons.show();
        } else if (usuarioICons.isClosed()) {
            this.desktopPane.add(usuarioICons);
            usuarioICons.toFront();
            usuarioICons.show();
            usuarioICons.centralizarTela();

        } else {
            usuarioICons.centralizarTela();
            usuarioICons.toFront();
            usuarioICons.show();
        }
    }

    public void fecharUsuarioICons() {
        if (usuarioICons != null) {
            usuarioICons.dispose();
            usuarioICons = null;
        }
    }

    private void novo() {
        if (this.usuarioDao == null) {
            this.usuarioDao = new UsuarioDao();
        }
        this.usuario = new Usuario();
        this.usuarioIFrm.setUsuario(this.usuario);
    }

    private void editar() {
        this.usuario = usuarioICons.getUsuarioSelecionado();
        this.abrirUsuarioIFrm();
        this.usuarioIFrm.setUsuario(usuario);
        this.usuarioIFrm.statusBotoes(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
    }

    private void salvar() {
        try {
            if (this.usuarioDao == null) {
                this.usuarioDao = new UsuarioDao();
            }
            this.usuario = usuarioIFrm.getUsuario();
            if (this.usuario != null) {
                this.usuarioDao.salvar(this.usuario);
                novo();
                JOptionPane.showMessageDialog(null, "Usuario Salvo com sucesso...");
                this.usuarioIFrm.statusBotoes(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void excluir() {
        try {
            if (JOptionPane.showConfirmDialog(null, "Tem serteza que deseja excluir o usuario " + this.usuario.getNome(), "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
                this.usuarioDao.excluir(this.usuario);
                this.listaUsuario.remove(this.usuario);
                this.usuarioICons.setListaUsuario(this.listaUsuario);
                this.novo();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }

    }

    private void pesquisar() {
        try {
            if (this.usuarioDao == null) {
                this.usuarioDao = new UsuarioDao();
            }
            if (this.listaUsuario == null) {
                this.listaUsuario = new ArrayList<>();
            }

            String param[];
            param = this.usuarioICons.getParametroPesquisa();

            if (param[0].equals("CODIGO")) {
                //this.listaUsuario = this.usuarioDao.pesquisar("");
            } else {
                this.listaUsuario = this.usuarioDao.pesquisar(param[1]);
            }
            this.usuarioICons.setListaUsuario(this.listaUsuario);
            usuarioICons.statusBotoes(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

}
