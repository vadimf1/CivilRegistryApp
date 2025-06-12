package eu.senla.regoffice.db.service;

import eu.senla.regoffice.db.dao.AdminDao;
import eu.senla.regoffice.db.dao.ApplicantDao;
import eu.senla.regoffice.db.dao.ApplicationDao;
import eu.senla.regoffice.db.dao.BirthCertificateDao;
import eu.senla.regoffice.db.dao.CitizenDao;
import eu.senla.regoffice.db.dao.DeathCertificateDao;
import eu.senla.regoffice.db.dao.MarriageCertificateDao;
import eu.senla.regoffice.models.Admin;
import eu.senla.regoffice.models.ApplicationType;
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

    public int createApplication(User user) {
        int applicantId = applicantDao.createApplicant(user);
        int citizenId = citizenDao.createCitizen(user);
        return applicationDao.createApplication(user, applicantId, citizenId);
    }

    public String getApplicationStatusById(int applicationId) {
        return applicationDao.getApplicationStatusById(applicationId);
    }

    public boolean applicationExistsById(Integer applicationId) {
        return applicationDao.applicationExistsById(applicationId);
    }

    public int getLastApplicationId() {
        return applicationDao.getLastApplicationId();
    }

    public void deleteApplicationById(int applicationId, ApplicationType applicationType) {
        int[] ids = applicationDao.getRelatedIds(applicationId);
        int citizenId = ids[0];
        int applicantId = ids[1];

        if (applicationType == ApplicationType.BIRTH) {
            birthCertificateDao.deleteBirthCertificateByCitizenId(citizenId);
        } else if (applicationType == ApplicationType.MARRIAGE) {
            marriageCertificateDao.deleteMarriageCertificateByCitizenId(citizenId);
        } else {
            deathCertificateDao.deleteDeathCertificateByCitizenId(citizenId);
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

    public int createAdmin(Admin admin) {
        return adminDao.createAdmin(admin);
    }

    public int getLastAdminId() {
        return adminDao.getLastAdminId();
    }
}
