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
import br.com.fiap.nac.to.ListaDesejo;
import br.com.fiap.nac.to.Livro;
import br.com.fiap.nac.to.Usuario;

public class ListaDesejoDAO implements GenericDAO<ListaDesejo> {

	@Override
	public List<ListaDesejo> getAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<ListaDesejo> listListaDesejo = new ArrayList<ListaDesejo>();
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * FROM LISTA_DESEJO;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				ListaDesejo listaDesejo = new ListaDesejo();
				listaDesejo.setId(rs.getInt("ID_LISTA"));
				listaDesejo.setDataHora(rs.getDate("DATA_HORA"));
				listaDesejo.setQuantidade(rs.getInt("QUANTIDADE"));
				listaDesejo.setTotal(rs.getDouble("TOTAL"));
				
				Livro livro = new LivroDAO().get(rs.getInt("ID_LIVRO"));
				listaDesejo.setLivro(livro);
				
				Usuario usuario = new UsuarioDAO().get(rs.getInt("ID_USUARIO"));
				listaDesejo.setUsuario(usuario);

				listListaDesejo.add(listaDesejo);
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return listListaDesejo;
	}

	@Override
	public ListaDesejo save(ListaDesejo object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO LISTA_DESEJO"
				+ "(DATA_HORA, QUANTIDADE, TOTAL, ID_LIVRO, ID_USUARIO) VALUES" + "(?, ?, ?, ?, ?);";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setTimestamp(1, new Timestamp(object.getDataHora().getTime()));
			preparedStatement.setInt(2, object.getQuantidade());
			preparedStatement.setDouble(3, object.getTotal());
			preparedStatement.setInt(4, object.getLivro().getLivroId());
			preparedStatement.setInt(5, object.getUsuario().getUsuarioId());

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
	public ListaDesejo get(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ListaDesejo object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ListaDesejo object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
