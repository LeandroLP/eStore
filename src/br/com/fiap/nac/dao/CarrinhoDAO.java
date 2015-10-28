package br.com.fiap.nac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.nac.factory.ConnectionFactory;
import br.com.fiap.nac.to.Carrinho;
import br.com.fiap.nac.to.Compra;
import br.com.fiap.nac.to.Livro;
import br.com.fiap.nac.to.Usuario;

public class CarrinhoDAO implements GenericDAO<Carrinho> {

	@Override
	public List<Carrinho> getAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Carrinho> listCarrinho = new ArrayList<Carrinho>();
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * FROM CARRINHO";

		try {

			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				Carrinho carrinho = new Carrinho();
				carrinho.setId(rs.getInt("ID_CARRINHO"));
				carrinho.setData(rs.getDate("DATA"));
				carrinho.setQuantidade(rs.getInt("QUANTIDADE"));
				carrinho.setPrecoUnitario(rs.getDouble("PRECO_UNITARIO"));
				carrinho.setTotal(rs.getDouble("TOTAL"));
				
				Integer livroId = rs.getInt("ID_LIVRO");
				Livro livro = new LivroDAO().get(livroId);
				carrinho.setLivro(livro);
				
				Integer usuarioId = rs.getInt("ID_USUARIO");
				Usuario usuario = new UsuarioDAO().get(usuarioId);
				carrinho.setUsuario(usuario);

				listCarrinho.add(carrinho);
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return listCarrinho;
	}

	@Override
	public Carrinho save(Carrinho object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO CARRINHO"
				+ "(DATA, QUANTIDADE, PRECO_UNITARIO, TOTAL, ID_LIVRO, ID_USUARIO) VALUES" + "(?, ?, ?, ?, ?, ?);";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setTimestamp(1, new Timestamp(object.getData().getTime()));
			preparedStatement.setInt(2, object.getQuantidade());
			preparedStatement.setDouble(3, object.getLivro().getValor());
			preparedStatement.setDouble(4, object.getLivro().getValor() * object.getQuantidade());
			preparedStatement.setInt(5, object.getLivro().getLivroId());
			preparedStatement.setInt(6, object.getUsuario().getUsuarioId());

			if (preparedStatement.executeUpdate() == 1) {
				// execute insert SQL stetement
				resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next())
                {
                	object.setId(resultSet.getInt(1));
                }
				return object;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;
	}
	
	
	
	public Compra saveCompra(Compra object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO COMPRA (DATA_HORA, FRETE, TOTAL, ID_FORMA_PAGAMENTO)"+
								"VALUES(?,?,?,?);";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setTimestamp(1, new Timestamp(object.getDataCompra().getTime()));
			preparedStatement.setDouble(2, object.getFrete());
			preparedStatement.setDouble(3, object.getTotal());
			preparedStatement.setDouble(4, object.getIdFormaPagamento());		

			if (preparedStatement.executeUpdate() == 1) {
				// execute insert SQL stetement
				resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next())
                {
                	object.setId(resultSet.getInt(1));
                }
				return object;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;
	}


	@Override
	public Carrinho get(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Carrinho object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Carrinho object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM CARRINHO WHERE ID_CARRINHO = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, object.getId());

			// execute delete SQL stetement
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return false;
	}

	@Override
	public boolean deleteAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
