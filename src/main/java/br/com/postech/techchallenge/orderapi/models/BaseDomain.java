package br.com.postech.techchallenge.orderapi.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDateTime creationTime;
    protected Boolean isActive;

    @PrePersist
    protected void onCreate() {
        this.creationTime = LocalDateTime.now();
        this.isActive = true;
    }
}
