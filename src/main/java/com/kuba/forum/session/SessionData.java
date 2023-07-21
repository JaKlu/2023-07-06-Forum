package com.kuba.forum.session;

import com.kuba.forum.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@NoArgsConstructor
@Getter
@Setter
@Component
@SessionScope
public class SessionData {
    private User user = null;
    private String lastPath = "/main";
    private String info = null;


    public boolean isLogged() {
        return this.user != null;
    }

    public boolean isAdmin() {
        if (this.user == null) {
            return false;
        }
        return this.user.getFunction() == User.Function.ADMIN;
    }

    public String getInfo() {
        if (this.info == null) {
            return "";
        } else {
            String temp = this.info;
            this.info = null;
            return temp;
        }
    }
}
