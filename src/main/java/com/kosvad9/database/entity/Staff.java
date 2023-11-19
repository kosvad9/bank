package com.kosvad9.database.entity;

import com.kosvad9.database.enums.RoleStaff;
import com.kosvad9.database.enums.StatusStaff;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Staff extends User {
    @Enumerated(EnumType.STRING)
    private RoleStaff role;

    @Enumerated(EnumType.STRING)
    private StatusStaff status;

    @Builder
    public Staff(Long id, String phoneNumber, String password, String firstName, String lastName, String patronymic, RoleStaff role, StatusStaff status) {
        super(id, phoneNumber, password, firstName, lastName, patronymic);
        this.role = role;
        this.status = status;
    }
}
