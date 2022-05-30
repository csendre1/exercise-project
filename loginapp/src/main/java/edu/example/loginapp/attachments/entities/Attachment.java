package edu.example.loginapp.attachments.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.example.loginapp.autentication.entities.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "attachments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
    @JsonIgnore
    private AuthUser user;
}
