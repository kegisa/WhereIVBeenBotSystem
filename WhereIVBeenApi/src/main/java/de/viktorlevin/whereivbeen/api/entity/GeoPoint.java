package de.viktorlevin.whereivbeen.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GeoPoint {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "bot_user_id")
    private BotUser user;

    @Column
    private String country;

    @Column
    private String city;
}

