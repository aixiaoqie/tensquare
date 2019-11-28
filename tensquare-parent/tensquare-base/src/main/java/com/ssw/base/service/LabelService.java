package com.ssw.base.service;

import com.ssw.base.dao.LabelDao;
import com.ssw.base.pojo.Label;
import com.ssw.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;
    public List<Label> findAll() {
        return  labelDao.findAll();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }


    public Label findById(String labelId ) {
        return labelDao.findById(labelId).get();
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void delete(String id) {
        labelDao.deleteById(id);
    }
}
