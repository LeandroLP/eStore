package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import br.com.fiap.nac.dao.AutorDAO;
import br.com.fiap.nac.to.Autor;

@ManagedBean
@SessionScoped
public class AutorBean {

	private Autor autor;
	// private LazyDataModel<Autor> listAutor;
	private AutorDAO autorDAO;
	private List<Autor> listAutor;

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public AutorDAO getAutorDAO() {
		return this.autorDAO;
	}

	public void setAutorDAO(AutorDAO autorDAO) {
		this.autorDAO = autorDAO;
	}

	public List<Autor> getListAutor() {
		return listAutor;
	}

	public void setListAutor(List<Autor> listAutor) {
		this.listAutor = listAutor;
	}

	@PostConstruct
	public void init() {
		autorDAO = new AutorDAO();

		limparCarregar();
	}

	public String salvar() {

		FacesMessage message = null;
		try {
			if (autorDAO.save(autor) != null) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Autor cadastrado com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();

		return "autor";
	}

	public String alterar() {
		return "autor";
	}

	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void excluir() {

		FacesMessage message = null;
		try {
			if (autorDAO.delete(autorId)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Autor excluída com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public String novo() {
		limparCarregar();

		return "autor";
	}

	public void onRowEdit(RowEditEvent event) {
		Autor autorEdit = (Autor) event.getObject();

		FacesMessage message = null;

		try {
			if (autorDAO.update(autorEdit)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Autor alterada com sucesso");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public void onRowCancel(RowEditEvent event) {
		Autor autorEdit = (Autor) event.getObject();

		FacesMessage msg = new FacesMessage("Edição cancelada", String.valueOf(autorEdit.getAutorId()));
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
		autor = new Autor();

		try {
			listAutor = autorDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
