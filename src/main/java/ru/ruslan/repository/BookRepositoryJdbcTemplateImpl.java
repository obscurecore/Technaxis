package ru.ruslan.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.ruslan.dto.BookStatistics;

import java.sql.Date;
import java.util.List;

@Repository
public class BookRepositoryJdbcTemplateImpl {
    //language=SQL
    private static final String SQL_SELECT_GROUP_In_THE_INTERVAL = "SELECT title, count(*) as Count FROM book WHERE read_already = true and date BETWEEN SYMMETRIC ? and ?";

    private JdbcTemplate jdbcTemplate;

    public BookRepositoryJdbcTemplateImpl(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private RowMapper<BookStatistics> courseRowMapper = (row, rowNumber) ->
            BookStatistics.builder()
                    .id(row.getLong("id"))
                    .title(row.getString("title"))
                    .count(row.getInt("count"))
                    .build();


    public List<BookStatistics> find(Date date1, Date date2) {
        return jdbcTemplate.query(SQL_SELECT_GROUP_In_THE_INTERVAL, new Object[]{date1, date2}, courseRowMapper);

    }

}
