package com.elksystems.rest.user.ui;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elksystems.rest.user.repository.model.User;
import com.elksystems.rest.user.service.IUserService;
import com.elksystems.rest.user.ui.error.UserAlreadyExistException;
import com.elksystems.rest.user.ui.model.UserDto;
import com.elksystems.rest.user.ui.util.GenericResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messages;

	@Autowired
	private Environment environment;

	public UserController() {
		super();
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse registerUserAccount(@RequestBody @Valid UserDto accountDto, HttpServletRequest request) {
		LOGGER.debug("Registering user account with information: {}", accountDto);
		final User registeredUser = userService.registerNewUserAccount(accountDto);
		if (registeredUser == null) {
			throw new UserAlreadyExistException();
		}

		mailSender.send(constructRegistrationSuccessEmail(getAppUrl(request), request.getLocale(), registeredUser));
		// String appUrl = "http://" + request.getServerName() + ":" +
		// request.getServerPort() + request.getContextPath();
		return new GenericResponse("success");
	}

	// ============== NON-API ============

	private SimpleMailMessage constructRegistrationSuccessEmail(final String contextPath, final Locale locale,
			final User user) {
		final String message = "You registered successfully. We will send you a confirmation message to your email account." ; //messages.getMessage("message.registrationSuccess", null, locale);
		return constructEmail("Registration success", message, user);
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom(environment.getProperty("support.email"));
		return email;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
