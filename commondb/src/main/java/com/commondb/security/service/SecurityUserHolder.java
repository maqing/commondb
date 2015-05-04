package com.commondb.security.service;

import com.commondb.db.bo.User;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

public class SecurityUserHolder
{
  public static User getCurrentUser()
  {
    return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
