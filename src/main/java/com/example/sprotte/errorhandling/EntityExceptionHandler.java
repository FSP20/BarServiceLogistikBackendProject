package com.example.sprotte.errorhandling;

import com.example.sprotte.constants.EntityEnums;

public abstract class EntityExceptionHandler {

    public void notFoundException(EntityEnums entity){};

    public void illegalException(EntityEnums entity){};

    public void objectAlreadyExistException(EntityEnums entity){};
}
