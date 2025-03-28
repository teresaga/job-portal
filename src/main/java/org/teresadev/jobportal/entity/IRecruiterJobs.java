package org.teresadev.jobportal.entity;

/* Spring will automatically wire the query result columns to the corresponding getter methods based on naming conventions. */
public interface IRecruiterJobs {

    Long getTotalCandidates();

    int getJob_post_id();

    String getJob_title();

    int getLocationId();

    String getCity();

    String getCountry();

    String getState();

    int getCompanyId();

    String getName();
}
