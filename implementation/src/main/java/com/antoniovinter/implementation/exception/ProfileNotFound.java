package com.antoniovinter.implementation.exception;

public class ProfileNotFound extends RuntimeException{
    public ProfileNotFound(String profile_not_found) {
        super(profile_not_found);
    }
}
