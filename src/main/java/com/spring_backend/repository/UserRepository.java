package com.spring_backend.repository;

import com.spring_backend.domain.User;
import generated.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.spring_backend.domain.Role.USER;
import static generated.Sequences.USER_ID;
import static generated.Tables.USERS;


/**
 * Created by sethur on 1/10/2016.
 */
@Repository
@Transactional
public class UserRepository {

    @Autowired
    private DSLContext dsl;

    public boolean createUser(User user){
        return dsl.insertInto(USERS)
                .columns(USERS.ID, USERS.EMAILID, USERS.PASSWORD_HASH, USERS.NAME, USERS.ROLE)
                .values(dsl.nextval(USER_ID),
                        user.getEmailid(),
                        user.getPassword(),
                        user.getName(),
                        USER.name())
                .execute() == 1; //One row inserted correctly
    }

    public boolean updateUser(User user) {
        return dsl.update(USERS)
                .set(USERS.EMAILID, user.getEmailid())
                .set(USERS.PASSWORD_HASH, user.getPassword())
                .set(USERS.NAME, user.getName())
                .where(USERS.ID.eq(user.getId())).execute() == 1;
    }


    public boolean deleteUser(long id) {
        return dsl.delete(USERS)
                .where(USERS.ID.eq(id)).execute() == 1;
    }

    public Optional<User> getUserByName(String name){
        try {
            UsersRecord user =
                    dsl.select()
                            .from(USERS)
                            .where(USERS.NAME.eq(name))
                            .fetchOne()
                            .into(UsersRecord.class);

            return Optional.of(new User(user));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> getUser(long id){
        try {
            UsersRecord user =
                    dsl.select()
                            .from(USERS)
                            .where(USERS.ID.eq(id))
                            .fetchOne()
                            .into(UsersRecord.class);

            return Optional.of(new User(user));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}