package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, String> {

    @Query(value = "SELECT MAX(employee_count) FROM account", nativeQuery = true)
    Integer maxEmployee();

    @Query(value = "SELECT MIN(employee_count) FROM account", nativeQuery = true)
    Integer minEmployee();

    @Query(value = "SELECT AVG(employee_count) FROM account", nativeQuery = true)
    Integer avgEmployee();

    @Query(value = "SET @row_index := -1;\n" +
            "\n" +
            "    SELECT AVG(subq.employee_count) as median_value\n" +
            "    FROM (\n" +
            "            SELECT @row_index:=@row_index + 1 AS row_index, employee_count\n" +
            "            FROM account\n" +
            "                    ORDER BY employee_count\n" +
            "    ) AS subq\n" +
            "    WHERE subq.row_index\n" +
            "    IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))", nativeQuery = true)
    Integer medianEmployee();

}
