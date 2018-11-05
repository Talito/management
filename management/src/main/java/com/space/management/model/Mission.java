package com.space.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "missions")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "missionName", nullable = false)
    private String missionName;
    @Column(nullable = false)
    private ImageryType imageryType;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "missionName", referencedColumnName = "missionName")
    List<Product> products;
    private LocalDateTime missionStartDate;
    private LocalDateTime missionFinishDate;
}
