package com.spring_backend.repository;

import com.spring_backend.domain.Role;
import org.jooq.impl.EnumConverter;

/**
 * Created by daniel on 5/5/17.
 */
public class RoleEnumConverter extends EnumConverter<String, Role> {
        public RoleEnumConverter() {
            super(String.class, Role.class);
        }
}

