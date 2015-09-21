package br.com.fiap.nac.lazydatamodel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.fiap.nac.dao.GeneroDAO;
import br.com.fiap.nac.to.Genero;

public class GeneroLazyList extends LazyDataModel<Genero> {
 
    private static final long serialVersionUID = 1L;
 
    private List<Genero> listGeneros;
    private List<Genero> listAll;
 
    private GeneroDAO generoDAO;
 
    @Override
    public List<Genero> load(int first, int pageSize, String sortField, SortOrder sortOrder,
    		Map<String, Object> filters) {
    	// TODO Auto-generated method stub
    	
    	generoDAO = new GeneroDAO();
   	 
        // with datatable pagination limits
    	try {
			listGeneros = generoDAO.findGeneros(first, pageSize);
			listAll = generoDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// set the total of players
        if(getRowCount() <= 0){
            setRowCount(listAll.size());
        }
 
        // set the page dize
        setPageSize(pageSize);
    	
    	return listGeneros;
    }
 
    @Override
    public Object getRowKey(Genero genero) {
        return genero.getId();
    }
 
    @Override
    public Genero getRowData(String playerId) {
        Integer id = Integer.valueOf(playerId);
 
        for (Genero player : listGeneros) {
            if(id.equals(player.getId())){
                return player;
            }
        }
 
        return null;
    }
}