package com.ums.model.response;

import com.ums.model.entity.dean.Dean;

public class DeanResponse extends AccountResponse{

    public static DeanResponse deanResponse(Dean dean) {
        DeanResponse responseObject = new DeanResponse();
        responseObject.id = dean.getId();
        responseObject.email = dean.getEmail();
        responseObject.firstName = dean.getFirstName();
        responseObject.lastName = dean.getLastName();
        responseObject.status = dean.getStatus();
        return responseObject;
    }
}
