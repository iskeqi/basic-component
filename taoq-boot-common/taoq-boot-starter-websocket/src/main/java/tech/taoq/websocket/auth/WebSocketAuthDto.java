package tech.taoq.websocket.auth;

/**
 * WebSocketAuthDto
 *
 * @author keqi
 */
public class WebSocketAuthDto {

    private Boolean authenticate;

    private String userIdentifier;

    public Boolean getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Boolean authenticate) {
        this.authenticate = authenticate;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public static WebSocketAuthDto build(Boolean authenticate, String userIdentifier) {
        WebSocketAuthDto dto = new WebSocketAuthDto();
        dto.setAuthenticate(authenticate);
        dto.setUserIdentifier(userIdentifier);
        return dto;
    }
}
