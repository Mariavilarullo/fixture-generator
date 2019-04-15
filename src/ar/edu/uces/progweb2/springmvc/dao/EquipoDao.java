package ar.edu.uces.progweb2.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.springmvc.model.Equipo;
import ar.edu.uces.progweb2.springmvc.model.EquipoAmateur;
import ar.edu.uces.progweb2.springmvc.model.EquipoUniversitario;

@Transactional(readOnly = true)
@Component
public class EquipoDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Equipo get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Equipo out = (Equipo) session.get(Equipo.class, id);
		// session.close();
		return out;
	}
	/*public List<EquipoAmateur> getEquiposAmateur()
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Equipo where TIPO =='AMA'")
			    .list();
		return  s;
		
	}
	public List<EquipoUniversitario> getEquiposUniversitarios()
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Equipo where TIPO =='UNI'")
			    .list();
		return  s;
		
	}*/
	public List<Equipo> getEquipos()
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Equipo")
			    .list();
		return  s;
		
	}
	public List<Equipo> search(String content)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Equipo where NOMBRE like '%"+content+"%' ") 
			    .list();
		return  s;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	
	public void save(Equipo equipo) {	
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(equipo);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	
	public void delete(int id) {		
		Session session = sessionFactory.getCurrentSession();
		Equipo equipo = (Equipo) session.get(Equipo.class, id);
		session.delete(equipo);
	}
}
