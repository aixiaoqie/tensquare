package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit, String> {
    /**
     * 根据上级ID查询吐槽数据（分页）
     *
     * @param parentid 父级id
     * @param pageable 分页组件
     * @return
     */

    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
