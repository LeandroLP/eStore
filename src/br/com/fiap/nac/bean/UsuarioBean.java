package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;

>>>>>>> origin/master
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
<<<<<<< HEAD
=======
import javax.faces.context.ExternalContext;
>>>>>>> origin/master
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
	private Usuario usuarioLogado;

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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void init() {
		usuarioDAO = new UsuarioDAO();

		limparCarregar();
	}

	public void salvar() {

		FacesMessage message = null;
		try {
<<<<<<< HEAD

			if (usuario.getUsuarioId() != null) {

				if (usuarioDAO.update(usuario)) {
					message = new FacesMessage("Usuario Alterado com sucesso!");
				}

			} else {
				if (usuarioDAO.save(usuario) != null) {
					message = new FacesMessage("Usuario cadastrado com sucesso!");
				}
=======
			if (usuarioDAO.save(usuario) != null) {
				message = new FacesMessage("Usuario cadastrado com sucesso!");
>>>>>>> origin/master
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();

	}

	public String alterar(Usuario usuario) {

		this.usuario = usuario;
		return "usuario";
	}

	public void excluir(Usuario usuario) {

		FacesMessage message = null;
		try {
<<<<<<< HEAD
			if (usuarioDAO.delete(usuario)) {
				message = new FacesMessage("Usuario excluída com sucesso!");
=======
			if (usuarioDAO.delete(usuarioId)) {
				message = new FacesMessage("Usuario excluído com sucesso!");
>>>>>>> origin/master
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public void novo() {
		limparCarregar();

	}

	private void limparCarregar() {
		usuario = new Usuario();

		try {

			listUsuario = usuarioDAO.getAll();
			//listTipoAcesso = usuarioDAO.listarTipoAceso();

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
				setUsuarioLogado(usuario);

				return null;
			} else {
				message = new FacesMessage("Usuário não encontrado!");
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
		setUsuarioLogado(null);

		return null;
	}
}
