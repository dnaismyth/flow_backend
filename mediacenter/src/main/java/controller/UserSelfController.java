package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.User;
import entities.RWorkout;

/**
 * Controller used for the current logged in user
 * @author DN
 *
 */
@RestController
@RequestMapping(value="/api/me")
public class UserSelfController {

//	@RequestMapping(method=RequestMethod.GET)
//	public User getMyProfile(@PathVariable Long id) {

		
//	}
}
