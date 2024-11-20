package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class V1__init1 extends BaseJavaMigration {

    @Override
    public void migrate(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("");

    }
}