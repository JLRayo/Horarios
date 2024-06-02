package com.iesgregorioprieto.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesgregorioprieto.web.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Long>{

}
