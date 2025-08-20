package com.neoxenus.webnovelreader.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_settings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSettings {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    public static UserSettings getDefaultSettings() {
        return new UserSettings();
    }
}
