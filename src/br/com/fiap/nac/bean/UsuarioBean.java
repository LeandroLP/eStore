package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.to.TipoAcesso;
import br.com.fiap.nac.to.Usuario;

public class UsuarioBean {

	private Usuario usuario;
	// private LazyDataModel<Usuario> listUsuario;
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
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario cadastrado com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
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
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario excluída com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public String novo() {
		limparCarregar();

		return "usuario";
	}

	public void onRowEdit(RowEditEvent event) {
		Usuario usuarioEdit = (Usuario) event.getObject();

		FacesMessage message = null;

		try {
			if (usuarioDAO.update(usuarioEdit)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario alterada com sucesso");
			}
		} catch (ClassNotFoundException e) {
			
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public void onRowCancel(RowEditEvent event) {
		Usuario usuarioEdit = (Usuario) event.getObject();

		FacesMessage msg = new FacesMessage("Edição cancelada", String.valueOf(usuarioEdit.getUsuarioId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
}
