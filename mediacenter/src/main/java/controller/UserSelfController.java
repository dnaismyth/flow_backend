package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller used for the current logged in user
 * @author DN
 *
 */
@RestController
@RequestMapping(value="/api/me")
public class UserSelfController {

}
