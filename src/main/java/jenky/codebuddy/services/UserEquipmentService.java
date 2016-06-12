package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Equipment;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserEquipmentService {
    /**
     * @param token
     * @return Equipment
     */
    Equipment returnEquipmentFromUser(String token);
}
