package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ContactUs;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface  ContactUsRepository extends JpaRepository<ContactUs,Long> {
}
