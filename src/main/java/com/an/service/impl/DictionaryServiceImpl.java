package com.an.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.dao.DictionaryMapper;
import com.an.entity.Dictionary;
import com.an.entity.Product;
import com.an.service.DictionaryService;
@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@Override
	public long selectCount(String searchStr) {
		// TODO Auto-generated method stub
		return dictionaryMapper.selectCount(searchStr);
	}

	@Override
	public List<Dictionary> findDicDataByPageAndCount(int i, int page, String searchStr) {
		// TODO Auto-generated method stub
		if(page<1) {
			page=1;
		}
		return dictionaryMapper.findDataByPageAndCount(i, (page-1)*i, searchStr);
	}

	@Override
	public Dictionary findByDicName(String ddName) {
		// TODO Auto-generated method stub
		return dictionaryMapper.findByDicName(ddName);
	}

	@Override
	public int saveDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return dictionaryMapper.insert(dictionary);
	}

	@Override
	public int deleteById(Integer valueOf) {
		// TODO Auto-generated method stub
		return dictionaryMapper.deleteById(valueOf);
	}

	@Override
	public Dictionary findById(Integer valueOf) {
		// TODO Auto-generated method stub
		return dictionaryMapper.findById(valueOf);
	}

	@Override
	public int updateDic(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return dictionaryMapper.updateById(dictionary);
	}

	@Override
	public List<Dictionary> findAll() {
		// TODO Auto-generated method stub
		return dictionaryMapper.findAll();
	}
}
