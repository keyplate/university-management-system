package com.ums.model.response;

import com.ums.model.entity.dean.Dean;

public class SaveDeanResponse extends AccountResponse{

    public static SaveDeanResponse deanResponse(Dean dean) {
        SaveDeanResponse responseObject = new SaveDeanResponse();
        responseObject.id = dean.getId();
        responseObject.email = dean.getEmail();
        responseObject.firstName = dean.getFirstName();
        responseObject.lastName = dean.getLastName();
        responseObject.status = dean.getStatus();
        return responseObject;
    }
}
