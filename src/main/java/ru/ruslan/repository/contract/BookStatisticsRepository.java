package ru.ruslan.repository.contract;

import ru.ruslan.dto.BookStatistics;

import java.sql.Date;
import java.util.List;

public interface BookStatisticsRepository {
    List<BookStatistics> find(Date date1, Date date2);
}
