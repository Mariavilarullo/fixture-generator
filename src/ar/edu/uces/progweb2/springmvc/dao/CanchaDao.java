package ar.edu.uces.progweb2.springmvc.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.springmvc.model.Cancha;
import ar.edu.uces.progweb2.springmvc.model.Sede;

@Transactional(readOnly = true)
@Component
public class CanchaDao {
	

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Cancha get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Cancha out = (Cancha) session.get(Cancha.class, id);
		// session.close();
		return out;
	}
	public Cancha getCancha(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		Cancha c =(Cancha) session.get(Cancha.class, id);
		return c;
		
	}
	public List<Cancha> getCanchas()
	{
		Session session = sessionFactory.getCurrentSession();
		List c =  session.createQuery(
			    "from Cancha")
			    .list();
		return  c;
		
	}

}
	