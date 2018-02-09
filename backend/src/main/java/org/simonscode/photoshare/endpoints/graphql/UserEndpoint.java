package org.simonscode.photoshare.endpoints.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.simonscode.photoshare.entities.User;
import org.simonscode.photoshare.exceptions.LoginException;
import org.simonscode.photoshare.repositories.OffsetLimitPagable;
import org.simonscode.photoshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class UserEndpoint {

    private final UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GraphQLQuery(name = "listUsers")
    public List<User> listUsers(@GraphQLArgument(name = "offset", defaultValue = "0") int offset,
                                @GraphQLArgument(name = "limit", defaultValue = "10") int limit) {
        return userRepository.findAll(new OffsetLimitPagable(offset, limit, new Sort("id"))).getContent();
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
        user.setLastOnline(new Date());

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
                      @GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        HttpSession httpSession = context.getRequest().get().getSession();

        User user = userRepository.findUserByUsernameAndPasswordHash(username, passwordHash);
        if (user == null) return null;
        httpSession.setAttribute("user", user);
        return user;
    }
}
