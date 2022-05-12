package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, String> {



    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    //The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
    //The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    //The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”
    @Query(value = "SELECT MAX(employee_count) FROM account")
    Integer maxEmployee();
    @Query(value = "SELECT MIN(employee_count) FROM account")
    Integer minEmployee();

    @Query(value = "SELECT AVG(employee_count) FROM account")
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
            "    IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))")
    Integer medianEmployee();

}
