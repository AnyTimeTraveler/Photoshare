package org.simonscode.photoshare.endpoints.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.simonscode.photoshare.entities.User;
import org.simonscode.photoshare.repositories.OffsetLimitPagable;
import org.simonscode.photoshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserEndpoint {

    private final UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GraphQLQuery(name = "listUsers")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> listUsers(@GraphQLArgument(name = "offset", defaultValue = "0") int offset,
                                @GraphQLArgument(name = "limit", defaultValue = "10") int limit) {
        return userRepository.findAll(new OffsetLimitPagable(offset, limit, Sort.by("id"))).getContent();
    }

    @GraphQLQuery(name = "whoami")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User whoami(@GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        Optional<HttpServletRequest> request = context.getRequest();
        //noinspection OptionalIsPresent
        if (request.isPresent()) {
            return (User) request.get().getSession(true).getAttribute("user");
        } else {
            return null;
        }
    }

    @GraphQLMutation(name = "createUser")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean logout(@GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        context.getRequest().ifPresent(request -> request.getSession().invalidate());
        return true;
    }

    @GraphQLMutation(name = "login")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User login(@GraphQLArgument(name = "username", defaultValue = "NULL") String username,
                      @GraphQLArgument(name = "passwordHash", defaultValue = "NULL") String passwordHash,
                      @GraphQLRootContext() graphql.servlet.GraphQLContext context) {
        Optional<HttpServletRequest> httpRequest = context.getRequest();

        if (httpRequest.isPresent()) {
            User user = userRepository.findUserByUsernameAndPasswordHash(username, passwordHash);
            if (user == null) return null;
            httpRequest.get().getSession(true).setAttribute("user", user);
            return user;
        }
        return null;
    }
}
