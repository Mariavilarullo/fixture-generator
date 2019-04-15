package ar.edu.uces.progweb2.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.uces.progweb2.springmvc.model.User;

@Transactional(readOnly = true)
@Component
public class UserDao {
	

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User get(int id) {
		Session session = sessionFactory.getCurrentSession();
		User out = (User) session.get(User.class, id);
		// session.close();
		return out;
	}
	public List<User> getUsers()
	{
		Session session = sessionFactory.getCurrentSession();
		List s =  session.createQuery(
			    "from User")
			    .list();
		return  s;
		
	}
	public User validate(String username, String pass) {
		
		Session session = sessionFactory.getCurrentSession();
		User usr = (User) session.createQuery("from User where username = '" + username + "' and pass = '" + pass + "'").uniqueResult();
		
		return usr;
	}

}
