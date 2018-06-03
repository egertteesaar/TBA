package com.example.TBA.social.providers;

import com.example.TBA.model.UserBean;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class FacebookProvider extends BaseProvider {

    private static final Logger log = LoggerFactory.getLogger(FacebookProvider.class);

    private static final String FACEBOOK = "facebook";
    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String REDIRECT_SUCCESS = "redirect:/";

    @Autowired
	Facebook facebook;

	public FacebookProvider(ConnectionRepository connectionRepository, Facebook facebook) {
		super(connectionRepository);
		this.facebook = facebook;
	}

    public UserBean getUserBean() {
		UserBean userBean = new UserBean();
		populateUserBean(userBean);
		return userBean;
	}

	public String login(Model model, UserBean userBean) {
        ConnectionRepository connectionRepository = getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return REDIRECT_LOGIN;
		}
		autoLoginUser(userBean);
		model.addAttribute("loggedInUser",userBean);
		return REDIRECT_SUCCESS;
	}

	protected void populateUserBean(UserBean userForm) {
		User user = facebook.userOperations().getUserProfile();
		log.info(user.getEmail());
		log.info(user.getFirstName());
		userForm.setEmail(user.getEmail());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		userForm.setImage("");
		userForm.setProvider(FACEBOOK);
	}
}
