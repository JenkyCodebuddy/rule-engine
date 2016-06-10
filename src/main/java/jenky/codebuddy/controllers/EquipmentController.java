package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Equipment;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.EquipmentService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by joost on 2-6-2016.
 */

@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {

    EquipmentService equipmentService;

    public EquipmentController() {
       setEquipmentService(new EquipmentService());
    }

    @RequestMapping(method = RequestMethod.GET)
    private Equipment getProfile(@RequestHeader Map<String,String> headers) {
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getEquipmentService().returnEquipmentFromUser(headers.get("token"));
        }
        else{
            return new Equipment(400,"Token not valid");
        }
    }

    public EquipmentService getEquipmentService() {
        return equipmentService;
    }

    public void setEquipmentService(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
}