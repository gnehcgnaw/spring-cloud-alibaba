package red.reksai.account2service.error.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 17:03
 * @since :
 */
@Service
public class Account2Servcie {

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Transactional
    public boolean update() {
        int count = jdbcTemplate.update("UPDATE account2 SET count= count + 10 WHERE id =1");
        if (count > 0){
            return true ;
        }else {
            return false ;
        }

    }
}
