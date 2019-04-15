package ar.edu.uces.progweb2.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.springmvc.model.Partido;
import ar.edu.uces.progweb2.springmvc.view.form.PartidoForm;

@Transactional(readOnly = true)
@Component
public class PartidoDao {
	

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Partido get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Partido out = (Partido) session.get(Partido.class, id);
		// session.close();
		return out;
	}
	public List<Partido> getPartidos(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Partido where id_cancha ="+id)
			    .list();
		return  s;
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	
	public void save(Partido partido) {	
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(partido);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	
	public void delete(int id) {		
		Session session = sessionFactory.getCurrentSession();
		Partido partido = (Partido) session.get(Partido.class, id);
		session.delete(partido);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	
	public void deleteAll(int id) {		
		Session session = sessionFactory.getCurrentSession();
		
		List s =  session.createQuery(
			    "from Partido where id_cancha ="+id)
			    .list();
		List<Partido> partidos =(List <Partido>) s;
		for(Partido p:partidos)
		{	
			int i= p.getId();
			Partido partido = (Partido) session.get(Partido.class, i);
			session.delete(partido);
		}
		
	}
	public List<Partido> searchString(String content)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Partido as p where p.equipo1.nombre like '%"+content+"%' or (p.equipo2.nombre like '%"+content+"%')  or (p.equipo1.dt like '%"+content+"%') or (p.equipo2.dt like '%"+content+"%')") 
			    .list();
		return  s;
		
	}
	public List<Partido> searchStringSede(String content, int id)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Partido as p where p.equipo1.nombre like '%"+content+"%' or (p.equipo2.nombre like '%"+content+"%')  or (p.equipo1.dt like '%"+content+"%') or (p.equipo2.dt like '%"+content+"%') and p.cancha.sede.id ="+id) 
			    .list();
		return  s;
		
	}
	public List<Partido> searchSede(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Partido as p where p.cancha.sede.id="+id) 
			    .list();
		return  s;
		
	}
		
	public List<Partido> getPartidosEquipo(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from Partido where id_equipo1 ="+id+"or(id_equipo2 ="+id+")")
			    .list();
		return  s;
		
	}

	public List<PartidoForm> getPartidosForm(int id)
	{
		List<Partido> games = getPartidos(id);
		List<PartidoForm> gamesForm = partidosForm(games);
		return gamesForm;
		
	}
	public List<PartidoForm> partidosForm(List<Partido> games)
	{
		List<PartidoForm> gamesForm = new ArrayList<PartidoForm>();
		
		for(Partido game:games)
		{
			PartidoForm p= new PartidoForm();
			p.setId(game.getId());
			p.setCancha(game.getCancha());
			p.setEquipo1(game.getEquipo1());
			p.setEquipo2(game.getEquipo2());
			p.setJugado(game.isJugado());
			p.setOrden(game.getOrden());
			p.setHorario();
			gamesForm.add(p);
		}
		return gamesForm;
	}
}
