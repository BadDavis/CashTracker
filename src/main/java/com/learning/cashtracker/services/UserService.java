package com.learning.cashtracker.services;

import com.learning.cashtracker.entity.User;
import com.learning.cashtracker.exceptions.EtAuthException;

public interface UserService {
    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
