package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.to.TipoAcesso;
import br.com.fiap.nac.to.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private List<Usuario> listUsuario;
	private List<TipoAcesso> listTipoAcesso;

	public Usuario getUsuario() {
		return usuario;
	}

	public List<TipoAcesso> getListTipoAcesso() {
		return listTipoAcesso;
	}

	public void setListTipoAcesso(List<TipoAcesso> listTipoAcesso) {
		this.listTipoAcesso = listTipoAcesso;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return this.usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Usuario> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	@PostConstruct
	public void init() {
		usuarioDAO = new UsuarioDAO();

		limparCarregar();
	}

	public String salvar() {

		FacesMessage message = null;
		try {
			if (usuarioDAO.save(usuario) != null) {
				message = new FacesMessage("Usuario cadastrado com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();

		return "usuario";
	}

	public String alterar() {
		return "usuario";
	}

	private Integer usuarioId;

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public void excluir() {

		FacesMessage message = null;
		try {
			if (usuarioDAO.delete(usuarioId)) {
				message = new FacesMessage("Usuario exclu�do com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public String novo() {
		limparCarregar();

		return "usuario";
	}

	private void limparCarregar() {
		usuario = new Usuario();

		try {

			listUsuario = usuarioDAO.getAll();
			listTipoAcesso = usuarioDAO.listarTipoAceso();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public String login() {
		FacesMessage message = null;

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			if (usuarioDAO.logar(usuario)) {

				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				sessionMap.put("usuario", usuario);

				return null;
			} else {
				message = new FacesMessage("Usu�rio n�o encontrado!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage("messagesLogin", message);
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return null;
	}
}
