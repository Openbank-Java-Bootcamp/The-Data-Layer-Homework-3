package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, String> {



    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    //The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
    //The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    //The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”
}
