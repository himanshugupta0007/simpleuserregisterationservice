package com.userregisteration.modal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    private String name;

    public Role() {
        super();
    }

    public Role(final String name) {
        super();
        this.name = name;
    }

    
    /** 
     * @return Long
     */
    //

    public Long getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    
    /** 
     * @return Collection<User>
     */
    public Collection<User> getUsers() {
        return users;
    }

    
    /** 
     * @param users
     */
    public void setUsers(final Collection<User> users) {
        this.users = users;
    }

    
    /** 
     * @return Collection<Privilege>
     */
    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    
    /** 
     * @param privileges
     */
    public void setPrivileges(final Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role role = (Role) obj;
        if (!getName().equals(role.getName())) {
            return false;
        }
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
}