package web.services;

import db.DAO.UserDataDao;
import db.POJO.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.SwingUtilities2;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс отвечает за авторизацию spring security
 */
@Service("provider")
public class SpringSecurityAuth implements AuthenticationProvider {
    @Autowired
    private UserDataDao userDataDao;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = passwordHashCode(authentication.getCredentials().toString());

        UserData userData = null;
        try {
            userData = userDataDao.getLoginAndPass(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userData != null) {
            ArrayList list = new ArrayList();
            list.add(new SimpleGrantedAuthority(userData.getRole()));
            if (!userData.getRole().equals("ROLE_USER")) {
                list.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            if (password.matches(userData.getPassword())) {
                System.out.println("зашли");
                return new UsernamePasswordAuthenticationToken(login, password, list);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    /**
     * Шифровка пароля
     *
     * @param password оригинальный пароль
     * @return возвращает строку password.hashCode() + hashCode(50)
     */
    private String passwordHashCode(String password) {
        return String.valueOf((password.hashCode() + new Integer(50).hashCode()));
    }
}
