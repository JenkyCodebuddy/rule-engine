package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Equipment;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.EquipmentService;
import org.springframework.web.bind.annotation.*;

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

    EquipmentService equipmentService;

    public EquipmentController() {
       this.equipmentService = new EquipmentService();
    }

    /**
     * @param headers Contains the token of the user
     * @return profile or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Equipment getProfile(@RequestHeader Map<String,String> headers) {
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return equipmentService.returnEquipmentFromUser(headers.get("token"));
        }
        else{
            return new Equipment(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/equip", method = RequestMethod.POST)
    private Response equipItems(@RequestHeader Map<String, String> headers, @RequestParam Map<String,String> items){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return equipmentService.equipItemsForUser(headers.get("token"), items);
        }
        else{
            return new Response(400,"Token not valid");
        }
    }
}