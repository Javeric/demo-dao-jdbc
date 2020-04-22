package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory { 
		
		
	public static SellerDao createSellerDao() {
			return new SellerDaoJDBC(DB.getConnection());
	
	}
	}

//classe com opera��es est�ticas
//para instanciar os DAOs
//m�todo que retorna um tipo da interface, mas internamente
//retorna a implementa��o. N�o exponho a implement., s� a interface
//agora vamos l� no Program e ao inves de instanciar um objeto SellerDao
//com o construtor, usando o new, eu s� chamo esse m�todo.