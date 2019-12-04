package com.ssw.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ssw.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {
    /**
     * 获取推荐职位
     * @return
     */
    public List<Recruit> getTop6ByStateOrderByCreatetimeDesc(String state); //  where state = ? order by createtime

    /**
     * 获取职位列表 state 0 关闭 1 开启 2 推荐
     * @return
     */
    public List<Recruit> getTop6ByStateNotOrderByCreatetimeDesc(String state);// where state != ? order by createtime
}
