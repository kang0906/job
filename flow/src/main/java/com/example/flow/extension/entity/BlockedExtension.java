package com.example.flow.extension.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockedExtension {
    @Id
    @GeneratedValue
    @Column(name = "BLOCKED_EXTENSION_ID")
    private Long id;

    @Column(unique = true, length = 20)
    private String extension;

    public BlockedExtension(String extension){
        this.extension = extension;
    }

}
