package br.com.triadworks.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.triadworks.jdbc.ConnectionFactory;
import br.com.triadworks.jdbc.modelo.Caloteiro;

public class CaloteiroDAO {
	private Connection connection;
	
	public CaloteiroDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Caloteiro caloteiro){
		String sql = "insert into caloteiro" +
						"(nome,email,devendo,dataDivida)" +
				  "values (?,?,?,?)";
		
		try{
		// preparando a insercao
		 PreparedStatement ps = connection.prepareStatement(sql);
		 
		 //setando os valores
		 ps.setString(1, caloteiro.getNome());
		 ps.setString(2, caloteiro.getEmail());
		 ps.setInt(3, caloteiro.getDevendo());
		 ps.setDate(4, new Date(caloteiro.getDataDivida().getTimeInMillis()));
		 
		 ps.execute();
		 ps.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
	}
	
	public List<Caloteiro> getLista(){
		try{
			PreparedStatement ps = this.connection
					.prepareStatement("select * from caloteiro");
			List<Caloteiro> lista = new ArrayList<Caloteiro>();
			Caloteiro caloteiro = null;
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				caloteiro = new Caloteiro();
				caloteiro.setNome(rs.getString("nome"));
				caloteiro.setEmail(rs.getString("email"));
				caloteiro.setDevendo(rs.getInt("devendo"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("dataDivida"));
				caloteiro.setDataDivida(calendar);
				
				lista.add(caloteiro);
			}
			rs.close();
			ps.close();
			return lista;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void altera (Caloteiro caloteiro){
		String sql = "update caloteiro set nome=?,"
					+ "devendo=? , dataDivida=?, where id=?";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, caloteiro.getNome());
			ps.setString(2, caloteiro.getEmail());
			ps.setInt(3, caloteiro.getDevendo());
			ps.setDate(4, new Date(caloteiro.getDataDivida().getTimeInMillis()));
			
			ps.execute();
			ps.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Caloteiro caloteiro){
		String sql = "delete from caloteiro where id = ?";
		try{
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, caloteiro.getId());
			ps.execute();
			ps.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public Caloteiro getCaloteiro(Long id){
		try{
			PreparedStatement ps = this.connection.prepareStatement("select * from caloteiro where id=?");
			ps.setLong(1, id);
			Caloteiro caloteiro = null;
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				caloteiro = new Caloteiro();
				caloteiro.setId(rs.getLong("id"));
				caloteiro.setNome(rs.getString("nome"));
				caloteiro.setEmail(rs.getString("email"));
				caloteiro.setDevendo(rs.getInt("devendo"));
				Calendar dataDivida = Calendar.getInstance();
				dataDivida.setTime(rs.getDate("dataDivida"));
				caloteiro.setDataDivida(dataDivida);				
			}
			rs.close();
			ps.close();
			return caloteiro;
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}
		
		
	}

}
