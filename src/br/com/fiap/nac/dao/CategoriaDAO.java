package br.com.fiap.nac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.nac.factory.ConnectionFactory;
import br.com.fiap.nac.to.Categoria;


public class CategoriaDAO implements GenericDAO<Categoria> {

	@Override
	public List<Categoria> getAll() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		List<Categoria> listCategoria = new ArrayList<Categoria>();
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * FROM CATEGORIA;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("ID_CATEGORIA"));
				categoria.setDescricao(rs.getString("DESCRICAO"));

				listCategoria.add(categoria);
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return listCategoria;
	}

	@Override
	public Categoria save(Categoria object)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO CATEGORIA"
				+ "(DESCRICAO) VALUES" + "(?);";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, object.getDescricao());

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
	public Categoria get(Integer id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM CATEGORIA WHERE ID_CATEGORIA = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, id);
			
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Categoria categoriaBean = new Categoria();

				return categoriaBean;
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
	public boolean update(Categoria object) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE CATEGORIA SET DESCRICAO = ? "
				+ " WHERE ID_CATEGORIA = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, object.getDescricao());
			preparedStatement.setInt(2, object.getId());

			// execute update SQL stetement
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

	public boolean delete(Integer id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

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
		Connection dbConnection = null;
		Statement statement = null;

		String deleteSQL = "DELETE FROM CATEGORIA;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute delete SQL stetement
			if (statement.executeUpdate(deleteSQL) == 1) {
				return true;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Categoria object) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
