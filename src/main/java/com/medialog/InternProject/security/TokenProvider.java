package com.medialog.InternProject.security;

public interface TokenProvider {
    String createToken(String subject);
    String getSubject(String token);
    boolean validateToken(String token);

<<<<<<< HEAD
}
=======
}
>>>>>>> 450c47b22b45e68d59e6d7e6bbc2baaffc6558c3
