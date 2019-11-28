package com.fh.service.category;

import com.alibaba.fastjson.JSONArray;
import com.fh.mapper.CategoryMapper;
import com.fh.model.Category;
import com.fh.util.RedisLocker;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory( category);
        boolean hasKey = RedisLocker.isAcquired(SystemConstant.CATEGORYLIST_LOCK, SystemConstant.REDIS_KEY_OUT_TIME);
        if(hasKey){
            List list = categoryMapper.queryList();
            String jsonString = JSONArray.toJSONString(list);
            RedisUtil.set(SystemConstant.CATEGORYLIST,jsonString);
            RedisLocker.release(SystemConstant.CATEGORYLIST_LOCK);
        }
    }

    @Override
    public List queryListByPid(Integer pid) {
        return categoryMapper.queryListByPid(pid);
    }

    @Override
    public List queryList() {
        return categoryMapper.queryList();
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategory(List idList) {
        categoryMapper.deleteCategory(idList);
    }
}
