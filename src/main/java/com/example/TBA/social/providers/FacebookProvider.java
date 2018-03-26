package com.example.TBA.social.providers;

import com.example.TBA.model.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class FacebookProvider  {

	private static final Logger log = LoggerFactory.getLogger(FacebookProvider.class);

	private static final String FACEBOOK = "facebook";
	private static final String REDIRECT_LOGIN = "redirect:/login";

    	@Autowired
    	BaseProvider baseProvider ;
    	

	public String getFacebookUserData(Model model, UserBean userForm) {

		ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return REDIRECT_LOGIN;
		}
		//Populate the Bean
		populateUserDetailsFromFacebook(userForm);
		//Save the details in DB
		baseProvider.saveUserDetails(userForm);
		//Login the User
		baseProvider.autoLoginUser(userForm);
		model.addAttribute("loggedInUser",userForm);
		return "secure/user";
	}

	protected void populateUserDetailsFromFacebook(UserBean userForm) {
		Facebook facebook = baseProvider.getFacebook();
		User user = facebook.userOperations().getUserProfile();
		log.info(user.getEmail());
		log.info(user.getFirstName());
		userForm.setEmail(user.getEmail());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		userForm.setImage("https://i.imgur.com/MDhbuT6.jpg");
		userForm.setProvider(FACEBOOK);
	}

	 

}
