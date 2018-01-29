package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.hibernate.Session;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.objects.Tag;

public class TagMutation {

    private Session session = HibernateUtil.openSession();
//
//    @GraphQLMutation(name = "createTag")
//    public Tag createTag() {
//        Tag tag = new Tag();
//        session.save(tag);
//        return tag;
//    }

    @GraphQLMutation(name = "createTag")
    public Tag createTag(@GraphQLArgument(name = "parentId", defaultValue = "-1") int parentId) {
        Tag tag = new Tag();
        if (parentId != -1) {
            tag.setParent(session.get(Tag.class, parentId));
        }
        session.save(tag);
        return tag;
    }
//
//    @GraphQLMutation(name = "createTag")
//    public Tag createTag(@GraphQLArgument(name = "childId") int... childIds) {
//        Tag tag = new Tag();
//        session.save(tag);
//        for (int id : childIds) {
//            Tag child = session.get(Tag.class, id);
//            child.setParent(tag);
//            session.update(child);
//        }
//        return tag;
//    }
}
