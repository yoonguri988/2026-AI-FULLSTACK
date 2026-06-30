package com.the703.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.the703.dto.AppUserAuthDto;
import com.the703.dto.AppUserDto;

import lombok.Getter;

@Getter   
public class CustomUserDetails implements UserDetails,OAuth2User{// UserDetails(security), oauth2-client
   
   private static final long serialVersionUID = 1L;
   private AppUserDto user;
   private AppUserAuthDto authDto;
   private Map<String,Object> attributes = new HashMap<>();
   ////////////////////////////////////////////////1.일반 로그인
   public CustomUserDetails(AppUserDto user, AppUserAuthDto authDto) {
         super();
         this.user = user;
         this.authDto = authDto;
         this.attributes.put("email"    , user.getEmail());
         this.attributes.put("provider" , user.getProvider());
   }

     @Override
      public Collection<? extends GrantedAuthority> getAuthorities() { 
         if( authDto ==null ||   authDto.getAuthList()  == null ||  authDto.getAuthList().isEmpty() ) {
             return List.of( new SimpleGrantedAuthority("ROLE_MEMBER") );
          }  // 권한없으면 ROLE_MEMBER 
      
         return authDto.getAuthList().stream()
                 .filter( a->a.getAuth() != null  &&  !a.getAuth().isBlank() )
                 .map(    a-> new SimpleGrantedAuthority(a.getAuth()))
                 .collect(Collectors.toList());
        }
   @Override public String getPassword() {  return user.getPassword(); }

   @Override public String getUsername() {  return user.getEmail() + ":" +  user.getProvider(); }
   
   public Integer getAppUserId() {return user.getAppUserId();}
   public String getEmail() {return user.getEmail();}
   public String getProvider() {return user.getProvider();}
 
   /////////////////////////// social
   
   public CustomUserDetails(AppUserDto user, Map<String, Object> attributes) {
      super();
      this.user = user;
      this.authDto = new AppUserAuthDto();
      this.attributes = new HashMap<>(attributes!=null? attributes : Map.of());
      this.attributes.put("email", user.getEmail());
      this.attributes.put("provider", user.getProvider());
   }
   
   @Override public Map<String,Object>getAttributes() {return attributes;}
           public void setattributes(Map<String,Object>attributes) {this.attributes=attributes;}
           
   @Override public String getName() {  return user.getEmail()+":"+user.getProvider(); }

}
