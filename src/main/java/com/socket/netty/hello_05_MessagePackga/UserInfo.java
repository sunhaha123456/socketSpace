package com.socket.netty.hello_05_MessagePackga;

import lombok.Data;
import org.msgpack.annotation.Message;

import java.io.Serializable;

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