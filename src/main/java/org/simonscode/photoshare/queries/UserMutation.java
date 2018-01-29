package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.exceptions.LoginException;
import org.simonscode.photoshare.objects.User;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

public class UserMutation {

    @GraphQLMutation(name = "createUser")
    public User createUser(@GraphQLArgument(name = "username") String username,
                           @GraphQLArgument(name = "passwordHash") String passwordHash,
                           @GraphQLArgument(name = "firstName") String firstName,
                           @GraphQLArgument(name = "lastName") String lastName,
                           @GraphQLArgument(name = "email") String email,
                           @GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        System.out.println(context.getRequest().get().getSession().getId());
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        try (Session session = HibernateUtil.openSession()) {
            session.save(user);
            return user;
        }
    }

    @GraphQLMutation(name = "logout")
    public boolean logout(@GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        HttpSession httpSession = context.getRequest().get().getSession();
        if (httpSession.getAttribute("user") == null) return false;
        httpSession.setAttribute("user", null);
        return true;
    }

    @GraphQLMutation(name = "login")
    public User login(@GraphQLArgument(name = "username", defaultValue = "NULL") String username,
                      @GraphQLArgument(name = "passwordHash", defaultValue = "NULL") String passwordHash,
                      @GraphQLRootContext() graphql.servlet.GraphQLContext context) throws LoginException {
        HttpSession httpSession = context.getRequest().get().getSession();

        try (Session session = HibernateUtil.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username and passwordHash = :passwordHash", User.class);
            query.setParameter("username", username);
            query.setParameter("passwordHash", passwordHash);
            User user = query.getSingleResult();
            httpSession.setAttribute("user", user);
            return user;
        } catch (Exception e) {
            if (!(e instanceof NoResultException)){
                e.printStackTrace();
            }
            throw new LoginException("User not found!");
        }
    }
}
