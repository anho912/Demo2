package com.an.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.an.entity.Dictionary;
import com.an.entity.Order;
import com.an.entity.OrderInfo;
import com.an.entity.Product;
import com.an.entity.User;
import com.an.service.DictionaryService;
import com.an.service.OrderService;
import com.an.service.ProductService;
import com.an.utils.FileUtil;
import com.an.utils.Results;
import com.an.vo.DatatablesViewPage;

/**
 * 商品控制器
 * 
 * @author 疯狂的蜗牛君_
 *
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private OrderService orderService;

	/**
	 * 加载主页数据展示
	 * @return
	 */
	@RequestMapping(value="/loadingAll",method=RequestMethod.POST)
	@ResponseBody
	Results loadingAll() {
		//查询所有书目
		List<Dictionary> dictionaries=dictionaryService.findAll();
		if(dictionaries.size()>0) {
			if(dictionaries.size()%2!=0) {
				dictionaries.remove(dictionaries.get(dictionaries.size()-1));
			}
			//查询热销榜前6名 通过订单记录
			List<Product> sixProducts=new ArrayList<>();
			List<OrderInfo> orderInfos=orderService.findSixProduct();
			if(orderInfos.size()>6) {
				for(int i=0;i<6;i++) {
					Product product=productService.findById(orderInfos.get(i).getProId());
					sixProducts.add(product);
				}
			}else {
				sixProducts=productService.findSixAll();
			}
			//查询其他八件商品
			List<Product> products=productService.findTenAll();
			return Results.ok().put("dictionaries", dictionaries).put("sixProducts", sixProducts).put("products", products);
		}else {
			return Results.error("查询为空");
		}
	}
	
	/**
	 * 分页查询
	 * 
	 * @param searchProductStr
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/BookData", method = RequestMethod.GET)
	@ResponseBody
	Results ProductData(String searchBookStr, int page) {
		String searchStr = "%" + searchBookStr + "%";
		// 获取符合条件的总条数
		long total = productService.selectCount(searchStr);
		// 计算总页数
		long totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;
		// 查询当页数据
		List<Product> products = productService.findProductDataByPageAndCount(10, page, searchStr);
		DatatablesViewPage datatablesViewPage = new DatatablesViewPage(products, totalPage, total);
		return Results.ok("查询成功").put("data", datatablesViewPage);
	}

	/**
	 * 跳转到列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findBySearch", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView findBySearch(String searchStr) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("searchStr", searchStr);
		modelAndView.setViewName("liebiao");
		return modelAndView;
	}
	
	/**
	 * 查看一个商品详情
	 * @param proId
	 * @return
	 */
	@RequestMapping(value="/getInfoById")
	@ResponseBody
	ModelAndView getInfoById(String proId) {
		ModelAndView modelAndView=new ModelAndView();
		Product product=productService.findById(Integer.valueOf(proId));
		modelAndView.addObject("products",product);
		modelAndView.setViewName("xiangqing");
		return modelAndView;
	}
}
