package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.objects.User;

import java.util.List;

public class UserQuery {

    private static Session session = HibernateUtil.openSession();

    @GraphQLQuery(name = "listUsers")
    public List<User> listUsers() {
        Query<User> query = session.createQuery("from User ", User.class);
        return query.list();
    }
}
