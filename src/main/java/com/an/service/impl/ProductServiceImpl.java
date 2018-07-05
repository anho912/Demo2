package com.an.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.dao.ProductMapper;
import com.an.entity.Product;
import com.an.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public long selectCount(String searchStr) {
		// TODO Auto-generated method stub
		return productMapper.selectCount(searchStr);
	}

	@Override
	public List<Product> findProductDataByPageAndCount(int i, int page, String searchStr) {
		// TODO Auto-generated method stub
		if(page<1) {
			page=1;
		}
		return productMapper.findProductDataByPageAndCount(i, (page-1)*i, searchStr,1);
	}

	@Override
	public Product findByProName(String proName) {
		// TODO Auto-generated method stub
		return productMapper.findByProName(proName);
	}

	@Override
	public int saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productMapper.insert(product);
	}

	@Override
	public int deleteById(Integer valueOf) {
		// TODO Auto-generated method stub
		return productMapper.deleteById(valueOf);
	}

	@Override
	public Product findById(Integer valueOf) {
		// TODO Auto-generated method stub
		return productMapper.findById(valueOf);
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productMapper.updateById(product);
	}

	@Override
	public List<Product> findSixAll() {
		// TODO Auto-generated method stub
		List<Product> products=productMapper.findAll();
		List<Product> products2=new ArrayList<>();
		if(products.size()>6) {
			for(int i=0;i<6;i++) {
				products2.add(products.get(i));
			}
			return products2;
		}else {
			return null;
		}
	}

	@Override
	public List<Product> findTenAll() {
		// TODO Auto-generated method stub
		List<Product> products=productMapper.findAll();
		List<Product> products2=new ArrayList<>();
		if(products.size()>10) {
			for(int i=0;i<10;i++) {
				products2.add(products.get(i));
			}
			return products2;
		}else {
			return null;
		}
	}

	@Override
	public Product findByCartInfo(Integer proId) {
		// TODO Auto-generated method stub
		return productMapper.findById(proId);
	}
	
}
