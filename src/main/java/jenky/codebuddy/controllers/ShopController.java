package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.ShopService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
