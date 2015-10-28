package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.CarrinhoDAO;
import br.com.fiap.nac.dao.ListaDesejoDAO;
import br.com.fiap.nac.to.Carrinho;
import br.com.fiap.nac.to.ListaDesejo;
import br.com.fiap.nac.to.Livro;
import br.com.fiap.nac.to.Usuario;

@ManagedBean
@SessionScoped
public class CarrinhoBean {

	private CarrinhoDAO carrinhoDAO;
	private Carrinho carrinho;
	private List<Carrinho> listCarrinho;
	private Double valorTotal;

	private ListaDesejo listaDesejo;
	private List<ListaDesejo> listListaDesejo;
	private ListaDesejoDAO listaDesejoDAO;

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Carrinho> getListCarrinho() {
		return listCarrinho;
	}

	public void setListCarrinho(List<Carrinho> listCarrinho) {
		this.listCarrinho = listCarrinho;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ListaDesejo getListaDesejo() {
		return listaDesejo;
	}

	public void setListaDesejo(ListaDesejo listaDesejo) {
		this.listaDesejo = listaDesejo;
	}

	public List<ListaDesejo> getListListaDesejo() {
		return listListaDesejo;
	}

	public void setListListaDesejo(List<ListaDesejo> listListaDesejo) {
		this.listListaDesejo = listListaDesejo;
	}

	@PostConstruct
	public void init() {
		carrinhoDAO = new CarrinhoDAO();
		listaDesejoDAO = new ListaDesejoDAO();

		limparCarregar();
	}

	public String adicionar() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		if(externalContext.getSessionMap() != null && externalContext.getSessionMap().get("usuario") != null){
			Usuario usuario = (Usuario) externalContext.getSessionMap().get("usuario");
			carrinho.setUsuario(new Usuario());
			carrinho.getUsuario().setUsuarioId(usuario.getUsuarioId());
		}
		carrinho.setData(new Date());
		carrinho.setTotal(carrinho.getLivro().getValor() * carrinho.getQuantidade());
		
		listCarrinho.add(carrinho);
		
		carrinho = new Carrinho();
		
		calcularValorTotal();

		return "index";
	}

	public String adicionarListaDesejo() {
		listaDesejo.setDataHora(new Date());
		listaDesejo.setQuantidade(carrinho.getQuantidade());
		listaDesejo.setTotal(carrinho.getQuantidade() * carrinho.getLivro().getValor());
		listaDesejo.setLivro(carrinho.getLivro());
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Usuario usuarioSession = (Usuario) externalContext.getSessionMap().get("usuario");
		if(usuarioSession != null){				
			listaDesejo.getUsuario().setUsuarioId(usuarioSession.getUsuarioId());
		}
		
		listListaDesejo.add(listaDesejo);
		
		carrinho = new Carrinho();
		listaDesejo = new ListaDesejo();

		return "index";
	}
	
	public String adicionarListaDesejoCarrinho(ListaDesejo listaDesejo){
		Carrinho carrinho = new Carrinho();
		carrinho.setData(new Date());
		carrinho.setQuantidade(listaDesejo.getQuantidade());
		carrinho.setLivro(listaDesejo.getLivro());
		carrinho.setTotal(listaDesejo.getLivro().getValor() * listaDesejo.getQuantidade());
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();			
		Usuario usuarioSession = (Usuario) externalContext.getSessionMap().get("usuario");
		if(usuarioSession != null){				
			carrinho.setUsuario(usuarioSession);
		}
		
		listListaDesejo.remove(listaDesejo);
		
		listCarrinho.add(carrinho);
		
		listaDesejo = new ListaDesejo();
		carrinho = new Carrinho();
		
		calcularValorTotal();

		return null;
	}

	private void limparCarregar() {
		carrinho = new Carrinho();
		carrinho.setLivro(new Livro());
		carrinho.setUsuario(new Usuario());
		
		listaDesejo = new ListaDesejo();
		listaDesejo.setLivro(new Livro());
		listaDesejo.setUsuario(new Usuario());
		
		listCarrinho = new ArrayList<>();
		listListaDesejo = new ArrayList<>();
		
		calcularValorTotal();
	}

	public String selecionarLivro(Livro livro) {
		carrinho.setLivro(livro);

		return "item";
	}

	public void calcularValorTotal() {
		Double valorTotal = 0.0;
		for (int i = 0; i < listCarrinho.size(); i++) {
			valorTotal += listCarrinho.get(i).getTotal();
		}

		setValorTotal(valorTotal);
	}
	
	public String excluir(Carrinho carrinho) {
		listCarrinho.remove(carrinho);
		
		calcularValorTotal();
		
		return null;
	}
	
	public String finalizarCompra(){
		FacesMessage message = null;
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();			
		Usuario usuarioSession = (Usuario) externalContext.getSessionMap().get("usuario");
		if(usuarioSession != null){
			for(int i = 0; i < listCarrinho.size(); i++){
				listCarrinho.get(i).setUsuario(usuarioSession);
			}
			
			for(int i = 0; i < listListaDesejo.size(); i++){
				listListaDesejo.get(i).setUsuario(usuarioSession);
			}
			
			try {
				salvarCarrinho();
				salvarListaDesejo();
				
				message = new FacesMessage("Sua compra foi finalizada com sucesso!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				message = new FacesMessage(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				message = new FacesMessage(e.getMessage());
			}

			if(message != null){
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			
			limparCarregar();
			
			return "index";
		} else {
			message = new FacesMessage("Favor logar para finalizar sua compra!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
			return null;
		}
	}
	
	public void salvarCarrinho() throws ClassNotFoundException, SQLException {		
		for(int i = 0; i < listCarrinho.size(); i++){
			carrinhoDAO.save(listCarrinho.get(i));
		}
	}
	
	public void salvarListaDesejo() throws ClassNotFoundException, SQLException {
		for(int i = 0; i < listListaDesejo.size(); i++){
			listaDesejoDAO.save(listListaDesejo.get(i));
		}
	}

}
