package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {
    Lessor findLessorById(Integer id);
    Lessor findLessorByCompanyName(String name);
}
