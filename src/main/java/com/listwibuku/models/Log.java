package com.listwibuku.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log extends BaseModel {
    private int id;
    private String description;
    private String ip;
    private String endpoint;

    private String client;
    private Date requestedAt;

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.description = rs.getString("description");
        this.ip = rs.getString("ip");
        this.endpoint = rs.getString("endpoint");
        this.client = rs.getString("client");
        this.requestedAt = rs.getDate("requestedAt");
    }
}
