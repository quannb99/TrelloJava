package cnweb.n10.trello.service;

import cnweb.n10.trello.model.User;
import cnweb.n10.trello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /*
        Tạo đối tượng User từ dữ liệu nhập vào.
    */
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUSERNAME(userName);
        if(user == null){
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        String role = "USER";
        GrantedAuthority auth = new SimpleGrantedAuthority(role);
        grantList.add(auth);
        System.out.println("Found User: " + userName);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUSERNAME(), user.getPASSWORD(),grantList);
        return userDetails;
    }
}
