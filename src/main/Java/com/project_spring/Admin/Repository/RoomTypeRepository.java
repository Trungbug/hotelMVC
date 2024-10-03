package com.project_spring.Admin.Repository;

import com.project_spring.Admin.Model.RoomType;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer>, CrudRepository<RoomType, Integer> {

}
