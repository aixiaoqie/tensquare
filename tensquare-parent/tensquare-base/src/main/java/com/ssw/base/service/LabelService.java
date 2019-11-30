package com.ssw.base.service;

import com.ssw.base.dao.LabelDao;
import com.ssw.base.pojo.Label;
import com.ssw.util.IdWorker;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }


    public Label findById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void delete(String id) {
        labelDao.deleteById(id);
    }

    /**
     * @desciption 条件查询
     * @author ssw
     * @date 2019/11/30
     * @time 20:20
     */
    public List<Label> search(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，将哪个对象封装进来
             * @param criteriaQuery 封装查询关键字 例如 group by ，order by
             * @param criteriaBuilder  用来封装查询条件 ，直接返回null，表示没有条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> arrayList = new ArrayList<>();
                if (Strings.isNotBlank(label.getLabelname())) {
                    // where labelname like %{label.getLabelname}%
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    arrayList.add(predicate);
                }
                if (Strings.isNotBlank(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState()); //where state = {label.getState}
                    arrayList.add(predicate);
                }

                Predicate[] predicates = new Predicate[arrayList.size()];
                arrayList.toArray(predicates);
                return criteriaBuilder.and(predicates);  //where labelname like %{label.getLabelname}% and tate = {label.getState}
            }
        });
    }

    /**
     * 分页条件查询
     *
     * @param page  页码
     * @param size  每页数据条数
     * @param label 分页查询对象
     * @date 2019/11/30
     * @time 20:20
     */
    public Page<Label> pageQuery(int page, int size, Label label) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> arrayList = new ArrayList<>();
                if (Strings.isNotBlank(label.getLabelname())) {
                    // where labelname like %{label.getLabelname}%
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    arrayList.add(predicate);
                }
                if (Strings.isNotBlank(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState()); //where state = {label.getState}
                    arrayList.add(predicate);
                }

                Predicate[] predicates = new Predicate[arrayList.size()];
                arrayList.toArray(predicates);
                return criteriaBuilder.and(predicates);  //where labelname like %{label.getLabelname}% and tate = {label.getState}
            }
        }, pageable);
    }
}
