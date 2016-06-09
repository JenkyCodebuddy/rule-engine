package jenky.codebuddy.controllers;

import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by joost on 6-6-2016.
 */

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    ShopService shopService;

    public ShopController() {
        setShopService(new ShopService());
    }

    @RequestMapping(method = RequestMethod.GET)
    private Items getAllItems(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getShopService().getAllItems();
        }
        else{
            return new Items("Token not valid");
        }
    }

    @RequestMapping(value = "/buy/{item_id}", method = RequestMethod.GET)
    private Response buyItemForUser(@PathVariable int item_id, @RequestHeader Map<String, String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getShopService().buyItemForUser(headers.get("token"), item_id);
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
