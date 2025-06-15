package eu.senla.regoffice.db.service;

import eu.senla.regoffice.db.dao.AdminDao;
import eu.senla.regoffice.db.dao.ApplicantDao;
import eu.senla.regoffice.db.dao.ApplicationDao;
import eu.senla.regoffice.db.dao.BirthCertificateDao;
import eu.senla.regoffice.db.dao.CitizenDao;
import eu.senla.regoffice.db.dao.DeathCertificateDao;
import eu.senla.regoffice.db.dao.MarriageCertificateDao;
import eu.senla.regoffice.models.Admin;
import eu.senla.regoffice.constants.ApplicationType;
import eu.senla.regoffice.models.ApplicationRelatedIds;
import eu.senla.regoffice.models.User;

import java.sql.Connection;

public class DbService {
    private final ApplicantDao applicantDao;
    private final ApplicationDao applicationDao;
    private final CitizenDao citizenDao;
    private final BirthCertificateDao birthCertificateDao;
    private final MarriageCertificateDao marriageCertificateDao;
    private final DeathCertificateDao deathCertificateDao;
    private final AdminDao adminDao;

    public DbService(Connection connection) {
        applicantDao = new ApplicantDao(connection);
        applicationDao = new ApplicationDao(connection);
        citizenDao = new CitizenDao(connection);
        birthCertificateDao = new BirthCertificateDao(connection);
        marriageCertificateDao = new MarriageCertificateDao(connection);
        deathCertificateDao = new DeathCertificateDao(connection);
        adminDao = new AdminDao(connection);
    }

    public int createApplication(User user, ApplicationType applicationType) {
        int applicantId = applicantDao.createApplicant(user);
        int citizenId = citizenDao.createCitizen(user);

        switch (applicationType) {
            case BIRTH:
                birthCertificateDao.createBirthCertificate(user, citizenId);
                break;
            case MARRIAGE:
                marriageCertificateDao.createMarriageCertificate(user, citizenId);
                break;
            case DEATH:
                deathCertificateDao.createDeathCertificate(user, citizenId);
                break;
        }

        return applicationDao.createApplication(user, applicantId, citizenId);
    }

    public String getApplicationStatusById(int applicationId) {
        return applicationDao.getApplicationStatusById(applicationId);
    }

    public int getApplicationIdByCitizen(User user) {
        int citizenId = citizenDao.getCitizenId(user);
        return applicationDao.getApplicationIdByCitizenId(citizenId);
    }

    public int getApplicationsCount() {
        return applicationDao.getApplicationsCount();
    }

    public boolean applicationExistsById(Integer applicationId) {
        return applicationDao.applicationExistsById(applicationId);
    }

    public void deleteApplicationById(int applicationId, ApplicationType applicationType) {
        ApplicationRelatedIds ids = applicationDao.getRelatedIds(applicationId);
        int citizenId = ids.getCitizenId();
        int applicantId = ids.getApplicantId();

        switch (applicationType) {
            case BIRTH:
                birthCertificateDao.deleteBirthCertificateByCitizenId(citizenId);
                break;
            case MARRIAGE:
                marriageCertificateDao.deleteMarriageCertificateByCitizenId(citizenId);
                break;
            case DEATH:
                deathCertificateDao.deleteDeathCertificateByCitizenId(citizenId);
                break;
        }

        applicationDao.deleteApplicationById(applicationId);
        citizenDao.deleteCitizenById(citizenId);
        applicantDao.deleteApplicantById(applicantId);
    }

    public boolean adminExistsById(int staffId) {
        return adminDao.adminExistsById(staffId);
    }

    public void deleteAdminById(int staffId) {
        adminDao.deleteAdminById(staffId);
    }

    public void deleteAdmin(Admin admin) {
        adminDao.deleteAdmin(admin);
    }

    public int createAdmin(Admin admin) {
        return adminDao.createAdmin(admin);
    }
}
