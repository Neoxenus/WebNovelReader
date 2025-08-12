package com.neoxenus.webnovelreader.book.entity;

import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "views")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class View {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Book book;

    @ManyToOne
    User user;

}
