package com.eevan.bankingservice.security;


import com.eevan.bankingservice.entities.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ClientDetails implements UserDetails {

    private final Client client;

    public ClientDetails(Client client) {
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.client.getPassword();
    }

    @Override
    public String getUsername() {
        return this.client.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //for getting data of authenticated user
    public Client getClient() {
        return this.client;
    }
}
