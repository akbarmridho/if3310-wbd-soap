package com.listwibuku.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiKey extends BaseModel {
    private String secret;
    private String client;

    @Override
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.secret = rs.getString("secret");
        this.client = rs.getString("client");
    }
}