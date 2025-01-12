package com.ecommerce.Amazon.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
public class SpringSessionAttribute {

    @Id
    @Column(name = "SESSION_PRIMARY_ID", nullable = false)
    private String sessionPrimaryId;

    @Id
    @Column(name = "ATTRIBUTE_NAME", nullable = false)
    private String attributeName;

    @Column(name = "ATTRIBUTE_BYTES", nullable = false)
    private byte[] attributeBytes;

    @ManyToOne
    @JoinColumn(name = "SESSION_PRIMARY_ID", referencedColumnName = "PRIMARY_ID", insertable = false, updatable = false)
    private SpringSession springSession;

	public String getSessionPrimaryId() {
		return sessionPrimaryId;
	}

	public void setSessionPrimaryId(String sessionPrimaryId) {
		this.sessionPrimaryId = sessionPrimaryId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public byte[] getAttributeBytes() {
		return attributeBytes;
	}

	public void setAttributeBytes(byte[] attributeBytes) {
		this.attributeBytes = attributeBytes;
	}

	public SpringSession getSpringSession() {
		return springSession;
	}

	public void setSpringSession(SpringSession springSession) {
		this.springSession = springSession;
	}

    // Getters and setters
    
}
