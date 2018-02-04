package org.simonscode.photoshare.graphql;

import graphql.servlet.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.simonscode.photoshare.exceptions.LoginException;
import org.simonscode.photoshare.objects.User;
import org.simonscode.photoshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Transactional
public class UserEndpoint {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GraphQLQuery(name = "listUsers")
    public List<User> listUsers(@GraphQLArgument(name = "offset", defaultValue = "0") int offset,
                                @GraphQLArgument(name = "limit", defaultValue = "10") int limit,
                                @GraphQLRootContext GraphQLContext context) {
        return userRepository.findAll();
    }

    @GraphQLQuery(name = "whoami")
    public User whoami(@GraphQLRootContext graphql.servlet.GraphQLContext context) throws LoginException {
        HttpSession httpSession = context.getRequest().get().getSession();

        Object user = httpSession.getAttribute("user");
        if (user != null && user instanceof User) {
            return (User) user;
        }

        throw new LoginException("Not logged in");
    }


    @GraphQLMutation(name = "createUser")
    public User createUser(@GraphQLArgument(name = "username") String username,
                           @GraphQLArgument(name = "passwordHash") String passwordHash,
                           @GraphQLArgument(name = "firstName") String firstName,
                           @GraphQLArgument(name = "lastName") String lastName,
                           @GraphQLArgument(name = "email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        userRepository.save(user);
        return user;
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

        User user = userRepository.findByUsername(username);
        if (user == null) return null;
        httpSession.setAttribute("user", user);
        return user;
    }
}
