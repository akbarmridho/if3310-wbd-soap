package com.listwibuku.repository;

import com.listwibuku.database.DatabaseInstance;
import com.listwibuku.database.DatabaseInstanceInterface;
import com.listwibuku.models.ApiKey;
import com.listwibuku.utils.Hash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApiKeyRepository {
    private DatabaseInstanceInterface database;
    private String tableName = "api_key";
    private static ApiKeyRepository instance;

    protected ApiKeyRepository(DatabaseInstanceInterface db) {
        this.database = db;
    }

    public static ApiKeyRepository getInstance() {
        if (instance == null) {
            instance = new ApiKeyRepository(
                    DatabaseInstance.getInstance()
            );
        }
        return instance;
    }

    public ApiKey findByKey(String key) throws SQLException {
        ApiKey result = null;

        String hashed = Hash.sha256(key);

        PreparedStatement stmt = this.database.getConnection().prepareStatement("SELECT * FROM " + this.tableName + " WHERE secret = ? LIMIT 1");
        stmt.setString(1, hashed);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ApiKey apiKey = new ApiKey();
            apiKey.constructFromSQL(rs);
            result = apiKey;
        }
        
        return result;
    }
}