package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id = problemid AND labelid = ? ORDER BY createtime DESC",nativeQuery = true)
    public Page<Problem> newList(String lableId, Pageable pageable);
//
//    @Query(value = "",nativeQuery = true)
//    public Page<Problem> hotlist(String lableId, Pageable pageable);
//
//    @Query(value = "",nativeQuery = true)
//    public Page<Problem> waitlist(String lableId, Pageable pageable);
}
