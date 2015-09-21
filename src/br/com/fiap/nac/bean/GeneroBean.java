package br.com.fiap.nac.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;

import br.com.fiap.nac.lazydatamodel.GeneroLazyList;
import br.com.fiap.nac.to.Genero;

@ManagedBean
@SessionScoped
public class GeneroBean {

	private Genero genero;
	private LazyDataModel<Genero> listGenero;
	
	@PostConstruct
	public void load(){
		listGenero = new GeneroLazyList();
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public LazyDataModel<Genero> getListGenero() {
		return listGenero;
	}

	public void setListGenero(LazyDataModel<Genero> listGenero) {
		this.listGenero = listGenero;
	}

	public String salvar() {
		return "genero";
	}

	public String alterar() {
		return "genero";
	}

	public String excluir() {
		return "genero";
	}
	
	public String novo() {
		return "genero";
	}
}
