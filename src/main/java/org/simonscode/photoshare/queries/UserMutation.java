package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.hibernate.Session;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.objects.User;

public class UserMutation {

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

        try (Session session = HibernateUtil.openSession()) {
            session.save(user);
            return user;
        }
    }
}
