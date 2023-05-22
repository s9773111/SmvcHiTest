package test.isaac.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import test.isaac.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	//Session
	public Session getSession() {
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
	}
	
	//更新密碼
	public Integer updatePwd(String account, String password) {
		System.out.println("開始更新使用者密碼");
		//1 無法
//		String hql = "UPDATE User SET UPWD=:password WHERE UACCT=:account";
//		//Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		Session session = getSession();
//		Query query = session.createQuery(hql);
//		query.setParameter("password", password);
//		query.setParameter("account", account);
//		int result = query.executeUpdate();
//		session.getTransaction().commit();
//		System.out.println("更新完");
		
		//2 可以
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE User SET UPWD=:password WHERE UACCT=:account";
		int result = session.createQuery(hql)
				.setString("account", account)
				.setString("password", password)
				.executeUpdate();
		tx.commit();
		session.close();
		return result;
	}
	
	//新增使用者資料
	public Integer addUser(User user) {
		int result = (Integer) sessionFactory.getCurrentSession().save(user);
		return result;
	}
	
	
	//查詢使用者by account
	public User findUserByAcct(String account) {
		System.out.println("開始查詢使用者 帳號:"+account);
		String sql = "SELECT * FROM USER WHERE UACCT=:account";
		
		@SuppressWarnings("deprecation")
		//Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(User.class);
		Query query = getSession().createSQLQuery(sql).addEntity(User.class);
		query.setParameter("account", account);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		}catch(NoResultException e) {
			user = null;
		}
		return user ;
	}
	
	//查詢使用者by id
	public User getUser(Integer userId) {
		return sessionFactory.getCurrentSession().get(User.class, userId);
	}
	
	//查詢使用者by account+password
	public User findUserByAcctPwd(String account, String password) {
		System.out.println("開始查詢使用者 帳密");
		String sql = "SELECT * FROM USER WHERE UACCT=:account AND UPWD=:password";
		@SuppressWarnings("deprecation")
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(User.class);
		query.setParameter("account", account);
		query.setParameter("password", password);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		}catch(NoResultException e) {
			user = null;
		}
		
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createNativeQuery("SELECT * FROM USER WHERE UACCT=:account AND UPWD=:password",User.class);
//		query.setParameter("account", account);
//		query.setParameter("password", password);
//		Iterator iterator = query.getResultList().iterator();
//		User user = null;
//		while(iterator.hasNext()) {
//			user = (User) iterator.next();
//		}
		return user;
	}
	

}
