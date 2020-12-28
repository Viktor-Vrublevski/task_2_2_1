package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(String model, int series) {
        Session session = sessionFactory.openSession();

        String hql = "FROM Car where model = :car_model AND series = :car_series";

        Query query = session.createQuery(hql).setParameter("car_model", model)
                .setParameter("car_series", series);
        Car car = (Car) query.getSingleResult();

        Query query1 = sessionFactory.openSession()
                .createQuery("From User where car = :car_id").setParameter("car_id", car);
        return (User) query1.getSingleResult();
    }

}
