package com.example.diarywebapplication.security;

import com.example.diarywebapplication.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() { // ここはパスワードを返すのでハッシュ済の値が返ってきても問題ない
        return user.getHashedPw();
    }

    @Override
    public String getUsername() { // ここは認証に使う識別子を返すのが正解、そのため今回はmailを返す形が適切
        return user.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public String getDisplayName() {
        return user.getName();
    }

    public long getUserId() {
        return user.getId();
    }
}
