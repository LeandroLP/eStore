package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import br.com.fiap.nac.dao.GeneroDAO;
import br.com.fiap.nac.to.Genero;


@ManagedBean
@SessionScoped
public class GeneroBean {

	private Genero genero;
	private GeneroDAO generoDAO;
	//private LazyDataModel<Genero> listGenero;
	private List<Genero> listGenero;
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public GeneroDAO getGeneroDAO() {
		return generoDAO;
	}

	public void setGeneroDAO(GeneroDAO generoDAO) {
		this.generoDAO = generoDAO;
	}

	public List<Genero> getListGenero() {
		return listGenero;
	}

	public void setListGenero(List<Genero> listGenero) {
		this.listGenero = listGenero;
	}

	@PostConstruct
    public void init() {
		generoDAO = new GeneroDAO();
		
		limparCarregar();
    }
	
	public String salvar(ActionEvent actionEvent) {
		FacesMessage message = null;
		try {
			if(generoDAO.save(genero) != null){
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",  "Genero cadastrado com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",  e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",  e.getMessage());
		}

        FacesContext.getCurrentInstance().addMessage(null, message);
        
        limparCarregar();
        
		return "genero";
	}

	public String alterar() {
		return "genero";
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void excluir() {		
		FacesMessage message = null;
		try {
			if(generoDAO.delete(id)){
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",  "Genero excluída com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",  e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",  e.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, message);
        
		limparCarregar();
		//return "genero";
	}

	public String novo() {
		limparCarregar();
		
		return "genero";
	}

	public void onRowEdit(RowEditEvent event) {
		Genero generoEdit = (Genero) event.getObject();
		
		FacesMessage message = null;
    	
    	try {
			if(generoDAO.update(generoEdit)){
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Genero alterada com sucesso");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",  e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",  e.getMessage());
		}
    	
    	FacesContext.getCurrentInstance().addMessage(null, message);
    	
    	limparCarregar();
    }
     
    public void onRowCancel(RowEditEvent event) {
    	Genero genero = (Genero)event.getObject();
    	
        FacesMessage msg = new FacesMessage("Edição cancelada", String.valueOf(genero.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    private void limparCarregar(){
    	genero = new Genero();
    	
    	try {
			listGenero = generoDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
