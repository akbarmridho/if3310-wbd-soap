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

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.subscriptionStartTime = rs.getDate("start_date");
        this.subscriptionEndTime = rs.getDate("end_date");
        this.email = rs.getString("email");
    }
}
