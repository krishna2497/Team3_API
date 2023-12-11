package com.sdpm.team3.model;

import jakarta.persistence.*;

@Entity
public class ProgressNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goal_id")
    private Integer goalId;

    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "overall_session_quality")
    private String overallSessionQuality;

    @Column(name = "service_provider_performance")
    private String serviceProviderPerformance;

    @Column(name = "meeting_elderly_needs")
    private String meetingElderlyNeeds;

    @Column(name = "communication_responsiveness")
    private String communicationResponsiveness;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getOverallSessionQuality() {
        return overallSessionQuality;
    }

    public void setOverallSessionQuality(String overallSessionQuality) {
        this.overallSessionQuality = overallSessionQuality;
    }

    public String getServiceProviderPerformance() {
        return serviceProviderPerformance;
    }

    public void setServiceProviderPerformance(String serviceProviderPerformance) {
        this.serviceProviderPerformance = serviceProviderPerformance;
    }

    public String getMeetingElderlyNeeds() {
        return meetingElderlyNeeds;
    }

    public void setMeetingElderlyNeeds(String meetingElderlyNeeds) {
        this.meetingElderlyNeeds = meetingElderlyNeeds;
    }

    public String getCommunicationResponsiveness() {
        return communicationResponsiveness;
    }

    public void setCommunicationResponsiveness(String communicationResponsiveness) {
        this.communicationResponsiveness = communicationResponsiveness;
    }




}
