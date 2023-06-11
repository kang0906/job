package com.example.flow.extension.repository;

import com.example.flow.extension.entity.BlockedExtension;
import com.example.flow.extension.entity.DefaultBlockedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DefaultBlockedExtensionRepository  extends JpaRepository<DefaultBlockedExtension, Long> {
    Optional<DefaultBlockedExtension> findByExtension(String Extension);

}
