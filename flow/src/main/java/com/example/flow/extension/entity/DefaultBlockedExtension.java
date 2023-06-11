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
public class DefaultBlockedExtension {

    @Id @GeneratedValue
    @Column(name = "DEFAULT_BLOCKED_EXTENSION_ID")
    private Long id;

    @Column(unique = true, length = 20)
    private String extension;
    private Boolean isChecked;

    public boolean changeStatus(){
        if(isChecked){
            isChecked = false;
        }else{
            isChecked = true;
        }
        return isChecked;
    }

    public DefaultBlockedExtension(String extension){
        this.extension = extension;
        this.isChecked = false;
    }

}
