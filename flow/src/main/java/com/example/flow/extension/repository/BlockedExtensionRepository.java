package com.example.flow.extension.repository;

import com.example.flow.extension.entity.BlockedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockedExtensionRepository extends JpaRepository<BlockedExtension, Long> {
    Optional<BlockedExtension> findByExtension(String Extension);
}
