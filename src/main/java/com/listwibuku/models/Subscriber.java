package com.listwibuku.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Subscriber extends BaseModel {
    private int id;

    private Date subscriptionStartTime;

    private Date subscriptionEndTime;

    private String email;

    public Subscriber(int id, Date startTime, Date endTime, String email) {
        this.id = id;
        this.subscriptionStartTime = startTime;
        this.subscriptionEndTime = endTime;
        this.email = email;
    }

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.subscriptionStartTime = rs.getDate("start_time");
        this.subscriptionEndTime = rs.getDate("end_time");
        this.email = rs.getString("email");
    }
}
