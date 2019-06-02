package com.beastbikes.android.modules.cycling.club.dto;

import java.io.Serializable;
import java.util.List;

public class ClubActUserList implements Serializable {
    private List<ClubActivityUser> users;

    public List<ClubActivityUser> getUsers() {
        return this.users;
    }

    public void setUsers(List<ClubActivityUser> list) {
        this.users = list;
    }

    public ClubActUserList(List<ClubActivityUser> list) {
        this.users = list;
    }
}
