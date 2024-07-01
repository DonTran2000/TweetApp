package teikyo.sdl.lec8.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import teikyo.sdl.lec8.entity.Department;
import teikyo.sdl.lec8.entity.Student;
import teikyo.sdl.lec8.entity.Tweet;

public class Dao {
	@PersistenceUnit
	EntityManagerFactory emf;

	@PersistenceContext(unitName = "Lec8App")
	EntityManager manager;

	void createManager() {
		emf = Persistence.createEntityManagerFactory("Lec8App");
		manager = emf.createEntityManager();
	}

	void closeManager() {
		if (manager != null && manager.isOpen()) {
			try {
				manager.close();
				emf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			manager = null;
			emf = null;
		}
	}

	public Student getStudentById(String stid) {
		try {
			createManager();
			Student result = manager.find(Student.class, stid);
			closeManager();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return null;
	}

	public List<Tweet> getTweetList() {
		try {
			createManager();

			Query query = manager.createQuery("from Tweet order by tweettime ASC");
			List<Tweet> result = query.getResultList();

			closeManager();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}

		return null;
	}

	public Student login(String stid, String pass) {
		try {
			createManager();
			Student result = manager.find(Student.class, stid);
			closeManager();
			if (result.getPassword().equals(pass))
				return result;

		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}

		return null;
	}

	public List<Department> getDepartmentList() {
		try {
			createManager();

			Query query = manager.createQuery("from Department");
			List<Department> result = query.getResultList();

			closeManager();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}

		return null;
	}

	public Department getDepartment(int deptcode) {
		try {
			createManager();
			Department result = manager.find(Department.class, deptcode);
			closeManager();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}

		return null;
	}

	public boolean insertTweet(Tweet tweet) {
		try {
			Tweet newTweet = new Tweet();

			newTweet.setContent(tweet.getContent());
			newTweet.setStudent(tweet.getStudent());
			newTweet.setTweettime(new Date());

			createManager();

			manager.getTransaction().begin();
			manager.persist(newTweet);
			manager.getTransaction().commit();

			closeManager();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}

		return false;
	}

	public boolean insertStudent(Student s) {
		try {

			Student newSt = new Student();
			newSt.setStid(s.getStid());
			newSt.setName(s.getName());
			newSt.setDepartment(s.getDepartment());
			newSt.setPassword(s.getPassword());

			createManager();

			manager.getTransaction().begin();
			manager.persist(newSt);
			manager.getTransaction().commit();

			closeManager();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return false;
	}

}
