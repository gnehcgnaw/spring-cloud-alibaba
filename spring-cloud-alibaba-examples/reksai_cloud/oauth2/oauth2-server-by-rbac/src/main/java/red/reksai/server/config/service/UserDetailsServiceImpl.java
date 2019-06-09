package red.reksai.server.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import red.reksai.server.daomain.TbPermission;
import red.reksai.server.daomain.TbUser;
import red.reksai.server.service.TbPermissionService;
import red.reksai.server.service.TbUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 12:47
 * @since :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private TbUserService tbUserService ;

    private TbPermissionService tbPermissionService ;

    public UserDetailsServiceImpl(TbUserService tbUserService, TbPermissionService tbPermissionService) {
        this.tbUserService = tbUserService;
        this.tbPermissionService = tbPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = tbUserService.getByUsername(username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (tbUser!=null){
            List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());
            tbPermissions.forEach(tbPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                grantedAuthorityList.add(grantedAuthority);
            });

        }
        return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorityList);
    }
}
