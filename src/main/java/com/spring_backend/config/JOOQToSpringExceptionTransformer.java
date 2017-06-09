package com.spring_backend.config;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

/**
 * Created by daniel on 5/5/17.
 */
public class JOOQToSpringExceptionTransformer extends DefaultExecuteListener {
    @Override
    public void exception(ExecuteContext ctx) {
        if(ctx.sqlException() == null) return;

        SQLDialect dialect = ctx.configuration().dialect();
        SQLExceptionTranslator translator = (dialect != null)
                ? new SQLErrorCodeSQLExceptionTranslator(dialect.name())
                : new SQLStateSQLExceptionTranslator();

        ctx.exception(translator.translate("JOOQ", ctx.sql(), ctx.sqlException()));
    }
}
