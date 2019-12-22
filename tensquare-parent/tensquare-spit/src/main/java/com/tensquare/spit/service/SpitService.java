package com.tensquare.spit.service;

import com.ssw.util.IdWorker;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ssw
 * @desciption 吐槽服务层
 * @date 2019/12/21
 * @time 23:09
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /**
     * @param parentId 父级id
     * @param page     页码
     * @param size     每页显示数据
     * @desciption 根据上级ID查询吐槽数据（分页）
     * @author ssw
     * @date 2019/12/22
     * @time 21:05
     */
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentId, pageable);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @param spitId 吐槽id
     * @desciption 吐槽点赞
     * @author ssw
     * @date 2019/12/22
     * @time 23:35
     */
    public void thumbup(String spitId) {
        //效率低 进行两次数据库交互
//        Spit spit = spitDao.findById(spitId).get();
//        spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) + 1);
//        spitDao.save(spit);
        /*
         *方式二 封装查询条件 进行一次交互
         *使用原生的mongo命令
         * db.spit.update({"_id":"1"},{$inc:{thumbup:NumberInt(1)}})
         */
        //封装的是查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //封装修改的数据内容
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
