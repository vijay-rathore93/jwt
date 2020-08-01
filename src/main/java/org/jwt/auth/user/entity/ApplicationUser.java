package org.jwt.auth.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "applicationUsers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser extends ApplicationAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationUserId;
    private String fullName;
    private String emailId;
    private Long contact;
}
