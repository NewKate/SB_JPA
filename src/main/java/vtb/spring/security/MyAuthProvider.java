//package vtb.spring.security;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayDeque;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class MyAuthProvider implements AuthenticationProvider {
//    private Map<String,String> users = new HashMap<>();
//
//    @PostConstruct
//    public void init(){
//        users.put("user", "user123");
//        users.put("admin", "admin123");
//    }
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//       String login = authentication.getName();
//       String password = String.valueOf(authentication.getCredentials());
//
//       final String passwordInDB = users.get(login);
//       if(password.equals(passwordInDB)){
//           Collection<SimpleGrantedAuthority> roles = new ArrayDeque<SimpleGrantedAuthority>(){{
//               add(new SimpleGrantedAuthority("ROLE_USER"));
//               add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//           }};
//         return new UsernamePasswordAuthenticationToken(login, password , roles);
//       } else{
//           throw new BadCredentialsException("Incorrect credentials");
//       }
//
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
