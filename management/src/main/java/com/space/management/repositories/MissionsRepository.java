package com.space.management.repositories;

import com.space.management.model.ImageryType;
import com.space.management.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionsRepository extends JpaRepository<Mission, Long> {

    List<Mission> findAllByImageryType(ImageryType imageryType);

}
