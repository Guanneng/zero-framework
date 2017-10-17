/*
 * Copyright 2016 Robert Li.
 * Released under the MIT license
 * https://opensource.org/licenses/MIT
 */
package robertli.zero.dao.impl;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import robertli.zero.dao.UserPasswordResetTokenDao;
import robertli.zero.entity.UserPasswordResetToken;

/**
 *
 * @author Robert Li
 */
@Component("userPasswordResetTokenDao")
public class UserPasswordResetTokenDaoImpl extends GenericHibernateDao<UserPasswordResetToken, String> implements UserPasswordResetTokenDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void clear(int lifeMinute) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -lifeMinute);
        Date endDate = cal.getTime();
        Session session = sessionFactory.getCurrentSession();
        TypedQuery query = session.createQuery("delete from UserPasswordResetToken where createDate<=:endDate");
        query.setParameter("endDate", endDate);
        query.executeUpdate();
    }

}
