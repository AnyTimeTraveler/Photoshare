package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.exceptions.LoginException;
import org.simonscode.photoshare.objects.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserQuery {

    private static Session session = HibernateUtil.openSession();

    @GraphQLQuery(name = "listUsers")
    public List<User> listUsers(@GraphQLArgument(name = "offset", defaultValue = "0") int offset,
                                @GraphQLArgument(name = "limit", defaultValue = "10") int limit,
                                @GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        System.out.println(context.getRequest().get().getSession().getId());
        Query<User> query = session.createQuery("from User", User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @GraphQLQuery(name = "whoami")
    public User whoami(@GraphQLRootContext() graphql.servlet.GraphQLContext context) throws LoginException {
        HttpSession httpSession = context.getRequest().get().getSession();

        Object user = httpSession.getAttribute("user");
        if (user != null && user instanceof User){
            return (User) user;
        }

        throw new LoginException("Not logged in");
    }
}
