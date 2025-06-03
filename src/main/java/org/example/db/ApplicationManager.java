package org.example.db;

import org.example.models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationManager {
    Connection connection;

    public ApplicationManager() {
        connection = ConnectionHolder.getConnection();
    }

    public int createApplication(User user) {
        try {
            int citizenId = insertCitizen(user);
            int applicantId = insertApplicant(user);
            return insertApplication(user, citizenId, applicantId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean applicationExistsById(int applicationId) {
        String sql = "SELECT 1 FROM applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicationId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check application existence with id=" + applicationId, e);
        }
    }

    public String getApplicationStatusById(int applicationId) {
        String sql = "SELECT statusofapplication FROM applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("statusofapplication");
            } else {
                throw new SQLException("No application found with id = " + applicationId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private int insertCitizen(User user) throws SQLException {
        String sql = """
                    INSERT INTO citizens (surname, name, middlename, passportnumber, dateofbirth, gender, registration_address, image)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING citizenid
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getCitizenLastName());
            stmt.setString(2, user.getCitizenFirstName());
            stmt.setString(3, user.getCitizenMiddleName());
            stmt.setString(4, user.getCitizenPassportNumber());
            stmt.setDate(5, Date.valueOf(user.getCitizenBirthDate()));
            stmt.setString(6, user.getCitizenGender());
            stmt.setString(7, user.getCitizenAddress());
            stmt.setString(8, null);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("citizenid");
            } else {
                throw new SQLException("Failed to insert citizen");
            }
        }
    }

    private int insertApplicant(User user) throws SQLException {
        String sql = """
                    INSERT INTO applicants (surname, name, middlename, passportnumber, phonenumber, registration_address)
                    VALUES (?, ?, ?, ?, ?, ?) RETURNING applicantid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getApplicantLastName());
            stmt.setString(2, user.getApplicantFirstName());
            stmt.setString(3, user.getApplicantMiddleName());
            stmt.setString(4, user.getApplicantPassportNumber());
            stmt.setString(5, user.getApplicantPhoneNumber());
            stmt.setString(6, user.getApplicantAddress());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("applicantid");
            } else {
                throw new SQLException("Failed to insert applicant");
            }
        }
    }

    private int insertApplication(User user, int citizenId, int applicantId) throws SQLException {
        String sql = """
                    INSERT INTO applications (citizenid, applicantid, staffid, dateofapplication, kindofapplication, statusofapplication, channel)
                    VALUES (?, ?, NULL, CURRENT_TIMESTAMP, ?, ?, NULL) RETURNING applicationid
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citizenId);
            stmt.setInt(2, applicantId);
            stmt.setString(3, user.getMode());
            stmt.setString(4, "created");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("applicationid");
            } else {
                throw new SQLException("Failed to insert application");
            }
        }
    }
}
