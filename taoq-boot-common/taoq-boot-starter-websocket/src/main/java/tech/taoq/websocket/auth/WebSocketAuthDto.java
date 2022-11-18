package tech.taoq.websocket.auth;

/**
 * WebSocketAuthDto
 *
 * @author keqi
 */
public class WebSocketAuthDto {

    /**
     * 是否认证成功[true:成功 false:失败]
     */
    private Boolean authenticate;

    /**
     * 用户唯一标识符
     */
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
