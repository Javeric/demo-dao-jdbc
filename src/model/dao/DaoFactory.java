package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory { 
		
		
	public static SellerDao createSellerDao() {
			return new SellerDaoJDBC(DB.getConnection());
	
	}
	}

//classe com operações estáticas
//para instanciar os DAOs
//método que retorna um tipo da interface, mas internamente
//retorna a implementação. Não exponho a implement., só a interface
//agora vamos lá no Program e ao inves de instanciar um objeto SellerDao
//com o construtor, usando o new, eu só chamo esse método.