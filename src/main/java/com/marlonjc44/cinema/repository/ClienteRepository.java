package com.marlonjc44.cinema.repository;

import com.marlonjc44.cinema.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
