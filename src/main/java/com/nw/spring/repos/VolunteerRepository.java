package com.nw.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.spring.models.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

}
