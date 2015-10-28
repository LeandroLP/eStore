package br.com.fiap.nac.bean;

import java.sql.SQLException;
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
		FacesMessage message = null;
		try {
			carrinho.setData(new Date());
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			if(externalContext.getSessionMap() != null && externalContext.getSessionMap().get("usuario") != null){
				Usuario usuario = (Usuario) externalContext.getSessionMap().get("usuario");
				carrinho.setUsuario(new Usuario());
				carrinho.getUsuario().setUsuarioId(usuario.getUsuarioId());
			}

			if (carrinhoDAO.save(carrinho) != null) {
				message = new FacesMessage("Item adicionado com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		limparCarregar();

		return "index";
	}

	public String adicionarListaDesejo() {
		FacesMessage message = null;
		try {
			listaDesejo.setDataHora(new Date());
			listaDesejo.setQuantidade(carrinho.getQuantidade());
			listaDesejo.setTotal(carrinho.getQuantidade() * carrinho.getLivro().getValor());
			listaDesejo.getLivro().setLivroId(carrinho.getLivro().getLivroId());
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Usuario usuarioSession = (Usuario) externalContext.getSessionMap().get("usuario");
			if(usuarioSession != null){				
				listaDesejo.getUsuario().setUsuarioId(usuarioSession.getUsuarioId());
			}
			
			if (listaDesejoDAO.save(listaDesejo) != null) {
				message = new FacesMessage("Lista desejo cadastrada com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();

		return "index";
	}

	private void limparCarregar() {
		carrinho = new Carrinho();
		carrinho.setUsuario(new Usuario());
		
		listaDesejo = new ListaDesejo();
		listaDesejo.setLivro(new Livro());
		listaDesejo.setUsuario(new Usuario());

		FacesMessage message = null;
		try {
			listCarrinho = carrinhoDAO.getAll();
			listListaDesejo = listaDesejoDAO.getAll();

			calcularValorTotal();
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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
	
	public String adicionarListaDesejoCarrinho(ListaDesejo listaDesejo){
		FacesMessage message = null;
		try {
			Carrinho carrinho = new Carrinho();
			carrinho.setData(new Date());
			carrinho.setQuantidade(listaDesejo.getQuantidade());
			carrinho.setLivro(listaDesejo.getLivro());
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();			
			Usuario usuarioSession = (Usuario) externalContext.getSessionMap().get("usuario");
			if(usuarioSession != null){				
				carrinho.setUsuario(usuarioSession);
			}

			if (carrinhoDAO.save(carrinho) != null) {
				message = new FacesMessage("Item adicionado ao carrinho com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		limparCarregar();

		return null;
	}
	
	public String finalizarCompra(){
		FacesMessage message = null;
		try {
			for(int i = 0; i < listCarrinho.size(); i++){
				carrinhoDAO.save(listCarrinho.get(i));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		limparCarregar();

		return null;
	}

}
