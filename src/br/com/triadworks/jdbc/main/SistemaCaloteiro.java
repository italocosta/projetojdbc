package br.com.triadworks.jdbc.main;

import java.util.Calendar;
import java.util.List;

import br.com.triadworks.jdbc.dao.CaloteiroDAO;
import br.com.triadworks.jdbc.modelo.Caloteiro;

public class SistemaCaloteiro {

	public static void main(String[] args) {
		Caloteiro caloteiro = new Caloteiro();
		
		caloteiro.setNome("André Costa Nascimento");
		caloteiro.setEmail("andrec@hapvida.com.br");
		caloteiro.setDevendo(1200);
		caloteiro.setDataDivida(Calendar.getInstance());
		
		CaloteiroDAO dao = new CaloteiroDAO();
		
		//dao.adiciona(caloteiro);
		
		System.out.println("Caloteiro gravado com sucesso");
		
		List<Caloteiro> lista = dao.getLista();
		
		for (Caloteiro c : lista){
			System.out.println("Nome :" + c.getNome());
			System.out.println("Email :" + c.getEmail());
			System.out.println("Devendo :"+ c.getDevendo());
			System.out.println("Data Divida :" + c.getDataDivida().getTime());
		}
		
		Caloteiro caloteirodel = new Caloteiro();
		caloteirodel = dao.getCaloteiro(2L);
		//dao.delete(caloteirodel);
		dao.altera(caloteirodel);
		
		/*List<Caloteiro> lista2 = dao.getLista();
		
		for (Caloteiro c : lista2){
			System.out.println("Nome :" + c.getNome());
			System.out.println("Email :" + c.getEmail());
			System.out.println("Devendo :"+ c.getDevendo());
			System.out.println("Data Divida :" + c.getDataDivida().getTime());
		}*/
		
		

	}

}
