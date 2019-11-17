package com.feldmanm.app.ws.userservice;

import com.feldmanm.app.ws.ui.model.request.UserDetailsRequestModel;
import com.feldmanm.app.ws.ui.model.respose.UserRest;

public interface UserSevice {

	UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
