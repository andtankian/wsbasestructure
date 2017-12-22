package br.com.wsbasestructure.domain.impl;

import br.com.wsbasestructure.domain.abstracts.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Andrew Ribeiro
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"login"}, name = "duplicate_login")
    ,@UniqueConstraint(columnNames = {"email"}, name = "duplicate_email")})
public abstract class User extends Entity {

    private String login;
    private String email;
    private String password;
    private String fullName;
    private String type;
    private UserConfig userConfig;
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(length = 500)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UserConfig.class)
    public UserConfig getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

}
