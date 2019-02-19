package com.socket.netty.hello_05_MessagePackga.hello_02;

import lombok.Data;
import org.msgpack.annotation.Message;

@Message
@Data
class UserInfo {
    private Integer userId;
    private String userName;

    public UserInfo() {
    }

    public UserInfo(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}