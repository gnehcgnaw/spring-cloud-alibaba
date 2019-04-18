package red.reksai.account1service.error.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 20:20
 * @since : 1.0
 */
@SuppressWarnings("all")
@Service
public class Account1Service {
    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    private RestTemplate restTemplate ;

    @Transactional
    public boolean update() {
        int update = jdbcTemplate.update("UPDATE account1 SET count= count - 10 WHERE id =1");

        if (update > 0){
            Boolean body = restTemplate.getForEntity("http://127.0.0.1:28082/account2/add", Boolean.class).getBody();
            if (body){
                int i = 100 /0 ;
                return true ;
            }else {
                int i = 100 /0 ;
                return false ;
            }
        }else {
            int i = 100 /0 ;
            return false ;
        }

    }
}
