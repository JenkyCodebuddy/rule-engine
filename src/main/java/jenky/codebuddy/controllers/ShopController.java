package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This endpoints is used by the user to buy and see items from the shop
 */

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    ShopService shopService;

    public ShopController() {
        setShopService(new ShopService());
    }

    /**
     * @param headers contain the token of the user
     * @return all items of the user of the user or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Items getAllItems(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getShopService().getAllPurchasableItems(headers.get("token"));
        }
        else{
            return new Items(400,"Token not valid");
        }
    }

    /**
     * @param item_id
     * @param headers contains the token of the user
     * @return 200 or 400 with description"
     */
    @RequestMapping(value = "/buy/{item_id}", method = RequestMethod.POST)
    private Response buyItemForUser(@PathVariable int item_id, @RequestHeader Map<String, String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getShopService().buyItemForUser(headers.get("token"), item_id);
        }
        else{
            return new Response(400,"Token not valid");
        }
    }

    private ShopService getShopService() {
        return shopService;
    }

    private void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
