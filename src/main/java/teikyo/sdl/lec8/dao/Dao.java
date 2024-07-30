package teikyo.sdl.lec8.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import teikyo.sdl.lec8.entity.Department;
import teikyo.sdl.lec8.entity.Student;
import teikyo.sdl.lec8.entity.Tweet;
import teikyo.sdl.lec8.entity.VerificationCode;

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

	public Department getDeptById(int deptcode) {
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

	@Transactional
	public boolean insertStudent(Student s) {
		try {

			Student newSt = new Student();
			newSt.setStid(s.getStid());
			newSt.setName(s.getName());
			newSt.setEmail(s.getEmail());
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

	public Student getByIdAndDeptAndEmail(String stid, Department department, String email) {
		try {
			createManager();

			String jpql = "SELECT s FROM Student s WHERE s.stid = :stid AND s.department = :department AND s.email = :email";

			TypedQuery<Student> query = manager.createQuery(jpql, Student.class);
			query.setParameter("stid", stid);
			query.setParameter("department", department);
			query.setParameter("email", email);

			Student st = query.getSingleResult();

			closeManager();
			return st;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return null;
	}

	public VerificationCode getLastVerificationCodeByEmail(String email) {
		try {
			createManager();
			String jpql = "SELECT v FROM VerificationCode v WHERE v.email = :email ORDER BY v.createdAt DESC";
			// Dòng này tạo một chuỗi truy vấn JPQL (Java Persistence Query Language).
			// Truy vấn này chọn tất cả các bản ghi từ bảng VerificationCode mà email trùng khớp với tham số truyền vào,
			// và sắp xếp các kết quả theo trường createdAt theo thứ tự giảm dần.

			TypedQuery<VerificationCode> query = manager.createQuery(jpql, VerificationCode.class);
			query.setParameter("email", email);
			query.setMaxResults(1);
			// Dòng này giới hạn kết quả của truy vấn chỉ lấy 1 bản ghi đầu tiên.
			// Vì truy vấn đã sắp xếp các kết quả theo thứ tự giảm dần của createdAt, 
			// nên bản ghi đầu tiên sẽ là bản ghi có thời gian tạo gần nhất.

			VerificationCode verificationCode = query.getSingleResult();
			closeManager();
			return verificationCode;

		} catch (NoResultException e) {
			// Bắt NoResultException nếu không tìm thấy bản ghi nào
			System.out.println("No entity found for query");
			closeManager();
			return null;
		} catch (Exception e) {
			// Bắt các ngoại lệ khác
			e.printStackTrace();
			closeManager();
			return null;
		}
	}

	public long countVerificationCodesByEmailInLastHour(String email) {
		try {
			createManager();
			String jpql = "SELECT COUNT(v) FROM VerificationCode v WHERE v.email = :email AND v.createdAt >= :time";
			// và có thời gian tạo lớn hơn hoặc bằng thời gian hiện tại trừ đi 1 giờ (3600 giây).
			TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
			query.setParameter("email", email);
			query.setParameter("time", new Date(System.currentTimeMillis() - 3600 * 1000));
			// Dòng này thiết lập giá trị của tham số truy vấn `:time` bằng thời gian hiện tại trừ đi 1 giờ.
			// `System.currentTimeMillis()` trả về thời gian hiện tại tính bằng mili giây từ thời điểm 1/1/1970.
			// `new Date(System.currentTimeMillis() - 3600 * 1000)` tạo ra một đối tượng Date tương ứng với thời gian 1 giờ trước.
			Long counted = query.getSingleResult();
			closeManager();
			return counted;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return 0;
	}

	public boolean saveVerificationCode(VerificationCode verificationCode) {
		try {
			createManager();

			// Bắt đầu giao dịch
			manager.getTransaction().begin();

			// Lưu đối tượng vào cơ sở dữ liệu
			manager.persist(verificationCode);

			// Cam kết giao dịch
			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (manager != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			return false;
		} finally {
			// Đảm bảo EntityManager được đóng
			if (manager != null) {
				closeManager();
			}
		}
	}

	private Date calculateExpiryTime(int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	public VerificationCode getVerificationCodeByEmailAndCode(String email, String code) {
		try {
			createManager();

			String jpql = "SELECT v FROM VerificationCode v WHERE v.email = :email AND v.code = :code";

			TypedQuery<VerificationCode> query = manager.createQuery(jpql, VerificationCode.class);
			query.setParameter("email", email);
			query.setParameter("code", code);

			VerificationCode verificationCode = query.getSingleResult();

			closeManager();
			return verificationCode;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return null;
	}

	public boolean updatePassword(Student st, String pass) {
		try {

			createManager();
			manager.getTransaction().begin();
			Student s = manager.find(Student.class, st.getStid());
			s.setPassword(pass);
			manager.getTransaction().commit();
			closeManager();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
			return false;
		}
	}

	public List<Student> getStudentByDeptcode(Department department) {
		try {
			createManager();
			String jpql = "SELECT s FROM Student s WHERE s.department = :department";

			TypedQuery<Student> query = manager.createQuery(jpql, Student.class);
			query.setParameter("department", department);
			List<Student> students = query.getResultList(); // なぜListを使うかというとNoResultExceptionを避けるために
			closeManager();

			return students;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return null;

	}

	public Student getStudentByEmail(String email) {
		try {
			createManager();
			String jpql = "SELECT s FROM Student s WHERE s.email = :email";

			TypedQuery<Student> query = manager.createQuery(jpql, Student.class);
			query.setParameter("email", email);
			Student student = query.getSingleResult();
			closeManager();

			return student;
		} catch (Exception e) {
			e.printStackTrace();
			closeManager();
		}
		return null;

	}

}
