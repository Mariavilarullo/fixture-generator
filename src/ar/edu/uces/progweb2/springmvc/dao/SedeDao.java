package ar.edu.uces.progweb2.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.springmvc.model.Sede;

@Transactional(readOnly = true)
@Component
public class SedeDao {
	

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Sede get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Sede out = (Sede) session.get(Sede.class, id);
		// session.close();
		return out;
	}
	public List<Sede> getSedes()
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Sede")
			    .list();
		return  s;
		
	}
	public List<Sede> search(String content)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Sede where NOMBRE like '%"+content+"%' ") 
			    .list();
		return  s;
		
	}
	
}
