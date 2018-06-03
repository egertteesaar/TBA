package com.example.TBA.social.providers;

import com.example.TBA.model.UserBean;
import com.example.TBA.repository.UserRepository;
import com.example.TBA.security.Autologin;
import com.example.TBA.service.EmailServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.ui.Model;

@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public abstract class BaseProvider {

    private ConnectionRepository connectionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected Autologin autologin;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

    public BaseProvider(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;

    }

    public void saveUserDetails(UserBean userBean) {
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }
//        if (userRepository.findByEmail(userBean.getEmail()) == null)
//            emailServiceImpl.sendSimpleMessage(userBean.getEmail(), "Login to MidaKanda ", "You just logged in to MidaKanda!");
        userRepository.save(userBean);
    }

    protected void autoLoginUser(UserBean userBean) {
        autologin.setSecuritycontext(userBean);
    }

    protected ConnectionRepository getConnectionRepository() {
        return connectionRepository;
    }

    public abstract UserBean getUserBean();

    protected abstract void populateUserBean(UserBean userBean);

    public abstract String login(Model model, UserBean userBean);

}
