package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Equipment;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.EquipmentService;
import jenky.codebuddy.services.SecurityService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by joost on 2-6-2016.
 */

/**
 * This endpoints return the equipment of the user.
 */
@RestController
@RequestMapping(value = "/security")
public class SecurityController{

    SecurityService securityService;

    public SecurityController() {
        this.securityService = new SecurityService();
    }

    @RequestMapping(method = RequestMethod.POST)
    private Response securityAssignment(@RequestHeader Map<String,String> headers) throws Exception{
        return securityService.checkHash(securityService.hmacSha1(headers.get("file")), headers.get("hash"));
    }
}