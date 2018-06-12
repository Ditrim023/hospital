package com.hospital.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author Sergii Manko
 */
public final class Util {

    public static String getAuthorizedUserName() {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
    public static String getRealDate(String dateFromForm){
        String temp [] = dateFromForm.split("-");
        String myDate = temp[2]+"."+temp[1]+"."+temp[0];
        return myDate;
    }
}
