package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Equipment;
import jenky.codebuddy.services.UserAuthenticationService;
import jenky.codebuddy.services.UserEquipmentService;
import jenky.codebuddy.services.UserEquipmentServiceImpl;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by joost on 2-6-2016.
 */

/**
 * This endpoints return the equipment of the user.
 */
@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {

    UserEquipmentService userEquipmentServiceImpl;

    public EquipmentController() {
       setUserEquipmentServiceImpl(new UserEquipmentServiceImpl());
    }

    /**
     * @param headers Contains the token of the user
     * @return profile or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Equipment getProfile(@RequestHeader Map<String,String> headers) {
        if(UserAuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getUserEquipmentServiceImpl().returnEquipmentFromUser(headers.get("token"));
        }
        else{
            return new Equipment(400,"Token not valid");
        }
    }

    public UserEquipmentService getUserEquipmentServiceImpl() {
        return userEquipmentServiceImpl;
    }

    public void setUserEquipmentServiceImpl(UserEquipmentService userEquipmentServiceImpl) {
        this.userEquipmentServiceImpl = userEquipmentServiceImpl;
    }
}